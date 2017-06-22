package app;

import database.Employee;
import database.dao.HibernateDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;


public class GUI {

   static Text infoText = new Text();
    public static Label linesLabel;
    public static Button helpButton;
    public static Label rankLabel;
    public static int rank = 10;
    public static Label timeToEndLabel;

    static Button hireButton;
    static int actualOnListCv = 0;
    static Label fundsLabel;
    static Label nameLabel;
    static HBox listViewHbox;


    Scene getMainScene() throws InterruptedException {
        GUIfunctions func = new GUIfunctions();

        final Background darkBlueBackground = new Background(new BackgroundFill(Color.web("#003040"), CornerRadii.EMPTY, Insets.EMPTY));
        final Background greyBackground = new Background(new BackgroundFill(Color.web("#16333d"), CornerRadii.EMPTY, Insets.EMPTY));
        final Background blueBackground = new Background(new BackgroundFill(Color.web("#006e93"), CornerRadii.EMPTY, Insets.EMPTY));
        final Background brightBackground = new Background(new BackgroundFill(Color.web("#fff9f4"), CornerRadii.EMPTY, Insets.EMPTY));


        func.addEntities();
        func.startAnimations();
        HibernateDAO hib = new HibernateDAO();
        BorderPane mainBorderPane = new BorderPane();
        //-------------------------------------------------------------------------TOP
        HBox topHbox = new HBox();

        fundsLabel = new Label();
        fundsLabel.setText("Owned funds: " + GUIfunctions.ownedFunds + "$");
        labelFontSettings(fundsLabel);

        rankLabel = new Label();
        rankLabel.setText("Ranking: 10th place");
        labelFontSettings(rankLabel);

        timeToEndLabel = new Label();
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
        linesLabel = new Label();
        labelFontSettings(linesLabel);

        Button saleButton = new Button();
        saleButton.setText("Sale code!");

        saleButton.setOnAction(event -> {
            func.sellCode();
            func.rankChecker();
        });

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

        Button promoteButton = new Button();
        promoteButton.setText("Promote!");
        buttonFontSettings(promoteButton);

        HBox devHBox = new HBox();
        devHBox.setPadding(new Insets(10));
        devHBox.setSpacing(60);
        devHBox.getChildren().addAll(promoteButton);

        listViewHbox = new HBox();
        listViewHbox.getChildren().add( func.createListView());

        vBoxRight.getChildren().addAll(listViewHbox, devHBox);
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
        nameVbox.setMinWidth(200);
        nameVbox.setSpacing(5);
        nameVbox.setPadding(new Insets(5));
        nameVbox.setBackground(brightBackground);
        nameLabel = new Label();
        nameLabel.setText( func.getCvName());

        nameLabel.setFont(Font.font("Century Gothic", 20));
        Label expLabel = new Label();
        expLabel.setFont(Font.font("Century Gothic", 20));
        expLabel.setText( func.getPosition() + ", " + String.valueOf( func.getSalary()) + "$");
        nameVbox.getChildren().addAll(nameLabel, expLabel);

        hireButton = new Button("Hire!");
        buttonFontSettings(hireButton);
        hireButton.setPrefSize(110, 30);
        Button nextButton = new Button();
        nextButton.setText("Next");

        Button prevButton = new Button("Prev");
        buttonFontSettings(nextButton);
        buttonFontSettings(prevButton);

        nextButton.setOnAction(event -> {
            func.nextCv();
            expLabel.setText( func.getPosition() + ", " + String.valueOf( func.getSalary()) + "$");
            nameLabel.setText( func.getCvName());
        });
        prevButton.setOnAction(event -> {
            func.prevCv();
            if (nextButton.isDisable()) {
                nextButton.setDisable(false);
            }
            expLabel.setText( func.getPosition() + ", " + String.valueOf( func.getSalary()) + "$");
            nameLabel.setText( func.getCvName());
        });
        //-------------------------------------------------------------------------------------HIRE action
        hireButton.setOnAction(event -> {
            if (! func.fundsControl()) {
                return;
            }

            List<Employee> list = hib.findByHired(0);
            String actPosition;
            int actSalaryCosts;
            actPosition = list.get(actualOnListCv).getPosition();
            actSalaryCosts = list.get(actualOnListCv).getSalary();
            switch (actPosition) {
                case "Junior":
                    DevObjects.permissionToAddDev = 1;
                    GUIfunctions.ownedFunds -= actSalaryCosts;
                    fundsLabel.setText("Owned funds: " + GUIfunctions.ownedFunds + "$");

                    break;
                case "Regular":
                    DevObjects.permissionToAddDev = 2;
                    GUIfunctions.ownedFunds -=actSalaryCosts;
                    fundsLabel.setText("Owned funds: " + GUIfunctions.ownedFunds + "$");

                    break;
                case "Senior":
                    DevObjects.permissionToAddDev = 3;
                    GUIfunctions.ownedFunds -= actSalaryCosts;
                    fundsLabel.setText("Owned funds: " + GUIfunctions.ownedFunds + "$");
                    break;
            }
            func.hire();
            nameLabel.setText("Hired!");
            expLabel.setText("-" + actSalaryCosts + "$!");


            if (nameLabel.getText().equals("Hired!")) {
                hireButton.setDisable(true);
            }
            if (actualOnListCv == hib.countHired(false)) {
                nextButton.setDisable(true);
            }

            if (hib.countHired(false) == 0) {
                hireButton.setDisable(true);
                prevButton.setDisable(true);
                nextButton.setDisable(true);
            }
            func.refreshListView();
        });


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
        helpButton = new Button();
        helpButton.setText("HELP CODING!" + DevObjects.codePerSec);
        helpButton.setOnAction(event -> CodeProduction.linesOfCodeMeter += 10);
        helpButton.setPrefSize(400, 100);
        buttonFontSettings(helpButton);
        helpHbox.setAlignment(Pos.CENTER);
        helpHbox.getChildren().addAll(helpButton);
        centerBorderPane.setBottom(helpHbox);

        //-------------------------------------------------------------CENTER/CENTER
        DevObjects gameObject = new DevObjects();
        StackPane textAndAnimationStackPane = new StackPane();


        textAndAnimationStackPane.getChildren().addAll(gameObject.createContent(),infoText );


        centerBorderPane.setCenter(textAndAnimationStackPane);

        mainBorderPane.setCenter(centerBorderPane);

        return new Scene(mainBorderPane);
    }

    private void labelFontSettings(Label label) {
        label.setFont(Font.font("Century Gothic", 20));
        label.setTextFill(Color.WHITE);
    }

    private void buttonFontSettings(Button button) {
        button.setFont(Font.font("Century Gothic", FontWeight.BOLD, 15));
    }
}
