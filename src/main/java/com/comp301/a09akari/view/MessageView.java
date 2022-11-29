package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MessageView implements FXComponent {
  private AlternateMvcController controller;

  public MessageView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    StackPane messageBox = new StackPane();
    if (controller.isSolved()) {
      VBox layout = new VBox();
      layout.setSpacing(0);
      Rectangle textBox1 = new Rectangle(410, 80);
      textBox1.setFill(Color.GRAY);
      Rectangle textBox2 = new Rectangle(400, 70);
      textBox2.setFill(Color.LIGHTGREEN);
      Text solvedMessage = new Text("Puzzle Solved!");
      solvedMessage.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 40));
      Text continueMessage = new Text("Select an Option to continue.");
      continueMessage.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 20));

      layout.getChildren().add(solvedMessage);
      layout.getChildren().add(continueMessage);
      layout.setAlignment(Pos.CENTER);

      messageBox.getChildren().add(textBox1);
      messageBox.getChildren().add(textBox2);
      messageBox.getChildren().add(layout);
      messageBox.setAlignment(Pos.CENTER);
    }
    return messageBox;
  }
}
