package org.demineur.demineur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DemineurView extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(DemineurView.class.getResource("demineur-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Démineur");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}
