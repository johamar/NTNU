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
 * This class represents the affine page.
 * Contains a constructor for creating a new AffinePage object,
 * and methods for getting the root of the page, the left menu, the input fields,
 * the predefined transformations, and the canvas.
 * Goal: act as a view for the affine page.
 *
 * @version 1.0
 * @since 0.1
 */
public class AffinePage {
  private final AnchorPane affineRoot = new AnchorPane();
  private final ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();
  private final Text transformationLabel = new Text();
  BorderPane affineContent = new BorderPane();
  Canvas canvas = new Canvas(800, 800);
  private ChaosGame chaosGame = new ChaosGame(
      ChaosGameDescriptionFactory.sierpinskiTriangle(), 800, 800);
  private String currentTransformation;

  /**
   * Constructs a new AffinePage object with the given scene controller.
   *
   * @param sceneController the scene controller
   */
  public AffinePage(SceneController sceneController) {

    VBox leftMenu = getLeftMenu(sceneController);
    VBox canvasBox = getCanvas();


    affineContent.setLeft(leftMenu);
    affineContent.setCenter(canvasBox);


    affineRoot.getChildren().addAll(
        affineContent
    );

    AnchorPane.setTopAnchor(affineContent, 20.0);
    AnchorPane.setRightAnchor(affineContent, 20.0);
    AnchorPane.setBottomAnchor(affineContent, 20.0);
    AnchorPane.setLeftAnchor(affineContent, 20.0);
  }

  public AnchorPane getRoot() {
    return affineRoot;
  }

  /**
   * Returns the left menu of the affine page.
   *
   * @param sceneController the scene controller
   * @return the left menu of the affine page
   */
  public VBox getLeftMenu(SceneController sceneController) {
    Text transformationText = new Text("Affine Transformation 2D");
    transformationText.setFont(Font.font("", 20));

    VBox inputFields = getInputFields();
    MenuButton predefinedTransformations = getPredefinedTransformations();

    CheckBox checkBox = new CheckBox("Use custom input");
    inputFields.setVisible(false);

    checkBox.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
      if (isSelected) {
        inputFields.setVisible(true);
        predefinedTransformations.setVisible(false);
      } else {
        inputFields.setVisible(false);
        predefinedTransformations.setVisible(true);
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
      int iterationInput = Integer.parseInt(iterationsField.getText());
      CanvasObserver.update(iterationInput, canvas, chaosGame);
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
        transformationText,
        predefinedTransformations,
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
   * Returns the input fields of the affine page.
   *
   * @return the input fields of the affine page
   */
  public VBox getInputFields() {
    TextField xMinField = new TextField();
    xMinField.setPromptText("X coordinate");

    TextField yMinField = new TextField();
    yMinField.setPromptText("Y coordinate");

    TextField xMaxField = new TextField();
    xMaxField.setPromptText("X coordinate");

    TextField yMaxField = new TextField();
    yMaxField.setPromptText("Y coordinate");

    double preferredWidth = 50;

    TextField a1Field = new TextField();
    a1Field.setPrefWidth(preferredWidth);
    a1Field.setPromptText("m1");
    TextField a2Field = new TextField();
    a2Field.setPrefWidth(preferredWidth);
    a2Field.setPromptText("m2");
    TextField a3Field = new TextField();
    a3Field.setPrefWidth(preferredWidth);
    a3Field.setPromptText("m3");
    TextField a4Field = new TextField();
    a4Field.setPrefWidth(preferredWidth);
    a4Field.setPromptText("m4");
    TextField a5Field = new TextField();
    a5Field.setPrefWidth(preferredWidth);
    a5Field.setPromptText("v1");
    TextField a6Field = new TextField();
    a6Field.setPrefWidth(preferredWidth);
    a6Field.setPromptText("v2");


    HBox transform1Box = new HBox();
    Label multiplyLabel1 = new Label(" * ");
    transform1Box.getChildren().addAll(
        a1Field,
        a2Field,
        a3Field,
        a4Field,
        multiplyLabel1,
        a5Field,
        a6Field
    );
    transform1Box.setSpacing(5);

    TextField b1Field = new TextField();
    b1Field.setPrefWidth(preferredWidth);
    b1Field.setPromptText("m1");
    TextField b2Field = new TextField();
    b2Field.setPrefWidth(preferredWidth);
    b2Field.setPromptText("m2");
    TextField b3Field = new TextField();
    b3Field.setPrefWidth(preferredWidth);
    b3Field.setPromptText("m3");
    TextField b4Field = new TextField();
    b4Field.setPrefWidth(preferredWidth);
    b4Field.setPromptText("m4");
    TextField b5Field = new TextField();
    b5Field.setPrefWidth(preferredWidth);
    b5Field.setPromptText("v1");
    TextField b6Field = new TextField();
    b6Field.setPrefWidth(preferredWidth);
    b6Field.setPromptText("v2");

    HBox transform2Box = new HBox();
    Label multiplyLabel2 = new Label(" * ");
    transform2Box.getChildren().addAll(
        b1Field,
        b2Field,
        b3Field,
        b4Field,
        multiplyLabel2,
        b5Field,
        b6Field
    );
    transform2Box.setSpacing(5);

    TextField c1Field = new TextField();
    c1Field.setPrefWidth(preferredWidth);
    c1Field.setPromptText("m1");
    TextField c2Field = new TextField();
    c2Field.setPrefWidth(preferredWidth);
    c2Field.setPromptText("m2");
    TextField c3Field = new TextField();
    c3Field.setPrefWidth(preferredWidth);
    c3Field.setPromptText("m3");
    TextField c4Field = new TextField();
    c4Field.setPrefWidth(preferredWidth);
    c4Field.setPromptText("m4");
    TextField c5Field = new TextField();
    c5Field.setPrefWidth(preferredWidth);
    c5Field.setPromptText("v1");
    TextField c6Field = new TextField();
    c6Field.setPrefWidth(preferredWidth);
    c6Field.setPromptText("v2");

    HBox transform3Box = new HBox();
    Label multiplyLabel3 = new Label(" * ");
    transform3Box.getChildren().addAll(
        c1Field,
        c2Field,
        c3Field,
        c4Field,
        multiplyLabel3,
        c5Field,
        c6Field
    );
    transform3Box.setSpacing(5);

    HBox minBox = new HBox();
    minBox.getChildren().addAll(
        xMinField,
        yMinField
    );
    minBox.setSpacing(5);

    HBox maxBox = new HBox();
    maxBox.getChildren().addAll(
        xMaxField,
        yMaxField
    );
    maxBox.setSpacing(5);

    Button transformButton = new Button("Transform");
    transformButton.setOnAction(e -> {
      try {
        currentTransformation = "Custom Affine Transformation";
        transformationLabel.setText(currentTransformation);
        chaosGame = new ChaosGame(ChaosGameDescriptionFactory.affineTransformCustom(
            Double.parseDouble(xMinField.getText()),
            Double.parseDouble(yMinField.getText()),
            Double.parseDouble(xMaxField.getText()),
            Double.parseDouble(yMaxField.getText()),
            Double.parseDouble(a1Field.getText()),
            Double.parseDouble(a2Field.getText()),
            Double.parseDouble(a3Field.getText()),
            Double.parseDouble(a4Field.getText()),
            Double.parseDouble(a5Field.getText()),
            Double.parseDouble(a6Field.getText()),
            Double.parseDouble(b1Field.getText()),
            Double.parseDouble(b2Field.getText()),
            Double.parseDouble(b3Field.getText()),
            Double.parseDouble(b4Field.getText()),
            Double.parseDouble(b5Field.getText()),
            Double.parseDouble(b6Field.getText()),
            Double.parseDouble(c1Field.getText()),
            Double.parseDouble(c2Field.getText()),
            Double.parseDouble(c3Field.getText()),
            Double.parseDouble(c4Field.getText()),
            Double.parseDouble(c5Field.getText()),
            Double.parseDouble(c6Field.getText())), 800, 800);
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
    Label minLabel = new Label("Min coordinates:");
    Label maxLabel = new Label("Max coordinates:");
    Label transform1 = new Label("Transformation 1:");
    Label transform2 = new Label("Transformation 2:");
    Label transform3 = new Label("Transformation 3:");
    inputFields.getChildren().addAll(
        minLabel,
        minBox,
        maxLabel,
        maxBox,
        transform1,
        transform1Box,
        transform2,
        transform2Box,
        transform3,
        transform3Box,
        transformButton,
        saveToFileButton
    );
    inputFields.setSpacing(10);

    return inputFields;
  }

  /**
   * Returns the predefined transformations of the affine page.
   *
   * @return the predefined transformations of the affine page
   */
  public MenuButton getPredefinedTransformations() {
    MenuItem sierpinskiTriangleButton = new MenuItem("Sierpinski Triangle");
    sierpinskiTriangleButton.setOnAction(e -> {
      currentTransformation = "Sierpinski Triangle";
      transformationLabel.setText(currentTransformation);
      chaosGame = new ChaosGame(ChaosGameDescriptionFactory.sierpinskiTriangle(), 800, 800);
      CanvasObserver.clearCanvas(canvas);
    });

    MenuItem barnsleyFernButton = new MenuItem("Barnsley Fern");
    barnsleyFernButton.setOnAction(e -> {
      currentTransformation = "Barnsley Fern";
      transformationLabel.setText(currentTransformation);
      chaosGame = new ChaosGame(ChaosGameDescriptionFactory.barnsleyFern(), 800, 800);
      CanvasObserver.clearCanvas(canvas);
    });

    MenuItem kochCurveButton = new MenuItem("Koch Curve");
    kochCurveButton.setOnAction(e -> {
      currentTransformation = "Koch Curve";
      transformationLabel.setText(currentTransformation);
      chaosGame = new ChaosGame(ChaosGameDescriptionFactory.kochCurve(), 800, 800);
      CanvasObserver.clearCanvas(canvas);
    });

    MenuItem dragonCurveButton = new MenuItem("Dragon Curve");
    dragonCurveButton.setOnAction(e -> {
      currentTransformation = "Dragon Curve";
      transformationLabel.setText(currentTransformation);
      chaosGame = new ChaosGame(ChaosGameDescriptionFactory.dragonCurve(), 800, 800);
      CanvasObserver.clearCanvas(canvas);
    });
    MenuButton predefinedTransformations = new MenuButton("Predefined Transformations");
    predefinedTransformations.getItems().addAll(
        sierpinskiTriangleButton,
        barnsleyFernButton,
        kochCurveButton,
        dragonCurveButton
    );
    return predefinedTransformations;
  }

  /**
   * Returns the canvas of the affine page.
   *
   * @return the canvas of the affine page
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
