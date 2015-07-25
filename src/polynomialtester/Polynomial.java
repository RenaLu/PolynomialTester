
package polynomialtester;

import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.io.*;
import static java.lang.Math.round;
import java.util.Collections;


public class Polynomial extends JFrame {
    //FIELDS
    ArrayList<Integer> exponents = new ArrayList();
    ArrayList<Integer> coefficients = new ArrayList();
    
    //CONSTRUCTORS
    public Polynomial(String p){
        fillArrayList(p);
    }
    
    public Polynomial (ArrayList<Integer> coef, ArrayList<Integer> expo){
        this.coefficients = coef;
        this.exponents = expo;
    }
    
    //Fills array lists for coefficients and exponents if the constructor that takes a string is called
    public void fillArrayList(String p){
        ArrayList<Integer> indexX = new ArrayList();
        ArrayList<Integer> indexSigns = new ArrayList();
        
        for (int i = 0; i < p.length(); i++) {//Keeps track of index of "x"'s and "+"'s in the polynomial
            String letter = Character.toString(p.charAt(i));

            if (letter.equals("x")){
                indexX.add(i);
            }
            
            if (i==0 && letter.equals("-")){}
            
            else{
                if (letter.equals("+") || letter.equals("-")){
                indexSigns.add(i);
                }
            }
        }
        
        //Reads integers
        if (indexX.isEmpty()){
            coefficients.add(Integer.parseInt(p));
            exponents.add(0);
        }
        
        else{
            //Appends all of the exponents of x in the function

            //Set different run times for different functions
            int runTimes;

            //If the function ends with an x
            if (indexX.size()>indexSigns.size()) {
                runTimes = indexX.size()-1;
            }

            //If the function does not end with an x
            else {
                runTimes = indexX.size();
            }

            String exponent;
            for (int i = 0; i < runTimes; i++) {
                String check = Character.toString(p.charAt(indexX.get(i)+1));//Checks the character in the string after each x term

                if (check.equals("^")) {//If the x term has an exponent > 1
                    exponent = p.substring((indexX.get(i)+2),indexSigns.get(i));
                }
                else {
                    exponent = "1";
                }
                    exponents.add(Integer.parseInt(exponent)); 
            }

            if (runTimes == indexX.size()-1) {//If the function ends with a x term, not an integer term
                if (!p.substring(p.length()-1, p.length()).equals("x")){//If the last x term has an exponent larger than 1, the last character will not be an x
                    exponent = p.substring((indexX.get(indexX.size()-1)+2), p.length());
                    exponents.add(Integer.parseInt(exponent));
                }
                else{
                    exponents.add(1);
                }
            }

            if (indexSigns.size()==indexX.size()){//last term has no x term, exponent of x is 0 because it is an integer term
                    exponents.add(0);
                }


            //Appends all of the coefficients in the function
            String coef="";

            //Set different run times for different functions

            int runTimes2;
            if(indexSigns.size()==indexX.size()){//If the function ends with an integer term
                runTimes2 = indexSigns.size();
            }

            else {
                runTimes2 = indexX.size();
            }

            int indexSign, indexXterm;
            for (int i = 0; i < runTimes2; i++) {     
                    if (i == 0) {//first term
                        if (p.substring(0, 1).equals("x")){//checks for cases where the coefficient is 1 for the first term
                            coef = "1";
                        }
                        else if (p.substring(0, 2).equals("-x")){//checks for cases where the coefficient for the first term is -1
                            coef = "-1";
                        }
                        else {
                            coef = p.substring(0, indexX.get(i));
                        }
                    }

                    else{
                        indexSign = indexSigns.get(i-1);
                        indexXterm = indexX.get(i);
                        if (!p.substring(indexSign+1, indexSign+2).equals("x")) {//checks for cases where the coefficient is not 1 for the rest of the terms
                            if (p.substring(indexSign, indexSign+1).equals("-")) {
                                coef = "-" + p.substring(indexSign+1, indexXterm);
                            }
                            else{
                                coef = p.substring(indexSign+1, indexXterm);
                            }

                        }
                        else{
                            if (p.substring(indexSign, indexSign+1).equals("-")) {
                                coef = "-1";
                            }
                            else{
                            coef = "1";
                            }
                        }

                    }
                    coefficients.add(Integer.parseInt(coef));
                }

            if (indexSigns.size()==indexX.size()) {//adds the last integer if the last term is an integer term
                coef = p.substring (indexSigns.get(indexSigns.size()-1), p.length());
                coefficients.add(Integer.parseInt(coef));
            }
        }

    }
    
    //Function for adding two Polynomials  
    public Polynomial addPolynomial(Polynomial Other){ 
        //Creates ArrayLists that will store the coefficients and exponents
        ArrayList<Integer> ExpNums = new ArrayList();
        ArrayList<Integer> SumNumsCo = new ArrayList();
        ArrayList<Integer> ExpNumsOld = new ArrayList();
        ArrayList<Integer> SumNumsCoNew = new ArrayList();
        //Stores the values from both Polynomials in their respective ArrayList
        for (int i = 0; i < this.exponents.size(); i++) {
            ExpNums.add(this.exponents.get(i));
            SumNumsCo.add(this.coefficients.get(i));
            ExpNumsOld.add(this.exponents.get(i));
            
        }
        for (int j = 0; j < Other.exponents.size(); j++) {
            ExpNums.add(Other.exponents.get(j));
            SumNumsCo.add(Other.coefficients.get(j));
            ExpNumsOld.add(Other.exponents.get(j));

        }
        //Sorts the Exponents from greatest to least
        Collections.sort(ExpNums);
        Collections.reverse(ExpNums);
        //Sorts the coefficients back with their old exponents after the exponents have been moved around
        for (int i = 0; i < ExpNums.size(); i++) {
            int j=0;
            while(ExpNums.get(i)!=ExpNumsOld.get(j)){
                j=j+1;
            }
            SumNumsCoNew.add(i, SumNumsCo.get(j));
            if(i<ExpNums.size()-1){
             if (ExpNums.get(i)==ExpNums.get(i+1)){
                ExpNums.remove(i);
            }
            
        }}
        //Checks both Polynomials for a situation when two coefficients have the same exponent and adds them
        for (int i = 0; i < this.exponents.size(); i++) {
            for (int j = 0; j < Other.exponents.size(); j++) {
                if(this.exponents.get(i)==Other.exponents.get(j)){
                    int Index = ExpNums.indexOf(ExpNumsOld.get(i));
                    SumNumsCoNew.add(Index, this.coefficients.get(i)+Other.coefficients.get(j));
                    SumNumsCoNew.remove(Index+1);
                }
                
            }
            
        }
        //Returns the sum as a new Polynomial
        int ZeroCase=0;
        
        for (int i = 0; i < SumNumsCoNew.size(); i++) {
            if(SumNumsCoNew.get(i)==0){
                ExpNums.set(i, 0);
            }
        }

        Polynomial Sum = new Polynomial(SumNumsCoNew, ExpNums);
        return Sum;
    }
      
    //Function for subtracting two Polynomials
    public Polynomial subtractPolynomial(Polynomial Other){ 
        //Creates two new Array Lists to store values
        ArrayList<Integer> NewCoef = new ArrayList();
        ArrayList<Integer> NewExp = new ArrayList();
        //Multiplies each coefficient value in the "Other" Polynomial by -1
        for (int i = 0; i < Other.coefficients.size(); i++) {
            NewCoef.add(i, Other.coefficients.get(i)*-1);
            NewExp.add(i,Other.exponents.get(i));
        }
        //Stores the new values in a new Polynomial and then uses the add function
        Polynomial NewOther = new Polynomial(NewCoef,NewExp);
        return this.addPolynomial(NewOther);
    }

    public Polynomial findPlaceHolders(Polynomial p) { //This is used for the multiplication and division methods only
        ArrayList<Integer> c = new ArrayList(); // coefficients
        ArrayList<Integer> e = new ArrayList(); // exponenets
        int numRuns; // a manual counter

        for(int i = p.exponents.get(0); i >= 0; i--) { // assuming this it is working from largest exponent down
            e.add(i); // adding all exponents to new list, even ones with a '0' coefficient
            numRuns = 0; // resetting the count
            for (int j = 0; j < p.exponents.size(); j++) {
                if (i == p.exponents.get(j)) { // if a coefficient for this exponent already exists, it will fill it its proper coefficient
                    c.add(p.coefficients.get(j));
                    numRuns = 1; // won't repeat on the same coefficient
                }
                else if (j==p.exponents.size()-1 && numRuns != 1) c.add(0); // if it doesn't exist, the coefficient is zero
            }
        }
        
        c.add(0); // adding 0 on end which helps in the division method (carrying down numbers in long division)
        e.add(0);

        Polynomial P3 = new Polynomial(c,e); // makes a new polynomial
        
        return P3;
    }

    public Polynomial removePlaceHolders(Polynomial p) {
        ArrayList<Integer> e = p.exponents; // exponenets
        ArrayList<Integer> c = p.coefficients; //coefficients
        
        for (int i = 0; i < p.exponents.size(); i++) {
            if (p.coefficients.get(i) == 0) { // if any of the coefficients are zero, the program will remove both the coefficient and exponent at that value
                c.remove(i); 
                e.remove(i);
                i--; // backs up now that it has lost an index
            }
        }
        
        Polynomial P = new Polynomial(c,e); //makes a new polynomial
        
        return P;
    }
    
    // Multiplies two polynomials
    public Polynomial multiplyPolynomial(Polynomial p) { //Needs to be debugged
        // find placeholders for two polynomials (makes it easier to collect like terms)
        Polynomial P1 = findPlaceHolders(this);
        Polynomial P2 = findPlaceHolders(p);

        ArrayList<Integer> c = new ArrayList(); // new arraylist for coefficients
        ArrayList<Integer> e = new ArrayList(); // new arraylist for exponents

	//multiply like terms
        for (int i = 0; i < P1.exponents.size(); i++) {
            for (int j = 0; j < P2.exponents.size(); j++) {
                int indexExp = e.indexOf(P1.exponents.get(i) + P2.exponents.get(j)); // finding the index of the added exponents (if it exsists already)
                if (indexExp == -1) { // if exponent doesn't appear in list, make a new exponent
                    e.add(P1.exponents.get(i) + P2.exponents.get(j));
                    int m = P1.coefficients.get(i)*P2.coefficients.get(j); // multiplying coefficients
                    c.add(m);
                }
                else { // otherwise, fix the coefficient at that exponent
                    int oldTerm = c.get(indexExp);
                    c.remove(indexExp); // replaces old term with old + new term of the same exponent
                    c.add(indexExp, oldTerm + P1.coefficients.get(i)*P2.coefficients.get(j));
                }
            }
        }
        
        Polynomial product = new Polynomial(c,e); // makes a new polynomial of the product
        Polynomial P = product.removePlaceHolders(product); // removes placeholders for better display
        
        return P;
    }
    
    //Divides a polynomial into another
    public Polynomial[] dividePolynomial(Polynomial p){ 
        Polynomial P1 = findPlaceHolders(this); // divisor
        Polynomial P2 = findPlaceHolders(p); // dividend
        
        ArrayList<Integer> P3e = new ArrayList(); // ArrayList for the quotient
        ArrayList<Integer> P3c = new ArrayList();
        
        for (int i =0; i< P2.coefficients.size(); i++) {
            if ((P2.exponents.get(0) - P1.exponents.get(0)) >= 0) {//if the divident is greater than the divisor, aka. if it is able to divide using the long division method
                if ((P2.coefficients.get(0) / P1.coefficients.get(0)) == ((double)(P2.coefficients.get(0)) / (double)(P1.coefficients.get(0)))) {
                    P3c.add(i, P2.coefficients.get(0) / P1.coefficients.get(0)); // dividing
                    P3e.add(i, P2.exponents.get(0) - P1.exponents.get(0)); // subtracting exponents when dividing

                    for (int j = 0; j < P1.coefficients.size()+1; j++) {
                        if (j < P1.coefficients.size()) {
                            int newP5c = P2.coefficients.get(j)-(P1.coefficients.get(j) * P3c.get(i)); 
                        // subtracting the product of the divisor and a term of the quotient by a term in the divident (like the long division model)
                            P2.coefficients.remove(j); // replaying it with the new value
                            P2.coefficients.add(j, newP5c);
                        }
                    }

                    P2.coefficients.remove(0); // removing the first term in the new "dividend" as it equals 0 anyways
                    P2.exponents.remove(0); // this shifts the entire Array over, which allows the loop to work
                }
                else { // if the leading coefficients are not divisible with an integer quotient, an error occurs
                    System.out.println("The program is not able to complete division because it currently doesn't support non-integer coefficients");
                    System.out.println("System will now exit.");
                    System.exit(0);
                }
            }
            else if (i==0){ // if the degree of the diviend is less than the divsor, an error occurs
                System.out.println("The program is not able to complete division because the degree of the divisor is larger than the dividend");
                System.out.println("System will now exit.");
                System.exit(0);
            }
        }        
        
        Polynomial quotient = new Polynomial(P3c,P3e); // making a new polynomial from the ArrayList P3
        Polynomial q = removePlaceHolders(quotient); // removing placeholders for better display

        Polynomial remainder = new Polynomial(P2.coefficients,P2.exponents); // making a new polynomial from ArrayList P2	
        Polynomial r = removePlaceHolders(remainder); // removing placeholders for better display
         
        Polynomial[] result = {q, r}; // returns in an array to be displayed by the next method
        
        return result;
    }
    
    // Displays the quotient and remainder of a division problem
    public void displayDivision(Polynomial p){
        Polynomial q = dividePolynomial(p)[0]; // isolating the quotient
        Polynomial r = dividePolynomial(p)[1]; // isolating the remainder
        System.out.print("Quotient: ");
        System.out.println(q.displayPolynomial()); // printing
        System.out.print("Remainder: ");
        
        if (r.exponents.isEmpty()) { // if the ArrayList is empty, there is no remainder
            System.out.println("None");
        }
        else System.out.println(r.displayPolynomial());// otherwise, print as usual
        
    }
    
    //Finds the first derivative of the polynomial function
    public Polynomial findFirstDerivative() {
        ArrayList<Integer> expo = new ArrayList();
        ArrayList<Integer> coef = new ArrayList();
        if (exponents.size()==1 & exponents.get(0)==0){//If it's an integer
            expo.add(0);
            coef.add(0);
        }
        else{
            for (int i = 0; i < exponents.size(); i++) {
                if (exponents.get(i)!=0){
                    expo.add(exponents.get(i)-1);
                    coef.add(exponents.get(i)*coefficients.get(i));
                }
            }
        }
        Polynomial firstD = new Polynomial(coef, expo);
        return firstD;
    }
    
    //Displays the first derivative function
    public void displayFirstDerivative() {
        Polynomial firstD = this.findFirstDerivative();
        System.out.print("First Derivative of ");
        System.out.print(this.displayPolynomial());
        System.out.print(" is: ");
        System.out.println(firstD.displayPolynomial());
    }
    
    //Finds the second derivative of the polynomial function
    public Polynomial findSecondDerivative() {
        Polynomial secondD = (this.findFirstDerivative().findFirstDerivative());
        return secondD;
    }
    
    //Displays the second derivative
    public void displaySecondDerivative() {
        Polynomial secondD = this.findSecondDerivative();
        System.out.print("Second Derivative of ");
        System.out.print(this.displayPolynomial());
        System.out.print(" is: ");
        System.out.println(secondD.displayPolynomial());
    }
    
    //Finds nth derivative;
    public Polynomial findNthDerivative(int n) {
        Polynomial nthD = this;
        for (int i = 0; i < n; i++) {
            nthD = nthD.findFirstDerivative();
        }
        return nthD;
    }
    
    //Displays nth derivative
    public void displayNthDerivative(int n) {
        Polynomial nthD = this.findNthDerivative(n);
        if (n==3){
            System.out.print(n + "rd Derivative of ");
        }
        else{
            System.out.print(n + "th Derivative of ");
        }
        System.out.print(this.displayPolynomial());
        System.out.print(" is: ");
        System.out.println(nthD.displayPolynomial());
    }
    
    //Displays polynomial in expanded form
    public String displayPolynomial(){ 
        String term = "";
        String Print = "";
        String coef;
        String sign;
        String xTerm = "";
        
        for (int i = 0; i < this.exponents.size(); i++) {
            //Determines the sign to use in each term
            //If the coefficient is negative, there would be a minus sign
            if (this.coefficients.get(i) < 0) {
                sign = "-";
            }
            //If the coefficient is positive
            else if (this.coefficients.get(i) > 0) {
                if (i==0) sign = "";//There will be no sign for the first term

                else{
                    int check = 0;//checks for cases of empty terms (i.e. both exponent and coefficient are zero)
                    for (int j = 0; j < this.coefficients.size(); j++) {
                        if (coefficients.get(j)==0 && exponents.get(j)==0) check++;
                    }
                    if (check == coefficients.size()-1) sign = "";
                    else sign = "+";
                }//There will be a plus sign in front of the rest of the terms
            }
            else sign="";//If the coefficient is 0, indicating that there is no term, there will be no sign

            //Determines the coefficient to print
            //If the coefficient is 1 or -1
            if (this.coefficients.get(i) == 1 || this.coefficients.get(i) == -1){
                //If the exponent is 0, indicating that it is an integer term, the coefficient displayed will be 1
                if (this.exponents.get(i)==0){
                    coef = "1";
                }
                //If the there is an x term, no coefficient will be displayed in front of the x
                else {
                    coef = "";
                }
            }
            //Else if the coefficient is a non-zero number, the coefficient displayed will be the absolute value of the original coefficient
            else if (this.coefficients.get(i) != 0){
                coef = String.valueOf(Math.abs(this.coefficients.get(i)));
            }
            //Else if the coefficient is 0, indicating that there is no term, there will be no coefficient 
            else coef="";
            
            //Determines the x term to print
            if (this.exponents.get(i) > 1) {
                xTerm = "x^" + String.valueOf(this.exponents.get(i));
            }
            else if (this.exponents.get(i) == 1) {
                xTerm = "x";
            }
            else if (this.exponents.get(i) == 0){
                xTerm = "";
            }
            
            //the term in the polynomial is constructed by adding together the sign, coefficient and the x term
            term = sign + coef + xTerm;
            //Adds the term to the whole polynomial
            Print += term;

        }
        if (Print.equals("")) return "0";
        else return Print;
    }
    
    
    /*******************************Graphing Portion*****************************************/
    //FIELDS FOR GRAPHING
    //Dimensions for window (can also be set by user)
    int width = 600;
    int length = 600;
    int xMin = -10;
    int xMax = 10;
    int yMin = -20;
    int yMax = 20;
    
    //Array for y-coordinates of points on function
    double[] yval = new double[width];//Each pixel across the screen has a different y-coordinate
    
    //position of axes
    int yPos, xPos; 

    //Determines whether to graph the derivatives or not
    boolean plotFirstDerivative = false;
    boolean plotSecondDerivative = false;
    
    //Calculates increments of x and y values
    //Each pixel across the screen has an increment of x or y based on the values calculated here
    double increY = ((double)(yMax-yMin))/(double)length;
    double increX = ((double)(xMax-xMin))/(double)width;
    
    
    public double calcY(double x){ //Calculate the y value of a polynomial function at a given x value
        double y = 0;
        double term;
        for (int k = 0; k < coefficients.size(); k++){ //Goes through each term of the polynomial funciton one by one
            term = coefficients.get(k)*(Math.pow(x, exponents.get(k)));
            y = y + term;
        }
        return y;
    }
    
    public void plotPoints(){ //Append y-coordinates calculated by calcY()of points on function into an array
        for (int i = 0; i < width; i++){
            yval[i] = calcY(xMin + i*increX);
        }
    }
    
    public double[] plotFirstDerivative(){ //Plot the first derivative of the function using the polynomial found by the function findFirstDerivative()
        double[] yDerivativeValue = new double[width];
        Polynomial p = this.findFirstDerivative();
        for (int i = 0; i < width; i++) {
            yDerivativeValue[i] = p.calcY(xMin + i*increX);
        }
        plotFirstDerivative = true;
        return yDerivativeValue;
    }

    public double[] plotSecondDerivative(){//Plot the second derivative of the function using the polynomial found by the function findSecondDerivative()
        double[] yDerivativeValue = new double[width];
        Polynomial p = this.findSecondDerivative();
        for (int i = 0; i < width; i++) {
            yDerivativeValue[i] = p.calcY(xMin + i*increX);
        }
        plotSecondDerivative = true;
        return yDerivativeValue;
    }
    
    
    public void drawAxes(){//Calculates positions of x and y axes based on the maximum and minimum values
        xPos = Math.abs(width/(xMax-xMin)*xMin);
        yPos = length-Math.abs(length/(yMax-yMin)*yMin);
    }
    
    public void paint(Graphics g){//Graphs the function
        g.setColor(Color.white);
        g.fillRect(0, 0, width, length);
        
        //Draws x and y axes
        g.setColor( Color.gray );
        drawAxes();
        g.drawLine(0, yPos, width, yPos);
        g.drawLine(xPos, 0, xPos, length);
        
        //prints x and y maximums and minimums at the edge of the window
        g.drawString(String.valueOf(xMax), width-20, yPos);
        g.drawString(String.valueOf(xMin), 10, yPos);
        g.drawString(String.valueOf(yMax), xPos, 40);
        g.drawString(String.valueOf(yMin), xPos, length-10);
        
        //Draws Legend
        g.setColor(Color.BLACK);
        g.drawLine(width-180, length-105, width-155, length-105);
        g.drawString("Function", width-150, length-100);
        g.setColor(Color.RED);
        g.drawLine(width-180, length-80, width-155, length-80);
        g.drawString("First Derivative", width-150, length-75);
        g.setColor(Color.BLUE);
        g.drawLine(width-180, length-55, width-155, length-55);
        g.drawString("Second Derivative",width-150, length-50);

        //Graphs the first derivative if the plotFirstDerivative() function is called
        if (plotFirstDerivative) {
            double[] yDerivValue = plotFirstDerivative();
            g.setColor(Color.red);
            for (int i = 0; i < width-1; i++) {
                int yvalD1 = length - (int)Math.round((yDerivValue[i]-yMin)*length/(yMax-yMin)); //Calculates the y coordinate of the pixel to fill 
                int yvalNextD1 = length - (int)Math.round((yDerivValue[i+1]-yMin)*length/(yMax-yMin)); //Calculates they coordinate of the next pixel to fill 
                g.drawLine(i, yvalD1 , i+1, yvalNextD1); //Draws line between current point to the next point
            }          
        }
        
        //Graphs the second derivative if the plotFirstDerivative() function is called
        if (plotSecondDerivative) {
            double[] yDerivValue = plotSecondDerivative();
            g.setColor(Color.blue);
            for (int i = 0; i < width-1; i++) {
                int yvalD2 = length - (int)Math.round((yDerivValue[i]-yMin)*length/(yMax-yMin)); //Calculates the y coordinate of the pixel to fill 
                int yvalNextD2 = length - (int)Math.round((yDerivValue[i+1]-yMin)*length/(yMax-yMin)); //Calculates they coordinate of the next pixel to fill 
                g.drawLine(i, yvalD2 , i+1, yvalNextD2); //Draws line between current point to the next point
            }          
        }
        
        //Graphs the function
        this.plotPoints();
        g.setColor( Color.BLACK );
        for(int i = 0; i < width-1; i++){
            int yVal = length - (int)Math.round((yval[i]-yMin)*length/(yMax-yMin)); //Calculates the y coordinate of the pixel to fill 
            int yValNext = length - (int)Math.round((yval[i+1]-yMin)*length/(yMax-yMin)); //Calculates they coordinate of the next pixel to fill 
            g.drawLine(i, yVal, i+1, yValNext);            
        }    
    }
    
    //InitializesWindow for graphing
    public void initializeWindow() {
        String title = "Polynomial: " + this.displayPolynomial();
        setTitle(title);
        setSize(width, length);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.white);
        setVisible(true); //calls paint() for the first time
    }
}
