package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MessageView implements FXComponent{
    private final ClassicMvcController controller;
    private Model model;

    public MessageView(ClassicMvcController controller, Model model) {
        this.controller = controller;
        this.model=model;
    }

    public Parent render(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(20);
        hbox.setStyle("-fx-background-color: #336699;");

        Button prevButn = new Button("Previous Puzzle");
        prevButn.setPrefSize(150, 40);
        prevButn.setOnAction((ActionEvent event) -> {controller.clickPrevPuzzle();});

        Label message = new Label("");
        message.setPrefSize(150, 40);

        Button nextButn = new Button("Next Puzzle");
        nextButn.setPrefSize(150, 40);
        nextButn.setOnAction((ActionEvent event) -> {controller.clickNextPuzzle();});

        hbox.getChildren().addAll(prevButn,message, nextButn);

        return hbox;
    }

    public void update(Model model){
        this.model=model;
    }
}
