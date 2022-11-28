package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;

public class View implements FXComponent {
  private AlternateMvcController controller;
  private VBox layout;
  private VBox puzzleAndButtons;
  private StackPane messageAndPuzzle;
  private ControlView controlsView;
  private PuzzleView puzzleView;
  private MessageView messageView;

  public View(AlternateMvcController controller) {
    this.controller = controller;
    this.layout = new VBox();
    this.puzzleAndButtons = new VBox();
    this.messageAndPuzzle = new StackPane();

    puzzleAndButtons.setSpacing(5);
    layout.setSpacing(10);

    // Controls view
    this.controlsView = new ControlView(controller);

    // Puzzle view
    this.puzzleView = new PuzzleView(controller);

    // Message view
    this.messageView = new MessageView(controller);
  }

  @Override
  public Parent render() {
    // Title
    Text titleText = new Text();
    titleText.setText("Akari by Evan");
    titleText.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 50));

    // Puzzle Index
    Text index = new Text("Puzzle " + (controller.getModel().getActivePuzzleIndex()+1) + " of 5");
    index.setFont(Font.font("Verdana", FontWeight.BOLD, 30));


    layout.getChildren().add(titleText);

    puzzleAndButtons.getChildren().add(controlsView.render());

    messageAndPuzzle.getChildren().add(puzzleView.render());
    messageAndPuzzle.getChildren().add(messageView.render());

    puzzleAndButtons.getChildren().add(messageAndPuzzle);
    layout.getChildren().add(puzzleAndButtons);
    layout.getChildren().add(index);

    layout.setAlignment(Pos.CENTER);

    return layout;
  }
}
