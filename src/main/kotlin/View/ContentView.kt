import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane

class ContentView(val isAddNote : Boolean = false): StackPane() {
    init {
        if (isAddNote) {
            val image = Image("file:///Users/brian/IdeaProjects/cs398project/notetakingapp/noteTakingApp/src/main/kotlin/View/1464710523.png")
            children.add(ImageView(image))
        }
    }
}