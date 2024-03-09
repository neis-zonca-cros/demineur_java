package org.demineur.demineur;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.Optional;

public class DemineurController {
    @FXML
    private GridPane gridPane;
    private DemineurModel demineurModel;


    public void initialize() {
        int rows = 10;
        int cols = 10;
        double buttonSize = 50;
        int largeur = 10;
        int hauteur = 10;
        int nombreMines = 10;
        demineurModel = new DemineurModel(largeur, hauteur, nombreMines);
        Font font = Font.font("Times", FontWeight.BOLD, 14);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Button button = new Button();
                button.setPrefSize(buttonSize, buttonSize);
                button.setOnAction(event -> handleCaseClick(button));
                button.setFont(font);
                gridPane.add(button, j, i);

            }

        }
    }

    private void handleCaseClick(Button button) {
        int columnIndex = GridPane.getColumnIndex(button);
        int rowIndex = GridPane.getRowIndex(button);

        boolean shouldContinueProcessing = true;
        if (demineurModel.estCaseMarquee(columnIndex, rowIndex)) {
            button.setStyle("-fx-background-color: red;");
            EnfantsRouges();
            System.out.println("BOMBES");
            System.out.println("PERDU");
            shouldContinueProcessing = false;
            gameOver();
        }

        if (shouldContinueProcessing) {
            demineurModel.decouvrirCase(columnIndex, rowIndex);
            EnfantsGris();
            Win();
        }
    }

    public void EnfantsGris() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                int columnIndex = GridPane.getColumnIndex(button);
                int rowIndex = GridPane.getRowIndex(button);
                if (demineurModel.estCaseDecouverte(columnIndex, rowIndex) && !(demineurModel.estCaseMarquee(columnIndex, rowIndex))) {
                    button.setStyle("-fx-background-color: #d19a60;");
                    if (demineurModel.getValeurCase(columnIndex, rowIndex) > 0 && !(demineurModel.estCaseMarquee(columnIndex, rowIndex))) {
                        button.setStyle("-fx-background-color: #d19a60;");
                        button.setText(String.valueOf(demineurModel.getValeurCase(columnIndex, rowIndex)));
                    }
                }
            }
        }
    }

    public void EnfantsRouges() {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                int columnIndex = GridPane.getColumnIndex(button);
                int rowIndex = GridPane.getRowIndex(button);
                if (demineurModel.estCaseMarquee(columnIndex, rowIndex)) {
                    button.setStyle("-fx-background-color: #83482f;");

                }
            }
        }
    }

    private void Win() {
        DialogueWin();
    }

    private void gameOver() {

        DialogueGameOver();
    }

    private void DialogueGameOver() {
        System.out.println("Partie terminée!");

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Oh :(");
        alert.setHeaderText("Vous avez perdu.");
        alert.setContentText("Voulez-vous rejouer ?");

        ButtonType buttonTypeRejouer = new ButtonType("Rejouer");

        alert.getButtonTypes().setAll(buttonTypeRejouer);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeRejouer) {
            initialize();
        } else {
            initialize();


        }
    }






    private void DialogueWin() {
        if (demineurModel.estPartieTerminee()) {
            System.out.println("Partie terminée!");

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Bravo !");
            alert.setHeaderText("Vous avez gagné !");
            alert.setContentText("Voulez-vous rejouer ?");

            ButtonType buttonTypeRejouer = new ButtonType("Rejouer");

            alert.getButtonTypes().setAll(buttonTypeRejouer);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeRejouer) {
                initialize();
            } else {
                initialize();
            }
        }

    }





}