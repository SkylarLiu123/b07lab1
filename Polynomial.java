import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;


public class Polynomial{
	//fields
	public double [] coefficients;
	public int [] exponents;
	
	//methods
	public Polynomial() {
		coefficients = new double[0];
		exponents = new int[0];
	}
	
	public Polynomial(double[] input_c, int [] input_e) {
		//assume input_c and input_e has the same length
        int len = input_c.length;
        if (len == 0){
            coefficients = new double[0];
            exponents = new int[0];
            return;
        }
        coefficients = new double[len];
        exponents = new int[len];
        for (int i = 0; i < len; i++) {
        	coefficients[i] = input_c[i];
        	exponents[i] = input_e[i];
        }
    }

    // public Polynomial(File file) throws FileNotFoundException{
    //     List<Double> coeffs = new ArrayList<>();
    //     List<Integer> exps = new ArrayList<>();
    //     Scanner scanner = new Scanner(file); 
    //     if (scanner.hasNextLine()){
    //         String line = scanner.nextLine();
    //         String[] terms = line.split("(?=[+-])");

    //         for (String term: terms){
    //             // int i = 0; 
    //             double coeff;
    //             int exp;
    //             if (term.contains("x")) {
    //                 String[] parts = term.split("x");
    //                 if (parts.length == 0){
    //                     coeff = 1.0;
    //                     exp = 0;
    //                 }
    //                 else if ((parts.length != 0) && (!parts[0].isEmpty())){
    //                     //reach here if it has coefficients  
    //                     if (parts[0].equals("+")){
    //                         coeff = 1.0;
    //                     }                   
    //                     else if (parts[0].equals("-")){
    //                         coeff = -1.0;
    //                     }
    //                     else{
    //                         coeff = Double.parseDouble(parts[0]);
    //                     }
    //                 }
    //                 if ((parts.length >= 2) && (!parts[1].isEmpty())){                       
    //                     exp = Integer.parseInt(parts[1]);
    //                 }
                    
    //             }
    //             else {
    //                 // constant term
    //                 coeff = Double.parseDouble(term);
    //                 exp = 0; 
    //             }

    //             // Add the coefficient and exponent to lists
    //             coeffs.add(coeff);
    //             exps.add(exp);
    //         }
    //     }
    //     scanner.close();

    //     // Convert lists to arrays
    //     coefficients = new double[coeffs.size()];
    //     exponents = new int[exps.size()];
    //     for (int i = 0; i < coeffs.size(); i++) {
    //         coefficients[i] = coeffs.get(i);
    //         exponents[i] = exps.get(i);
    //     }
    // }

    public Polynomial(File file) throws FileNotFoundException{
        List<Double> coeffs = new ArrayList<>();
        List<Integer> exps = new ArrayList<>();
        Scanner scanner = new Scanner(file); 
        if (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] terms = line.split("(?=[+-])");

            for (String term: terms){
                // int i = 0; 
                double coeff = 1.0;
                int exp = 1;
                if (term.contains("x")) {
                    String[] parts = term.split("x");
                    if (parts.length == 0){
                        coeff = 1.0;
                        exp = 1;
                    }
                    else{
                        if (parts[0].length() == 0 || parts[0].equals("+")){
                            coeff = 1.0;
                        }
                        else if (parts[0].equals("-")){
                            coeff = -1.0;
                        }
                        else{
                            coeff = Double.parseDouble(parts[0]);
                        }
                        //exponents
                        if (parts.length >= 2){
                            if (parts[1].length() == 0){
                                System.out.println("here");
                                exp = 1;
                            }
                            else{
                                exp = Integer.parseInt(parts[1]);
                            }
                        }
                    }
                        
                }
                else {
                    // constant term
                    coeff = Double.parseDouble(term);
                    exp = 0; 
                }

                // Add the coefficient and exponent to lists
                coeffs.add(coeff);
                exps.add(exp);
            }
        }
        scanner.close();

        // Convert lists to arrays
        coefficients = new double[coeffs.size()];
        exponents = new int[exps.size()];
        for (int i = 0; i < coeffs.size(); i++) {
            coefficients[i] = coeffs.get(i);
            exponents[i] = exps.get(i);
        }
    }

	public Polynomial rm_redundant() {
		//assume the length of input coefficients and exponents have the same length

        if (this.coefficients.length != this.exponents.length){
            return new Polynomial();
        }
		int len = this.coefficients.length;
        if (len <= 0){
            return new Polynomial();
        }
        //collect terms with same power
        for (int i = 0; i < len; i ++){
            for (int j = i + 1; j < len; j++){
                if (this.exponents[i] == this.exponents[j]){
                    this.coefficients[i] += this.coefficients[j];
                    this.coefficients[j] = 0; //mark to be removed
                }
            }
        }
        //remove terms with coefficient 0
		int nonzero_c = 0;
		for (int i = 0; i < len; i++) {
			if (this.coefficients[i] != 0) {
				nonzero_c++;
			}
		}
		if (nonzero_c == 0) {
			return new Polynomial();
		}
		double [] result_c = new double[nonzero_c];
		int [] result_e = new int[nonzero_c];
		int res_index = 0;
		for (int i = 0; i < len; i++) {
			if (this.coefficients[i] != 0) {
				result_c[res_index] = this.coefficients[i];
				result_e[res_index] = this.exponents[i];

				res_index++;
			}
		}
	    return new Polynomial(result_c, result_e);
	}
	
	public Polynomial add(Polynomial poly2) {
		int len1 = this.coefficients.length;
		int len2 = poly2.coefficients.length;
		int maxLen = this.coefficients.length + poly2.coefficients.length;
		
		double [] result_c = new double[maxLen];
		int [] result_e = new int[maxLen];
		
		for (int i = 0; i< len1; i++) {
			result_e[i] = this.exponents[i];
			result_c[i] = this.coefficients[i];
		}
		
        int res_index = len1;
		for (int j = 0; j < len2; j++) {
            result_e[res_index] = poly2.exponents[j];
			result_c[res_index] = poly2.coefficients[j];
            res_index++;			
		}
		Polynomial with_Red = new Polynomial(result_c, result_e);
	    return with_Red.rm_redundant();
	}
	
	public double evaluate(double x) {
		double result = 0;
        int len = this.coefficients.length;
        for (int i = 0; i < len; i++) {
        	result = result + (this.coefficients[i] * Math.pow(x, this.exponents[i]));
        }
        return result;
	}
	
	public boolean hasRoot(double num) {
		return this.evaluate(num) == 0;	
	}
	
    public Polynomial multiply(Polynomial poly2) {
        int len1 = this.coefficients.length;
        int len2 = poly2.coefficients.length;
        double [] res_c = new double[len1 * len2];
        int [] res_e = new int[len1 * len2];

        int res_index = 0;
        for (int i = 0; i < len1; i++){
            for (int j = 0; j < len2; j++){
                res_c[res_index] = this.coefficients[i] * poly2.coefficients[j];
                res_e[res_index] = this.exponents[i] + poly2.exponents[j];
                res_index++;
            }
        }
        Polynomial temp = new Polynomial(res_c, res_e);
        return temp.rm_redundant();
    }

    public String get_term(Double coeff, int exp){
        //assume no redundant
        if (exp == 0){ //when constant term
            return String.valueOf(coeff);
        }
        // reaches here when exp is not 0
        if (coeff == 1.0) {
            return "x" + exp;
        }
        if (coeff == -1.0) {
            return "-x" + exp; 
        }
        else {
            return String.valueOf(coeff) + "x" + exp;
        }

    }
    
    public String polyToStr(){ 
        Polynomial poly = this.rm_redundant();
        int len = poly.coefficients.length;
        if (len == 0){
            return ""; //if empty polynomial return empty string
        }
        String result = "";
        String temp = get_term(this.coefficients[0], this.exponents[0]);
        result = result + temp;
        for (int i = 1; i < len; i++){
            temp = get_term(poly.coefficients[i], poly.exponents[i]);
            if (temp.startsWith("-")){
                result = result + temp;
            }
            else{
               result = result + "+" + temp;
            }
        }
        return result;
    }

    public void saveToFile(String file_name){
        if (file_name == null || file_name.trim().isEmpty()) {
            System.out.println("Invalid file name.");
        }
        try{
            FileWriter myWriter = new FileWriter(file_name);
            String content = this.polyToStr();
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}


