/*
 * Name: Patrice Moracchini
 * Assignment: Module 1.3:Random Card Display
 * This program displays four randomly selected cards from a deck of 52.
 * A Refresh button shows four new random cards.
 */

// Import necessary JavaFX classes
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
// Import necessary Java classes
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class RandomCards extends Application {     

    // Array to hold the ImageView objects for the cards
    private final ImageView[] cardViews = new ImageView[4];  

     // Override the start method to set up the JavaFX application
    @Override                          
    public void start(Stage primaryStage) {
        // Set spacing between cards, align them in the center and add padding around the card box
        HBox cardBox = new HBox(10);            
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setPadding(new Insets(20));

        // Create 4 image views for the cards
        for (int i = 0; i < 4; i++) {
            cardViews[i] = new ImageView();         // Set an empty image view for each card
            cardViews[i].setFitWidth(120);          // Set the width of the card images
            cardViews[i].setPreserveRatio(true);    // Preserve the aspect ratio of the card images
            cardBox.getChildren().add(cardViews[i]);    // Add the card image views to the HBox
        }

        Button refreshButton = new Button("Refresh");       // Create a Refresh button to display new random cards when clicked

        // Lambda expression for button click
        // refresh the cards when the button is clicked by calling the displayRandomCards method
        refreshButton.setOnAction(e -> displayRandomCards());   
        
        // Align the button in the center and add padding
        HBox buttonBox = new HBox(refreshButton);       
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(0, 0, 20, 0));

        // Set the card box in the center and the button box at the bottom of the BorderPane
        BorderPane root = new BorderPane();             
        root.setCenter(cardBox);
        root.setBottom(buttonBox);

        // Show cards when program starts
        displayRandomCards();
        // Set the title of the stage, add the scene to the stage, and display it
        Scene scene = new Scene(root, 600, 300);        
        primaryStage.setTitle("Random Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // Create a list to represent the deck of cards, where each card is represented by a number from 1 to 52
    private void displayRandomCards() {              
        ArrayList<Integer> deck = new ArrayList<>();

        // Add card numbers 1 through 52
        for (int i = 1; i <= 52; i++) {              
            deck.add(i);
        }

        // Shuffle the deck
        Collections.shuffle(deck);

        // Display the first 4 shuffled cards
        // for loop to load and display the images for the first 4 cards in the shuffled deck
        for (int i = 0; i < 4; i++) {              
            String fileName = "cards/" + deck.get(i) + ".png";
            //try-catch block to handle potential FileNotFoundException when loading card images
            try {                                  
                Image cardImage = new Image(new FileInputStream(fileName));
                cardViews[i].setImage(cardImage);
            } catch (FileNotFoundException ex) {
                // Print an error message if the image file is not found
                System.out.println("Could not find image: " + fileName);
            }
        }
    }
    // Launch the JavaFX application
    public static void main(String[] args) {        
        launch(args);
    }
}