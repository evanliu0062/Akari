package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
    private AlternateMvcController controller;

    public View(AlternateMvcController controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        VBox layout = new VBox();

        ControlView control_view = new ControlView(controller);

        layout.getChildren().add(control_view.render());
        return layout;
    }
}
