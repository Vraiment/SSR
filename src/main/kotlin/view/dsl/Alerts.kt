package vraiment.sage.ssr.view.dsl

import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType

/**
 * Shows a (blocking) error alert using the given builder to configure it.
 *
 * @param block The block that configures the alert.
 */
@JavaFx
fun showErrorAlert(block: AlertBuilder.() -> Unit) {
    AlertBuilder()
        .apply(block).build(AlertType.ERROR)
        .showAndWait()
}

/**
 * Class to configure an [Alert] instance.
 */
class AlertBuilder {
    var title: String? = null
    var headerText: String? = null

    /**
     * Builds a new alert with the given and using the values in the builder to configure it.
     *
     * @param type The type of the alert.
     */
    fun build(type: AlertType): Alert = Alert(type)
        .also { alert ->
            alert.title = title
            alert.headerText = headerText
        }
}
