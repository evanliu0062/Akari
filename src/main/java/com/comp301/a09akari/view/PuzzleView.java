package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;

public class PuzzleView implements FXComponent{
    private AlternateMvcController controller;

    public PuzzleView(AlternateMvcController controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        return null;
    }
}
