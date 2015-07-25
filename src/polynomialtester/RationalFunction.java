
package polynomialtester;

import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.util.HashSet;


public class RationalFunction extends JFrame{
    //FIELDS
    Polynomial numerator, denominator;
    ArrayList<Double> asymptotes;
    ArrayList<Double> asymptotesFirstD;
    ArrayList<Double> asymptotesSecondD;
    
    int horizontalAsymp;
    Polynomial LOA;
    
    //FIELDS FOR GRAPHING
    //Dimensions for window (can also be set by user)
    int width;
    int length;
    int xMin;
    int xMax;
    int yMin;
    int yMax;
    
    //Array for y-coordinates of points on function
    double[] yval;
    
    int yPos, xPos;
    
    //Array for y-coordinates of points of asymptote
    double[] yValAsymp;
    
    //Determines whether to graph the derivatives or not
    boolean plotFirstDerivative = false;
    boolean plotSecondDerivative = false;
    
    //Calculates increments of x and y values
    //Each pixel across the screen has an increment of x or y based on the values calculated here
    double increY;
    double increX ;
    
    //CONSTRUCTORS
    public RationalFunction (String r){
        findNumAndDeno(r);
    }
    
    public RationalFunction (Polynomial Numerator, Polynomial Denominator){
        this.numerator = Numerator;
        this.denominator = Denominator;
    }
    
    //Sets field values
    public void setFieldValues(int w, int l, int xmin, int xmax, int ymin, int ymax){
        this.width = w;
        this.length = l;
        this.xMin = xmin;
        this.xMax = xmax;
        this.yMin = ymin;
        this.yMax = ymax;
        this.yval = new double[width];
        this.yValAsymp = new double[width];
        this.increY = ((double)(yMax-yMin))/(double)length;
        this.increX = ((double)(xMax-xMin))/(double)width;
        this.xPos = Math.abs(width/(xMax-xMin)*xMin);
        this.yPos = Math.abs(length-Math.abs(length/(yMax-yMin)*yMin));
        this.asymptotes = this.findRoots();
    }
    
    //Separates string into numerator and denominator
    public void findNumAndDeno(String r){
        String numeratorString, denominatorString;
        int indexSlash = r.indexOf("/");
        numeratorString = r.substring(0, indexSlash);
        denominatorString = r.substring(indexSlash+1, r.length());
        numerator = new Polynomial(numeratorString);
        denominator = new Polynomial(denominatorString);
    }
    
    //Find the roots of denominator and find asymptotes
    public ArrayList<Double> findRoots(){
        ArrayList<Double> asymp = new ArrayList();
        for (double i = (double)(xMin); i < (double)(xMax); i+=increX) {
            if (Math.abs(denominator.calcY(i))<0.000000001){ //If the y coordinate of the point is very close to 0
                        asymp.add(i);
            }
        }
        //There might be more than one points that are very close to 0, this removes the same values of x
        HashSet<Double> removeDuplicate = new HashSet<>();
        removeDuplicate.addAll(asymp);
        asymp.clear();
        asymp.addAll(removeDuplicate);
        return asymp;
    }
    
    //Finds horinzontal asymptotes or the other asymptotes that are not vertical
    public boolean findHA(){
        if (denominator.exponents.get(0)-numerator.exponents.get(0)>=1) {//If degree of denominator is larger than degree of numerator
            horizontalAsymp = 0;
            return true;
        }
        else if (denominator.exponents.get(0)==numerator.exponents.get(0)){//If degree of denominator is the same as degree of numerator
            horizontalAsymp = numerator.coefficients.get(0)/denominator.coefficients.get(0);
            return true;
        }
        else{//If degree of denominator is less than degree of numerator
            LOA = denominator.dividePolynomial(numerator)[0];
            for (int i = 0; i < width; i++) {
                yValAsymp[i] = LOA.calcY(xMin + i*increX);
            }
            return false;
        }
    }
    
    //Calculates the y value at given x on the rational function
    public double calcRationalY(double x){
        double numVal, denVal;
        numVal = numerator.calcY(x);
        denVal = denominator.calcY(x);
        double y = numVal/denVal;
        return y;
    }
    
    //Fills the array of y values
    public void plotPoints(){
        int asympPix = 0;
        for (int i = 0; i < asymptotes.size(); i++) {//Skips over the asymptotes when filling in y values
            asympPix = (int)Math.round(asymptotes.get(i)*width/(xMax-xMin)+xPos);
            int startPix = 0;
            for (int j = startPix; j < asympPix; j++) {
                yval[j] = calcRationalY(xMin + j*increX);
            }
            startPix = asympPix + 1;
        }
        
        for (int i = asympPix; i < width-asymptotes.size(); i++) {
            yval[i] = calcRationalY(xMin + i*increX);
        }
    }
    
    public RationalFunction findFirstDerivative(){//Finds first derivative of rational function
        Polynomial fPrime = this.numerator.findFirstDerivative();
        Polynomial gPrime = this.denominator.findFirstDerivative();
        Polynomial numFirstTerm = this.denominator.multiplyPolynomial(fPrime);
        Polynomial numSecondTerm = this.numerator.multiplyPolynomial(gPrime);
        Polynomial num = numFirstTerm.subtractPolynomial(numSecondTerm);
        Polynomial den = this.denominator.multiplyPolynomial(this.denominator);
        RationalFunction derivative = new RationalFunction(num, den);
        return derivative;
    }
    
    public RationalFunction findSecondDerivative(){//Finds second derivative of rational function
        RationalFunction derivative = this.findFirstDerivative().findFirstDerivative();
        return derivative;
    }
    
    public void displayFirstDerivative(){
        System.out.println("First derivative of " + this.displayRationalFunction()+": ");
        RationalFunction firstDer = this.findFirstDerivative();
        System.out.println(firstDer.displayRationalFunction());
    }
    
    public void displaySecondDerivative(){
        System.out.println("Second derivative of " + this.displayRationalFunction()+": ");
        RationalFunction secondDer = this.findSecondDerivative();
        System.out.println(secondDer.displayRationalFunction());
    }
    
    public double[] plotFirstDerivative(){
        double[] yDerivativeValue = new double[width];
        RationalFunction r = this.findFirstDerivative();
        asymptotesFirstD = r.findRoots(); //finds new asymptotes for the derivative function
        plotFirstDerivative = true;
        
        int asympPix = 0;
        for (int i = 0; i < asymptotesFirstD.size(); i++) {//Skips over the asymptotes when filling in y values
            asympPix = (int)Math.round(asymptotesFirstD.get(i)*width/(xMax-xMin)+xPos);
            int startPix = 0;
            for (int j = startPix; j < asympPix; j++) {
                yDerivativeValue[j] = r.calcRationalY(xMin + j*increX);
            }
            startPix = asympPix + 1;
        }
        
        for (int i = asympPix; i < width-asymptotesFirstD.size(); i++) {
            yDerivativeValue[i] = r.calcRationalY(xMin + i*increX);
        }
        
        return yDerivativeValue;
    }
    
    public double[] plotSecondDerivative(){
        double[] yDerivativeValue = new double[width];
        RationalFunction r = this.findSecondDerivative();
        asymptotesSecondD = r.findRoots(); //finds new asymptotes for the derivative function
        plotSecondDerivative = true;
        
        int asympPix = 0;
        for (int i = 0; i < asymptotesSecondD.size(); i++) {//Skips over the asymptotes when filling in y values
            asympPix = (int)Math.round(asymptotesSecondD.get(i)*width/(xMax-xMin)+xPos);
            int startPix = 0;
            for (int j = startPix; j < asympPix; j++) {
                yDerivativeValue[j] = r.calcRationalY(xMin + j*increX);
            }
            startPix = asympPix + 1;
        }
        
        for (int i = asympPix; i < width-asymptotesSecondD.size(); i++) {
            yDerivativeValue[i] = r.calcRationalY(xMin + i*increX);
        }
        
        return yDerivativeValue;
    }
    
    
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, width, length);
        
        //Draws x and y axes
        g.setColor( Color.gray );
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
        g.setColor(Color.GREEN);
        g.drawLine(width-180, length-30, width-155, length-30);
        g.drawString("Aymptotes",width-150, length-25);
        
        //Draws non-vertical asymptotes 
        findHA();
        g.drawString("Non-vertical Asymptote:", width-175, 75);
        if (findHA()){//If the asymptote is horizontal
            g.drawLine(0, yPos-horizontalAsymp*length/(yMax-yMin), width, yPos-horizontalAsymp*length/(yMax-yMin));
            g.drawString("y = "+String.valueOf(horizontalAsymp), width-175, 100);
        }
        else{
            for(int i = 0; i < width-1; i++){
                g.drawLine(i, length - (int)Math.round((yValAsymp[i]-yMin)*length/(yMax-yMin)), i+1, length - (int)Math.round((yValAsymp[i+1]-yMin)*length/(yMax-yMin)));
                g.drawString("y = "+LOA.displayPolynomial(), width-150, 100);
            }
        }
        
        //Graphs the function
        plotPoints();
        g.setColor( Color.BLACK );
        int asympPix = 0;
        int startPix = 0;
        //Skips points of asymptotes when grahphing
        for (int i = 0; i < asymptotes.size(); i++) {
            asympPix = (int)Math.round(asymptotes.get(i)*width/(xMax-xMin)+xPos);
            for (int j = startPix; j < asympPix-1; j++) {
                int yVal = length - (int)Math.round((yval[j]-yMin)*length/(yMax-yMin));
                int yValNext = length - (int)Math.round((yval[j+1]-yMin)*length/(yMax-yMin));
                g.drawLine(j, yVal, j+1, yValNext);  
            }
            startPix = asympPix + 1;
        }
        
        for (int i = startPix; i < width-asymptotes.size()-1; i++) {
            int yVal = length - (int)Math.round((yval[i]-yMin)*length/(yMax-yMin));
            int yValNext = length - (int)Math.round((yval[i+1]-yMin)*length/(yMax-yMin));
            g.drawLine(i, yVal, i+1, yValNext);  
        }
        
        //Plots the first derivative
        asympPix = 0;
        startPix = 0;
        if (plotFirstDerivative) {
            double[] yDerivValue = plotFirstDerivative();
            g.setColor(Color.red);
            for (int i = 0; i < asymptotesFirstD.size(); i++) {
                asympPix = (int)Math.round(asymptotesFirstD.get(i)*width/(xMax-xMin)+xPos);
                for (int j = startPix; j < asympPix-1; j++) {
                    int yVal = length - (int)Math.round((yDerivValue[j]-yMin)*length/(yMax-yMin));
                    int yValNext = length - (int)Math.round((yDerivValue[j+1]-yMin)*length/(yMax-yMin));
                    g.drawLine(j, yVal, j+1, yValNext);  
                }
                startPix = asympPix + 1;
            }

            for (int i = startPix; i < width-asymptotesFirstD.size()-1; i++) {
                int yVal = length - (int)Math.round((yDerivValue[i]-yMin)*length/(yMax-yMin));
                int yValNext = length - (int)Math.round((yDerivValue[i+1]-yMin)*length/(yMax-yMin));
                g.drawLine(i, yVal, i+1, yValNext);  
            }
        }
        
        //Plots the second derivative
        asympPix = 0;
        startPix = 0;
        if (plotSecondDerivative) {
            double[] yDerivValueSec = plotSecondDerivative();
            g.setColor(Color.blue);
            for (int i = 0; i < asymptotesSecondD.size(); i++) {
                asympPix = (int)Math.round(asymptotesSecondD.get(i)*width/(xMax-xMin)+xPos);
                for (int j = startPix; j < asympPix-1; j++) {
                    int yVal = length - (int)Math.round((yDerivValueSec[j]-yMin)*length/(yMax-yMin));
                    int yValNext = length - (int)Math.round((yDerivValueSec[j+1]-yMin)*length/(yMax-yMin));
                    g.drawLine(j, yVal, j+1, yValNext);  
                }
                startPix = asympPix + 1;
            }

            for (int i = startPix; i < width-asymptotesSecondD.size()-1; i++) {
                int yVal = length - (int)Math.round((yDerivValueSec[i]-yMin)*length/(yMax-yMin));
                int yValNext = length - (int)Math.round((yDerivValueSec[i+1]-yMin)*length/(yMax-yMin));
                g.drawLine(i, yVal, i+1, yValNext);  
            }
        }
        
        //Draws Vertical Asymptotes
        findRoots();
        for(int i = 0; i < asymptotes.size(); i++){
            g.setColor( Color.green );
            g.drawLine((int)Math.round(asymptotes.get(i)*width/(xMax-xMin)+xPos), 0, (int)Math.round(asymptotes.get(i)*width/(xMax-xMin)+xPos), length); 
            g.drawString(String.valueOf((int)Math.round(asymptotes.get(i))), (int)Math.round(asymptotes.get(i)*width/(xMax-xMin)+xPos), yPos);
        }
        

    }
    
    public String displayRationalFunction(){//Displays the rational function
        String num = numerator.displayPolynomial();
        String den = denominator.displayPolynomial();
        return "("+num+")/("+den+")";
    }
    
    //InitializesWindow for graphing
    public void initializeWindow() {
        String title = "Rational Function: " + this.displayRationalFunction();
        setTitle(title);
        setSize(width, length);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.white);
        setVisible(true); //calls paint() for the first time
    }
}

