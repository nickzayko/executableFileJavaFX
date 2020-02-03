//package controllers;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class MyThread extends Thread {
//    @Override
//    public void run() {
//        try {
//            Thread.sleep(2000);
//            startProgressIndicator();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    private void startProgressIndicator() throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("../views/indicator.fxml"));
//        Scene scene = new Scene(root, 500, 150);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
//    }
//}
