package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logging.LoggerConf;
import sun.util.calendar.BaseCalendar;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Confirmation {

//    public static void main(String[] args) throws IOException {
//        String SERVER_AGENT_1C = "1C:Enterprise 8.3 Server Agent";
//
//        String GET_SERVICE_BEGIN = "powershell.exe Get-Service -Name '";
//        String GET_SERVICE_END = "'";
//
//        Process process = Runtime.getRuntime().exec(GET_SERVICE_BEGIN + SERVER_AGENT_1C + GET_SERVICE_END);
//        processDetail(process, SERVER_AGENT_1C);
//
//    }

        private final String SERVER_AGENT_1C = "1C:Enterprise 8.3 Server Agent";
    //    private final String SERVER_AGENT_1C = "MySQL80";
    private final String SERVER_AGENT_1C_X86_64 = "1C:Enterprise 8.3 Server Agent (x86-64)";
    //    private final String SERVER_AGENT_1C_X86_64 = "MySQL80";
    private final String SERVER_AGENT_1C_SHORT_NAME = "1C:Enterprise*";
//    private final String SERVER_AGENT_1C_SHORT_NAME = "MySQL80";
    private final String MSSQLSERVER = "MSSQLSERVER";
//        private final String MSSQLSERVER = "lfsvc";
    private final String MSSQL$SQLEXPRESS = "MSSQL$SQLEXPRESS";
//        private final String MSSQL$SQLEXPRESS = "lfsvc";
    private final String PROCESS_1cv8 = "1cv8";
//        private final String PROCESS_1cv8 = "notepad";
    private final String PROCESS_1cv8c = "1cv8c";
//    private final String PROCESS_1cv8c = "notepad";

    private final String STOP_SERVICE_BEGIN = "powershell.exe -Command \"Start-Process powershell \\\"-ExecutionPolicy -NoProfile -Command `Stop-Service -Name '";

    private final String STOP_PROCESS_BEGIN = "powershell.exe -Command \"Start-Process powershell \\\"-ExecutionPolicy -NoProfile -Command `Stop-Process -Name '";

    private final String START_SERVICE_BEGIN = "powershell.exe -Command \"Start-Process powershell \\\"-ExecutionPolicy -NoProfile -Command `Start-Service -Name '";

    private final String COMMAND_END = "'`\\\"\\\" -Verb RunAs\"";

    private final String GET_PROCESS = "powershell.exe Get-Process -Name ";
    private final String GET_SERVICE = "powershell.exe Get-Service -Name ";

    private ArrayList info = new ArrayList();

    private boolean flag = false;

    static Logger logger;

    static {
        LoggerConf conf = null;
        try {
            conf = new LoggerConf(Confirmation.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger = conf.getLogger();
    }

    public void restart(ActionEvent actionEvent) throws IOException, InterruptedException {
        ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        logger.log(Level.INFO, "1. начало остановки службы \"" + SERVER_AGENT_1C + "\" или  \"" + SERVER_AGENT_1C_X86_64 + "\"");
//        stopService(SERVER_AGENT_1C, SERVER_AGENT_1C_X86_64);
        stopService(SERVER_AGENT_1C_SHORT_NAME);
        Thread.sleep(5000);
        stopService(MSSQLSERVER, MSSQL$SQLEXPRESS);
        Thread.sleep(15000);
        stopProcess(PROCESS_1cv8, PROCESS_1cv8c);
        Thread.sleep(5000);
        startService(info);
        Thread.sleep(5000);
        successAlert();
        Runtime.getRuntime().exit(0);
    }

    private void stopService(String serviceName) throws IOException {
        logger.log(Level.INFO, "******* 1. Остановка службы 1C:Enterprise 8.3 Server Agent  *******");
        if (isServiceExist(serviceName)) {
            logger.log(Level.INFO, "1.1. Служба " + "\"" + serviceName + "\"" + " найдена успешно");
            flag = false;
            info.add(serviceName);
            Process stopService = Runtime.getRuntime().exec(STOP_SERVICE_BEGIN + serviceName + COMMAND_END);
            System.out.println(STOP_SERVICE_BEGIN + serviceName + COMMAND_END);
            logger.log(Level.INFO, "1.2. Служба " + "\"" + serviceName + "\"" + " остановлена");
        } else {
            logger.log(Level.INFO, "1.1. Служба " + "\"" + serviceName + "\"" + " НЕ найдена");
        }
    }

    private void startService(ArrayList info) throws InterruptedException {
        try {
            logger.log(Level.INFO, "****** 3. Запуск остановленных ранее служб ******");
            Process startMSSQL = Runtime.getRuntime().exec(START_SERVICE_BEGIN + info.get(1) + COMMAND_END);
            logger.log(Level.INFO, "3.1 Служба " + "\"" + info.get(1) + "\"" + " запущена.");
            Thread.sleep(10000);
            Process start1CServer = Runtime.getRuntime().exec(START_SERVICE_BEGIN + info.get(0) + COMMAND_END);
            logger.log(Level.INFO, "3.2 Служба " + "\"" + info.get(0) + "\"" + " запущена.");
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
        logger.log(Level.INFO, "******* 2. Проверка наличия процесса *******");
        if (isProcessExist(processFirstName)) {
            logger.log(Level.INFO, "2.1. Процесс " + "\"" + processFirstName + "\"" + " найден успешно");
            flag = false;
            Process stopService = Runtime.getRuntime().exec(STOP_PROCESS_BEGIN + processFirstName + COMMAND_END);
            logger.log(Level.INFO, "2.2. Процесс " + "\"" + processFirstName + "\"" + " остановлен");
        } else {
            logger.log(Level.INFO, "2.1. Процесс " + "\"" + processFirstName + "\"" + " НЕ найден, пытаемся остановить процесс " +
                    "\"" + processSecondName + "\"");
            Process stopService = Runtime.getRuntime().exec(STOP_PROCESS_BEGIN + processSecondName + COMMAND_END);
            logger.log(Level.INFO, "****** 2.2. Процесс " + "\"" + processSecondName + "\"" + " остановлен, в случае если он был предварительно запущен ******");
        }
    }

    private boolean isProcessExist(String processName) throws IOException {
        flag = false;
        System.out.println("process flag begin = " + flag);
        Process process = Runtime.getRuntime().exec(GET_PROCESS + processName);
        process.getOutputStream().close();
        String line;
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        System.out.println("---------------   Process details     -------------------------");
        while ((line = stdout.readLine()) != null) {
            System.out.println(line);
            if (line.contains(processName)) {
                flag = true;
            }
        }
        System.out.println("process flag end = " + flag);
        System.out.println("----------------------------------------------------------------");
        stdout.close();
        return flag;
    }

    private void stopService(String serviceFirstName, String serviceSecondName) throws IOException {
        logger.log(Level.INFO, "******* 1. Проверка наличия служб *******");
        if (isServiceExist(serviceFirstName)) {
            logger.log(Level.INFO, "1.1. Служба " + "\"" + serviceFirstName + "\"" + " найдена успешно");
            flag = false;
            info.add(serviceFirstName);
            Process stopService = Runtime.getRuntime().exec(STOP_SERVICE_BEGIN + serviceFirstName + COMMAND_END);
            System.out.println(STOP_SERVICE_BEGIN + serviceFirstName + COMMAND_END);
            logger.log(Level.INFO, "1.2. Служба " + "\"" + serviceFirstName + "\"" + " остановлена");
        } else {
            logger.log(Level.INFO, "1.1. Служба " + "\"" + serviceFirstName + "\"" + " НЕ найдена, производим попытку остановки службы " +
                    "\"" + serviceSecondName + "\"");
            logger.log(Level.INFO, "Проверим, служба " + "\"" + serviceSecondName + "\"" + " существует: " + isServiceExist(serviceSecondName));
            flag = false;
            info.add(serviceSecondName);
            Process stopService = Runtime.getRuntime().exec(STOP_SERVICE_BEGIN + serviceSecondName + COMMAND_END);
            System.out.println(STOP_SERVICE_BEGIN + serviceSecondName + COMMAND_END);
            logger.log(Level.INFO, "****** 1.2. Служба " + "\"" + serviceSecondName + "\"" + " остановлена, в случае если она была предварительно запущена ******");
        }
    }

    private boolean isServiceExist(String name) throws IOException {
        Process process = Runtime.getRuntime().exec(GET_SERVICE + name);
        process.getOutputStream().close();
        String line;
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            if (line.contains(name.substring(0, name.length() - 2))) {
                flag = true;
            }
        }
        System.out.println("flag = " + flag);
        stdout.close();
        return flag;
    }

    private static void processDetail(Process process, String name) throws IOException {
        boolean flag = false;
        process.getOutputStream().close();
        String line;

        System.out.println("-------------- Begin Standard Output (process " + name + " ): ------------------------------");
        BufferedReader stdout = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            System.out.println(line);
            if (line.contains("1C:Enterprise 8...")) {
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