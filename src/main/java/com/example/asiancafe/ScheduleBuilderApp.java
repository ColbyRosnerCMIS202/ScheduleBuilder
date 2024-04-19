package com.example.asiancafe;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ScheduleBuilderApp extends Application {

    private ScheduleBuilder scheduleBuilder = new ScheduleBuilder();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Schedule Builder");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Name label
        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        GridPane.setConstraints(nameInput, 1, 0);

        // Occupation label
        Label occupationLabel = new Label("Occupation:");
        GridPane.setConstraints(occupationLabel, 0, 1);
        TextField occupationInput = new TextField();
        occupationInput.setPromptText("Occupation");
        GridPane.setConstraints(occupationInput, 1, 1);

        // Availability label
        Label availabilityLabel = new Label("Availability:");
        GridPane.setConstraints(availabilityLabel, 0, 2);
        TextField availabilityInput = new TextField();
        availabilityInput.setPromptText("Availability (comma separated)");
        GridPane.setConstraints(availabilityInput, 1, 2);

        // Add Worker button
        Button addButton = new Button("Add Worker");
        GridPane.setConstraints(addButton, 1, 3);
        addButton.setOnAction(e -> {
            String name = nameInput.getText();
            String occupation = occupationInput.getText();
            String[] availabilityArray = availabilityInput.getText().split(",");
            List<Integer> availabilityList = new ArrayList<>();
            for (String availability : availabilityArray) {
                availabilityList.add(Integer.parseInt(availability.trim()));
            }
            Worker worker;
            if (occupation.equalsIgnoreCase("Delivery Driver")) {
                DeliveryDriver DeliveryDriver = new DeliveryDriver(name, availabilityList);
            } else if (occupation.equalsIgnoreCase("Cashier")) {
                Cashier Cashier = new Cashier(name, availabilityList);
            } else {
                // Handle other occupations
                return;
            }
            scheduleBuilder.addWorker(worker);
            nameInput.clear();
            occupationInput.clear();
            availabilityInput.clear();
        });

        // Save to file button
        Button saveButton = new Button("Save to File");
        GridPane.setConstraints(saveButton, 1, 4);
        saveButton.setOnAction(e -> scheduleBuilder.writeToFile("schedule.txt"));

        grid.getChildren().addAll(nameLabel, nameInput, occupationLabel, occupationInput, availabilityLabel, availabilityInput, addButton, saveButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
