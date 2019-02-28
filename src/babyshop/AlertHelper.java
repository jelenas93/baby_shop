package babyshop;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertHelper {

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        addDialogIconTo(alert);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void addDialogIconTo(Alert alert) {
        final Image APPLICATION_ICON = new Image("logo.jpg");
        Stage dialogStage = (Stage) alert.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(APPLICATION_ICON);

    }
}
