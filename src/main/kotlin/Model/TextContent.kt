import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
class TextContent {
    var fontSize : Int = 0
    var font : String = ""
    var text : String = ""
    constructor() {}
    constructor(fontSize: Int, font: String, text: String) {
        this.fontSize = fontSize
        this.font = font
        this.text = text
    }
}
