import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class DevObjects {
    private StackPane root;
    private List<DevObjects> devBalls = new ArrayList<>();
    private AnimationTimer timer;
    private Node node;
    private Point2D velocity = new Point2D(0, 0);
    static int permissionToAddDev = 0;
    static double codePerSec = 0;

    DevObjects(Node node) {
        this.node = node;
    }

    DevObjects() {
    }

    private void update() {
        node.setTranslateX(node.getTranslateX() + velocity.getX());
        node.setTranslateY(node.getTranslateY() + velocity.getY());

        if (node.getTranslateY() < -135) {
            setVelocity(new Point2D(0, 1));
        } else if (node.getTranslateY() > 135) {
            setVelocity(new Point2D(0, -1));
        } else if (node.getTranslateX() < -340) {
            setVelocity(new Point2D(1, 0));
        } else if (node.getTranslateX() > 340) {
            setVelocity(new Point2D(-1, 0));
        }
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

    private void addDevBall(DevObjects devBall, double x, double y) {
        devBalls.add(devBall);
        devBall.getNode().setTranslateX(x);
        devBall.getNode().setTranslateY(y);
        root.getChildren().add(devBall.getNode());

        devBall.setVelocity(new Point2D(0, 1));
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
        if (permissionToAddDev == 1) {
            addJuniorDev();

            permissionToAddDev = 0;
        } else if (permissionToAddDev == 2) {
            addRegularDev();
            permissionToAddDev = 0;
        } else if (permissionToAddDev == 3) {
            addSeniorDev();
            permissionToAddDev = 0;
        }
    }

    private void addRegularDev() {
        RegularDev regular = new RegularDev();
        addDevBall(regular, Math.random() * root.getPrefWidth(), 0 * root.getPrefHeight());
        codePerSec += 5;
    }

    private void addJuniorDev() {
        JuniorDev junior = new JuniorDev();
        addDevBall(junior, Math.random() * root.getPrefWidth(), 0 * root.getPrefHeight());
        codePerSec += 3;
    }

    private void addSeniorDev() {
        SeniorDev senior = new SeniorDev();
        addDevBall(senior, Math.random() * root.getPrefWidth(), 0 * root.getPrefHeight());
        codePerSec += 9;
    }

    //----------------------------------------------------------------------------------------------------------Devs circles
    static class JuniorDev extends DevObjects {
        JuniorDev() {
            super(new Circle(10, 10, 10, Color.FORESTGREEN));
        }
    }

    static class RegularDev extends DevObjects {
        RegularDev() {
            super(new Circle(15, 15, 15, Color.ORANGE));
        }
    }

    static class SeniorDev extends DevObjects {
        SeniorDev() {
            super(new Circle(20, 20, 20, Color.DARKRED));
        }
    }

    //----------------------------------------------------------------------------------------------------------Movement
    private Node getNode() {
        return node;
    }

    private void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    private void rotateRight() {
        node.setRotate(node.getRotate() + Math.random() * 100);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    private void rotateLeft() {
        node.setRotate(node.getRotate() - Math.random() * 100);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    private double getRotate() {
        return node.getRotate();
    }

}
