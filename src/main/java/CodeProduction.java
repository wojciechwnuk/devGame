
import java.util.Timer;
import java.util.TimerTask;


public class CodeProduction extends TimerTask {
    static double linesOfCodeMeter = 0;
    private DevObjects devObjects;

    public void run() {
        devObjects = new DevObjects();
        linesOfCodeMeter += DevObjects.codePerSec;
    }

    void startCoding() {
        Timer timer = new Timer();
        timer.schedule(new CodeProduction(), 0, 100);
    }

    public static void main(String[] args) {
        CodeProduction codeProduction = new CodeProduction();
        codeProduction.startCoding();

    }

}
