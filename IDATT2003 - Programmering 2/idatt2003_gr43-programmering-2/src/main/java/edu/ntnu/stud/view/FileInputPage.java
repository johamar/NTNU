package edu.ntnu.stud.view;

import edu.ntnu.stud.controller.CanvasObserver;
import edu.ntnu.stud.controller.SceneController;
import edu.ntnu.stud.model.ChaosGame;
import edu.ntnu.stud.model.ChaosGameDescription;
import edu.ntnu.stud.model.ChaosGameFileHandler;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Path;

/**
 * This class represents the file input page.
 * Contains methods for creating the file input page,
 * and getting the root of the file input page.
 * Goal: act as a view for the file input page.
 *
 * @version 1.0
 * @since 0.3
 */
public class FileInputPage {
  private final AnchorPane fileInputRoot = new AnchorPane();
  private final ChaosGameFileHandler fileHandler;
  private final Text transformationLabel = new Text();
  BorderPane fileInputContent = new BorderPane();
  ChaosGame chaosGame;
  private Canvas canvas;
  private int iterations = 1000000;
  private ChaosGameDescription description;
  private String currentTransformation;

  /**
   * Constructs a new FileInputPage object with the given scene controller.
   *
   * @param sceneController the scene controller to be used for the file input page
   */
  public FileInputPage(SceneController sceneController) {
    fileHandler = new ChaosGameFileHandler();

    VBox leftMenu = getLeftMenu(sceneController);
    VBox centerMenu = getCanvas();

    fileInputContent.setLeft(leftMenu);
    fileInputContent.setCenter(centerMenu);

    fileInputRoot.getChildren().addAll(
        fileInputContent
    );

    AnchorPane.setTopAnchor(fileInputContent, 20.0);
    AnchorPane.setLeftAnchor(fileInputContent, 20.0);
    AnchorPane.setRightAnchor(fileInputContent, 20.0);
    AnchorPane.setBottomAnchor(fileInputContent, 20.0);
  }

  /**
   * Returns the root of the file input page.
   *
   * @return the root of the file input page
   */
  public AnchorPane getRoot() {
    return fileInputRoot;
  }

  /**
   * Returns the left menu of the file input page.
   *
   * @param sceneController the scene controller to be used for the file input page
   * @return the left menu of the file input page
   */
  public VBox getLeftMenu(SceneController sceneController) {
    Text titleText = new Text("File Input");
    titleText.setFont(Font.font("", 20));

    HBox filePathBox = new HBox();

    TextField filePathField = new TextField();
    filePathField.setPromptText("Paste file path here");

    FileChooser fileChooser = new FileChooser();
    Button fileChooserButton = new Button("Find file");
    fileChooserButton.setOnAction(e -> {
      File file = fileChooser.showOpenDialog(null);
      if (file != null) {
        filePathField.setText(file.getAbsolutePath());
      }
    });
    filePathBox.getChildren().addAll(filePathField, fileChooserButton);

    Button runFileButton = new Button("Transform file");
    runFileButton.setOnAction(e -> {
      String filePathFieldText = filePathField.getText();
      Path path = Path.of(filePathFieldText);
      Path fileName = path.getFileName();

      currentTransformation = fileName.toString();
      transformationLabel.setText(currentTransformation);
      String filePath = filePathField.getText();
      description = fileHandler.readFromFile(filePath);
      chaosGame = new ChaosGame(description, 800, 800);
      CanvasObserver.update(0, canvas, chaosGame);
      CanvasObserver.clearCanvas(canvas);
    });

    Button plus1000Button = new Button("+ 1 000");
    plus1000Button.setOnAction(e -> CanvasObserver.update(1000, canvas, chaosGame));

    Button plus10000Button = new Button("+ 10 000");
    plus10000Button.setOnAction(e -> CanvasObserver.update(10000, canvas, chaosGame));

    TextField iterationsField = new TextField();
    iterationsField.setPromptText("Iterations");
    Button plusIterationsButton = new Button("+");
    plusIterationsButton.setOnAction(e -> {
      iterations = Integer.parseInt(iterationsField.getText());
      CanvasObserver.update(iterations, canvas, chaosGame);
    });
    iterationsField.setOnAction(e -> {
      iterations = Integer.parseInt(iterationsField.getText());
      CanvasObserver.clearCanvas(canvas);
      CanvasObserver.update(iterations, canvas, chaosGame);
    });
    iterationsField.setMaxWidth(70);
    HBox iterationsBox = new HBox();
    iterationsBox.getChildren().addAll(plusIterationsButton, iterationsField);

    HBox iterationsButtonsBox = new HBox();
    iterationsButtonsBox.getChildren().addAll(plus1000Button, plus10000Button, iterationsBox);
    iterationsButtonsBox.setSpacing(10);

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText("Confirm Your Action");
    alert.setContentText("Are you sure you want to proceed?");
    ButtonType buttonTypeYes = new ButtonType("Yes");
    ButtonType buttonTypeNo = new ButtonType("No");
    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

    Button stopProgramButton = new Button("Stop program");
    stopProgramButton.setOnAction(e -> alert.showAndWait().ifPresent(response -> {
      if (response == buttonTypeYes) {
        Platform.exit();
      } else if (response == buttonTypeNo) {
        alert.close();
      }
    }));

    Region spacer = new Region();
    VBox.setVgrow(spacer, Priority.ALWAYS);

    VBox leftMenu = new VBox();
    PageMenu pageMenu = new PageMenu(sceneController);
    MenuButton menuButton = pageMenu.getMenuButton();
    Label filePathLabel = new Label("File path:");
    leftMenu.getChildren().addAll(
        menuButton,
        titleText,
        filePathLabel,
        filePathBox,
        runFileButton,
        iterationsButtonsBox,
        spacer,
        stopProgramButton
    );
    leftMenu.setSpacing(10);

    return leftMenu;
  }

  /**
   * Returns the canvas of the file input page.
   *
   * @return the canvas of the file input page
   */
  public VBox getCanvas() {
    int canvasWidth = 800;
    int canvasHeight = 800;
    VBox canvasBox = new VBox();
    canvas = new Canvas(canvasWidth, canvasHeight);

    transformationLabel.setFont(Font.font("", 20));


    canvasBox.getChildren().addAll(
        transformationLabel,
        canvas
    );

    canvasBox.setAlignment(Pos.CENTER);
    CanvasObserver.clearCanvas(canvas);

    return canvasBox;
  }
}