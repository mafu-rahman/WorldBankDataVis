package analyser;

public class Analyser {
	
	private int mean;
	
	public int calcMean(int sum, int totalnums) {
		this.mean = sum / totalnums;
		return this.mean;
	}
	
	public void printMean() {
		System.out.println("The mean is " + this.mean);
	}
	
}
