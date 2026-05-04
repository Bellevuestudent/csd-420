/*
 * Patrice Moracchini
 * 05/03/2026
 * CSD-420
 * Assignment 7.2
 * Test program for StyledCircles.java. Rebuilds the same scene and verifies
 * structure, sizing, and styling of all panes and circles.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class StyledCirclesTest extends Application {

    private static int passed = 0;
    private static int failed = 0;

    @Override
    public void start(Stage stage) {

        // ── Build the same scene as StyledCircles ─────────────────────────
        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(10));

        int paneHeight = 250;
        int cy = paneHeight / 2;
        int radius = 30;

        Pane pane1 = new Pane();
        pane1.getStyleClass().add("border");
        pane1.setPrefSize(70, paneHeight);
        Circle circle1 = new Circle(35, cy, radius);
        circle1.getStyleClass().add("plaincircle");
        pane1.getChildren().add(circle1);

        Pane pane2 = new Pane();
        pane2.setPrefSize(3 * (2 * radius) + 2 * 5, paneHeight);
        Circle circle2 = new Circle(radius, cy, radius);
        circle2.getStyleClass().add("plaincircle");
        Circle circle3 = new Circle(radius + 65, cy, radius);
        circle3.setId("redcircle");
        Circle circle4 = new Circle(radius + 130, cy, radius);
        circle4.setId("greencircle");
        pane2.getChildren().addAll(circle2, circle3, circle4);

        hBox.getChildren().addAll(pane1, pane2);

        Scene scene = new Scene(hBox, 300, 270);
        scene.getStylesheets().add("mystyle.css");
        stage.setScene(scene);
        stage.show();
        scene.getRoot().applyCss();
        scene.getRoot().layout();

        // Tests
        // HBox structure: should have 2 panes
        check("HBox has exactly 2 children (pane1 and pane2)",
                hBox.getChildren().size() == 2);

        // Pane1 - border class and size
        check("pane1 has 'border' style class",
                pane1.getStyleClass().contains("border"));
        check("pane1 has 1 child (circle1)",
                pane1.getChildren().size() == 1);
        check("pane1 prefWidth == 70",  pane1.getPrefWidth()  == 70);
        check("pane1 prefHeight == 250", pane1.getPrefHeight() == 250);

        // Pane2 - no border class and size
        check("pane2 does NOT have 'border' style class",
                !pane2.getStyleClass().contains("border"));
        check("pane2 has 3 children (circle2, circle3, circle4)",
                pane2.getChildren().size() == 3);
        check("pane2 prefWidth == 190",
                pane2.getPrefWidth() == 3 * (2 * radius) + 2 * 5);

        // Style class assignment: only circle1 and circle2 should have 'plaincircle' class
        check("circle1 has 'plaincircle' class",
                circle1.getStyleClass().contains("plaincircle"));
        check("circle2 has 'plaincircle' class",
                circle2.getStyleClass().contains("plaincircle"));

        // ID assignment
        check("circle3 id == 'redcircle'",   "redcircle".equals(circle3.getId()));
        check("circle4 id == 'greencircle'", "greencircle".equals(circle4.getId()));
        
        // Results
        System.out.println("\n" + passed + "/" + (passed + failed) + " tests passed.");
        Platform.exit();
    }
    
    private static void check(String description, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("PASS: " + description);
        } else {
            failed++;
            System.out.println("FAIL: " + description);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
