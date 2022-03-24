import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.ScrollPane
import javafx.scene.layout.*
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import javafx.stage.Stage

// Scene Graph (javafx)
// Toolbar

// Init Page (Add, open ,setting , previously created notes)
//          noteView (GRID FIXED SIZE)

class InitView(val model: Model) : BorderPane(), View {
    val searchFilter = TextField()
    val noteContainer = ScrollPane()
    val notes = FlowPane()
    val addNote = NoteCellView("", mutableListOf<String>(), "PLUS IMAGE");

    init {
        searchFilter.promptText = "  Search"
        searchFilter.style = """
            .text-field {
               -fx-font-family: "Quicksand";
               -fx-font-size: 12;
               -fx-padding: 1,1,1,1;
               -fx-border-color: #DAA520;
               -fx-border-width: 2;
               -fx-border-radius: 1;
               -fx-border: gone;
               -fx-background-color: #FFFACD;
               -fx-text-fill: #DAA520;
            }
        """.trimIndent()
        searchFilter.prefHeight = 10.0

        searchFilter.onAction = EventHandler {
            notes.children.clear()
            notes.children.add(addNote)
            val query = searchFilter.text
            val results = model.search(query)
            for (result in results) {
                val extra = NoteCellView(result.title, result.tags!!, "");
                notes.children.add(extra)
            }
            print(results.toString())
        }

        noteContainer.content = notes
        noteContainer.isFitToWidth = true
        noteContainer.background = Background(BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY))

        notes.children.add(addNote)
        top = searchFilter
        center = noteContainer

        addNote.setOnMouseClicked {
            val secondaryStage = Stage()
            val note = Note()
            secondaryStage.scene = Scene(AddEditNoteView(secondaryStage, model, note))
            secondaryStage.minWidth = 800.0
            secondaryStage.minHeight = 600.0
            secondaryStage.isResizable = false
            secondaryStage.show()
        }
    }

    override fun updateView() {
        notes.children.clear()
        notes.children.add(addNote)
        for (note in model.notes) {
            val extraNote = model.fetch(note.id)!!
            val extra = NoteCellView(note.title, note.tags!!, note.content);
            extra.setOnMouseClicked {
                val secondaryStage = Stage()
                secondaryStage.scene = Scene(AddEditNoteView(secondaryStage, model, extraNote, false))
                secondaryStage.minWidth = 800.0
                secondaryStage.minHeight = 600.0
                secondaryStage.show()
            }
            notes.children.add(extra)
        }
    }
}

// add/edit Page
//  toolbar + blank (content to modifify)