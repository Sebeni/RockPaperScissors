package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
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
        newGame.setOnAction(event -> window.setScene(NewGameOptions.getInstance(window).getScene()));
        
        Button about = new Button("About");
        about.setPrefSize(130,20);
        about.setOnAction(event -> {
            AfterClick instance = About.getInstance(window);
            AfterClick.centerWindow(instance);
        });
        
        Button exit = new Button("Exit");
        about.setPrefSize(130,20);
        about.setOnAction(event -> AfterClick.closeProgram(window));
        
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
