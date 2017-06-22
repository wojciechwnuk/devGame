package app;

import database.Employee;
import database.dao.HibernateDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import utility.CodeTimerAnimation;
import java.text.DecimalFormat;
import java.util.List;

class GUIfunctions {
    static double ownedFunds = 6000;
    private CodeProduction codeProduction = new CodeProduction();
    private CodeTimerAnimation codeTimer = new CodeTimerAnimation();


    void sellCode() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        GUI.fundsLabel.setText("Owned funds: " + String.valueOf(df.format(ownedFunds += codeProduction.getLinesOfCodeMeter() * 1.5) + "$"));
        CodeProduction.linesOfCodeMeter = 0;
    }

    String getCvName() {
        HibernateDAO da = new HibernateDAO();
        List<Employee> list = da.findByHired(0);

        String name;
        name = list.get(GUI.actualOnListCv).getFirstName() + " " + list.get(GUI.actualOnListCv).getLastName();
        return name;
    }

    int getSalary() {
        HibernateDAO da = new HibernateDAO();
        List<Employee> list = da.findByHired(0);
        int salary;
        salary = list.get(GUI.actualOnListCv).getSalary();
        return salary;
    }

    String getPosition() {
        HibernateDAO da = new HibernateDAO();
        List<Employee> list = da.findByHired(0);
        String experience;
        experience = list.get(GUI.actualOnListCv).getPosition();
        return experience;
    }


    void nextCv() {
        HibernateDAO da = new HibernateDAO();

        if (GUI.actualOnListCv < da.countHired(false) - 1) {
            GUI.actualOnListCv++;
        }
        if ("Hired!".equals(GUI.nameLabel.getText())) {
            GUI.hireButton.setDisable(false);
        }
    }

    void prevCv() {
        if (GUI.actualOnListCv > 0) {
            GUI. actualOnListCv--;
        }
        if ("Hired!".equals(GUI.nameLabel.getText())) {
            GUI.hireButton.setDisable(false);
        }

    }

    void addEntities() {
        HibernateDAO hibernateDAO = new HibernateDAO();
        hibernateDAO.addHumansIfEmpty();
    }

    void hire() {
        HibernateDAO da = new HibernateDAO();
        List<Employee> list = da.findByHired(0);
        int actId;
        actId = list.get(GUI.actualOnListCv).getId();
        da.hire(actId);
    }

    void startAnimations() {
        codeProduction.startTimer();
        codeProduction.startCoding();
        codeTimer.update();
    }

    void refreshListView() {
        GUI. listViewHbox.getChildren().clear();
        GUI. listViewHbox.getChildren().addAll(createListView());
    }

    ListView createListView() {
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

    boolean fundsControl() {
        boolean gotEnough;
        gotEnough = !(ownedFunds - getSalary() < 0);
        return gotEnough;
    }

    void rankChecker() {
        if (ownedFunds > 0 && ownedFunds < 6001) {
            GUI.rank = 10;
        } else if (ownedFunds > 6001 && ownedFunds <= 8001) {
            GUI.rank = 9;
        } else if (ownedFunds > 8001 && ownedFunds <= 10001) {
            GUI.rank = 8;
        } else if (ownedFunds > 10001 && ownedFunds <= 12001) {
            GUI.rank = 7;
        } else if (ownedFunds > 12001 && ownedFunds <= 15001) {
            GUI.rank = 6;
        } else if (ownedFunds > 15001 && ownedFunds <= 18001) {
            GUI.rank = 5;
        } else if (ownedFunds > 18001 && ownedFunds <= 20001) {
            GUI.rank = 4;
        } else if (ownedFunds > 20001 && ownedFunds <= 25001) {
            GUI.rank = 3;
        } else if (ownedFunds > 25001 && ownedFunds <= 30001) {
            GUI.rank = 2;
        } else if (ownedFunds > 30001) {
            GUI.rank = 1;
        }
    }
}
