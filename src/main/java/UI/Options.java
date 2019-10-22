package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Options implements AfterClick {
    private Scene scene;
    private Stage window;
    private static Options options;
    private double width = 450;
    private double height = 400;
    private static boolean standardGame;
    private static boolean normalRandomness;
    private static int numOfRounds;
    
    private ChoiceBox<String> randomLevel;
    private ChoiceBox<String> gameVersion;
    private ChoiceBox<Integer> numOfRoundsField;

    public static Options getInstance(Stage primaryStage){
        if(options == null){
            options = new Options(primaryStage);
        }
        return options;
    }
    
    private Options(Stage primaryStage){
        window = primaryStage;
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10));
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setAlignment(Pos.CENTER);
        
        
        Label info = new Label("Game is based on pseudo random CPU choices");
        GridPane.setConstraints(info, 0, 0, 2, 1);
        
        //labels left side
        Label gameVersionLabel = new Label("Select game version");
        Label randomLevelLabel = new Label("Select randomness level");
        Label numOfRoundsLabel = new Label("Enter number of rounds");
        
        GridPane.setConstraints(gameVersionLabel, 0, 1);
        GridPane.setConstraints(randomLevelLabel, 0, 2);
        GridPane.setConstraints(numOfRoundsLabel, 0, 3);
        
        //right side - choice boxes and textfield
        gameVersion = new ChoiceBox<>();
        gameVersion.getItems().addAll("Standard", "Spock");
        gameVersion.setValue("Standard");
        
        randomLevel = new ChoiceBox<>();
        randomLevel.getItems().addAll("Total randomness <normal>", "CPU has advantage <hard>");
        randomLevel.setValue("Total randomness <normal>");

        numOfRoundsField = new ChoiceBox<>();
        numOfRoundsField.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        numOfRoundsField.setValue(1);
        
        GridPane.setConstraints(gameVersion, 1, 1);
        GridPane.setConstraints(randomLevel, 1, 2);
        GridPane.setConstraints(numOfRoundsField, 1, 3);

        //buttons bottom of the scene
        Button returnButton = new Button("Return");
        returnButton.setPrefSize(130,20);
        returnButton.setOnAction(event -> {
            AfterClick instance = HomeScreen.getInstance(window);
            AfterClick.centerWindow(instance);
        });
        GridPane.setConstraints(returnButton, 0, 5);
        
        Button startGameButton = new Button("Start");
        startGameButton.setPrefSize(130,20);
        startGameButton.setOnAction(event -> createNewGame());
        
        GridPane.setConstraints(startGameButton, 1, 5);
        
        
        gp.getChildren().addAll(info, gameVersionLabel, gameVersion, randomLevelLabel, randomLevel, numOfRoundsLabel, numOfRoundsField, returnButton, startGameButton);
        scene = new Scene(gp, width, height);
    }

    @Override
    public Stage getWindow() {
        return window;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public Scene getScene() {
        return scene;
    }
    
    private void createNewGame(){
        standardGame = gameVersion.getValue().equals("Standard");
        normalRandomness = randomLevel.getValue().equals("Total randomness <normal>");
        numOfRounds = numOfRoundsField.getValue();
        AfterClick instance = new Game(window);
        AfterClick.centerWindow(instance);
    }

    public static boolean isStandardGame() {
        return standardGame;
    }

    public static boolean isNormalRandomness() {
        return normalRandomness;
    }

    public static int getNumOfRounds() {
        return numOfRounds;
    }
}
