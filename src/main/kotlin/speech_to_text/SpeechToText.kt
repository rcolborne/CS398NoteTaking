package speech_to_text

import com.google.api.gax.rpc.ResponseObserver
import com.google.api.gax.rpc.StreamController
import com.google.cloud.speech.v1.*
import com.google.protobuf.ByteString
import javax.sound.sampled.*

object SpeechToText {
    /** Performs microphone streaming speech recognition with a duration of 1 minute.  */
    @Throws(Exception::class)
    fun listen(): String {
        var responseObserver: ResponseObserver<StreamingRecognizeResponse?>? = null
        var finaltext = ""

        try {
            SpeechClient.create().use { client ->
                responseObserver = object : ResponseObserver<StreamingRecognizeResponse?> {
                    var responses =
                        ArrayList<StreamingRecognizeResponse>()

                    override fun onStart(controller: StreamController) {}
                    override fun onResponse(response: StreamingRecognizeResponse?) {
                        if (response != null) {
                            responses.add(response)
                        }
                    }

                    override fun onComplete() {
                        for (response in responses) {
                            val result =
                                response.resultsList[0]
                            val alternative =
                                result.alternativesList[0]
                            finaltext += alternative.transcript
                            System.out.printf("Transcript : %s\n", alternative.transcript)
                        }
                    }

                    override fun onError(t: Throwable) {
                        println(t)
                    }
                }
                val clientStream =
                    client.streamingRecognizeCallable().splitCall(responseObserver)
                val recognitionConfig =
                    RecognitionConfig.newBuilder()
                        .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                        .setLanguageCode("en-US")
                        .setSampleRateHertz(16000)
                        .build()
                val streamingRecognitionConfig =
                    StreamingRecognitionConfig.newBuilder().setConfig(recognitionConfig)
                        .build()
                var request =
                    StreamingRecognizeRequest.newBuilder()
                        .setStreamingConfig(streamingRecognitionConfig)
                        .build() // The first request in a streaming call has to be a config
                clientStream.send(request)
                // SampleRate:16000Hz, SampleSizeInBits: 16, Number of channels: 1, Signed: true,
                // bigEndian: false
                val audioFormat = AudioFormat(16000F, 16, 1, true, false)
                val targetInfo = DataLine.Info(
                    TargetDataLine::class.java,
                    audioFormat
                ) // Set the system information to read from the microphone audio stream
                if (!AudioSystem.isLineSupported(targetInfo)) {
                    println("Microphone not supported")
                    System.exit(0)
                }
                // Target data line captures the audio stream the microphone produces.
                val targetDataLine = AudioSystem.getLine(targetInfo) as TargetDataLine
                targetDataLine.open(audioFormat)
                targetDataLine.start()
                println("Start speaking")
                val startTime = System.currentTimeMillis()
                // Audio Input Stream
                val audio = AudioInputStream(targetDataLine)
                while (true) {
                    val estimatedTime = System.currentTimeMillis() - startTime
                    val data = ByteArray(6400)
                    audio.read(data)
                    if (estimatedTime > 10000) { // 60 seconds
                        println("Stop speaking.")
                        targetDataLine.stop()
                        targetDataLine.close()
                        break
                    }
                    request = StreamingRecognizeRequest.newBuilder()
                        .setAudioContent(ByteString.copyFrom(data))
                        .build()
                    clientStream.send(request)
                }
            }
        } catch (e: Exception) {
            println(e)
        }
        responseObserver!!.onComplete()
        return finaltext

    }
}