import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.RadialGradient
import javafx.scene.paint.Stop
import javafx.stage.Stage

class NoteTakingApp : Application() {

    override fun start(primaryStage: Stage) {

        var model = Model()
        var root = InitView(model)
        var scene = Scene(root)
        primaryStage.scene = scene
        scene.fill = RadialGradient(
            0.0, 0.0, 0.0, 0.0, 1.0, true,  //sizing
            CycleMethod.NO_CYCLE,  //cycling
            Stop(0.0, Color.web("#81c483")),  //colors
            Stop(1.0, Color.web("#fcc200"))
        )
        scene.userAgentStylesheet = "scene.css"

        primaryStage.minWidth = 800.0
        primaryStage.minHeight = 600.0
        primaryStage.show()

        model.views.add(0, root)
        model.loadNotes("./")
        model.updateViews()
    }
}
