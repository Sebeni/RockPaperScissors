package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreen implements AfterClick {
    private static HomeScreen homeScreen;
    private Stage window;
    private Scene scene;
    private double width = 400;
    private double height = 600;
    
    public static HomeScreen getInstance(Stage primaryStage){
        if(homeScreen == null) {
            homeScreen = new HomeScreen(primaryStage);
        }
        return homeScreen;
    }

    private HomeScreen(Stage primaryStage) {
        window = primaryStage;
        
        window.setOnCloseRequest(e -> {
            e.consume();
            AfterClick.closeProgram(window);
        });
        

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 50, 50, 50));
        
        Button newGame = new Button("New Game");
        newGame.setPrefSize(130,20);
        newGame.setOnAction(event -> {
            AfterClick instance = NewGameOptions.getInstance(window);
            AfterClick.centerWindow(instance);
        });
        
        Button about = new Button("Instructions");
        about.setPrefSize(130,20);
        about.setOnAction(event -> {
            AfterClick instance = Instructions.getInstance(window);
            AfterClick.centerWindow(instance);
        });
        
        Button exit = new Button("Exit");
        exit.setPrefSize(130,20);
        exit.setOnAction(event -> AfterClick.closeProgram(window));
        
        layout.getChildren().addAll(newGame, about, exit);
        scene = new Scene(layout, width, height);
        
    }

    public Scene getScene() {
        return this.scene;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Stage getWindow() {
        return window;
    }
    
}
