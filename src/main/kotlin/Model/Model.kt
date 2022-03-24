import Note
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.io.File
import java.lang.Integer.max

class Model {
    var notes : MutableList<Note> = mutableListOf()

    var views : MutableList<View> = mutableListOf()

    fun loadNotes(path : String) {
        for(file in File(path).listFiles()) {
            if(file.name.endsWith(".json")) {
                val note : Note = Json.decodeFromString(file.readText(Charsets.UTF_8))
                if (!notes.map{ it.id }.contains( note.id )) {
                    notes.add(note)
                }
            }
        }

        Note.ID = if (Note.ID == 0) 0 else notes.map{ it.id }.maxOf{ it } + 1
        updateViews()
    }

    fun save(note : Note) {
        File("${note.title}.json").writeText(Json.encodeToString(note))
        loadNotes("./")
//        updateViews()
    }

    fun search(query : String) : MutableList<Note> {
        if (query == "") {
            return notes
        }

        var results = mutableListOf<Note>()
        for(note in notes) {
            var tagged = false
            for(tag in note.tags!!) {
                if(tag in query) {
                    results.add(note)
                    tagged = true
                }
            }
            if (!tagged) {
                if (note.title.contains(query)) {
                    results.add(note)
                }
            }
        }
        return results
    }

    fun add(note: Note) {
        notes.add(note)
        updateViews()
    }

    fun fetch(id: Int) : Note? {
        for (note in notes) {
            if (note.id == id) {
                return note
            }
        }
        return null
    }

    fun edit(editedNote: Note, title: String,
             tags: MutableList<String>? = mutableListOf(),
             content: MutableList<TextContent>? = mutableListOf()) {
        for (i in notes.indices) {
            println(notes[i].id.toString() + " " + editedNote.id.toString())
            if (notes[i].id == editedNote.id || notes[i].title == editedNote.title) {
                notes[i].title = editedNote.title
                notes[i].tags = editedNote.tags
                notes[i].content = editedNote.content
            }
        }
        updateViews()
    }


    fun updateViews() { for (view in views) {view.updateView()} }

}
