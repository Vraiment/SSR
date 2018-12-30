package vraiment.sage.savereader

import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.net.URL

fun main(args: Array<String>) {
    launch(Main::class.java, *args)
}

val MAIN_WINDOW_URL: URL = TODO()
const val TITLE = "Sage Save Editor"

class Main : Application() {
    override fun start(primaryStage: Stage) {
        val root: Parent = FXMLLoader.load(MAIN_WINDOW_URL)

        primaryStage.title = TITLE
        primaryStage.scene = Scene(root)

        primaryStage.show()
    }
}
