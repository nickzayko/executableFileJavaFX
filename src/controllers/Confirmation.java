package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Confirmation {

    private final String SERVER_AGENT_1C = "1C:Enterprise 8.3 Server Agent";
    private final String SERVER_AGENT_1C_X86_64 = "1C:Enterprise 8.3 Server Agent (x86-64)";
    private final String MSSQLSERVER = "MSSQLSERVER";
    private final String MSSQL$SQLEXPRESS = "MSSQL$SQLEXPRESS";
    private final String PROCESS_1cv8 = "1cv8";
    private final String PROCESS_1cv8c = "1cv8c";

    private final String STOP_SERVICE_BEGIN = "powershell.exe -Command \"Start-Process powershell " +
            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Service -Name \"";

    private final String STOP_PROCESS_BEGIN = "powershell.exe -Command \"Start-Process powershell " +
            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Process -Name \"";

    private final String START_SERVICE_BEGIN = "powershell.exe -Command \"Start-Process powershell " +
            "\\\"-ExecutionPolicy -NoProfile -Command `Start-Service -Name \"";

    private final String COMMAND_END = "`\\\"\\\" -Verb RunAs\"";

    private final String GET_PROCESS = "powershell.exe Get-Process -Name ";
    private final String GET_SERVICE = "powershell.exe Get-SERVICE -Name ";

    private ArrayList info = new ArrayList();

    private boolean flag = false;

//    private final String STOP_1C_SERVER_AGENT = "powershell.exe -Command \"Start-Process powershell " +
//            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Service -Name \"1C:Enterprise 8.3 Server Agent\"`\\\"\\\" -Verb RunAs\"";
//
//    private final String STOP_1C_SERVER_AGENT_X86_64 = "powershell.exe -Command \"Start-Process powershell " +
//            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Service -Name \"1C:Enterprise 8.3 Server Agent (x86-64)\"`\\\"\\\" -Verb RunAs\"";
//
//    private final String STOP_MSSQLSERVER = "powershell.exe -Command \"Start-Process powershell " +
//            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Service -Name \"MSSQLSERVER\"`\\\"\\\" -Verb RunAs\"";
//
//    private final String STOP_MSSQL$SQLEXPRESS = "powershell.exe -Command \"Start-Process powershell " +
//            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Service -Name \"MSSQL$SQLEXPRESS\"`\\\"\\\" -Verb RunAs\"";
//
//    private final String STOP_1CV8 = "powershell.exe -Command \"Start-Process powershell " +
//            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Process -Name \"1cv8\"`\\\"\\\" -Verb RunAs\"";
//
//    private final String STOP_1CV8C = "powershell.exe -Command \"Start-Process powershell " +
//            "\\\"-ExecutionPolicy -NoProfile -Command `Stop-Process -Name \"1cv8c\"`\\\"\\\" -Verb RunAs\"";
//
//    private final String START_MSSQLSERVER = "powershell.exe -Command \"Start-Process powershell " +
//            "\\\"-ExecutionPolicy -NoProfile -Command `Start-Service -Name \"MSSQLSERVER\"`\\\"\\\" -Verb RunAs\"";
//
//    private final String START_MSSQL$SQLEXPRESS = "powershell.exe -Command \"Start-Process powershell " +
//            "\\\"-ExecutionPolicy -NoProfile -Command `Start-Service -Name \"MSSQL$SQLEXPRESS\"`\\\"\\\" -Verb RunAs\"";
//
//    private final String START_1C_SERVER_AGENT = "powershell.exe -Command \"Start-Process powershell " +
//            "\\\"-ExecutionPolicy -NoProfile -Command `Start-Service -Name \"1C:Enterprise 8.3 Server Agent\"`\\\"\\\" -Verb RunAs\"";
//
//    private final String START_1C_SERVER_AGENT_X86_64 = "powershell.exe -Command \"Start-Process powershell " +
//            "\\\"-ExecutionPolicy -NoProfile -Command `Start-Service -Name \"1C:Enterprise 8.3 Server Agent (x86-64)\"`\\\"\\\" -Verb RunAs\"";

    public void restart(ActionEvent actionEvent) throws IOException, InterruptedException {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();

//        MyThread myThread = new MyThread();
//        myThread.start();
//        myThread.join();
//        startProgressIndicator();

        stopService(SERVER_AGENT_1C, SERVER_AGENT_1C_X86_64);
        Thread.sleep(5000);
        stopService(MSSQLSERVER, MSSQL$SQLEXPRESS);
        Thread.sleep(15000);
        stopProcess(PROCESS_1cv8, PROCESS_1cv8c);
        Thread.sleep(5000);
        startService(info);


//        Process processStop1CServer = Runtime.getRuntime().exec(STOP_1C_SERVER_AGENT);
//        Process processStop1CServer_X86_64 = Runtime.getRuntime().exec(STOP_1C_SERVER_AGENT_X86_64);
//        Thread.sleep(5000);
//        Process processStopMSSQLServer = Runtime.getRuntime().exec(STOP_MSSQLSERVER);
//        Process processStopMSSQLExpress = Runtime.getRuntime().exec(STOP_MSSQL$SQLEXPRESS);
//        Thread.sleep(15000);
//        Process processStop1CV8 = Runtime.getRuntime().exec(STOP_1CV8);
//        Process processStop1CV8C = Runtime.getRuntime().exec(STOP_1CV8C);
//        Thread.sleep(5000);
//        Process processStartMSSQL = Runtime.getRuntime().exec(START_MSSQL$SQLEXPRESS);
//        Thread.sleep(10000);
//        Process processStart1CServer = Runtime.getRuntime().exec(START_1C_SERVER_AGENT);

//        String cmd5 = "powershell.exe Get-Process -Name notepad";
//        Process process = Runtime.getRuntime().exec(cmd5);
//        processDetail(process, 1);


        Thread.sleep(5000);
        successAlert();
        Runtime.getRuntime().exit(0);
    }

    private void startService(ArrayList info) throws InterruptedException {
        try {
            Process startMSSQL = Runtime.getRuntime().exec(START_SERVICE_BEGIN + info.get(1) + COMMAND_END);
            Thread.sleep(10000);
            Process start1CServer = Runtime.getRuntime().exec(START_SERVICE_BEGIN + info.get(0) + COMMAND_END);
        } catch (IOException e) {
            e.printStackTrace();
            badAlert();
        }

    }

    private void badAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Внимание!");
        alert.setHeaderText(null);
        alert.setContentText("Программа выполнена с ошибкой. Один или несколько сервисов не удалось стартовать, так как изначально они не были запущены!");
        alert.showAndWait();
    }

    private void stopProcess(String processFirstName, String processSecondName) throws IOException {
        if (isProcessExist(processFirstName)) {
            flag = false;
            Process stopService = Runtime.getRuntime().exec(STOP_PROCESS_BEGIN + processFirstName + COMMAND_END);
        }  else {
            Process stopService = Runtime.getRuntime().exec(STOP_PROCESS_BEGIN + processSecondName + COMMAND_END);
        }
    }

    private boolean isProcessExist(String processName) throws IOException {
        Process process = Runtime.getRuntime().exec(GET_PROCESS + processName);
        process.getOutputStream().close();
        String line;
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            if (line.contains(processName)) {
                flag = true;
            }
        }
        stdout.close();
        return flag;
    }

    private void stopService(String serviceFirstName, String serviceSecondName) throws IOException {
        if (isServiceExist(serviceFirstName)) {
            flag = false;
            info.add(serviceFirstName);
            Process stopService = Runtime.getRuntime().exec(STOP_SERVICE_BEGIN + serviceFirstName + COMMAND_END);
        }  else {
            info.add(serviceSecondName);
            Process stopService = Runtime.getRuntime().exec(STOP_SERVICE_BEGIN + serviceSecondName + COMMAND_END);
        }
    }

    private boolean isServiceExist(String name) throws IOException {
        Process process = Runtime.getRuntime().exec(GET_SERVICE + name);
        process.getOutputStream().close();
        String line;
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            if (line.contains(name)) {
                flag = true;
            }
        }
        stdout.close();
        return flag;
    }

    private static void processDetail(Process process, int i) throws IOException {
        boolean flag = false;
        process.getOutputStream().close();
        String line;

        System.out.println("-------------- Begin Standard Output (process " + i + " ): ------------------------------");
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            System.out.println(line);
            if (line.contains("notepad")) {
                flag = true;
            }
        }
        System.out.println(flag);
        System.out.println("----------------- End -------------------------------------------");
        stdout.close();
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