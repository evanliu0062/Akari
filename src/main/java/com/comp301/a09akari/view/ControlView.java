package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ControlView implements FXComponent {
  private AlternateMvcController controller;

  public ControlView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();

    Button nextPuzzleButton = new Button("Next");
    nextPuzzleButton.setPrefSize(100, 30);
    nextPuzzleButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickNextPuzzle();
          }
        });

    Button prevPuzzleButton = new Button("Previous");
    prevPuzzleButton.setPrefSize(100, 30);
    prevPuzzleButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickPrevPuzzle();
          }
        });

    Button shufflePuzzleButton = new Button("Shuffle");
    shufflePuzzleButton.setPrefSize(100, 30);
    shufflePuzzleButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickRandPuzzle();
          }
        });

    Button resetPuzzleButton = new Button("Reset");
    resetPuzzleButton.setPrefSize(100, 30);
    resetPuzzleButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clickResetPuzzle();
          }
        });

    layout.getChildren().add(nextPuzzleButton);
    layout.getChildren().add(prevPuzzleButton);
    layout.getChildren().add(shufflePuzzleButton);
    layout.getChildren().add(resetPuzzleButton);

    return layout;
  }
}
