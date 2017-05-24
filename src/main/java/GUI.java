import database.Employee;
import database.HibernateDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.List;


class GUI {
    private static final Logger logger = LoggerFactory.getLogger(GUI.class);


    static Label linesLabel;
    static Button helpButton;
    static Label rankLabel;
    static int rank = 10;
    static Label timeToEndLabel;

    private Button hireButton;
    private double ownedFunds = 6000;
    private int actualOnListCv = 0;
    private CodeProduction codeProduction = new CodeProduction();
    private Label fundsLabel;
    private Label nameLabel;
    private HBox listViewHbox;


    Scene getMainScene() {

        final Background darkBlueBackground = new Background(new BackgroundFill(Color.web("#003040"), CornerRadii.EMPTY, Insets.EMPTY));
        final Background greyBackground = new Background(new BackgroundFill(Color.web("#16333d"), CornerRadii.EMPTY, Insets.EMPTY));
        final Background blueBackground = new Background(new BackgroundFill(Color.web("#006e93"), CornerRadii.EMPTY, Insets.EMPTY));
        final Background brightBackground = new Background(new BackgroundFill(Color.web("#fff9f4"), CornerRadii.EMPTY, Insets.EMPTY));

        addEntities();
        startAnimations();
        HibernateDAO hib = new HibernateDAO();
        BorderPane mainBorderPane = new BorderPane();
        //-------------------------------------------------------------------------TOP
        HBox topHbox = new HBox();

        fundsLabel = new Label();
        fundsLabel.setText("Owned funds: " + ownedFunds + "$");
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
            sellCode();
            rankChecker();
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
        listViewHbox.getChildren().add(createListView());

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
        nameLabel.setText(getCvName());

        nameLabel.setFont(Font.font("Century Gothic", 20));
        Label expLabel = new Label();
        expLabel.setFont(Font.font("Century Gothic", 20));
        expLabel.setText(getPosition(0) + ", " + String.valueOf(getSalary(0)) + "$");
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
            nextCv();
            expLabel.setText(getPosition(0) + ", " + String.valueOf(getSalary(0)) + "$");
            nameLabel.setText(getCvName());
        });
        prevButton.setOnAction(event -> {
            prevCv();
            if (nextButton.isDisable()) {
                nextButton.setDisable(false);
            }
            expLabel.setText(getPosition(0) + ", " + String.valueOf(getSalary(0)) + "$");
            nameLabel.setText(getCvName());
        });
        //-------------------------------------------------------------------------------------HIRE action
        hireButton.setOnAction(event -> {
            if (!fundsControl()) {
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
                    ownedFunds = ownedFunds - actSalaryCosts;
                    fundsLabel.setText("Owned funds: " + ownedFunds + "$");

                    break;
                case "Regular":
                    DevObjects.permissionToAddDev = 2;
                    ownedFunds = ownedFunds - actSalaryCosts;
                    fundsLabel.setText("Owned funds: " + ownedFunds + "$");

                    break;
                case "Senior":
                    DevObjects.permissionToAddDev = 3;
                    ownedFunds = ownedFunds - actSalaryCosts;
                    fundsLabel.setText("Owned funds: " + ownedFunds + "$");
                    break;
            }
            hire();
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
            refreshListView();
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
        helpButton.setText("HELP IN CODING!" + DevObjects.codePerSec);
        helpButton.setOnAction(event -> CodeProduction.linesOfCodeMeter += 10);
        helpButton.setPrefSize(400, 100);
        buttonFontSettings(helpButton);
        helpHbox.setAlignment(Pos.CENTER);
        helpHbox.getChildren().addAll(helpButton);
        centerBorderPane.setBottom(helpHbox);

        //-------------------------------------------------------------CENTER/CENTER
        DevObjects gameObject = new DevObjects();
        centerBorderPane.setCenter(gameObject.createContent());

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

    private void sellCode() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        fundsLabel.setText("Owned funds: " + String.valueOf(df.format(ownedFunds += codeProduction.getLinesOfCodeMeter() * 1.5) + "$"));
        CodeProduction.linesOfCodeMeter = 0;
    }

    private String getCvName() {
        HibernateDAO da = new HibernateDAO();
        List<Employee> list = da.findByHired(0);

        String name;
        name = list.get(actualOnListCv).getFirstName() + " " + list.get(actualOnListCv).getLastName();
        return name;
    }

    private int getSalary(int ifHired) {
        HibernateDAO da = new HibernateDAO();
        List<Employee> list = da.findByHired(ifHired);
        int salary;
        salary = list.get(actualOnListCv).getSalary();
        return salary;
    }

    private String getPosition(int ifHired) {
        HibernateDAO da = new HibernateDAO();
        List<Employee> list = da.findByHired(ifHired);
        String experience;
        experience = list.get(actualOnListCv).getPosition();
        return experience;
    }


    private void nextCv() {
        HibernateDAO da = new HibernateDAO();

        if (actualOnListCv < da.countHired(false) - 1) {
            actualOnListCv++;
        }
        if ("Hired!".equals(nameLabel.getText())) {
            hireButton.setDisable(false);
        }
    }

    private void prevCv() {
        if (actualOnListCv > 0) {
            actualOnListCv--;
        }
        if ("Hired!".equals(nameLabel.getText())) {
            hireButton.setDisable(false);
        }

    }

    private void addEntities() {
        HibernateDAO hibernateDAO = new HibernateDAO();
        hibernateDAO.addHumansIfEmpty();
    }

    private int hire() {
        HibernateDAO da = new HibernateDAO();
        List<Employee> list = da.findByHired(0);
        int actId;
        actId = list.get(actualOnListCv).getId();
        da.hire(actId);
        return actId;
    }

    private void startAnimations() {
        codeProduction.startCoding();
        CodeTimerAnimation codeTimer = new CodeTimerAnimation();
        codeTimer.update();
    }

    private void refreshListView() {
        listViewHbox.getChildren().clear();
        listViewHbox.getChildren().addAll(createListView());
    }

    private ListView createListView() {
        HibernateDAO hib = new HibernateDAO();
        ListView listView = new ListView();
        listView.setMaxHeight(300);
        ObservableList<String> observableListOfHired = FXCollections.observableArrayList();
        for (int i = 0; i < hib.countHired(true); i++) {
            observableListOfHired.add(hib.findByHired(1).get(i).getLastName() + ", " + hib.findByHired(1).get(i).getPosition() + ", " + hib.findByHired(1).get(i).getSalary() + "$");
        }
        listView.setItems(observableListOfHired);
        return listView;
    }

    private boolean fundsControl() {
        boolean gotEnough;
        if (ownedFunds - getSalary(0) < 0) {
            System.out.println("nie stac cie");
            gotEnough = false;
        } else gotEnough = true;
        return gotEnough;
    }

    void promoteDev() {
        if (getPosition(1).equals("Junior")) {

        }
    }

    void rankChecker(){
        if (ownedFunds > 0 && ownedFunds < 6001) {
            rank = 10;
        } else if (ownedFunds > 6001) {
            rank = 9;
        } else if (ownedFunds > 8001) {
            rank = 8;
        } else if (ownedFunds > 10001) {
            rank = 7;
        } else if (ownedFunds > 12001) {
            rank = 6;
        } else if (ownedFunds > 15001) {
            rank = 5;
        } else if (ownedFunds > 18001) {
            rank = 4;
        } else if (ownedFunds > 20001) {
            rank = 3;
        } else if (ownedFunds > 25001) {
            rank = 2;
        } else if (ownedFunds > 30001) {
            rank = 1;
        }
    }

}
