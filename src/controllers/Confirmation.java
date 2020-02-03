package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

public class Confirmation {

    private final String STOP_1C_SERVER_AGENT = "powershell.exe -Command \"Start-Process powershell " +
            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Service -Name \"1C:Enterprise 8.3 Server Agent\"`\\\"\\\" -Verb RunAs\"";

    private final String STOP_MSSQL$SQLEXPRESS = "powershell.exe -Command \"Start-Process powershell " +
            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Service -Name \"MSSQL$SQLEXPRESS\"`\\\"\\\" -Verb RunAs\"";

    private final String STOP_1CV8 = "powershell.exe -Command \"Start-Process powershell " +
            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Process -Name \"1cv8\"`\\\"\\\" -Verb RunAs\"";

    private final String START_MSSQL$SQLEXPRESS = "powershell.exe -Command \"Start-Process powershell " +
            "\\\"-ExecutionPolicy -NoProfile -Command `Start-Service -Name \"MSSQL$SQLEXPRESS\"`\\\"\\\" -Verb RunAs\"";

    private final String START_1C_SERVER_AGENT = "powershell.exe -Command \"Start-Process powershell " +
            "\\\"-ExecutionPolicy -NoProfile -Command `Start-Service -Name \"1C:Enterprise 8.3 Server Agent\"`\\\"\\\" -Verb RunAs\"";

    public void restart(ActionEvent actionEvent) throws IOException, InterruptedException {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();

//        MyThread myThread = new MyThread();
//        myThread.start();
//        myThread.join();
//        startProgressIndicator();
        Process processStop1CServer = Runtime.getRuntime().exec(STOP_1C_SERVER_AGENT);
        Thread.sleep(5000);
        Process processStopMSSQL = Runtime.getRuntime().exec(STOP_MSSQL$SQLEXPRESS);
        Thread.sleep(15000);
        Process processStop1CV8 = Runtime.getRuntime().exec(STOP_1CV8);
        Thread.sleep(5000);
        Process processStartMSSQL = Runtime.getRuntime().exec(START_MSSQL$SQLEXPRESS);
        Thread.sleep(10000);
        Process processStart1CServer = Runtime.getRuntime().exec(START_1C_SERVER_AGENT);
        Thread.sleep(5000);
        successAlert();
        Runtime.getRuntime().exit(0);
    }

    private void successAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Статус");
        alert.setHeaderText(null);
        alert.setContentText("Выполнение успешно завершено!");
        alert.showAndWait();
    }

    private void startProgressIndicator() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/indicator.fxml"));
        Scene scene = new Scene(root, 500, 150);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void exit(ActionEvent actionEvent) {
        Runtime.getRuntime().exit(0);
    }
}