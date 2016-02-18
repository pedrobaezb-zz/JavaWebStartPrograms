package com.example.javawebstart.holamundo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class HolaMundoMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		final Button btn = new Button();
		btn.setText("Prueba");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			int contador=1;

			@Override
			public void handle(ActionEvent event) {
				btn.setText("Prueba " + contador);
				contador++;
			}
		});

		StackPane root = new StackPane();
		root.getChildren().add(btn);

		Scene scene = new Scene(root, 300, 250);

		primaryStage.setTitle("Hola Mundo");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}