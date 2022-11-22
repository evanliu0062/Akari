package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.*;

public class PuzzleView implements FXComponent {
  private AlternateMvcController controller;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();

    // Controls view
    ControlView controlsView = new ControlView(controller);
    layout.getChildren().add(controlsView.render());

    // Puzzle view
    PuzzleView puzzleView = new PuzzleView(controller);
    layout.getChildren().add(puzzleView.render());
    layout.setAlignment(Pos.CENTER);

    // Message view
    MessageView messageView = new MessageView(controller);
    layout.getChildren().add(messageView.render());

    return layout;
  }
}