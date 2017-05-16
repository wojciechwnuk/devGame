import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(getMainScene());
        primaryStage.setResizable(false);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(800);
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }

    private final Background darkBlueBackground = new Background(new BackgroundFill(Color.web("#003040"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background greyBackground = new Background(new BackgroundFill(Color.web("#16333d"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background blueBackground = new Background(new BackgroundFill(Color.web("#006e93"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background brightBackground = new Background(new BackgroundFill(Color.web("#fff9f4"), CornerRadii.EMPTY, Insets.EMPTY));

    private Scene getMainScene() {
        DevObjects devObjects = new DevObjects();

        BorderPane mainBorderPane = new BorderPane();
        //-------------------------------------------------------------------------TOP
        HBox topHbox = new HBox();
        Label fundsLabel = new Label();
        fundsLabel.setText("Owned funds: ");
        labelFontSettings(fundsLabel);

        Label rankLabel = new Label();
        rankLabel.setText("Ranking: x-th place");
        labelFontSettings(rankLabel);

        Label timeToEndLabel = new Label();
        timeToEndLabel.setText("Time to end: x days");
        labelFontSettings(timeToEndLabel);

        topHbox.getChildren().addAll(fundsLabel, rankLabel, timeToEndLabel);
        topHbox.setSpacing(80);
        topHbox.setPrefSize(1000, 100);
        topHbox.setBackground(darkBlueBackground);
        topHbox.setAlignment(Pos.CENTER);
        mainBorderPane.setTop(topHbox);

        labelFontSettings(timeToEndLabel);

        //------------------------------------------------------------------------- BOTTOM
        HBox bottomHbox = new HBox();
        Label linesLabel = new Label();
        linesLabel.setText("Lines of code: x");
        labelFontSettings(linesLabel);

        Button saleButton = new Button();
        saleButton.setText("Sale code!");
        saleButton.setPrefSize(160, 60);
        buttonFontSettings(saleButton);


        bottomHbox.getChildren().addAll(linesLabel, saleButton);
        bottomHbox.setBackground(darkBlueBackground);
        bottomHbox.setSpacing(500);
        bottomHbox.setPrefSize(1000, 100);
        bottomHbox.setAlignment(Pos.CENTER);
        mainBorderPane.setBottom(bottomHbox);

        //--------------------------------------------------------------------------RIGHT
        VBox vBoxRight = new VBox();
        vBoxRight.setBackground(brightBackground);
        ListView listView = new ListView();
        listView.setMaxHeight(300);
        listView.setItems(FXCollections.observableArrayList("Developer 1", "Developer 2", "Developer 3", "Developer 4", "Developer 5",
                "Developer 6", "Developer 7", "Developer 8", "Developer 9", "Developer 10"));

        Button promoteButton = new Button();
        Button dismissButton = new Button();
        promoteButton.setText("Promote!");
        dismissButton.setText("Dismiss!");
        buttonFontSettings(dismissButton);
        buttonFontSettings(promoteButton);

        HBox devHBox = new HBox();
        devHBox.setPadding(new Insets(10));
        devHBox.setSpacing(60);
        devHBox.getChildren().addAll(promoteButton, dismissButton);

        vBoxRight.getChildren().addAll(listView, devHBox);
        vBoxRight.setPadding(new Insets(10));
        mainBorderPane.setRight(vBoxRight);

        //--------------------------------------------------------------------------CENTER
        BorderPane centerBorderPane = new BorderPane();
        centerBorderPane.setPadding(new Insets(10));

        centerBorderPane.setBackground(blueBackground);

        //-------------------------------------------------------------CENTER/TOP

        HBox hireHBox = new HBox();
        Label cvLabel = new Label();

        hireHBox.setSpacing(80);
        hireHBox.setPadding(new Insets(10));
        hireHBox.setBackground(greyBackground);
        cvLabel.setText("Received CV's: ");
        labelFontSettings(cvLabel);

        VBox nameVbox = new VBox();
        nameVbox.setSpacing(5);
        nameVbox.setPadding(new Insets(5));
        nameVbox.setBackground(brightBackground);
        Label nameLabel = new Label();
        nameLabel.setText("First name + surname");
        nameLabel.setFont(Font.font("Century Gothic", 20));
        Label expLabel = new Label();
        expLabel.setFont(Font.font("Century Gothic", 20));
        expLabel.setText("Experience + salary");
        nameVbox.getChildren().addAll(nameLabel, expLabel);

        Button hireButton = new Button("Hire!");
        buttonFontSettings(hireButton);
        hireButton.setPrefSize(110, 30);
        Button nextButton = new Button("Next");
        Button prevButton = new Button("Prev");
        buttonFontSettings(nextButton);
        buttonFontSettings(prevButton);


        VBox buttonsHireVbox = new VBox();
        buttonsHireVbox.setSpacing(5);
        buttonsHireVbox.setPadding(new Insets(5));


        HBox nextPrevHbox = new HBox();
        nextPrevHbox.setSpacing(5);
        nextPrevHbox.setPadding(new Insets(3, 3, 3, 0));
        nextPrevHbox.getChildren().addAll(prevButton, nextButton);
        buttonsHireVbox.getChildren().addAll(hireButton, nextPrevHbox);

        hireHBox.setAlignment(Pos.CENTER);
        hireHBox.getChildren().addAll(cvLabel, nameVbox, buttonsHireVbox);
        centerBorderPane.setTop(hireHBox);

        //-------------------------------------------------------------CENTER/BOTTOM
        HBox helpHbox = new HBox();
        Button helpButton = new Button();
        helpButton.setText("HELP IN CODING!");
        helpButton.setOnAction(event -> devObjects.addWorkingDev());
        helpButton.setPrefSize(400, 100);
        buttonFontSettings(helpButton);
        helpHbox.setAlignment(Pos.CENTER);
        helpHbox.getChildren().addAll(helpButton);
        centerBorderPane.setBottom(helpHbox);

        //-------------------------------------------------------------CENTER/CENTER
        DevObjects gameObject = new DevObjects();
        centerBorderPane.setCenter(gameObject.createContent());


                mainBorderPane.setCenter(centerBorderPane);
        Scene mainScene = new Scene(mainBorderPane);
        return mainScene;
    }

    private void labelFontSettings(Label label) {
        label.setFont(Font.font("Century Gothic", 20));
        label.setTextFill(Color.WHITE);
    }

    private void buttonFontSettings(Button button) {
        button.setFont(Font.font("Century Gothic", FontWeight.BOLD, 15));

    }




}
