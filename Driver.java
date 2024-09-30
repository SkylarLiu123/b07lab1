import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        //empty polynomial
        //test no arg constructor
        Polynomial empty1 = new Polynomial(); 
        System.out.println(Arrays.toString(empty1.coefficients));
        System.out.println(Arrays.toString(empty1.exponents));
        System.out.println(empty1.polyToStr());
        //test constructor with 2 input args
        double [] c1 = new double[]{123,2,32948.34};
		int [] e1 = new int[]{5,9,342};
        Polynomial empty2 = new Polynomial(c1,e1);
        System.out.println(Arrays.toString(empty2.coefficients));
        System.out.println(Arrays.toString(empty2.exponents));
        System.out.println(empty2.polyToStr());
        //test constructor with input file
        try {
        File myObj = new File("firsttestsaveToFile.txt");
        Polynomial empty3 = new Polynomial(myObj);
        System.out.println(Arrays.toString(empty3.coefficients));
        System.out.println(Arrays.toString(empty3.exponents));
        System.out.println(empty3.polyToStr());
        } catch (IOException e) {
        System.out.println("An error occurred.");
        }      

        //test rm_redundant
        double [] co = new double[] {1,5};
		int [] exp = new int[] {10,20};
        Polynomial mypoly = new Polynomial(co, exp);
        System.out.println(Arrays.toString(mypoly.exponents));
        String test = mypoly.polyToStr();
        // mypoly.saveToFile("firsttestsaveToFile");
        System.out.println(test);

        //test add
        double [] co1 = new double[] {-1,5};
		int [] exp1 = new int[] {2,0};
        Polynomial poly1 = new Polynomial(co1, exp1);
        double [] co2 = new double[] {3,-2};
		int [] exp2 = new int[] {1,0};
        Polynomial poly2 = new Polynomial(co2, exp2);
        Polynomial sum = poly1.add(poly2);
        String testAdd = sum.polyToStr();
        System.out.println(testAdd);

        //test multiply
        Polynomial mul = poly1.multiply(poly2);
        String testMul = mul.polyToStr();
        System.out.println(poly1.polyToStr());
        System.out.println(poly2.polyToStr());
        System.out.println(testMul);


    }
}
