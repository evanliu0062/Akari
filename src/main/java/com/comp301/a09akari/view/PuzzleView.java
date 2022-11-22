package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.CellType;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;

public class PuzzleView implements FXComponent {
  private AlternateMvcController controller;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane grid = new GridPane();
    grid.setHgap(controller.getActivePuzzle().getWidth());
    grid.setVgap(controller.getActivePuzzle().getHeight());

    for (int x = 0; x < controller.getActivePuzzle().getWidth(); x++) {
      for (int y = 0; y < controller.getActivePuzzle().getHeight(); y++) {
        if (controller.getActivePuzzle().getCellType(x, y) == CellType.WALL) {
          Rectangle wall = new Rectangle(85, 85);
          wall.setFill(Color.BLACK);
          grid.add(wall, x, y);
        }

        if (controller.getActivePuzzle().getCellType(x, y) == CellType.CORRIDOR) {
          Rectangle corridor = new Rectangle(85, 85);
          corridor.setFill(Color.WHITE);
          grid.add(corridor, x, y);
        }

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
          clueLabel.setFill(Color.WHITE);
          clueLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 40));

          clueBox.setFill(Color.BLACK);

          pane.getChildren().addAll(clueBox, clueLabel);

          grid.add(pane, x, y);
        }
      }
    }
    return grid;
  }
}
