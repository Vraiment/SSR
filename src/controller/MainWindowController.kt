package vraiment.sage.ssr.controller

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File

class MainWindowController {
    @FXML
    private lateinit var root: VBox

    @FXML
    private lateinit var placeholder: Label

    private val stage by lazy {
        root.scene.window as Stage
    }

    private companion object {
        val FILE_CHOOSER = FileChooser().also {
            it.title = "Open save file..."
            it.extensionFilters.add(FileChooser.ExtensionFilter("Sage save file", "*sav"))
        }
    }

    fun displayOpenFileWindow() {
        FILE_CHOOSER.showOpenDialog(stage)?.also { loadFile(it) }
    }

    fun quit() {
        stage.close()
    }

    private fun loadFile(file: File) {
        TODO()
    }
}
