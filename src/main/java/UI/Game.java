package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game implements AfterClick {
    private Scene scene;
    private Stage window;
    private double width = 800;
    private double height = 800;
    private int roundNum = 1;
    private int playerWin = 0;
    private int cpuWin = 0;
  

    public Game(Stage primaryStage){
        window = primaryStage;
        
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10));
        gp.setVgap(10);
        gp.setHgap(50);
        gp.setAlignment(Pos.CENTER);

       //Left top labels
        Label roudNumL = new Label("Round number");
        Label playerWinsL = new Label("Player wins");
        Label cpuWinsL = new Label("CPU wins");

        GridPane.setConstraints(roudNumL, 0, 0);
        GridPane.setConstraints(playerWinsL, 0, 1);
        GridPane.setConstraints(cpuWinsL, 0, 2);
        
       //Right top labels
        Label roundNumR = new Label("" + roundNum);
        Label playerWinsR = new Label("" + playerWin);
        Label cpuWinsR = new Label("" + cpuWin);

        GridPane.setConstraints(roundNumR, 2, 0);
        GridPane.setConstraints(playerWinsR, 2, 1);
        GridPane.setConstraints(cpuWinsR, 2, 2);
        
        //Game buttons
        Button rock = new Button("Rock\n<press>");
        rock.setPrefSize(100,100);
        rock.setStyle("-fx-background-color: #7A0F12; " +
                "-fx-text-fill: white; " +
                "-fx-border-color: white");
        
        Button scissors = new Button("Scissors\n<press>");
        scissors.setPrefSize(100,100);
        scissors.setStyle("-fx-background-color: #067F06;" +
                " -fx-text-fill: white;" +
                " -fx-border-color: white");
        
        Button paper = new Button("Paper\n<press>");
        paper.setPrefSize(100,100);
        paper.setStyle("-fx-background-color: #4F4C9D;" +
                " -fx-text-fill: white;" +
                " -fx-border-color: white");

        GridPane.setConstraints(rock, 0, 4);
        GridPane.setConstraints(scissors, 0, 5);
        GridPane.setConstraints(paper, 0, 6);
        
        //instruction pane
        VBox instructionBox = new VBox();
        GridPane.setConstraints(instructionBox, 1, 4,2, 5);
        
        //standard game
        ImageView rpsBasicRulesImg = new ImageView("rps basic.png");
        rpsBasicRulesImg.setFitHeight(300);
        rpsBasicRulesImg.setFitWidth(300);
        
        //spock game
        ImageView rpsSpock = new ImageView("rpsSpock.png");
        rpsSpock.setFitHeight(300);
        rpsSpock.setFitWidth(300);

        ImageView rpsSpockSymbols = new ImageView("rpsSpockImage.png");
        rpsSpockSymbols.setFitHeight(300);
        rpsSpockSymbols.setFitWidth(300);
        

        gp.getChildren().addAll(roudNumL, roundNumR, playerWinsL, playerWinsR, cpuWinsL, cpuWinsR, rock, scissors, paper, instructionBox);
        
        if(!NewGameOptions.isStandardGame()){
            Button spock = new Button("Spock\n<press>");
            spock.setPrefSize(100,100);
            spock.setStyle("-fx-background-color: #d5e5ff;" +
                    " -fx-text-fill: black;" +
                    " -fx-border-color: white");
            
            Button lizard = new Button("Lizard\n<press>");
            lizard.setPrefSize(100,100);
            lizard.setStyle("-fx-background-color: #d7f4d7;" +
                    " -fx-text-fill: black;" +
                    " -fx-border-color: white");


            rock.setStyle("-fx-background-color: #FED6D8;" +
                    " -fx-text-fill: black;" +
                    " -fx-border-color: white");
            
            scissors.setStyle("-fx-background-color: #F8D5FF;" +
                    " -fx-text-fill: black;" +
                    " -fx-border-color: white");
            
            paper.setStyle("-fx-background-color: #fff6d5;" +
                    " -fx-text-fill: black;" +
                    " -fx-border-color: white");
            
            GridPane.setConstraints(spock, 0, 7);
            GridPane.setConstraints(lizard, 0, 8);
            gp.getChildren().addAll(spock, lizard);

            instructionBox.getChildren().addAll(rpsSpock, rpsSpockSymbols);
        } else {
            instructionBox.getChildren().addAll(rpsBasicRulesImg);
        }
        
        
        
        
        
        //return button
        Button returnButton = new Button("Return");
        returnButton.setPrefSize(130,20);
        returnButton.setOnAction(event -> {
            AfterClick instance = NewGameOptions.getInstance(window);
            AfterClick.centerWindow(instance);
        });
        
        GridPane.setConstraints(returnButton, 0, 10, 2,1);
        
        gp.getChildren().addAll(returnButton);
        
        scene = new Scene(gp, width, height);
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getWindow() {
        return window;
    }
    
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
