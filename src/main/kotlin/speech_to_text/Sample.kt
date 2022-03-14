package speech_to_text

object Sample {

    var exit = false

    @Throws(Exception::class)
    fun call() {
        println("Opening speech recognition...")
        val analyser = Analyzer()
        val speaker = Speaker()
        val listener = Listener("en-US", 0.0005, { text ->
            println("Recognised speech: $text")
            println(analyser.analyse(text))
            if (text.equals("exit")) {
                exit = true
            }
        })
        println("Opened speech recognition.")

        println("Say exit to quit program.")
        while(!exit) {
            Thread.sleep(100)
        }

        println("Closing speech recognition...")
        listener.close()
        println("Closed speech recognition.")

        println("Exit.")
    }

}
