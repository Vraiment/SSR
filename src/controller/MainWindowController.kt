package vraiment.sage.savereader.controller

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage

class MainWindowController {
    @FXML
    private lateinit var root: VBox

    @FXML
    private lateinit var placeholder: Label

    private val stage by lazy {
        root.scene.window as Stage
    }

    fun displayOpenFileWindow() {
        TODO()
    }

    fun quit() {
        stage.close()
    }
}
