package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;

public class View implements FXComponent {
  private AlternateMvcController controller;
  private VBox layout;
  private ControlView controlsView;
  private PuzzleView puzzleView;
  private MessageView messageView;

  public View(AlternateMvcController controller) {
    this.controller = controller;
    this.layout = new VBox();

    // Title
    HBox title = new HBox();
    Text titleText = new Text();
    titleText.setText("Akari by Evan");
    titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
    layout.getChildren().add(title);

    // Controls view
    this.controlsView = new ControlView(controller);
    layout.getChildren().add(controlsView.render());

    // Puzzle view
    this.puzzleView = new PuzzleView(controller);
    layout.getChildren().add(puzzleView.render());
    layout.setAlignment(Pos.CENTER);

    // Message view
    this.messageView = new MessageView(controller);
    layout.getChildren().add(messageView.render());
  }

  @Override
  public Parent render() {


    return layout;
  }
}
