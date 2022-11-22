package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MessageView implements FXComponent {
  private AlternateMvcController controller;

  public MessageView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();

    Label solvedMessage = new Label();
    solvedMessage.setText("Puzzle Solved!");

    Label continueMessage = new Label();
    continueMessage.setText("Select an Option to continue.");

    layout.getChildren().add(solvedMessage);
    layout.getChildren().add(continueMessage);

    return layout;
  }
}
