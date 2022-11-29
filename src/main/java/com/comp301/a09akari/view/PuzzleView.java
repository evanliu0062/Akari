package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.awt.*;

public class PuzzleView implements FXComponent {
  private AlternateMvcController controller;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    GridPane grid = new GridPane();
    layout.getChildren().add(grid);
    //    grid.setPrefHeight(controller.getActivePuzzle().getWidth());
    //    grid.setPrefWidth(controller.getActivePuzzle().getHeight());

    for (int x = 0; x < controller.getActivePuzzle().getHeight(); x++) {
      for (int y = 0; y < controller.getActivePuzzle().getWidth(); y++) {
        // Wall
        if (controller.getActivePuzzle().getCellType(x, y) == CellType.WALL) {
          Rectangle wall = new Rectangle(60, 60);
          wall.setFill(Color.BLACK);
          grid.add(wall, x, y);
        }

        // Corridor
        if (controller.getActivePuzzle().getCellType(x, y) == CellType.CORRIDOR) {
          Button corridor = new Button();
          corridor.setPrefSize(60, 60);
          corridor.setStyle("-fx-background-radius: 0; -fx-border-color: gray;");
          grid.add(corridor, x, y);

          int finalX = x;
          int finalY = y;
          corridor.setOnAction((ActionEvent a) -> controller.getModel().addLamp(finalX, finalY));

          if (controller.isLit(x, y)) {
            corridor.setStyle("-fx-background-color: #FFFF00; -fx-border-color: gray;");
          }
          if (controller.isLamp(x, y)) {
            Image lightBulb = new Image("light-bulb.png");
            ImageView imageView = new ImageView(lightBulb);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            corridor.setGraphic(imageView);
            corridor.setOnAction(
                (ActionEvent a) -> controller.getModel().removeLamp(finalX, finalY));
            if (controller.getModel().isLampIllegal(x, y)) {
              corridor.setStyle("-fx-background-color: #FF0000; -fx-border-color: gray;");
            }
          }
        }

        // Clue
        if (controller.getActivePuzzle().getCellType(x, y) == CellType.CLUE) {
          String clueString = " ";
          switch (controller.getActivePuzzle().getClue(x, y)) {
            case 0:
              clueString = "0";
              break;
            case 1:
              clueString = "1";
              break;
            case 2:
              clueString = "2";
              break;
            case 3:
              clueString = "3";
              break;
            case 4:
              clueString = "4";
              break;
          }

          StackPane pane = new StackPane();
          Rectangle clueBox = new Rectangle(60, 60);
          Text clueLabel = new Text(clueString);
          clueLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 40));

          if (controller.isClueSatisfied(x, y)) {
            clueLabel.setFill(Color.LIMEGREEN);
          } else {
            clueLabel.setFill(Color.WHITE);
          }

          clueBox.setFill(Color.BLACK);

          pane.getChildren().addAll(clueBox, clueLabel);

          grid.add(pane, x, y);
        }
      }
    }
    // Puzzle Index
    Text index = new Text("Puzzle " + (controller.getModel().getActivePuzzleIndex() + 1) + " of 5");
    index.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
    layout.getChildren().add(index);

    grid.setAlignment(Pos.CENTER);
    layout.setAlignment(Pos.CENTER);
    return layout;
  }
}
