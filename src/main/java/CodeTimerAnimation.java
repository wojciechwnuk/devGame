import javafx.animation.AnimationTimer;

import java.text.DecimalFormat;

public class CodeTimerAnimation extends AnimationTimer {


    void update() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(0);
                DecimalFormat df2 = new DecimalFormat();
                df2.setMaximumFractionDigits(2);
                GUI.linesLabel.setText("Lines of code: " + String.valueOf(df.format(CodeProduction.linesOfCodeMeter)));
                GUI.helpButton.setText("Click to help coding!\n Code per sec: " + df2.format(DevObjects.codePerSec));
                GUI.rankLabel.setText("Ranking: " + df.format(GUI.rank) + "th place");
            }
        }.start();
    }

    @Override
    public void handle(long now) {

    }
}
