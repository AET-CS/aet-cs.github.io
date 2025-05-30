
public class NumVal  {
	
	private double num;
	private int count;
	
	public NumVal (double num) {
		this.num=num;
		count=1;
	}
	
	public boolean equals( Object obj) {
		Double val = (Double)obj;
		if (Math.abs(num-val) < .0000001)
			return true;
		else 
			return false;
	}
	public double getVal() {
		return num;
	}
	
	public void incCount() {
		count++;
	}
	public int getCount() {
		return count;
	}

}
