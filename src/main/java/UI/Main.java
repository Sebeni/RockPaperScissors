package UI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        HomeScreen hs = HomeScreen.getInstance(primaryStage);
        primaryStage.setTitle("Rock-Paper-Scissors");
        primaryStage.setScene(hs.getScene());
        primaryStage.show();
    }
}
