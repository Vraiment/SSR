package vraiment.sage.ssr.controller

import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.TreeView
import javafx.stage.FileChooser
import javafx.stage.Stage
import vraiment.sage.ssr.io.readSaveFile
import vraiment.sage.ssr.view.dsl.showErrorAlert
import vraiment.sage.ssr.view.getView
import java.io.File

class MainWindowController {
    @FXML
    private lateinit var root: Node

    @FXML
    private lateinit var treeView: TreeView<String>

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
        FILE_CHOOSER.showOpenDialog(stage)?.also {
            loadFile(it)
            FILE_CHOOSER.initialDirectory = it.parentFile
        }
    }

    fun quit() {
        stage.close()
    }

    private fun loadFile(file: File) {
        try {
            val saveFile = readSaveFile(file)
            treeView.root = saveFile.getView(file.name).also { it.isExpanded = true }
        } catch (e: Exception) {
            showErrorAlert {
                title = "Error reading file"
                headerText = "Could not read file $file due \"${e.message}\""
            }
        }
    }
}
