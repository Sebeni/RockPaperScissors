package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class Game implements AfterClick {
    private Scene scene;
    private Stage window;
    private double width = 800;
    private double height = 800;
    private int roundNum = 1;
    private int playerWin = 0;
    private int cpuWin = 0;
    private int playerChoice;
    private int cpuChoice;
    private Random random = new Random();
    private static Map<Integer, String> possibleChoices = new HashMap<>();
    
    private Label roundNumR;
    private Label playerWinsR;
    private Label cpuWinsR;
    
    static {
        possibleChoices.put(0, "Rock");
        possibleChoices.put(1, "Scissors");
        possibleChoices.put(2, "Paper");
        possibleChoices.put(3, "Lizard");
        possibleChoices.put(4, "Spock");
    }

    public Game(Stage primaryStage){
        //layout
        window = primaryStage;
        
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10));
        gp.setVgap(10);
        gp.setHgap(50);

       //Left top labels
        Label roundNumL = new Label("Round number");
        Label playerWinsL = new Label("Player wins");
        Label cpuWinsL = new Label("CPU wins");

        GridPane.setConstraints(roundNumL, 0, 0);
        GridPane.setConstraints(playerWinsL, 0, 1);
        GridPane.setConstraints(cpuWinsL, 0, 2);
        
       //Right top labels
        roundNumR = new Label("" + roundNum + "/" + Options.getNumOfRounds());
        playerWinsR = new Label("" + playerWin);
        cpuWinsR = new Label("" + cpuWin);

        GridPane.setConstraints(roundNumR, 2, 0);
        GridPane.setConstraints(playerWinsR, 2, 1);
        GridPane.setConstraints(cpuWinsR, 2, 2);
        
        //Game buttons
        Button rock = new Button("Rock");
        rock.setOnAction(event -> {
            playerChoice = 0;
            cpuChoiceLogic();
        });
        rock.setPrefSize(100,100);
        rock.setStyle("-fx-background-color: #7A0F12; " +
                "-fx-text-fill: white; " +
                "-fx-border-color: white");
        
        Button scissors = new Button("Scissors");
        scissors.setOnAction(event -> {
            playerChoice = 1;
            cpuChoiceLogic();
        });
        scissors.setPrefSize(100,100);
        scissors.setStyle("-fx-background-color: #067F06;" +
                " -fx-text-fill: white;" +
                " -fx-border-color: white");
        
        Button paper = new Button("Paper");
        paper.setOnAction(event -> {
            playerChoice = 2;
            cpuChoiceLogic();
        });
        paper.setPrefSize(100,100);
        paper.setStyle("-fx-background-color: #4F4C9D;" +
                " -fx-text-fill: white;" +
                " -fx-border-color: white");

        GridPane.setConstraints(rock, 0, 4);
        GridPane.setConstraints(scissors, 0, 5);
        GridPane.setConstraints(paper, 0, 6);
        
        //instruction pane (will be filled with picture accordingly to player's choice)
        VBox instructionBox = new VBox();
        GridPane.setConstraints(instructionBox, 1, 4,2, 5);
        
        gp.getChildren().addAll(roundNumL, roundNumR, playerWinsL, playerWinsR, cpuWinsL, cpuWinsR, rock, scissors, paper, instructionBox);
        
        
        //additional layout accordingly to player's choice
        if(!Options.isStandardGame()){
            //adding spock
            Button spock = new Button("Spock");
            spock.setOnAction(event -> {
                playerChoice = 4;
                cpuChoiceLogic();
            });
            spock.setPrefSize(100,100);
            spock.setStyle("-fx-background-color: #d5e5ff;" +
                    " -fx-text-fill: black;" +
                    " -fx-border-color: white");
            
            Button lizard = new Button("Lizard");
            lizard.setOnAction(event -> {
                playerChoice = 3;
                cpuChoiceLogic();
            });
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

            //spock game instructions picture
            ImageView rpsSpockSymbols = new ImageView("rpsSpockImage.png");
            rpsSpockSymbols.setFitHeight(300);
            rpsSpockSymbols.setFitWidth(300);

            instructionBox.getChildren().addAll(rpsSpockSymbols);
        } else {
            //standard game instructions picture
            ImageView rpsBasicRulesImg = new ImageView("rps basic.png");
            rpsBasicRulesImg.setFitHeight(300);
            rpsBasicRulesImg.setFitWidth(300);
            
            instructionBox.getChildren().addAll(rpsBasicRulesImg);
        }
        
        
        //return button
        Button returnButton = new Button("Return");
        returnButton.setPrefSize(130,20);
        returnButton.setOnAction(event -> {
            if(ConfirmBox.display("Return to options?", "Your current game will be lost. Continue?")){
                AfterClick instance = Options.getInstance(window);
                AfterClick.centerWindow(instance);
            }
        });
        
        Button newGameButton = new Button("New game");
        newGameButton.setPrefSize(130,20);
        newGameButton.setOnAction(event -> {
            if(ConfirmBox.display("Open new game?", "Your current game will be lost. Continue?")){
                AfterClick instance = new Game(window);
                AfterClick.centerWindow(instance);
            }
        });
        
        GridPane.setConstraints(returnButton, 0, 10);
        GridPane.setConstraints(newGameButton, 2, 10);
        
        gp.getChildren().addAll(returnButton, newGameButton);
        gp.setAlignment(Pos.CENTER);
        scene = new Scene(gp, width, height);
    }
    
    private void cpuChoiceLogic(){
        //cpu choices - on hard delete one loosing number
        //normal game
        Integer[] normalChoices = {2, 1, 0, 0, 1, 2};
        
        //spock game
        Integer[] spockChoices = {4, 3, 2, 1, 0, 0, 1, 2, 3, 4};
        
        if(Options.isNormalRandomness()){
            if(Options.isStandardGame()){
                cpuChoice = normalChoices[random.nextInt(normalChoices.length - 1)];
            } else {
                cpuChoice = spockChoices[random.nextInt(normalChoices.length - 1)];
            }
        } else {
            if(Options.isStandardGame()){
                highDifficultyChoice(normalChoices);
            } else {
                highDifficultyChoice(spockChoices);
            }
        }
        resultPopUp();
    }

    //deletes from cpu choices one (standard game) or two (spock) loosing numbers, therefore cpu has lower chance of choosing losing number
    private void highDifficultyChoice(Integer[] cpuChoices) {
        List<Integer> normalChoicesList = new ArrayList<>(Arrays.asList(cpuChoices));
        List<Integer> loosingList = loosingNumbers(playerChoice);
        for(Integer i : loosingList){
            normalChoicesList.remove(i);
        }
        cpuChoice = normalChoicesList.get(random.nextInt(normalChoicesList.size() - 1));
    }

    //sends to popup window Strings to display in it
    private void resultPopUp() {
        String playerChoiceString = "You chose: " + possibleChoices.get(playerChoice);
        String cpuChoiceString  = "CPU chose: " + possibleChoices.get(cpuChoice);
        String result = "";
        roundNum++;
        
        boolean playerLost = loosingNumbers(cpuChoice).contains(playerChoice);
        
        if(cpuChoice == playerChoice){
            result = "Draw";
        } else if(playerLost){
            cpuWin++;
            cpuWinsR.setText("" + cpuWin);
            result = "You lost the round!";
        } else {
            playerWin++;
            playerWinsR.setText("" + playerWin);
            result = "You won the round!";
        }
        ResultBox.display("Result", playerChoiceString, cpuChoiceString, result, roundNum, window, playerWin, cpuWin);
        
        //changing visible current round number
        if(roundNum <= Options.getNumOfRounds()){
            roundNumR.setText("" + roundNum + "/" + Options.getNumOfRounds());
        } 
    }

    //returns list of numbers which loose with given in argument numberToWin
    private List<Integer> loosingNumbers(int numberToWin){
        /* 0 - rock beats scissors (1) and lizard (3)
         * 1 - scissors beats paper (2) and lizard (3)
         * 2 - paper beats rock (0) and spock (4)
         * 3 - lizard beats paper (2) and spock (4)
         * 4 - spock beats scissors (1) and rock (0)
         */
        
        List<Integer> losingNumbers = new ArrayList<>();
        if(Options.isStandardGame()){
            switch(numberToWin) {
                case 0:
                    losingNumbers.add(1);
                    break;
                case 1:
                    losingNumbers.add(2);
                    break;
                case 2:
                    losingNumbers.add(0);
                    break;
            }
        } else {
            switch(numberToWin){
                case 0:
                    losingNumbers.add(1);
                    losingNumbers.add(3);
                    break;
                case 1:
                    losingNumbers.add(2);
                    losingNumbers.add(3);
                    break;
                case 2:
                    losingNumbers.add(0);
                    losingNumbers.add(4);
                    break;
                case 3:
                    losingNumbers.add(2);
                    losingNumbers.add(4);
                    break;
                case 4:
                    losingNumbers.add(1);
                    losingNumbers.add(0);
                    break;
            }
        }
        return losingNumbers;
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
