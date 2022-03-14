import javafx.scene.layout.*
import javafx.geometry.Insets
import javafx.geometry.NodeOrientation
import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Line
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment

class TagView(var tags: MutableList<String>) : ScrollPane() {
    val root = Text()
    init {
        var first = true
        for (tag in tags) {
            if (first) {
                root.text += tag
                first = false
            } else {
                root.text += ", " + tag
            }
        }
        root.wrappingWidth = 100.0
        root.font = Font.font(9.0)
        root.textAlignment = TextAlignment.CENTER
        content = root
    }
}

class TitleView(val title: String) : ScrollPane() {
    val root = Text(title)
    init {
        root.wrappingWidth = 100.0
        root.font = Font.font(9.0)
        root.textAlignment = TextAlignment.CENTER
        content = root
    }
}


class NoteCellView(title: String, tags: MutableList<String>, content: String) : StackPane() {
    val root = VBox()

    init {
        minWidth = 130.0
        minHeight = 200.0
        maxWidth = 130.0
        maxHeight = 200.0

        val titleView = TitleView(title)
        var contentView = StackPane()
        val tagView = TagView(tags)

        if (content == "PLUS IMAGE") {
            var image = ImageView(Image("file:///Users/brian/IdeaProjects/cs398project/notetakingapp/noteTakingApp/src/main/kotlin/View/1464710523.png"))
            image.fitHeight = 200.0
            image.fitWidth = 130.0

            root.children.add(image)
            children.add(root)

        } else {
            root.children.addAll(titleView, contentView, tagView)
            root.padding = Insets(10.0, 15.0, 0.0, 15.0)

            children.add(root)
        }
        background = Background(BackgroundFill(Color.BEIGE, CornerRadii(10.0), Insets(10.0)))

    }
}