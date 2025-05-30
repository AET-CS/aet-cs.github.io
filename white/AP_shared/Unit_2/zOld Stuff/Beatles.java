import java.util.ArrayList;
import java.util.List;


public class Beatles {

	public static void main(String[] args) {
		
		List<String> band = new ArrayList<String>();
		
		System.out.println(band.size());
		
		band.add("Paul");
		band.add("Pete");
		band.add("John");
		band.add("Ringo");
		
		System.out.println(band);
		
		int location = band.indexOf("Pete");
		System.out.println("location of Pete: " + location);
		
		band.remove(location);
		System.out.println(band);
		
		band.add(1, "George");
		System.out.println(band);
		System.out.println("At index 2: " + band.get(2));
		
		
		String memberRemoved = band.remove(2);
		System.out.println(band);
		System.out.println("got shot: " + memberRemoved);
		
		
		band.add("Yoko");
		for (String member : band) {
			System.out.print(member + "  ");
		}
		System.out.println();
		
//		for (int i = band.size() - 1; i >= 0; i--) {
//			band.remove(i);
//			System.out.println(band);
//		}
		
//		while(band.size() > 0){
//			band.remove(0);
//			System.out.println(band);
//		}
		
		band.clear();
		System.out.println(band);
		
		
	}

}
