import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogWriter {

    private PrintWriter foodColoringLog;

    private int count;

    public LogWriter() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String currentDateTimeString = currentDateTime.toString();
        currentDateTimeString = currentDateTimeString.replaceAll(":", "_");
        try {
            foodColoringLog = new PrintWriter(new File("simulation_log/foodColoringLog" + currentDateTimeString + ".csv"));

            StringBuilder sb = new StringBuilder();
            sb.append("Step");
            sb.append(",");
            sb.append("Food Coloring Count");
            sb.append("\n");
            foodColoringLog.write(sb.toString());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void logFoodColoring(int step, int foodColoringCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(step);
        sb.append(",");
        sb.append(foodColoringCount);
        sb.append("\n");
        foodColoringLog.write(sb.toString());
        foodColoringLog.flush();
    }

    void close(){
        foodColoringLog.close();
    }
}
