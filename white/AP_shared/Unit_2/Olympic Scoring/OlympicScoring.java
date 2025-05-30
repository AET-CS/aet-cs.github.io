import java.util.List;
import java.util.ArrayList; 
import java.util.Scanner;

public class OlympicScoring {

	public static void main(String[] args) {
		//set up judges array
		String[] judges = chooseJudges();
		
		//TODO: complete main by calling methods and generating output
		List<Double> scores = getScores(judges); 
		

	}
	
	//TODO: add/complete the getScores, removeHighLow, and getAverage methods 
	
	
	
	
	
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
