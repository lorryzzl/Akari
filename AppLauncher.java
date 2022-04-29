package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelImpl;
import com.comp301.a09akari.model.PuzzleImpl;
import com.comp301.a09akari.model.PuzzleLibraryImpl;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.View;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    // Model
    SamplePuzzles sp = new SamplePuzzles();
    PuzzleLibraryImpl library = new PuzzleLibraryImpl();
    library.addPuzzle(new PuzzleImpl(sp.PUZZLE_01));
    library.addPuzzle(new PuzzleImpl(sp.PUZZLE_02));
    library.addPuzzle(new PuzzleImpl(sp.PUZZLE_03));
    library.addPuzzle(new PuzzleImpl(sp.PUZZLE_04));
    library.addPuzzle(new PuzzleImpl(sp.PUZZLE_05));
    Model model = new ModelImpl(library);

    // Controller
    ClassicMvcController controller = new ControllerImpl(model);
    AllView view = new AllView(controller, model);
    Scene scene = new Scene(view.render());
    scene.getStylesheets().add("main.css");

    model.addObserver((Model m) -> {
      scene.setRoot(view.render());
      stage.sizeToScene();
      stage.setScene(scene);
    });

    stage.setScene(scene);
    stage.setTitle("Akari");
    stage.show();
  }
}
