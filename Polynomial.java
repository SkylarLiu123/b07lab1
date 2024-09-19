public class Polynomial{
	//fields
	double [] coefficients;
	
	//methods
	public Polynomial() {
		coefficients = new double[]{0};
	}
	
	public Polynomial(double[] input) {
        int len = input.length;
        coefficients = new double[len];
        for (int i = 0; i < len; i++) {
        	coefficients[i] = input[i];
        }
    }
	
	public Polynomial add(Polynomial poly2) {
		int len1 = this.coefficients.length;
		int len2 = poly2.coefficients.length;
		int minLen = Math.min(len1, len2);
		int maxLen = Math.max(len1, len2);
		double [] result = new double[maxLen];
		
		for (int i = 0; i < minLen; i++) {
			result[i] = this.coefficients[i] + poly2.coefficients[i];
		}
		for (int j = minLen; j< maxLen; j++) {
			if (len1 > len2) {
				result[j] = this.coefficients[j];
			}
			else {
				result[j] = poly2.coefficients[j];
			}
		}
	    return new Polynomial(result);
	}
	
	public double evaluate(double x) {
		double result = 0;
        int len = this.coefficients.length;
        for (int i = 0; i < len; i++) {
        	result = result + (this.coefficients[i] * Math.pow(x, i));
        }
        return result;
	}
	
	public boolean hasRoot(double num) {
		return this.evaluate(num) == 0;	
	}
		
}