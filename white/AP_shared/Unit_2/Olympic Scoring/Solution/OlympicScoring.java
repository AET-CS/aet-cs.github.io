import java.util.List;
import java.util.ArrayList; 
import java.util.Scanner;

public class OlympicScoring {

	public static void main(String[] args) {
		//set up judges array
		String[] judges = chooseJudges();
		for (int i = 0; i < judges.length; i++) {
			System.out.print(judges[i] + " ");
		}
		System.out.println(); 
		
		//TODO: complete main by calling methods and generating output
		List<Double> scores = getScores(judges); 
		System.out.println("Scores before removal: " + scores);
		removeScores(scores);
		System.out.println("Scores after removal: " + scores);
		System.out.println("Average score: " + getAverage(scores));

	}
	
	//TODO: add/complete the getScores, removeHighLow, and getAverage methods 
	public static List<Double> getScores(String[] countries) {
		//set up variables
		List<Double> scores = new ArrayList<Double>(); 
		Scanner s = new Scanner(System.in); 
		
		//loop over countries list and add scores 
		for (int i = 0; i < countries.length; i++) {
			System.out.print("Enter score for judge: " + countries[i] + " = ");
			double score = s.nextDouble(); 
			if (score >= 0.0 && score <= 10.0) {
				scores.add(score);
			}
			else {
				System.out.println("Invalid score entered");
				i--; 
			}
		}
		return scores; 
	}
	
	public static void removeScores(List<Double> scores) {
		//find biggest and smallest
		double small = scores.get(0);
		double big = scores.get(0);
		int smallIndex = 0;
		int bigIndex = 0;
		for (int i = 0; i < scores.size(); i++) {
			if (scores.get(i) > big) {
				big = scores.get(i);
				bigIndex = i;
			}
			if (scores.get(i) < small) {
				small = scores.get(i);
				smallIndex = i; 
			}
		}
		//remove
		System.out.println("Score Removed: " + scores.remove(bigIndex));
		if (bigIndex < smallIndex) {
			smallIndex--; 
		}
		System.out.println("Score Removed: " + scores.remove(smallIndex)); 
	}
	
	public static double getAverage(List<Double> scores) {
		double sum = 0;
		for (double d : scores) {
			sum += d;
		}
		return sum / scores.size(); 
	}
	
	
	//Completed for you do not modify
	public static String[] chooseJudges() {
		String[] judges = new String[(int)(Math.random() * 3 + 5)];
		ArrayList<String> countries = new ArrayList<String>();
		countries.add("Germany"); countries.add("USA"); countries.add("Canada"); countries.add("Botswana"); countries.add("China"); countries.add("France"); countries.add("Eritrea"); countries.add("Russia"); countries.add("Mexico"); countries.add("Belgium");     
		for (int i = 0; i < judges.length; i++) {
			int choice = (int) (Math.random() * countries.size()); 
			judges[i] = countries.get(choice);
			countries.remove(choice);
		}
		return judges; 
	}

}
