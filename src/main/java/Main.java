import database.HibernateDAO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GUI GUI = new GUI();
        HibernateDAO hibernateDAO = new HibernateDAO();

        primaryStage.setScene(GUI.getMainScene());

        primaryStage.setResizable(false);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(800);
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event-> {
            hibernateDAO.clearTable();
            Platform.exit();
            System.exit(0);
        });
    }

}
