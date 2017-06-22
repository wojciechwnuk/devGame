package app;

import java.util.Timer;
import java.util.TimerTask;


public class CodeProduction extends TimerTask {
    public static double linesOfCodeMeter = 0;
    public static double timeToEndMeter = 1000;
    private Timer timer = new Timer();


    double getLinesOfCodeMeter() {
        return linesOfCodeMeter;
    }


    public void run() {
        linesOfCodeMeter += DevObjects.codePerSec;
        timeToEndMeter -= 1;

    }

    void startCoding() {

        timer.schedule(new CodeProduction(), 0, 1000);
    }

    void startTimer() {

        timer.schedule(new CodeProduction(), 1000, 10000);
    }

}
