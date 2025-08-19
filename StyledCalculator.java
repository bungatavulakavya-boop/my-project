import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StyledCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Input fields
        TextField number1Field = new TextField();
        number1Field.setPromptText("Enter first number");

        TextField number2Field = new TextField();
        number2Field.setPromptText("Enter second number");

        // Style input fields
        number1Field.setStyle("-fx-font-size: 14px; -fx-background-color: #f0f8ff;");
        number2Field.setStyle("-fx-font-size: 14px; -fx-background-color: #f0f8ff;");

        // Buttons
        Button addButton = createStyledButton("Add", "#4CAF50");
        Button subtractButton = createStyledButton("Subtract", "#2196F3");
        Button multiplyButton = createStyledButton("Multiply", "#FF9800");
        Button divideButton = createStyledButton("Divide", "#f44336");

        // Result label
        Label resultLabel = new Label("Result will appear here");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333;");

        // Result label in a centered pane
        StackPane resultPane = new StackPane(resultLabel);
        resultPane.setPrefHeight(60);
        resultPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 5px;");
        resultPane.setAlignment(Pos.CENTER);

        // Button actions
        addButton.setOnAction(e -> performOperation(number1Field, number2Field, resultLabel, "add"));
        subtractButton.setOnAction(e -> performOperation(number1Field, number2Field, resultLabel, "subtract"));
        multiplyButton.setOnAction(e -> performOperation(number1Field, number2Field, resultLabel, "multiply"));
        divideButton.setOnAction(e -> performOperation(number1Field, number2Field, resultLabel, "divide"));

        // Layout for buttons
        HBox buttonBox = new HBox(15, addButton, subtractButton, multiplyButton, divideButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Main layout
        VBox layout = new VBox(20, number1Field, number2Field, buttonBox, resultPane);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 25px; -fx-background-color: #e8f0fe;");

        // Scene and Stage
        Scene scene = new Scene(layout, 450, 320);
        primaryStage.setTitle("🎯 Styled Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to style buttons
    private Button createStyledButton(String text, String bgColor) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + bgColor + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 6px;" +
                        "-fx-padding: 8px 16px;");
        return button;
    }

    // Calculation logic
    private void performOperation(TextField num1Field, TextField num2Field, Label resultLabel, String operation) {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result;

            switch (operation) {
                case "add":
                    result = num1 + num2;
                    break;
                case "subtract":
                    result = num1 - num2;
                    break;
                case "multiply":
                    result = num1 * num2;
                    break;
                case "divide":
                    if (num2 == 0) {
                        resultLabel.setText("❌ Error: Division by zero");
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    resultLabel.setText("Unknown operation");
                    return;
            }

            resultLabel.setText("✅ Result: " + result);
        } catch (NumberFormatException e) {
            resultLabel.setText("⚠️ Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
