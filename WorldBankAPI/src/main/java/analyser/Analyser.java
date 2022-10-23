package analyser;

public class Analyser {
	
	private double mean;
	
	/**
	 * Constructor method
	 */
	public Analyser() {
		this.mean = 0;
	}
	
	/**
	 * Method used to compute mean given two parameters
	 * @param sum  the total value of all elements
	 * @param totalnums total number of elements from which mean is to be derived
	 * @return mean the mean value
	 */
	public double calcMean(double sum, double totalnums) {
		if(sum == 0) {
			throw new IllegalArgumentException("Error: Attemping to divide zero by a number!");
		}
		this.mean = (double) sum / (double) totalnums;
		return this.mean;
	}
	
	/**
	 * Print the mean to console
	 */
	public void printMean() {
		System.out.println("The mean is " + this.mean);
	}
	
	/**
	 * Getter Method
	 * @return the mean
	 */
	public double getMean() {
		return this.mean;
	}
	
	
}
