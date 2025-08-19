import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StyledCalculator2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Input fields
        TextField number1Field = new TextField();
        number1Field.setPromptText("Enter first number");

        TextField number2Field = new TextField();
        number2Field.setPromptText("Enter second number");

        // AI input field
        TextField aiInputField = new TextField();
        aiInputField.setPromptText("Ask me: e.g., What is 10 plus 5?");
        aiInputField.setStyle("-fx-font-size: 14px; -fx-background-color: #fffde7;");

        // Style input fields
        number1Field.setStyle("-fx-font-size: 14px; -fx-background-color: #f0f8ff;");
        number2Field.setStyle("-fx-font-size: 14px; -fx-background-color: #f0f8ff;");

        // Buttons
        Button addButton = createStyledButton("Add", "#4CAF50");
        Button subtractButton = createStyledButton("Subtract", "#2196F3");
        Button multiplyButton = createStyledButton("Multiply", "#FF9800");
        Button divideButton = createStyledButton("Divide", "#f44336");
        Button smartButton = createStyledButton("Smart Calculate", "#9C27B0");

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
        smartButton.setOnAction(e -> performSmartCalculation(aiInputField.getText(), resultLabel));

        // Layout for buttons
        HBox buttonBox = new HBox(15, addButton, subtractButton, multiplyButton, divideButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Main layout
        VBox layout = new VBox(15, number1Field, number2Field, buttonBox, aiInputField, smartButton, resultPane);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 25px; -fx-background-color: #e8f0fe;");

        // Scene and Stage
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setTitle("🤖 Styled Calculator with AI Feature");
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

    // Basic AI feature - Natural Language Processing
    private void performSmartCalculation(String input, Label resultLabel) {
        input = input.toLowerCase();

        double num1 = 0;
        double num2 = 0;
        String operation = "";

        try {
            // Replace words with symbols for easier parsing
            input = input.replaceAll("plus", "+")
                         .replaceAll("add", "+")
                         .replaceAll("minus", "-")
                         .replaceAll("subtract", "-")
                         .replaceAll("times", "*")
                         .replaceAll("multiplied by", "*")
                         .replaceAll("multiply", "*")
                         .replaceAll("divided by", "/")
                         .replaceAll("divide", "/");

            // Extract numbers and operator
            if (input.contains("+")) {
                operation = "+";
            } else if (input.contains("-")) {
                operation = "-";
            } else if (input.contains("*")) {
                operation = "*";
            } else if (input.contains("/")) {
                operation = "/";
            } else {
                resultLabel.setText("🤖 Sorry, I didn’t understand.");
                return;
            }

            String[] parts = input.split("[+\\-*/]");
            if (parts.length != 2) {
                resultLabel.setText("⚠️ Could not parse two numbers.");
                return;
            }

            num1 = Double.parseDouble(parts[0].replaceAll("[^\\d.]", "").trim());
            num2 = Double.parseDouble(parts[1].replaceAll("[^\\d.]", "").trim());

            double result;
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        resultLabel.setText("❌ Error: Division by zero");
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    resultLabel.setText("🤖 Unknown operation.");
                    return;
            }

            resultLabel.setText("🤖 Smart Result: " + result);
        } catch (Exception e) {
            resultLabel.setText("⚠️ AI couldn't parse your question.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}