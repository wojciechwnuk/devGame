import database.Employee;
import database.HibernateDAO;
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
import java.text.DecimalFormat;
import java.util.List;

class GUI {

    private final Background darkBlueBackground = new Background(new BackgroundFill(Color.web("#003040"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background greyBackground = new Background(new BackgroundFill(Color.web("#16333d"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background blueBackground = new Background(new BackgroundFill(Color.web("#006e93"), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background brightBackground = new Background(new BackgroundFill(Color.web("#fff9f4"), CornerRadii.EMPTY, Insets.EMPTY));

    static Label linesLabel;
    static Button helpButton;
    private double ownedFunds = 5000;
    private int actualOnListCv = 0;
    private CodeProduction codeProduction = new CodeProduction();
    private Label fundsLabel;


    Scene getMainScene() {
        addEntities();
        HibernateDAO hib = new HibernateDAO();


        BorderPane mainBorderPane = new BorderPane();
        //-------------------------------------------------------------------------TOP
        HBox topHbox = new HBox();

        fundsLabel = new Label();
        fundsLabel.setText("Owned funds: " + ownedFunds + "$");
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
        linesLabel = new Label();

        labelFontSettings(linesLabel);

        Button saleButton = new Button();
        saleButton.setText("Sale code!");

        saleButton.setOnAction(event -> sellCode());

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

        HBox experimentalHbox = new HBox();
        Button startButton = new Button("start");
        startButton.setOnAction(event -> {
            codeProduction.startCoding();
            CodeTimerAnimation codeTimer = new CodeTimerAnimation();
            codeTimer.update();
        });
        Button addJunDev = new Button("addJun");
        addJunDev.setOnAction(event -> {
            DevObjects.permissionToAddDev = 1;
        });

        Button addRegDev = new Button("addReg");
        addRegDev.setOnAction(event -> {
            DevObjects.permissionToAddDev = 2;
        });
        Button addSenDev = new Button("addSen");
        addSenDev.setOnAction(event -> {
            DevObjects.permissionToAddDev = 3;
        });
        experimentalHbox.getChildren().addAll(startButton, addJunDev, addRegDev, addSenDev);

        vBoxRight.getChildren().addAll(listView, devHBox, experimentalHbox);
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
        nameLabel.setText(getCvName());

        nameLabel.setFont(Font.font("Century Gothic", 20));
        Label expLabel = new Label();
        expLabel.setFont(Font.font("Century Gothic", 20));
        expLabel.setText(getCvExperience()+", "+String.valueOf(getCvSalary())+"$");
        nameVbox.getChildren().addAll(nameLabel, expLabel);

        Button hireButton = new Button("Hire!");
        hireButton.setOnAction(event->hire());
        buttonFontSettings(hireButton);
        hireButton.setPrefSize(110, 30);
        Button nextButton = new Button();
        nextButton.setText("Next");
        nextButton.setOnAction(event -> {
            nextCv();
            expLabel.setText(getCvExperience()+", "+String.valueOf(getCvSalary())+"$");
            nameLabel.setText(getCvName());
        });

        Button prevButton = new Button("Prev");
        buttonFontSettings(nextButton);
        buttonFontSettings(prevButton);
        prevButton.setOnAction(event -> {
            prevCv();
            expLabel.setText(getCvExperience()+", "+String.valueOf(getCvSalary())+"$");
            nameLabel.setText(getCvName());
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
        helpButton.setOnAction(event -> CodeProduction.linesOfCodeMeter += 0.5);
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

    private void sellCode() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        fundsLabel.setText("Owned funds: " + String.valueOf(df.format(ownedFunds += codeProduction.getLinesOfCodeMeter() * 0.5) + "$"));
        CodeProduction.linesOfCodeMeter = 0;
    }

    private String getCvName() {
        HibernateDAO da = new HibernateDAO();
        da.setUp();
        List<Employee> list = da.findByHired(0);

        String name;
        name = list.get(actualOnListCv).getFirstName() + " " + list.get(actualOnListCv).getLastName();
        da.exit();
        return name;
    }
    private int getCvSalary(){
        HibernateDAO da = new HibernateDAO();
        da.setUp();
        List<Employee> list = da.findByHired(0);
        int salary;
        salary=list.get(actualOnListCv).getSalary();
        da.exit();
        return salary;
    }
    private String getCvExperience(){
        HibernateDAO da = new HibernateDAO();
        da.setUp();
        List<Employee> list = da.findByHired(0);
        String experience;
        experience=list.get(actualOnListCv).getPosition();
        da.exit();
        return  experience;
    }

    private void nextCv() {
        if (actualOnListCv< 3) {
            actualOnListCv++;}
    }

    private void prevCv() {
        if (actualOnListCv>0){
        actualOnListCv--;}
    }

    private void addEntities() {
        HibernateDAO hibernateDAO = new HibernateDAO();
        hibernateDAO.setUp();
        hibernateDAO.addHumansIfEmpty();
        hibernateDAO.exit();
    }
    private int hire(){
        HibernateDAO da = new HibernateDAO();
        da.setUp();
        List<Employee> list = da.findByHired(0);
        int actId;
        actId=list.get(actualOnListCv).getId();
        da.hire(actId);
        da.exit();
        return actId;
    }


}
