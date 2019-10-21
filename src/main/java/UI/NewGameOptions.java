package UI;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewGameOptions implements AfterClick {
    private Scene scene;
    private Stage window;
    private static NewGameOptions newGameOptions;
    private double width = 800;
    private double height = 600;
    
    public static NewGameOptions getInstance(Stage primaryStage){
        if(newGameOptions == null){
            newGameOptions = new NewGameOptions(primaryStage);
        }
        return newGameOptions;
    }
    
    private NewGameOptions(Stage primaryStage){
        window = primaryStage;
        
        
        
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
}
