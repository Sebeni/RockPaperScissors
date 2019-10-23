package UI;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ResultBox {
    
    public static void display(String title, String playerChoice, String cpuChoice, String result, int roundNum, Stage primaryStage, int playerWins, int cpuWins){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        
        window.setTitle(title);
        window.setMinWidth(500);
        window.setMinHeight(200);

        Label playerChoiceLabel = new Label(playerChoice);
        Label cpuChoiceLabel = new Label(cpuChoice);
        HBox top = new HBox(50);
        top.getChildren().addAll(playerChoiceLabel, cpuChoiceLabel);
        top.setAlignment(Pos.CENTER);
        
        Label resultLabel = new Label(result);
        if(result.contains("You won")){
            resultLabel.setStyle("-fx-text-fill: green; " +
                    "-fx-font-size:20");
        } else if(result.contains("You lost")){
            resultLabel.setStyle("-fx-text-fill: red;" +
                    "-fx-font-size:20");
        } else {
            resultLabel.setStyle("-fx-text-fill: blue;" +
                    "-fx-font-size:20");
        }
        
        VBox root = new VBox(10);
        
        Button button = new Button();
        button.setPrefSize(200,20);

        //final pane
        VBox finalResult = new VBox(10);
        
        
        //checking num of rounds
        if(roundNum <= Options.getNumOfRounds()){
            button.setText("Continue");
            button.setOnAction(e -> window.close());
        } else {
            window.initStyle(StageStyle.UNDECORATED);
            root.setStyle("-fx-border-color: darkgrey;" +
                    "-fx-border-width: 5");
            button.setText("Return to options");
            button.setOnAction(e -> {
                AfterClick instance = Options.getInstance(primaryStage);
                AfterClick.centerWindow(instance);
                window.close();
            });

            //first pane
            Label gameOver = new Label("GAME OVER");
            gameOver.setStyle("-fx-font-size:20;" +
                    "-fx-font-family:Verdana");
            
            
            //second pane
            HBox winsCompare = new HBox(10);
            Label yourWinsLabel = new Label("Your wins: " + playerWins);
            Label cpuWinsLabel = new Label("CPU wins: " + cpuWins);
            winsCompare.getChildren().addAll(yourWinsLabel, cpuWinsLabel);
            winsCompare.setAlignment(Pos.CENTER);
            
            //last pane
            Label finalResultCompare = new Label(); 
            finalResultCompare.setStyle("-fx-font-size:20;" +
                    "-fx-font-family:Verdana");
            
            if(playerWins == cpuWins){
                finalResultCompare.setText("DRAW");
            }
            else if(playerWins > cpuWins){
                finalResultCompare.setText("CONGRATULATIONS! YOU WON THE GAME!");
            } else {
                finalResultCompare.setText("YOU LOST THE GAME! BETTER LUCK NEXT TIME");
            }
            finalResult.getChildren().addAll(gameOver, winsCompare, finalResultCompare);
            finalResult.setAlignment(Pos.CENTER);
        }
        
        root.getChildren().addAll(top, resultLabel, finalResult, button);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        
        Scene layout = new Scene(root);
        window.setScene(layout);

        window.showAndWait();
    }
    
    public static void lastRoundDisplay(Stage primaryStage){
        
        
        
    }
}
