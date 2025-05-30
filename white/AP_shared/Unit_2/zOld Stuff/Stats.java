import java.util.ArrayList;
import java.util.List;

public class Stats {
	List<Double> myList;
	
	public Stats(List<Double>list) {
		myList = list;
	}
	public double getMean() {
		if (myList.size() == 0)
			return 0;
		
		double sum = 0;

		for (int k=0; k<myList.size(); k++)
			sum += myList.get(k);

		return sum/myList.size();
	}
	//http://standard-deviation.appspot.com
	public double getStdDeviation() {
		if (myList.size() == 0)
			return 0;
		
		double mean = getMean();
		double diffSum = 0;
		for (int k=0; k<myList.size(); k++)
			diffSum += Math.pow(mean-myList.get(k), 2);

		diffSum /= myList.size()-1;

		return Math.sqrt(diffSum);
	}

	public double getMode() {
		if (myList.size() == 0)
			return 0;
		
		List<NumVal> numList = new ArrayList<NumVal>();
		for (double val : myList) {
			boolean found = false;
			 
			for (NumVal nVal : numList) {
				if (nVal.equals(val)) {
					nVal.incCount();
					found=true;
					break;
				}
			}
			if (!found)
				numList.add(new NumVal(val));

		}
		//get the NumVal with the max count
		int maxCnt = numList.get(0).getCount();
		Double mode = numList.get(0).getVal();
		for (NumVal nVal : numList) {
			if (nVal.getCount() > maxCnt) {
				maxCnt = nVal.getCount();
				mode = nVal.getVal();
			}
		}
		return mode;
	}
	public double getMax() {
		if (myList.size() == 0)
			return 0;
		
		double max = myList.get(0);

		for (Double val: myList)
			 if (val > max)
				 max = val;

		return max;
	}
	public double getMin() {
		if (myList.size() == 0)
			return 0;
		
		double min = myList.get(0);

		for (Double val: myList)
			 if (val < min)
				 min = val;

		return min;
	}
	public String toString() {
		String str = "";
		str += String.format("Average .............. %8.2f\n", getMean());
		str += String.format("Standard deviation ... %8.2f\n", getStdDeviation());
		str += String.format("Mode ................. %8.2f\n", getMode());
		str += String.format("Max value ............ %8.2f\n", getMax());
		str += String.format("Min value ............ %8.2f\n", getMin());
		
		return str;
	}


}
