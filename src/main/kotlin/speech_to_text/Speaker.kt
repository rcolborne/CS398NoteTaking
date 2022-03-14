package speech_to_text

import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.SourceDataLine

class Speaker {

    private fun play(filename: String) {
        val strFilename = filename
        val soundFile = File(strFilename)
        val audioStream = AudioSystem.getAudioInputStream(soundFile)
        val audioFormat = audioStream!!.format
        val info = DataLine.Info(SourceDataLine::class.java, audioFormat)
        val sourceLine = AudioSystem.getLine(info) as SourceDataLine
        sourceLine.open(audioFormat)
        sourceLine.start()

        var count = 0
        val buffer = ByteArray(4096)
        while (count != -1) {
            count = audioStream!!.read(buffer, 0, buffer.size)
            if (count >= 0) {
                sourceLine!!.write(buffer, 0, count)
            }
        }

        sourceLine.drain()
        sourceLine.close()
    }
}
