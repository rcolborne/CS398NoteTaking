import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.text.Text
import javafx.stage.Stage
import speech_to_text.Sample


class Controller

class AddEditNoteView(val stage: Stage, val model: Model, note: Note, val isNew: Boolean = true) : VBox(), View {
    init {
        var title = TextField(note.title)
        title.style = """
            .text-field {
               -fx-font-family: "Quicksand";
               -fx-font-size: 19;
               -fx-padding: 1,1,1,1;
               -fx-border-color: #DAA520;
               -fx-border-width: 2;
               -fx-border-radius: 1;
               -fx-border: gone;
               -fx-background-color: #F5F5DC;
               -fx-text-fill: #0;
            }""".trimIndent()
        title.minWidth = 1000.0
        val titleConstantView = Text("Title: ")
        val titleView = HBox(titleConstantView, title)
        var tag = TextField()
        if (note.tags != null && note.tags!!.count() > 0) {
            tag.text = note.tags!![0]
        }

        tag.style = """.text-field {
           -fx-font-family: "Quicksand";
           -fx-font-size: 19;
           -fx-padding: 1,1,1,1;
           -fx-border-color: #DAA520;
           -fx-border-width: 2;
           -fx-border-radius: 1;
           -fx-border: gone;
           -fx-background-color: #F5F5DC;
           -fx-text-fill: #0;
        }""".trimIndent()

        val tagConstantView = Text("Tags: ")
        val tagView = HBox(tagConstantView, tag)
        tag.minWidth = 1000.0
        tagView.spacing = 100.0
        titleView.spacing = 100.0

        title.alignment = Pos.BASELINE_LEFT
        tag.alignment = Pos.BASELINE_LEFT

        tagView.alignment = Pos.BASELINE_RIGHT
        titleView.alignment = Pos.BASELINE_RIGHT

        val content = TextArea()
        content.isWrapText = true
        content.prefRowCount = 35
        content.prefColumnCount = 30

        content.style = """
            -fx-control-inner-background: #F5F5DC;
        """.trimIndent()
        content.background = Background(BackgroundFill(Color.BEIGE, CornerRadii(10.0), Insets(10.0)))

        background = Background(BackgroundFill(Color.hsb(37.1, 0.5, 0.7, 0.6), CornerRadii.EMPTY, Insets.EMPTY))

        val save = Button("Save")
        save.setOnAction { event ->
            val titleText = title.text
            note.title = titleText
            note.tags = mutableListOf(tag.text)
            note.content = content.text
            model.save(note)
        }
        save.style = """
            .text-area {
               -fx-font-family: "Quicksand";
               -fx-font-size: 29;
               -fx-padding: 1,1,1,1;
               -fx-border-width: 2;
               -fx-border-radius: 3;
               -fx-border: gone;
               -fx-background-color: #F5F5DC;
               -fx-text-fill: #DAA520;
            }
        """.trimIndent()
        val cancel = Button("Cancel")
        cancel.style = """
            .text-area .content {
               -fx-font-family: "Quicksand";
               -fx-font-size: 29;
               -fx-padding: 1,1,1,1;
               -fx-border-width: 2;
               -fx-border-radius: 3;
               -fx-border: gone;
               -fx-background-color: #F5F5DC;
               -fx-text-fill: #DAA520;
            }
        """.trimIndent()
        val speechToTextButton = Button("Speech To Text")
        speechToTextButton.style = """        
              * {
           -fx-font-family: "Quicksand";
           -fx-font-size: 19;
           -fx-padding: 1,1,1,1;
           -fx-border-width: 2;
           -fx-border-radius: 1;
           -fx-border: gone;
           -fx-background-color: #F5F5DC;
           -fx-text-fill: #DAA520;
        }""".trimIndent()

        cancel.setOnMouseClicked {
            stage.close()
        }

        save.minWidth = 300.0
        cancel.minWidth = 300.0
        speechToTextButton.minWidth = 300.0
        save.minHeight = 90.0
        cancel.minHeight = 90.0
        speechToTextButton.minHeight = 90.0
        speechToTextButton.setOnAction {
            Sample.call()
        }

        titleConstantView.style = """
            * {
           -fx-font-family: "Quicksand";
           -fx-font-size: 19;
           -fx-padding: 1,1,1,1;
           -fx-border-width: 2;
           -fx-border-radius: 1;
           -fx-border: gone;
           -fx-background-color: #F5F5DC;
           -fx-text-fill: #DAA520;
        """.trimIndent()

        tagConstantView.style = """
            * {
           -fx-font-family: "Quicksand";
           -fx-font-size: 19;
           -fx-padding: 1,1,1,1;
           -fx-border-width: 2;
           -fx-border-radius: 1;
           -fx-border: gone;
           -fx-background-color: #F5F5DC;
           -fx-text-fill: #DAA520;
        """.trimIndent()

        var returnMenu = HBox()
        returnMenu.children.addAll(save, cancel, speechToTextButton)
        returnMenu.maxHeight = 150.0;

        returnMenu.alignment = Pos.CENTER
        returnMenu.spacing = 100.0
//        returnMenu.padding = Insets(100.0)
        AnchorPane.setLeftAnchor(tag, 10.0)
        AnchorPane.setRightAnchor(tag, 10.0)
        AnchorPane.setLeftAnchor(title, 10.0)
        AnchorPane.setRightAnchor(title, 10.0)
        children.addAll(titleView, tagView, content, returnMenu)
    }

    override fun updateView() {
        // fetch data from model
    }
}
