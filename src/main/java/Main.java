import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class Main extends Application {


    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(getMainScene());
        primaryStage.setResizable(false);
        primaryStage.setMaxWidth(800);
        primaryStage.setMaxHeight(1000);
        primaryStage.setMinHeight(1000);
        primaryStage.setMinWidth(800);
        primaryStage.show();
    }

    private final Background paneBackground = new Background(new BackgroundFill(Color.web("#003040"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background centralPaneBackground = new Background(new BackgroundFill(Color.web("#006e93"), CornerRadii.EMPTY, Insets.EMPTY));

    private Scene getMainScene() {
        BorderPane mainBorderPane = new BorderPane();

        //-------------------------------------------------------------------------TOP
        HBox topHbox = new HBox();
        Label fundsLabel = new Label();
        fundsLabel.setText("Owned funds: ");
        labelSettings(fundsLabel);

        Label rankLabel = new Label();
        rankLabel.setText("Ranking: x-th place");
        labelSettings(rankLabel);

        Label timeToEndLabel = new Label();
        timeToEndLabel.setText("Time to end: x days");
        labelSettings(timeToEndLabel);


        topHbox.getChildren().addAll(fundsLabel, rankLabel, timeToEndLabel);
        topHbox.setSpacing(80);
        topHbox.setPrefSize(1000, 100);
        topHbox.setBackground(paneBackground);
        topHbox.setAlignment(Pos.CENTER);
        mainBorderPane.setTop(topHbox);

        labelSettings(timeToEndLabel);

        //------------------------------------------------------------------------- BOTTOM

        HBox bottomHbox = new HBox();
        Label linesLabel = new Label();
        linesLabel.setText("Lines of code: x");
        labelSettings(linesLabel);

        Button saleButton = new Button();
        saleButton.setText("Sale code!");
        saleButton.setPrefSize(160, 60);

        bottomHbox.getChildren().addAll(linesLabel, saleButton);
        bottomHbox.setBackground(paneBackground);
        bottomHbox.setSpacing(300);
        bottomHbox.setPrefSize(1000, 100);
        bottomHbox.setAlignment(Pos.CENTER);
        mainBorderPane.setBottom(bottomHbox);

        //--------------------------------------------------------------------------RIGHT
        VBox vBoxRight = new VBox();
        ListView listView = new ListView();
        listView.setMaxHeight(200);
        listView.setItems(FXCollections.observableArrayList("Developer 1", "Developer 2", "Developer 3", "Developer 4", "Developer 5",
                "Developer 6", "Developer 7", "Developer 8", "Developer 9", "Developer 10"));

        Button promoteButton = new Button();
        Button dismissButton = new Button();
        promoteButton.setText("Promote!");
        dismissButton.setText("Dismiss!");


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
        centerBorderPane.setBackground(centralPaneBackground);

        mainBorderPane.setCenter(centerBorderPane);

        Scene mainScene = new Scene(mainBorderPane);
        return mainScene;
    }

    private void labelSettings(Label label) {
        label.setFont(Font.font("Century Gothic", 20));
        label.setTextFill(Color.WHITE);
    }
}
