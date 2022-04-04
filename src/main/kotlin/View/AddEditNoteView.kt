import Style
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.stage.Stage
import speech_to_text.SpeechToText

import javafx.scene.web.WebView;
import javafx.stage.FileChooser
import java.io.File

class Controller

class AddEditNoteView(val stage: Stage, val model: Model, val note: Note, val isNew: Boolean = true) : VBox(), View {
    var images = mutableListOf<ImageView>()
    var mode = false
    init {
        updateView()
    }


    override fun updateView() {
        children.clear()
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
        val imageImport = Button("Import Image")
        imageImport.style = """
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
        imageImport.minHeight = 25.0
        imageImport.minWidth = 55.0


        val titleConstantView = Text("Title: ")
        val titleView = HBox(imageImport, titleConstantView, title)
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

        val modeButton = ToggleButton("Mode")
        modeButton.style = """
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

        val tagView = HBox(modeButton, tagConstantView, tag)
        tag.minWidth = 1000.0
        tagView.spacing = 100.0
        titleView.spacing = 100.0

        title.alignment = Pos.BASELINE_LEFT
        tag.alignment = Pos.BASELINE_LEFT

        tagView.alignment = Pos.BASELINE_RIGHT
        titleView.alignment = Pos.BASELINE_RIGHT

        val contents = HBox()
        contents.minWidth = 1300.0

        val content = TextArea()
        content.isWrapText = true
        content.prefRowCount = 35
        content.prefColumnCount = 20

        content.text = note.content
        content.maxWidth = 600.0
        content.minWidth = 600.0

        content.style = """
        -fx-control-inner-background: #F5F5DC;
        """.trimIndent()
        content.background = Background(BackgroundFill(Color.BEIGE, CornerRadii(10.0), Insets(10.0)))

        val imageSection = ScrollPane()
        imageSection.maxWidth = 850.0
        imageSection.minWidth = 850.0
        imageSection.isFitToHeight = false
        imageSection.isFitToWidth = true

        imageSection.hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER

        contents.children.addAll(content, imageSection)


        background = Background(BackgroundFill(Color.hsb(37.1, 0.5, 0.7, 0.6), CornerRadii.EMPTY, Insets.EMPTY))

        val save = Button("Save")
        save.setOnAction { event ->
            val titleText = title.text
            note.title = titleText
            note.tags = mutableListOf(tag.text)
            note.content = content.text
            model.save(note)
            stage.close()
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

        val compile = Button("Compile")
        compile.style = """
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


        imageImport.setOnMouseClicked {
            val fileStage = Stage()
            val fileChooser = FileChooser()
            fileChooser.title = "Open an Image"
            fileChooser.initialDirectory = File("./")
            var file = fileChooser.showOpenDialog(fileStage)

            if (file != null) {
                val image = Image(file.toURI().toString())
                val imageView = ImageView(image)
                images.add(imageView)
                //setting the fit height and width of the image view
                imageView.setFitHeight(600.0);
                imageView.setFitWidth(800.0);

                imageView.minHeight(600.0);
                imageView.minWidth(800.0);

                //Setting the preserve ratio of the image view
                imageView.setPreserveRatio(true);
                val imageContent = VBox()
                imageContent.children.addAll(images)
                imageSection.content = imageContent

//                contents.right = Group(imageView)

//                val imageStage = Stage()
//                val root = Group(imageView)
//                imageStage.scene = Scene(root)
//                imageStage.show()
            }

        }

        save.minWidth = 250.0
        cancel.minWidth = 250.0
        speechToTextButton.minWidth = 250.0
        compile.minWidth = 250.0
        save.minHeight = 90.0
        cancel.minHeight = 90.0
        compile.minHeight = 90.0
        speechToTextButton.minHeight = 90.0
        speechToTextButton.setOnAction {
            val currentPosition = content.caretPosition
            content.insertText(currentPosition, SpeechToText.listen())
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

        fun fetchWebView() {
            val webView = WebView()
            val webEngine = webView.engine
            webEngine.loadContent("<script src=\"https://cdn.jsdelivr.net/gh/google/code-prettify@master/loader/run_prettify.js\"></script>" +
                    "<pre class=\"prettyprint\">" + content.text +
                    "</pre>", "text/html")


            imageSection.content = webView

            webView.style = Style.webViewStyle
            webView.maxWidth = 850.0
            webView.minWidth = 850.0
        }

        compile.setOnMouseClicked {
            fetchWebView()
        }

        modeButton.setOnAction {
            mode = !mode
            if (mode) {
                fetchWebView()
            } else {
                val imageContent = VBox()
                imageContent.children.addAll(images)
                imageSection.content = imageContent
            }
        }

        var returnMenu = HBox()
        returnMenu.children.addAll(save, cancel, speechToTextButton, compile)
        returnMenu.maxHeight = 150.0;

        returnMenu.alignment = Pos.CENTER
        returnMenu.spacing = 100.0
        AnchorPane.setLeftAnchor(tag, 10.0)
        AnchorPane.setRightAnchor(tag, 10.0)
        AnchorPane.setLeftAnchor(title, 10.0)
        AnchorPane.setRightAnchor(title, 10.0)
        children.addAll(titleView, tagView, contents, returnMenu)
    }


}
