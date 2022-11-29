package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    Puzzle puzzle1 = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
    Puzzle puzzle2 = new PuzzleImpl(SamplePuzzles.PUZZLE_02);
    Puzzle puzzle3 = new PuzzleImpl(SamplePuzzles.PUZZLE_03);
    Puzzle puzzle4 = new PuzzleImpl(SamplePuzzles.PUZZLE_04);
    Puzzle puzzle5 = new PuzzleImpl(SamplePuzzles.PUZZLE_05);

    PuzzleLibrary library = new PuzzleLibraryImpl();
    library.addPuzzle(puzzle1);
    library.addPuzzle(puzzle2);
    library.addPuzzle(puzzle3);
    library.addPuzzle(puzzle4);
    library.addPuzzle(puzzle5);

    Model model = new ModelImpl(library);
    AlternateMvcController controller = new ControllerImpl(model);
    MessageView messageView = new MessageView(controller);
    ControlView controlView = new ControlView(controller);
    PuzzleView puzzleView = new PuzzleView(controller);

    // Title
    Text titleText = new Text();
    titleText.setText("Akari by Evan");
    titleText.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 50));

    VBox gui = new VBox(titleText, controlView.render(), puzzleView.render(), messageView.render());
    gui.setSpacing(0);
    gui.setAlignment(Pos.CENTER);

    Scene scene = new Scene(gui);
    stage.setScene(scene);
    stage.show();
    ModelObserver observer =
        (Model model1) -> {
          gui.getChildren().clear();
          gui.getChildren().add(titleText);
          gui.getChildren().add(controlView.render());
          gui.getChildren().add(puzzleView.render());
          gui.getChildren().add(messageView.render());
          stage.sizeToScene();
        };
    model.addObserver(observer);
  }
}
