import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.util.Date

@Serializable
class Note {
    companion object idGenerator {
        var ID = 0
    }
    val id: Int
    var title: String = ""
    var tags: MutableList<String>? = mutableListOf()
    var content: String = ""
    constructor() { id = ID; ID += 1}
    constructor(title: String, tags: MutableList<String>?, content: String) {
        this.title = title
        this.tags = tags
        this.content = content
        id = ID; ID += 1
    }
}
