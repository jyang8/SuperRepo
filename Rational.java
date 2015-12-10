// Jessica Yang
// APCS1 pd9
// HW45 -- Come Together
// 2015-12-09


public class Rational implements Comparable {

    // instance vars
    private int num;
    private int den;

    // default constructor
    public Rational() {
	num = 0;
	den = 1;
    }

    // constructor
    public Rational(int num, int den) {
	this();
	if (den == 0) {
	    System.out.println("Error: invalid denominator");
	    this.num = 0;
	    this.den = 1;
	} else {
	    this.num = num;
	    this.den = den;
    	}
    }

    // overriding toString()
    public String toString() {
    	return num + "/" + den;
    }

    // returns a floating point value of the Rational
    public double floatValue() {
    	return (double)num / den;
    }

    // takes a Rational and multiplies it by this Rational, modifies this object
    public void multiply(Rational r) {
        num *= r.num;
        den *= r.den;
    }

    // takes a Rational and multiplies this Rational by the reciprocal of the input, modifies this object 
    public void divide(Rational r) {
        if (r.num == 0) {
	    System.out.println("Error: division by zero"); // undefined
	} else {
	    num *= r.den;
	    den *= r.num;
	}
    }

    // takes a Rational and adds it to this Rational, modifies this object
    public void add(Rational r) {
	num = num * r.den + den * r.num;
	den = den * r.den;
    }

    // takes a Rational and subtracts it from this Rational, modifies this object
    public void subtract(Rational r) {
	num = num * r.den - den * r.num;
	den = den * r.den;
    }

    // returns the GCD of two integer inputs recursively.
    public static int gcd (int a, int b){
        if ((a == b) || (b == 0)){
            return a; 
	} else if (a < b) {
            return gcd(b,a); // run again with both values swapped
	} else {
            return gcd(b,(a-b));
        }
    }

    // returns GCD of this Rational
    public int gcd() {
	return gcd(num, den);
    }

    // modifies this object to its lowest terms
    public void reduce(){
        int GCD = gcd();
        num /= GCD;
        den /= GCD;
    }

    // compares this Rational to input Rational
    // returns 0 if equal, 1 if this object is greater than input, -1 if this object is less than input
    public int compareTo(Object o){
	if (o == null) {
	    throw new NullPointerException("\nObject is null" + " | compareTo() input not assigned a value");
	}
	if (this instanceof Rational && o instanceof Rational) {
	    int crossC = ((Rational)this).num * ((Rational)o).den; //this is the calling fraction
	    int crossP = ((Rational)o).den * ((Rational)this).num; //this is the parameter fraction
	    return crossC - crossP;
	} else {
	    throw new ClassCastException("\nClasses do not match" + " | compareTo() input not a Rational");
	}
    }

    // checks if this Rational and input Rational are equal
    public boolean equals(Rational r){
    	return r instanceof Rational
	    && this.compareTo(r) == 0;
    }


    public static void main(String[] args) {
        /*=============================================
	  =============================================*/

        //Tests multiplication and division functions
        System.out.println("Multiplying and dividing");
        Rational s = new Rational(1,2);
        //s.multiply(s);
        System.out.println(s);
        Rational t = new Rational(2,3);
        Rational u = new Rational(1,2);
        t.divide(u);
        System.out.println(t);


        //Tests addition and subtraction functions
        System.out.println("Adding and subtracting");
	Rational v = new Rational(2,3);
        Rational w = new Rational(1,2);
        Rational x = new Rational (7,6);
        v.add(w);
        System.out.println(v);
        v.subtract (x);
        System.out.println(v);


        //Tests reducting function
        System.out.println ("Reducing");
        Rational y = new Rational (2,4);
        System.out.println ("Before: " + y);
        y.reduce();
        System.out.println ("After:  " + y);


        //Tests compareTo function
        System.out.println ("Comparing");
        Rational z = new Rational (2,5);
        System.out.println (w.compareTo(z)); //should return positive number
        System.out.println (w.compareTo(y)); //should return zero.
        System.out.println (y.compareTo(x)); //should return negative number

      	//Tests Equals
      	System.out.println(y.equals(x));//should return false
       	System.out.println(s.equals(u));//should return true
      	System.out.println(y.compareTo(x));//should return -1
       	System.out.println(s.compareTo(u));//should return 0
        double i = 1/2;
      	System.out.println(s.equals(i));

	//Tests exceptions
	Rational test = null;
	System.out.println(x.compareTo(test));//should throw NullPointerException error
	System.out.println(x.compareTo("0"));//should throw ClassCastException error	

    }
}
