package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AllView implements FXComponent {
    private final ClassicMvcController controller;
    private Model model;

    public AllView(ClassicMvcController controller, Model model) {
        this.controller = controller;
        this.model=model;
    }

    @Override
    public Parent render() {
        BorderPane layout = new BorderPane();
        ControlsView controlsView = new ControlsView(controller, model);
        MessageView messageview = new MessageView(controller, model);
        PuzzleView puzzleView=new PuzzleView(controller, model);
        VBox vbox1=new VBox();
        GridPane grid=new GridPane();
        VBox vbox2=new VBox();
        layout.setTop(controlsView.render());
        layout.setBottom(messageview.render());
        layout.setLeft(vbox1);
        layout.setCenter(puzzleView.render());
        layout.setRight(vbox2);
        return layout;
    }

    public void update(Model model){
        this.model=model;
    }
}
