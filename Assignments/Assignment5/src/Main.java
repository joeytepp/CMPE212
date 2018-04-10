import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
// Importing the necessary packages

public class Main extends Application {
	public static void main(String args[]) {
		launch(args);
	} // main method

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		ArrayList<LineItem> db = new ArrayList<>(); // An arrayList that will act as a database for the LineItems in the
													// current order
		BorderPane rootPane = new BorderPane();
		GridPane gridRoot = new GridPane(); // Panes for the GUI
		GridPane.setValignment(gridRoot, VPos.TOP);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		gridRoot.getColumnConstraints().add(column1); // Setting alignments
		final Button clearPizza = new Button("Clear Pizza");
		final Button clearOrder = new Button("Clear Order"); // Buttons to clear the current pizza and the whole order
		final HBox buttonBox = new HBox();
		final ComboBox<String> sizeBox = new ComboBox<>();
		final ComboBox<String> cheeseBox = new ComboBox<>();
		final ComboBox<String> shroomBox = new ComboBox<>();
		final ComboBox<String> pepBox = new ComboBox<>(); // ComboBox objects for selecting customizing pizza
		final TextField quantityField = new TextField();
		final TextArea orderField = new TextArea("My Order:"); // TextField for the order
		final Button orderButton = new Button("Add to Order"); // A button for adding a new LineItem to an order
		DecimalFormat dFormat = new DecimalFormat("##.00"); // A decimal formatter for displaying prices
		sizeBox.getItems().addAll("Small", "Medium", "Large");
		cheeseBox.getItems().addAll("Single", "Double", "Triple");
		shroomBox.getItems().addAll("None", "Single", "Double"); // Setting the options for the ComboBox objects
		buttonBox.getChildren().addAll(clearPizza, clearOrder);
		gridRoot.add(buttonBox, 0, 0); // Adding the clear buttons to the top of the GridPane
		gridRoot.add(new Label("Add a Pizza"), 0, 1);
		gridRoot.add(new Label("Enter size:"), 0, 2);
		gridRoot.add(sizeBox, 1, 2); // Adding initial labels and a ComboBox to the GridPane
		orderField.setMinHeight(400);
		orderField.setMinWidth(500);
		orderField.setEditable(false); // Setting the size of the TextArea

		sizeBox.setOnAction((event) -> {
			if (gridRoot.getRowCount() == 3) {
				gridRoot.add(new Label("Enter cheese:"), 0, 3);
				gridRoot.add(cheeseBox, 1, 3);
			}
			try {
				Pizza pizza = new Pizza(sizeBox.getValue(), cheeseBox.getValue(), shroomBox.getValue(),
						pepBox.getValue());
				double cost = new LineItem(Integer.parseInt(quantityField.getText()), pizza).getCost();
				orderButton.setText("Add to Order ($" + dFormat.format(cost) + ")");
			} catch (IllegalPizza | NumberFormatException exc) {
			}
		}); // Adding an event handler to the size selection ComboBox

		cheeseBox.setOnAction((event) -> {
			if (gridRoot.getRowCount() == 4) {
				gridRoot.add(new Label("Enter mushrooms:"), 0, 4);
				gridRoot.add(shroomBox, 1, 4);
			}

			double cost = 0.0;
			try {
				Pizza pizza = new Pizza(sizeBox.getValue(), cheeseBox.getValue(), shroomBox.getValue(),
						pepBox.getValue());
				cost = new LineItem(Integer.parseInt(quantityField.getText()), pizza).getCost();
			} catch (IllegalPizza | NumberFormatException exc) {

			}
			orderButton.setText("Add to Order ($" + dFormat.format(cost) + ")");
		}); // Adding an event handler to the cheese selection ComboBox

		shroomBox.setOnAction((event) -> {
			if (gridRoot.getRowCount() == 5) {
				gridRoot.add(new Label("Enter pepperoni:"), 0, 5);
				gridRoot.add(pepBox, 1, 5);
			}
			ArrayList<String> items = new ArrayList<>();
			String value = pepBox.getValue();
			if (shroomBox.getValue().equals("None"))
				items.add("None");
			items.add("Single");
			items.add("Double");
			if (!pepBox.getItems().toArray().equals(items.toArray())) {
				pepBox.getItems().clear();
				pepBox.getItems().addAll(items);
				if (isIn(value, items))
					pepBox.setValue(value);
				else {
					if (gridRoot.getRowCount() == 8)
						gridRoot.getChildren().remove(orderButton);
				}
			}
			double cost = 0.0;
			try {
				Pizza pizza = new Pizza(sizeBox.getValue(), cheeseBox.getValue(), shroomBox.getValue(),
						pepBox.getValue());
				cost = new LineItem(Integer.parseInt(quantityField.getText()), pizza).getCost();
			} catch (IllegalPizza | NumberFormatException exc) {

			}
			orderButton.setText("Add to Order ($" + dFormat.format(cost) + ")");
		}); // Adding an event handler to the mushroom selection ComboBox

		pepBox.setOnAction((event) -> {
			if (gridRoot.getRowCount() == 6) {
				gridRoot.add(new Label("Enter quantity:"), 0, 6);
				gridRoot.add(quantityField, 1, 6);
			} else {

				String text = quantityField.getText();
				try {
					int val = Integer.parseInt(text);
					if (val <= 100 && gridRoot.getRowCount() == 7) {
						gridRoot.add(orderButton, 0, 7);
					}
				} catch (NumberFormatException e) {

				}
			}
			try {
				if (gridRoot.getRowCount() >= 6) {
					Pizza pizza = new Pizza(sizeBox.getValue(), cheeseBox.getValue(), shroomBox.getValue(),
							pepBox.getValue());
					double cost = new LineItem(Integer.parseInt(quantityField.getText()), pizza).getCost();
					orderButton.setText("Add to Order ($" + dFormat.format(cost) + ")");
				}
			} catch (IllegalPizza | NumberFormatException exc) {

			}

		}); // Adding an event handler to the pepperoni selection box

		quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				Pizza pizza = new Pizza(sizeBox.getValue(), cheeseBox.getValue(), shroomBox.getValue(),
						pepBox.getValue());
				double cost = new LineItem(Integer.parseInt(quantityField.getText()), pizza).getCost();
				orderButton.setText("Add to Order ($" + dFormat.format(cost) + ")");
			} catch (IllegalPizza | NumberFormatException exc) {
			}

			if (!newValue.matches("\\d*")) {
				quantityField.setText(newValue.replaceAll("[^\\d]", ""));
			}
			String text = quantityField.getText();
			if (!text.equals("")) {
				int val = Integer.parseInt(text);
				if (val <= 100 && gridRoot.getRowCount() == 7) {
					gridRoot.add(orderButton, 0, 7);
				}
				if (val > 100 || pepBox.getValue() == null)
					gridRoot.getChildren().remove(orderButton);
			} else {
				if (gridRoot.getRowCount() == 8) {
					gridRoot.getChildren().remove(orderButton);
				}
			}
		}); // Adding an event handler to the quantity selection text box

		orderButton.setOnAction((event) -> {
			try {
				if (gridRoot.getRowCount() >= 6) {
					Pizza pizza = new Pizza(sizeBox.getValue(), cheeseBox.getValue(), shroomBox.getValue(),
							pepBox.getValue());
					LineItem lineItem = new LineItem(Integer.parseInt(quantityField.getText()), pizza);
					db.add(lineItem);
					String text = orderField.getText();
					if (text.contains("\n")) {
						String[] split = text.split("\n");
						text = text.replace("\n" + split[split.length - 1], "");
					}
					text += "\n" + lineItem;
					double cost = 0;
					for (LineItem line : db) {
						cost += line.getCost();
					}
					text += "\n Cost: $" + dFormat.format(cost);
					orderField.setText(text);
				}
			} catch (IllegalPizza | NumberFormatException exc) {

			}
		}); // Adding an event handler to the "Add to Order" button

		clearOrder.setOnAction((event) -> {
			orderField.setText("My Order:");
			db.clear();
		}); // Adding an event handler to the "Clear Order" button

		clearPizza.setOnAction((event) -> {
			for (Object node : gridRoot.getChildren().toArray()) {
				if (node instanceof ComboBox) {
					ComboBox<String> comboNode = (ComboBox<String>) node;
					comboNode.setValue("");
				}
			}
			quantityField.setText("");
			sizeBox.setValue("");
			gridRoot.getChildren().clear();
			gridRoot.add(buttonBox, 0, 0);
			gridRoot.add(new Label("Add a Pizza"), 0, 1);
			gridRoot.add(new Label("Enter size:"), 0, 2);
			gridRoot.add(sizeBox, 1, 2);
		}); // Adding an event handler to the "Clear Pizza" button

		rootPane.setCenter(gridRoot);
		rootPane.setTop(new Label("PIZZA ORDER SYSTEM"));
		rootPane.setRight(orderField); // Adding the necessary panes to the root
		Scene scene = new Scene(rootPane, 1000, 500); // Creating a scene with the root
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pizza Order System");
		primaryStage.show(); // Adding style and formatting to the scene

		Image pizzaIcon = new Image("Pizza-icon.png");
		scene.setCursor(new ImageCursor(pizzaIcon)); // Setting the cursor icon

	}

	// A method to see if a String is present in an arrayList of Strings
	private boolean isIn(String key, ArrayList<String> list) {
		for (String i : list) {
			if (i.equals(key))
				return true;
		}
		return false;
	} // isIn method

}
