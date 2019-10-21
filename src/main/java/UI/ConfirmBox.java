package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class ConfirmBox {
   

    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);
        Label label = new Label(message);
        
        VBox outerLayout = new VBox(30);
        outerLayout.setAlignment(Pos.CENTER);
        outerLayout.setPadding(new Insets(10, 50, 50, 50));

        HBox layout = new HBox(50);

        Button yesButton = new Button("Yes");
        yesButton.setPrefSize(130,20);
        Button noButton = new Button("No");
        noButton.setPrefSize(130, 20);

        AtomicBoolean yesAnwser = new AtomicBoolean(false);

        yesButton.setOnAction(e -> {
            yesAnwser.set(true);
            window.close();
        });
        noButton.setOnAction(e -> {
            yesAnwser.set(false);
            window.close();
        });

        outerLayout.getChildren().addAll(label, layout);
        layout.getChildren().addAll(yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(outerLayout);
        window.setScene(scene);

        window.showAndWait();

        return yesAnwser.get();
    }
    
}
