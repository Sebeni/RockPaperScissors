package UI;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public interface AfterClick {
    //this interface gives methods common to all scenes and stages

    //changes AND center on the screen new scene
    static void centerWindow(AfterClick acs){
        Stage window = acs.getWindow();
        Rectangle2D screenBoundsHome = Screen.getPrimary().getVisualBounds();
        window.setX(((screenBoundsHome.getWidth()) - acs.getWidth()) / 2);
        window.setY(((screenBoundsHome.getHeight()) - acs.getHeight()) / 2);
        window.setScene(acs.getScene());
    }
    
    double getWidth();
    double getHeight();
    Stage getWindow();
    Scene getScene();

    //shows exit popup window
    static void closeProgram(Stage window) {
        boolean closing = ConfirmBox.display("Warning", "Do you wish to quit?");
        if(closing){
            window.close();
        }
    }
            
}

 
