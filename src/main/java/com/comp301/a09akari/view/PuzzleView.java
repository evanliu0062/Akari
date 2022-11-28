package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.CellType;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
    GridPane grid = new GridPane();
    grid.setPrefHeight(controller.getActivePuzzle().getWidth());
    grid.setPrefWidth(controller.getActivePuzzle().getHeight());

    for (int x = 0; x < controller.getActivePuzzle().getWidth(); x++) {
      for (int y = 0; y < controller.getActivePuzzle().getHeight(); y++) {
        // Wall
        if (controller.getActivePuzzle().getCellType(x, y) == CellType.WALL) {
          Rectangle wall = new Rectangle(85, 85);
          wall.setFill(Color.BLACK);
          grid.add(wall, x, y);
        }

        // Corridor
        if (controller.getActivePuzzle().getCellType(x, y) == CellType.CORRIDOR) {
          Button corridor = new Button();
          corridor.setPrefSize(85, 85);
          corridor.setStyle("-fx-background-radius: 0; -fx-border-color: gray;");
          grid.add(corridor, x, y);

          int finalX = x;
          int finalY = y;
          corridor.setOnAction((ActionEvent a) -> controller.clickCell(finalX, finalY));

          if (controller.isLit(x, y)) {
            corridor.setStyle("-fx-background-color: #ffffe0");
          }
          if (controller.isLamp(x, y)) {
            Circle lamp = new Circle(50);
            lamp.setFill(Color.YELLOW);
            lamp.setStroke(Color.BLACK);
            lamp.setStrokeWidth(5);
            corridor.setGraphic(lamp);
            if (controller.getModel().isLampIllegal(x, y)) {
              corridor.setStyle("-fx-background-color: #FF0000");
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
          Rectangle clueBox = new Rectangle(85, 85);
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
    grid.setAlignment(Pos.CENTER);
    return grid;
  }
}
