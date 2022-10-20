package analyser;

public class Analyser {
	
	private int mean;
	private double doubleMean;
	
	public int calcMean(int sum, int totalnums) {
		this.mean = sum / totalnums;
		return this.mean;
	}
	
	public double calcMean(double sum, double totalnums) {
		this.doubleMean = (double) sum / (double) totalnums;
		return this.doubleMean;
	}
	
	public void printMean() {
		System.out.println("The mean is " + this.mean);
	}
	
}
