import java.io.*;
import java.util.*;

public class WeatherAnalyzer
{
    private ArrayList<String> dates;
    private ArrayList<Integer> highTemps;
    private ArrayList<Integer> lowTemps;
    private ArrayList<Double> precipitation;
    private ArrayList<String> conditions;

    public WeatherAnalyzer()
    {
        dates = new ArrayList<String>();
        highTemps = new ArrayList<Integer>();
        lowTemps = new ArrayList<Integer>();
        precipitation = new ArrayList<Double>();
        conditions = new ArrayList<String>();
    }

    public void loadData(String filename)
    {
        try
        {
            Scanner fileScanner = new Scanner(new File(filename));
            fileScanner.nextLine();  // skip header

            // --- Your code here ---


            fileScanner.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: File not found - " + filename);
        }
    }

    public int findHottestDayIndex()
    {
        // --- Your code here ---

        return -1;
    }

    public int findColdestDayIndex()
    {
        // --- Your code here ---

        return -1;
    }

    public double averageHighTemp()
    {
        // --- Your code here ---

        return 0.0;
    }

    public double totalPrecipitation()
    {
        // --- Your code here ---

        return 0.0;
    }

    public int countCondition(String condition)
    {
        // --- Your code here ---

        return 0;
    }

    public void writeReport(String filename)
    {
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));

            writer.println("===== WEATHER SUMMARY REPORT =====");
            writer.println();

            // --- Your code here ---


            writer.println();
            writer.println("===== END OF REPORT =====");

            writer.close();
            System.out.println("Report written to " + filename);
        }
        catch (IOException e)
        {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        WeatherAnalyzer analyzer = new WeatherAnalyzer();
        analyzer.loadData("weather_data.csv");
        analyzer.writeReport("weather_report.txt");
    }
}
