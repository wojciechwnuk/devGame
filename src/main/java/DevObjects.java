import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
//  --------------------------------------zrob z devball oddzielna klase i niech main ja sobie tworzy
@Data
public class DevObjects {
    private StackPane root;
    private List<DevObjects> devBalls = new ArrayList<DevObjects>();
    private AnimationTimer timer;
    private Node view;
    private Point2D velocity = new Point2D(0, 0);

    int workingDevs = 2;
    int queueDevs = 1;
    int pozwol = 0;

    DevObjects(Node view) {
        this.view = view;
    }

    DevObjects() {
    }


    void update() {
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());

        if (view.getTranslateY() < -135) {
            setVelocity(new Point2D(0, 1));
        } else if (view.getTranslateY() > 135) {
            setVelocity(new Point2D(0, -1));
        } else if (view.getTranslateX() < -340) {
            setVelocity(new Point2D(1, 0));
        } else if (view.getTranslateX() > 340) {
            setVelocity(new Point2D(-1, 0));
        }

    }

    private Node getView() {
        return view;
    }

    private void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    private void rotateRight() {
        view.setRotate(view.getRotate() + Math.random() * 100);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    private void rotateLeft() {
        view.setRotate(view.getRotate() - Math.random() * 100);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    private double getRotate() {
        return view.getRotate();
    }


    StackPane createContent() {
        root = new StackPane();

        root.setMaxSize(800, 300);


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    onUpdate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.start();
        return root;
    }


    void addDevBall(DevObjects enemy, double x, double y) {
        devBalls.add(enemy);
        addGameObject(enemy, x, y);

        enemy.setVelocity(new Point2D(0, 1));
    }

    private void addGameObject(DevObjects object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }

    private void onUpdate() throws InterruptedException {

        devBalls.forEach(DevObjects::update);

        for (DevObjects devBall : devBalls) {
            if (Math.random() > 0.99) {
                devBall.rotateRight();

            } else if (Math.random() < 0.01) {
                devBall.rotateLeft();
            }
        }


    }

    private static class DevBall extends DevObjects {
        DevBall() {
            super(new Circle(15, 15, 15, Color.RED));
        }
    }


}
