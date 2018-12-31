package vraiment.sage.ssr

import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

object Runner {
    @JvmStatic
    fun main(args: Array<String>) {
        launch(Application::class.java, *args)
    }
}

class Application : javafx.application.Application() {
    override fun start(primaryStage: Stage) {
        val root: Parent = FXMLLoader.load(javaClass.getResource("/MainWindow.fxml"))

        primaryStage.title = "Sage Save Editor"
        primaryStage.scene = Scene(root)

        primaryStage.show()
    }
}
