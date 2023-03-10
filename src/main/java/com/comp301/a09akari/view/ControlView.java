package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ControlView implements FXComponent {
  private AlternateMvcController controller;

  public ControlView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();

    Button nextPuzzleButton = new Button("Next");
    nextPuzzleButton.setStyle("-fx-background-radius: 0; -fx-border-color: gray;");
    nextPuzzleButton.setPrefSize(150, 50);
    nextPuzzleButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 20));
    nextPuzzleButton.setOnAction((ActionEvent a) -> controller.clickNextPuzzle());

    Button prevPuzzleButton = new Button("Previous");
    prevPuzzleButton.setStyle("-fx-background-radius: 0; -fx-border-color: gray;");
    prevPuzzleButton.setPrefSize(150, 50);
    prevPuzzleButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 20));
    prevPuzzleButton.setOnAction((ActionEvent b) -> controller.clickPrevPuzzle());

    Button shufflePuzzleButton = new Button("Shuffle");
    shufflePuzzleButton.setStyle("-fx-background-radius: 0; -fx-border-color: gray;");
    shufflePuzzleButton.setPrefSize(150, 50);
    shufflePuzzleButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 20));
    shufflePuzzleButton.setOnAction((ActionEvent c) -> controller.clickRandPuzzle());

    Button resetPuzzleButton = new Button("Reset");
    resetPuzzleButton.setStyle("-fx-background-radius: 0; -fx-border-color: gray;");
    resetPuzzleButton.setPrefSize(150, 50);
    resetPuzzleButton.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 20));
    resetPuzzleButton.setOnAction((ActionEvent d) -> controller.clickResetPuzzle());

    layout
        .getChildren()
        .addAll(nextPuzzleButton, prevPuzzleButton, shufflePuzzleButton, resetPuzzleButton);
    layout.setAlignment(Pos.CENTER);

    return layout;
  }
}
