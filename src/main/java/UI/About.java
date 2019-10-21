package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class About implements AfterClick{
    private Scene scene;
    private Stage window;
    private static About about;
    private double width = 700;
    private double height = 1000;
    
    public static About getInstance(Stage primaryStage){
        if(about==null){
            about = new About(primaryStage);
        }
        return about;
    }
    
    private About(Stage primaryStage){
        
        window = primaryStage;
        
        VBox layout = new VBox(0);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 15, 15, 15));
        layout.setPrefSize(width, height);
        
        //basic about and rules
        Label about = new Label("text and images from Wikipedia:\n" +
                "Rock-paper-scissors (also known as scissors-rock-paper and scissors-paper-stone) is a hand game usually played between two people," +
                " in which each player simultaneously forms one of three shapes with an outstretched hand. These shapes are \"rock\" (a closed fist)" +
                ", \"paper\" (a flat hand), and \"scissors\" (a fist with the index finger and middle finger extended, forming a V).");
        about.setWrapText(true);
        about.setPrefSize(width - 60,300);
        about.setTextAlignment(TextAlignment.JUSTIFY);
        
        //rps image
        ImageView rpsBasicrulesImg = new ImageView("rps basic.png");
        rpsBasicrulesImg.setFitHeight(300);
        rpsBasicrulesImg.setFitWidth(300);
        
        //about spock
        Label spockRulesTxt = new Label("One popular five-weapon expansion is \"rock-paper-scissors-Spock-lizard\"," +
                " invented by Sam Kass and Karen Bryla, which adds \"Spock\" and \"lizard\" to the standard three choices. " +
                "\"Spock\" is signified with the Star Trek Vulcan salute, while \"lizard\" is shown by forming the hand into" +
                " a sock-puppet-like mouth. Spock smashes scissors and vaporizes rock; he is poisoned by lizard and disproven by paper.");
        spockRulesTxt.setWrapText(true);
        spockRulesTxt.setPrefSize(width - 60,300);
        spockRulesTxt.setMaxSize(width - 60,300);
        spockRulesTxt.setTextAlignment(TextAlignment.JUSTIFY);

        //spock images
        HBox spockImage = new HBox(10);
        ImageView rpsSpock = new ImageView("rpsSpock.png");
        rpsSpock.setFitHeight(300);
        rpsSpock.setFitWidth(300);
        
        ImageView rpsSpockSymbols = new ImageView("rpsSpockImage.png");
        rpsSpockSymbols.setFitHeight(300);
        rpsSpockSymbols.setFitWidth(300);
        
        spockImage.getChildren().addAll(rpsSpock, rpsSpockSymbols);
        spockImage.setAlignment(Pos.CENTER);
        
        //return button
        Button returnButton = new Button("Return");
        returnButton.setOnAction(event -> {
            AfterClick instance = HomeScreen.getInstance(window);
            AfterClick.centerWindow(instance);
        });
        
        //adding all
        layout.getChildren().addAll(about, rpsBasicrulesImg, spockRulesTxt, spockImage, returnButton);

        window.setOnCloseRequest(e -> {
            e.consume();
            AfterClick.closeProgram(window);
        });
        
        ScrollPane sp = new ScrollPane(layout);
        
        scene = new Scene(sp, width, height);
        
    }

    public Scene getScene() {
        return scene;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Stage getWindow() {
        return window;
    }
}
