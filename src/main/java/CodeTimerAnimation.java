import javafx.animation.AnimationTimer;
import java.text.DecimalFormat;

public class CodeTimerAnimation extends AnimationTimer {

    void update(){
        new AnimationTimer(){
            @Override
            public void handle(long now){
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(0);
                GUI.linesLabel.setText("Lines of code: "+String.valueOf(df.format(CodeProduction.linesOfCodeMeter)));
            }
        }.start();
    }

    @Override
    public void handle(long now) {

    }
}
