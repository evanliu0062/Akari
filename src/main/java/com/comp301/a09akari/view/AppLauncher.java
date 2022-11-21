package com.comp301.a09akari.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    stage.setTitle("Akari");

    Pane layout = new VBox();
    layout.getStyleClass().add("layout");

    Pane titleContainer = new HBox();
    Label title = new Label("Akari by Evan Liu");
    titleContainer.getStyleClass().add("title");

    Scene scene = new Scene(layout, 1000, 1000);
    scene.getStylesheets().add("style/main.css");
    stage.setScene(scene);

    stage.show();
  }
}
