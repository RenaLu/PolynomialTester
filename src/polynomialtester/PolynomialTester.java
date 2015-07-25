
package polynomialtester;
import java.util.ArrayList;
import java.awt.Color;
import java.util.*;

public class PolynomialTester {
    
    Polynomial P1, P2;
    RationalFunction r;
    Scanner in = new Scanner(System.in);
    

    public static void main(String[] args) {
        PolynomialTester run = new PolynomialTester();
        String GoAgain = ""; // this variable determines whether or not the program repeats
        System.out.println("Welcome to the Polynomial Master!");
        
        do { // General start ups for the program
            System.out.println("");
            System.out.println("Would you like to run the test cases for this program, or enter your own function?");
            System.out.println("To run test cases, type 'test'. To enter your own, type 'function'");
            String start = run.in.next();
            System.out.println("");
            if (start.equalsIgnoreCase("test")) { // If they want to run test cases, another method will run
                run.runTestCases();
            }
            else if (start.equalsIgnoreCase("function")) { // If they want to make their own function
                System.out.println("Would you like to enter a polynomial or rational function? (type: 'polynomial' or 'rationalfunction'");
                String type = run.in.next();
                System.out.println("");
                if (type.equalsIgnoreCase("Polynomial")){ // If they are entering a polynomial, another method will run
                    run.StartMenuPoly();
                }
                else if (type.equalsIgnoreCase("RationalFunction")) { // If they are entering a rational function, another method will run
                    run.StartMenuRF();
                }
                else { // if they enter something else then the program will exit
                    System.out.println("Error: invalid answer. The program will now exit.");
                    System.exit(0);
                }
            }
            else { // if they enter something else then the program will exit
                System.out.println("Error: invalid start type. The program will now exit");
                System.exit(0);
            }
            
            System.out.println("");
            System.out.println("Would you like to use the program again? Type: 'Yes' or 'No'"); 
            GoAgain = run.in.next();  
            
        } while (GoAgain.equalsIgnoreCase("Yes") || GoAgain.equals(""));
        
        System.out.println("");
        System.out.println("Goodbye for now!");
        System.exit(0);
    }
    
    public void runTestCases() { // Running test cases
        Polynomial a = new Polynomial("36x^5+39x^4-81x^3+41x^2-35x^2");
        Polynomial b = new Polynomial("-30x^7+60x^6+35x^5+24x^2-48x");
        Polynomial c = new Polynomial("9x^3-66x^2+85x");
        Polynomial d = new Polynomial("3x-5");
        Polynomial e = new Polynomial("5x^5-4");
        Polynomial f = new Polynomial("12x^2-3x+5");
        Polynomial g = new Polynomial("-x^3-3x+8");
        Polynomial h = new Polynomial("x^2+10");
        Polynomial j = new Polynomial("x^3-4x");
        
        //Testing for addition
        System.out.println("");
        System.out.println("**Testing Addition**");
        System.out.print("A)");
        Polynomial sum1 = a.addPolynomial(b);
        System.out.println("The sum of " + a.displayPolynomial() + " and " + b.displayPolynomial() + " is:");
        System.out.println(sum1.displayPolynomial());
        System.out.println("");
        
        System.out.print("B)");
        Polynomial sum2 = c.addPolynomial(d);
        System.out.println("The sum of " + c.displayPolynomial() + " and " + d.displayPolynomial() + " is:");
        System.out.println(sum2.displayPolynomial());
        System.out.println("");
        
        System.out.print("C)");
        Polynomial sum3 = e.addPolynomial(f);
        System.out.println("The sum of " + e.displayPolynomial() + " and " + f.displayPolynomial() + " is:");
        System.out.println(sum3.displayPolynomial());
        System.out.println("");
        
        System.out.print("D)");
        Polynomial sum4 = g.addPolynomial(h);
        System.out.println("The sum of " + g.displayPolynomial() + " and " + h.displayPolynomial() + " is:");
        System.out.println(sum4.displayPolynomial());
        System.out.println("");
        
        //Testing for subtraction
        System.out.println("**Testing Subtraction**");
        System.out.print("A)");
        Polynomial diff1 = a.subtractPolynomial(d);
        System.out.println("The difference between " + a.displayPolynomial() + " and " + d.displayPolynomial() + " is:");
        System.out.println(diff1.displayPolynomial());
        System.out.println("");
        
        System.out.print("B)");
        Polynomial diff2 = f.subtractPolynomial(c);
        System.out.println("The difference between " + f.displayPolynomial() + " and " + c.displayPolynomial() + " is:");
        System.out.println(diff2.displayPolynomial());
        System.out.println("");
        
        System.out.print("C)");
        Polynomial diff3 = e.subtractPolynomial(b);
        System.out.println("The difference between " + e.displayPolynomial() + " and " + b.displayPolynomial() + " is:");
        System.out.println(diff3.displayPolynomial());
        System.out.println("");
        
        System.out.print("D)");
        Polynomial diff4 = h.subtractPolynomial(g);
        System.out.println("The difference between " + h.displayPolynomial() + " and " + g.displayPolynomial() + " is:");
        System.out.println(diff4.displayPolynomial());
        System.out.println("");
        
        
        //Testing for multiplication
        System.out.println("**Testing Multiplication**");
        System.out.print("A)");
        Polynomial prod1 = a.multiplyPolynomial(f);
        System.out.println("The product of " + a.displayPolynomial() + " and " + f.displayPolynomial() + " is:");
        System.out.println(prod1.displayPolynomial());
        System.out.println("");
        
        System.out.print("B)");
        Polynomial prod2 = b.multiplyPolynomial(e);
        System.out.println("The difference of " + b.displayPolynomial() + " and " + e.displayPolynomial() + " is:");
        System.out.println(prod2.displayPolynomial());
        System.out.println("");
        
        System.out.print("C)");
        Polynomial prod3 = c.multiplyPolynomial(d);
        System.out.println("The difference of " + c.displayPolynomial() + " and " + d.displayPolynomial() + " is:");
        System.out.println(prod3.displayPolynomial());
        System.out.println("");
        
        
        //Testing for division
        System.out.println("**Testing for division**");
        System.out.print("A) ");
        System.out.println(a.displayPolynomial() + " / " + f.displayPolynomial() + " is:");
        f.displayDivision(a);
        System.out.println("");
        
        System.out.print("B)");
        System.out.println(b.displayPolynomial() + " / " + e.displayPolynomial() + " is:");
        e.displayDivision(b);
        System.out.println("");
        
        System.out.print("C)");
        System.out.println(c.displayPolynomial() + " / " + d.displayPolynomial() + " is:");
        d.displayDivision(c);
        System.out.println("");
        
        
        //Testing of finding and graphing
        System.out.println("**Testing for Finding Derivatives & Graphing of Polynomial Functions**");
        
        j.findFirstDerivative();
        j.findSecondDerivative();
        j.findNthDerivative(3);
        j.displayFirstDerivative();
        j.displaySecondDerivative();
        j.displayNthDerivative(3);
        
        System.out.println("The Program will now graph: " + j.displayPolynomial());
        j.plotPoints();
        j.plotFirstDerivative();
        j.plotSecondDerivative();
        j.initializeWindow();
        System.out.println("");
        
        //Testing rational functions
        ArrayList<Integer> coe = new ArrayList();
        ArrayList<Integer> exp = new ArrayList();
        coe.add(1);
        exp.add(0);
        
        System.out.println("**Testing Rational Functions**");
        Polynomial denom = new Polynomial("x^3+5x^2-x-5");
        Polynomial num = new Polynomial("1");
        System.out.println(num.displayPolynomial() + " / " + denom.displayPolynomial());
        System.out.println("");
        RationalFunction test = new RationalFunction(num, denom);
        test.setFieldValues(600, 600, -10, 10, -5, 5);
        test.displayFirstDerivative();
        test.displaySecondDerivative();
        test.findRoots();
        test.plotFirstDerivative();
        test.plotSecondDerivative();
        System.out.println("The Program will now graph: " + test.displayRationalFunction());
        test.initializeWindow();
    }
    
    public void StartMenuRF() { // Method sets up rational function route
        System.out.println("Please enter your rational function in this format: ax^2+bx+c/dx^2+ex+f");
        RationalFunction r = new RationalFunction(in.next());
        
        // This will launch the graphics for rational functions
        userEnterGraphicRF(r);
    }
    
    public void StartMenuPoly() { // Method will set up polynomials
        System.out.println("To start, please enter your polynomial in this format: ax^2+bx+c");
        P1 = new Polynomial(in.next());
        
        System.out.println("What would you like to do with this polynomial?");
        PrintOption(); // refers to another method, will show all of the enter options
        String option = in.next();
        
        // if the user wants to add, subtract, multiply or divide, they will be prompted to enter another polynomial
        if (option.equalsIgnoreCase("A") || option.equalsIgnoreCase("S") || option.equalsIgnoreCase("M") || option.equalsIgnoreCase("D")) {
            System.out.println("Enter a second polynomial in this format: ax^2+bx+c");
            P2 = new Polynomial(in.next());
        }
        
        char opt = option.charAt(0); // converts the entered option to a String for the switch statement
        
        //Switch statment goes through all of the options 
        switch(opt) {
            case 'A': // Addition case
                System.out.println("The sum of " + P1.displayPolynomial() + " and " + P2.displayPolynomial() + " is:");
                Polynomial sum = P1.addPolynomial(P2); // adds
                System.out.println(sum.displayPolynomial()); // displays
                System.out.println("");
                break;
            case 'S': // Subtraction case
                System.out.println("The difference of " + P1.displayPolynomial() + " and " + P2.displayPolynomial() + " is:");
                Polynomial difference = P1.subtractPolynomial(P2); //subtracts
                System.out.println(difference.displayPolynomial()); //displays
                System.out.println("");
                break;
            case 'M': // Multiplication case
                System.out.println("The product of " + P1.displayPolynomial() + " and " + P2.displayPolynomial() + " is:");
                Polynomial product = P1.multiplyPolynomial(P2); // multiplies
                System.out.println(product.displayPolynomial()); // displays
                System.out.println("");
                break;
            case 'D': // Division case
                launchDivision(); // Method launches more division commands
                System.out.println("");
                break;
            case 'X': // first and second derivative case
                P1.displayFirstDerivative(); //calculates and displays the first derivative
                P1.displaySecondDerivative(); //calculates and displays the second derivative
                System.out.println("");
                break;
            case 'N': // nth derivative
                System.out.println("What derivative would you like to print? (enter an integer)");
                int n = in.nextInt(); // gets n
                P1.displayNthDerivative(n); //calculates and displays the nth derivative
                System.out.println("");
                break;
            case 'G': // graphing case
                userEnterGraphicPoly();
                break;
            default: // otherwise, an invalid entry
                System.out.println("Error: invalid entry. The program will now exit");
                System.exit(0);
        }
    }
    
    public void PrintOption() { //This method prints all of the options
        System.out.println("OPTIONS:");
        System.out.println("To Add: 'A'");
        System.out.println("To Subtract, type: 'S'");
        System.out.println("To Multiply, type: 'M'");
        System.out.println("To Divide, type: 'D'");
        System.out.println("To Print the first and second derivatives: 'X'");
        System.out.println("To Print the nth derivative: 'N'");
        System.out.println("To Graph, type: 'G'");
        System.out.println("***");
    }
    
    public void launchDivision(){ // This method launches division
        System.out.println("Do you want to divide your first polynomial by your second polynomial? Enter 'Yes' or 'No'");
        System.out.println("If 'No', the program will divide your second polynomial by your first one");
        String Ans1 = in.next(); // asks which way the polynomials are divided
        System.out.println("");
        if (Ans1.equalsIgnoreCase("Yes")) {
            System.out.println(P1.displayPolynomial() + " divided by " + P2.displayPolynomial() + " is:");
            P2.displayDivision(P1);
        }
        else if (Ans1.equalsIgnoreCase("No")) {
            System.out.println(P2.displayPolynomial() + " divided by " + P1.displayPolynomial() + " is:");
            P1.displayDivision(P2);
        }
        else { // invalid entry, system will exit
            System.out.println("Error: You did not type 'Yes' or 'No'. The program will exit now");
            System.exit(0);
        }
    }
    
    public void userEnterGraphicPoly() { // Method that graphs a polynomial
        // User sets the width, length of screen as well as min/max y/x
        System.out.println("");
        System.out.println("Enter the screen width you would like (in pixels)");
        P1.width = in.nextInt();
        System.out.println("Enter the screen height you would like (in pixels)");
        P1.length = in.nextInt(); 
        System.out.println("Enter the minimum x value you want on the graph (an integer)");
        P1.xMin = in.nextInt();
        System.out.println("Enter the maximum x value you want on the graph (an integer)");
        P1.xMax = in.nextInt();
        System.out.println("Enter the minimum y value you want on the graph (an integer)");
        P1.yMin = in.nextInt();
        System.out.println("Enter the maximum x value you want on the graph (an integer)");
        P1.yMax = in.nextInt();

        //Array for y-coordinates of points on function
        P1.yval = new double[P1.width];
        //Calculates increments of x and y values
        //Each pixel across the screen has an increment of x or y based on the values calculated here
        P1.increY = ((double)(P1.yMax-P1.yMin))/(double)P1.length;
        P1.increX = ((double)(P1.xMax-P1.xMin))/(double)P1.width;
        
        // These methods, found in the polynomial class will graph the function, first and second derivative
        P1.plotPoints();
        System.out.println("The Program will now graph: " + P1.displayPolynomial());
        P1.plotFirstDerivative();
        P1.plotSecondDerivative();
        P1.initializeWindow();  
        
        
    }
    
    public void userEnterGraphicRF(RationalFunction r) { // Method that graphs a Rational Functions
        // User sets the width, length of screen as well as min/max y/x
        System.out.println("");
        System.out.println("Enter the screen width you would like (in pixels)");
        int width = in.nextInt();
        System.out.println("Enter the screen height you would like (in pixels)");
        int length = in.nextInt(); 
        System.out.println("Enter the minimum x value you want on the graph (an integer)");
        int xMin = in.nextInt();
        System.out.println("Enter the maximum x value you want on the graph (an integer)");
        int xMax = in.nextInt();
        System.out.println("Enter the minimum y value you want on the graph (an integer)");
        int yMin = in.nextInt();
        System.out.println("Enter the maximum x value you want on the graph (an integer)");
        int yMax = in.nextInt();
        
        r.setFieldValues(width, length, xMin, xMax, yMin, yMax);
        
        // These methods, found in the polynomial class will graph the function, first and second derivative
        r.plotPoints();
        r.displayFirstDerivative();
        r.displaySecondDerivative();
        r.findRoots();
        r.plotFirstDerivative();
        r.plotSecondDerivative();
        System.out.println("The Program will now graph: " + r.displayRationalFunction());
        r.initializeWindow();
        System.out.println("");
    }
    
}
