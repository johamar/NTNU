package edu.ntnu.stud.view;

import edu.ntnu.stud.controller.CanvasObserver;
import edu.ntnu.stud.controller.SceneController;
import edu.ntnu.stud.model.ChaosGame;
import edu.ntnu.stud.model.ChaosGameDescriptionFactory;
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

/**
 * This class represents the Julia page.
 * Contains methods for creating the Julia page,
 * and getting the root of the Julia page.
 * Goal: act as a view for the Julia page.
 *
 * @version 1.0
 * @since 0.3
 */
public class JuliaPage {
  private final AnchorPane juliaRoot = new AnchorPane();
  private final ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();
  private final Text transformationLabel = new Text();
  BorderPane juliaContent = new BorderPane();
  Canvas canvas = new Canvas(800, 800);
  private ChaosGame chaosGame;
  private String currentTransformation;

  /**
   * Constructs a new JuliaPage object with the given scene controller.
   *
   * @param sceneController the scene controller to be used for the Julia page
   */
  public JuliaPage(SceneController sceneController) {
    VBox leftMenu = getLeftMenu(sceneController);
    VBox canvasBox = getCanvas();

    juliaContent.setLeft(leftMenu);
    juliaContent.setCenter(canvasBox);

    juliaRoot.getChildren().addAll(
        juliaContent
    );

    AnchorPane.setTopAnchor(juliaContent, 20.0);
    AnchorPane.setLeftAnchor(juliaContent, 20.0);
    AnchorPane.setRightAnchor(juliaContent, 20.0);
    AnchorPane.setBottomAnchor(juliaContent, 20.0);
  }

  /**
   * Returns the root of the Julia page.
   *
   * @return the root of the Julia page
   */
  public AnchorPane getRoot() {
    return juliaRoot;
  }

  /**
   * Returns the left menu of the Julia page.
   *
   * @param sceneController the scene controller to be used for the Julia page
   * @return the left menu of the Julia page
   */
  public VBox getLeftMenu(SceneController sceneController) {

    Text titleText = new Text("Julia Transformation");
    titleText.setFont(Font.font("", 20));

    VBox inputFields = getInputFields();
    MenuButton predefinedJuliaSets = getPredefinedJuliaSets();

    CheckBox checkBox = new CheckBox("Use custom input");
    inputFields.setVisible(false);

    checkBox.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
      if (isSelected) {
        inputFields.setVisible(true);
        predefinedJuliaSets.setVisible(false);
      } else {
        inputFields.setVisible(false);
        predefinedJuliaSets.setVisible(true);
      }
    });

    Button plus1000Button = new Button("+ 1 000");
    plus1000Button.setOnAction(e -> CanvasObserver.update(1000, canvas, chaosGame));

    Button plus10000Button = new Button("+ 10 000");
    plus10000Button.setOnAction(e -> CanvasObserver.update(10000, canvas, chaosGame));


    TextField iterationsField = new TextField();
    iterationsField.setPromptText("Iterations");
    Button plusIterationsButton = new Button("+");
    plusIterationsButton.setOnAction(e -> {
      int iterationsInput = Integer.parseInt(iterationsField.getText());
      CanvasObserver.update(iterationsInput, canvas, chaosGame);
    });

    iterationsField.setOnAction(e -> {
      int iterationsInput = Integer.parseInt(iterationsField.getText());
      CanvasObserver.update(iterationsInput, canvas, chaosGame);
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
    leftMenu.getChildren().addAll(
        menuButton,
        titleText,
        predefinedJuliaSets,
        checkBox,
        inputFields,
        iterationsButtonsBox,
        spacer,
        stopProgramButton
    );
    leftMenu.setSpacing(10);

    return leftMenu;
  }

  /**
   * Returns the input fields of the Julia page.
   *
   * @return the input fields of the Julia page
   */
  public VBox getInputFields() {
    TextField xMinField = new TextField();
    xMinField.setPromptText("X min");
    TextField yMinField = new TextField();
    yMinField.setPromptText("Y min");
    TextField xMaxField = new TextField();
    xMaxField.setPromptText("X max");
    TextField yMaxField = new TextField();
    yMaxField.setPromptText("Y max");

    HBox complexNumber = new HBox();
    TextField realField = new TextField();
    realField.setPromptText("Real");
    TextField imaginaryField = new TextField();
    imaginaryField.setPromptText("Imaginary");
    Label iLabel = new Label("i");
    complexNumber.getChildren().addAll(realField, imaginaryField, iLabel);
    complexNumber.setSpacing(5);

    HBox minBox = new HBox();
    HBox maxBox = new HBox();
    minBox.getChildren().addAll(xMinField, yMinField);
    minBox.setSpacing(5);
    maxBox.getChildren().addAll(xMaxField, yMaxField);
    maxBox.setSpacing(5);

    Button transformButton = new Button("Transform");
    transformButton.setOnAction(e -> {
      try {
        currentTransformation = "Custom Julia Set";
        transformationLabel.setText(currentTransformation);
        chaosGame = new ChaosGame(ChaosGameDescriptionFactory.juliaSetCustom(
            Double.parseDouble(xMinField.getText()),
            Double.parseDouble(yMinField.getText()),
            Double.parseDouble(xMaxField.getText()),
            Double.parseDouble(yMaxField.getText()),
            Double.parseDouble(realField.getText()),
            Double.parseDouble(imaginaryField.getText())
        ), 800, 800);
        CanvasObserver.clearCanvas(canvas);
      } catch (NumberFormatException ex) {
        transformationLabel.setText("Invalid input");
      }
    });

    Button saveToFileButton = new Button("Save to file");
    saveToFileButton.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      File file = fileChooser.showSaveDialog(null);
      fileHandler.writeToFile(chaosGame.getDescription(), file.getAbsolutePath());
    });

    VBox inputFields = new VBox();
    Label minLabel = new Label("Min coordinates");
    Label maxLabel = new Label("Max coordinates");
    Label complexLabel = new Label("Complex number");
    inputFields.getChildren().addAll(
        minLabel,
        minBox,
        maxLabel,
        maxBox,
        complexLabel,
        complexNumber,
        transformButton,
        saveToFileButton
    );
    inputFields.setSpacing(10);

    return inputFields;
  }

  /**
   * Returns the predefined Julia sets of the Julia page.
   *
   * @return the predefined Julia sets of the Julia page
   */
  public MenuButton getPredefinedJuliaSets() {
    MenuItem preJuliaButton1 = new MenuItem("Pre Julia Set 1");
    preJuliaButton1.setOnAction(e -> {
      currentTransformation = "Julia Set 1";
      transformationLabel.setText(currentTransformation);
      chaosGame = new ChaosGame(ChaosGameDescriptionFactory.juliaSet1(), 800, 800);
      CanvasObserver.clearCanvas(canvas);
    });
    MenuItem preJuliaButton2 = new MenuItem("Pre Julia Set 2");
    preJuliaButton2.setOnAction(e -> {
      currentTransformation = "Julia Set 2";
      transformationLabel.setText(currentTransformation);
      chaosGame = new ChaosGame(ChaosGameDescriptionFactory.juliaSet2(), 800, 800);
      CanvasObserver.clearCanvas(canvas);
    });
    MenuItem preJuliaButton3 = new MenuItem("Pre Julia Set 3");
    preJuliaButton3.setOnAction(e -> {
      currentTransformation = "Julia Set 3";
      transformationLabel.setText(currentTransformation);
      chaosGame = new ChaosGame(ChaosGameDescriptionFactory.juliaSet3(), 800, 800);
      CanvasObserver.clearCanvas(canvas);
    });
    MenuItem preJuliaButton4 = new MenuItem("Pre Julia Set 4");
    preJuliaButton4.setOnAction(e -> {
      currentTransformation = "Julia Set 4";
      transformationLabel.setText(currentTransformation);
      chaosGame = new ChaosGame(ChaosGameDescriptionFactory.juliaSet4(), 800, 800);
      CanvasObserver.clearCanvas(canvas);
    });
    MenuItem preJuliaButton5 = new MenuItem("Pre Julia Set 5");
    preJuliaButton5.setOnAction(e -> {
      currentTransformation = "Julia Set 5";
      transformationLabel.setText(currentTransformation);
      chaosGame = new ChaosGame(ChaosGameDescriptionFactory.juliaSet5(), 800, 800);
      CanvasObserver.clearCanvas(canvas);
    });
    MenuButton predefinedJuliaSets = new MenuButton("Predefined Julia Sets");
    predefinedJuliaSets.getItems().addAll(
        preJuliaButton1,
        preJuliaButton2,
        preJuliaButton3,
        preJuliaButton4,
        preJuliaButton5
    );
    return predefinedJuliaSets;
  }

  /**
   * Returns the canvas of the Julia page.
   *
   * @return the canvas of the Julia page
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
