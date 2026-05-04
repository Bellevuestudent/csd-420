/*
 * Patrice Moracchini
 * 05/03/2026
 * CSD-420
 * assignment 7.2
 * This program creates a JavaFX application that displays styled circles
 * using CSS. It has of two panes: the first one contains a single
 * circle with a border, while the second contains three circles with
 * different styles. The circles are styled using an external CSS file
 * named "mystyle.css".
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class StyledCircles extends Application {

    @Override
    public void start(Stage primaryStage) {

        HBox hBox = new HBox(5);                            // Create HBox with spacing of 5 pixels
        hBox.setPadding(new Insets(10));                    // Set padding of 10 pixels around the HBox

        int paneHeight = 250;                               // Define height for the panes
        int cy = paneHeight / 2;                            // Calculate the vertical center (cy) for the circles in the panes
        int radius = 30;                                    // Define radius for the circles

        // Pane 1 - bordered, contains circle 1
        Pane pane1 = new Pane();                            // Border for pane1
        pane1.getStyleClass().add("border");
        pane1.setPrefSize(70, paneHeight);                  // Set preferred size for pane1
        Circle circle1 = new Circle(35, cy, radius);        // Center circle1 in pane1
        circle1.getStyleClass().add("plaincircle");      // Style circle1 with plaincircle class
        pane1.getChildren().add(circle1);

        // Pane 2 - no border, contains circles 2, 3, 4 at same cy
        Pane pane2 = new Pane();                                    // No border for pane2
        pane2.setPrefSize(3 * (2 * radius) + 2 * 5, paneHeight);    // Set preferred size for pane2     
        Circle circle2 = new Circle(radius, cy, radius);            // Center circle2 in pane2    
        circle2.getStyleClass().add("plaincircle");              // Style circle2 with plaincircle class
        Circle circle3 = new Circle(radius + 65, cy, radius);       // Center circle3 in pane2
        circle3.setId("redcircle");                                 // Style circle3 with redcircle ID
        Circle circle4 = new Circle(radius + 130, cy, radius);      // Center circle4 in pane2
        circle4.setId("greencircle");                               // Style circle4 with greencircle ID
        pane2.getChildren().addAll(circle2, circle3, circle4);      // Add circles to pane2

        hBox.getChildren().addAll(pane1, pane2);                    // Add panes to HBox

        Scene scene = new Scene(hBox, 300, 270);                    // Create scene with HBox and set dimensions
        scene.getStylesheets().add("mystyle.css");               // Add CSS stylesheet

        primaryStage.setTitle("Assignment 7.2");                    // Set title for the stage
        primaryStage.setScene(scene);                               // Set the scene for the stage
        primaryStage.show();                                        // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}