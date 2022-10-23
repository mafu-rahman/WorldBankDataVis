package analyser;

public class Analyser {
	
	private double mean;
	
	public Analyser() {
		this.mean = 0;
	}
	
	public double calcMean(double sum, double totalnums) {
		if(sum == 0) {
			throw new IllegalArgumentException("Error: Attemping to divide zero by a number!");
		}
		this.mean = (double) sum / (double) totalnums;
		return this.mean;
	}
	
	public void printMean() {
		System.out.println("The mean is " + this.mean);
	}
	
	public double getMean() {
		return this.mean;
	}
	
	
}
