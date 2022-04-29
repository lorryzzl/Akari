package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

public class PuzzleView implements FXComponent{
    private final ClassicMvcController controller;
    private Model model;

    public PuzzleView(ClassicMvcController controller, Model model) {
        this.controller = controller;
        this.model=model;
    }

    @Override
    public Parent render(){
        GridPane grid=new GridPane();
        Puzzle puzzle=model.getActivePuzzle();
        //Setting size for the pane
        grid.setPrefSize(500, 500);
        //Setting the padding
        grid.setPadding(new Insets(30, 30, 30, 30));

        //Setting the vertical and horizontal gaps between the columns
        grid.setVgap(0);
        grid.setHgap(0);
        grid.setAlignment(Pos.CENTER);
        int height= puzzle.getHeight();
        int width= puzzle.getWidth();
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++){
                if (puzzle.getCellType(i, j)== CellType.CLUE ){
                    int a=puzzle.getClue(i ,j);
                    Label clueLabel=new Label("   "+String.valueOf(a));
                    clueLabel.setAlignment(Pos.CENTER);
                    clueLabel.setFont(new Font("Arial", 25));
                    grid.add(clueLabel, i, j);
                } else if (puzzle.getCellType(i, j)== CellType.WALL){
                    Label wallLabel=new Label("");
                    grid.add(wallLabel, i, j);
                }
                else {
                    Button butn=new Button("");
                    final int i1=i;
                    final int j1=j;
                    grid.add(butn, i, j);
                    butn.setStyle("-fx-background-color: green; -fx-border-width: 1px; -fx-border-color: black;");
                    butn.setPrefSize(50,50);
                    butn.setOnAction((ActionEvent event) -> {
                        controller.clickCell(i1, j1);
                        if (model.isLamp(i1 ,j1)){
                            butn.setStyle("-fx-background-color: yellow; -fx-border-width: 1px; -fx-border-color: black;");
                        } else {
                            butn.setStyle("-fx-background-color: green; -fx-border-width: 1px; -fx-border-color: black;");
                        }
                        for (int a=0; a<height; a++){
                            for (int b=0; a<width; b++){
                                if (model.isLit(a, b)){
                                    Node result = null;
                                    ObservableList<Node> childrens = grid.getChildren();
                                    for (Node node : childrens) {
                                        if(grid.getRowIndex(node) == a && grid.getColumnIndex(node) == b) {
                                            result = node;
                                            break;
                                        }
                                    }
                                    result.setStyle("-fx-background-color: yellow; -fx-border-width: 1px; -fx-border-color: black;");
                                }
                            }
                        }
                        update(model);
                    });
                }

            }
        }
        return grid;
    }

    public void update(Model model){
        this.model=model;
    }
}
