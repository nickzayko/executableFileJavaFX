import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.log(Level.INFO,"Starting project");
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("views/confirmation.fxml"));
        primaryStage.setScene(new Scene(root, 500, 150));
        primaryStage.show();
    }

}

//    private static void processDetail(Process process, int i) throws IOException {
//        process.getOutputStream().close();
//        String line;
//
//        System.out.println("-------------- Begin Standard Output (process " + i + " ): ------------------------------");
//        BufferedReader stdout = new BufferedReader(new InputStreamReader(
//                process.getInputStream()));
//        while ((line = stdout.readLine()) != null) {
//            System.out.println(line);
//        }
//        System.out.println("----------------- End -------------------------------------------");
//        stdout.close();
//    }



//        String testFilePath = "C:\\Users\\anduser\\IdeaProjects\\command\\src\\test.txt";
//        try {
//            // Microsoft Windows NT or later
//            Process process = Runtime.getRuntime().exec("cmd /c notepad.exe ");
//
//            // Microsoft Windows 95/98
//            // Runtime.getRuntime().exec("c:\\windows\\notepad.exe " + testFilePath);
//            process.waitFor();
//        } catch ( Exception ex ) {
//            ex.printStackTrace();
//        }

//        NTSystem ntSystem = new NTSystem();
//        String userSID = ntSystem.getUserSID();
//        System.out.println("SID = " + userSID);

//        ProcessBuilder pb = new ProcessBuilder( "Notepad.exe");
//        try {
//            pb.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        ProcessBuilder pb = new ProcessBuilder( "C:\\Users\\anduser\\IdeaProjects\\command\\src\\Gut.bat");
//        ProcessBuilder pb = new ProcessBuilder( "/C" "Gut.bat");
//        try {
//            pb.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {

//
//        String cmd1 = "powershell.exe Start-Process notepad.exe -verb RunAs";
//        String cmd2 = "powershell.exe Get-Process -Name \"notepad\" ";
//        String cmd3 = "powershell.exe Requires -RunAsAdministrator Stop-Process -Name \"notepad\" ";
//        String cmd4 = "powershell.exe Start-Process powershell -Verb runAs -ArgumentList \"powershell.exe Stop-Process -Name \"notepad\"\" ";
//        String cmd5 = "powershell.exe Get-Process | where {$_.name -match ″notepad″}  | Stop-Process";
////        String cmd6 = "powershell.exe -Command \"Start-Process powershell \\\"-ExecutionPolicy Bypass -NoProfile -NoExit -Hidden -Command `Stop-Process -Name \"1cv8\"`\\\"\\\" -Verb RunAs\"";
//        String cmd6 = "powershell.exe -Command \"Start-Process powershell \\\"-ExecutionPolicy -NoProfile -Command `Stop-Process -Name \"notepad\"`\\\"\\\" -Verb RunAs\"";
//        String cmd7 = "powershell.exe Get-Service -Name  \"WSearch\"";
//        String cmd8 = "powershell.exe -Command \"Start-Process powershell \\\"-ExecutionPolicy -NoProfile -Command `Stop-Service -Name \"WSearch\"`\\\"\\\" -Verb RunAs\"";
//        String cmd9 = "powershell.exe -Command \"Start-Process powershell \\\"-ExecutionPolicy -NoProfile -Command `Start-Service -Name \"WSearch\"`\\\"\\\" -Verb RunAs\"";
////            Process process = Runtime.getRuntime().exec("powershell.exe Start-Process notepad.exe -verb RunAs");
////            Process process = Runtime.getRuntime().exec("powershell.exe Stop-Process -Name notepad");
////        Process process1 = Runtime.getRuntime().exec(cmd1);
////        processDetail(process1, 1);
//        Process process2 = Runtime.getRuntime().exec(cmd2);
//        processDetail(process2, 2);
////        Process process3 = Runtime.getRuntime().exec(cmd3);
////        processDetail(process3, 3);
////        Process process4 = Runtime.getRuntime().exec(cmd4);
////        processDetail(process4, 4);
////        Process process5 = Runtime.getRuntime().exec(cmd5);
////        processDetail(process5, 5);
////        Process process6 = Runtime.getRuntime().exec(cmd6);
////        processDetail(process6, 6);
//        Process process7 = Runtime.getRuntime().exec(cmd7);
//        processDetail(process7, 7);
////        Process process8 = Runtime.getRuntime().exec(cmd8);
////        processDetail(process8, 8);
//        Process process9 = Runtime.getRuntime().exec(cmd9);
//        processDetail(process9, 9);


//            Process myappProcess1 = Runtime.getRuntime().exec("cmd.exe start notepad.exe");
////            Process myappProcess = Runtime.getRuntime().exec("powershell.exe Start-Process cmd.exe -verb RunAs");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if(args.length != 1) {
//            System.out.println("No arguments");
//            System.exit(1);
//        }
//        Process proc = Runtime.getRuntime().exec("cmd.exe /C \"\"c:\\program files\\winrar\\rar.exe\" param1 param2 \"");
//        Process proc = Runtime.getRuntime().exec("cmd.exe /C \"\"C:\\WINDOWS\\system32\" param1 param2 \"");