package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlsView implements FXComponent {
    private final ClassicMvcController controller;
    private Model model;

    public ControlsView(ClassicMvcController controller, Model model) {
        this.controller = controller;
        this.model=model;
    }

    public Parent render(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(15);
        hbox.setStyle("-fx-background-color: #336699;");

        Button resetButn = new Button("Reset Puzzle");
        resetButn.setPrefSize(150, 40);
        resetButn.setOnAction((ActionEvent event) -> {controller.clickResetPuzzle();});

        Button randButn = new Button("Random Puzzle");
        randButn.setPrefSize(150, 40);
        randButn.setOnAction((ActionEvent event) -> {controller.clickRandPuzzle();});


        Button submitButn = new Button("Submit Answer");
        submitButn.setPrefSize(150, 40);
        hbox.getChildren().addAll(resetButn, randButn, submitButn);

        return hbox;
    }

    public void update(Model model){
        this.model=model;
    }
}
