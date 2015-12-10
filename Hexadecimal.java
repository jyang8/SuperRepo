// Jessica Yang
// APCS1 pd9
// HW45 -- Come Together
// 2015-12-09

//skeleton file for class Hexadecimal

public class Hexadecimal implements Comparable {

    private int _decNum;
    private String _hexNum;
    private final static String HEXDIGITS = "0123456789ABCDEF"; 

    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
	_decNum = 0;
	_hexNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {  
	_decNum = n;
	_hexNum = decToHex(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {  
	_decNum = hexToDec(s);
	_hexNum = s;
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _hexNum;
    }


    /*=====================================
      String decToHex(int) -- converts base-10 input to hexadecimal
      pre:  n >= 0
      post: returns String of bits
      eg  decToHex(0) -> "0"
      decToHex(1) -> "1"
      decToHex(2) -> "10"
      decToHex(3) -> "11"
      decToHex(14) -> "1110"
      =====================================*/
    public static String decToHex( int n ) {
	String retStr = "";
	while (n>0) {
	    retStr = HEXDIGITS.substring((n % 16), (n % 16) + 1) + retStr;
	    n /= 16;
	}
	return retStr;
    }


    /*=====================================
      String decToHexR(int) -- converts base-10 input to hexadecimal, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToHexR(0) -> "0"
      decToHexR(1) -> "1"
      decToHexR(2) -> "10"
      decToHexR(3) -> "11"
      decToHexR(14) -> "1110"
      =====================================*/
    public static String decToHexR( int n ) { 
	if (n == 0) { //base case - pre says n >= 0
	    return "";
	} 
	else {
	    return decToHexR(n/16) + HEXDIGITS.substring((n % 16) , (n % 16) + 1);
	}
    }


    /*=====================================
      String hexToDec(String) -- converts base-10 input to hexadecimal
      pre:  s represents non-negative hexadecimal number
      post: returns String of bits
      eg  
      hexToDec("0") -> 0
      hexToDec("1") -> 1
      hexToDec("10") -> 2
      hexToDec("11") -> 3
      hexToDec("1110") -> 14
      =====================================*/
    public static int hexToDec( String s ) {
	int ret = 0;
	for (int c = 0 ; c < s.length() ; c++) {
	    int translate = HEXDIGITS.indexOf(s.substring(c,c+1));
	    ret += translate*((int)(Math.pow(16, s.length() -1 -c)));
	}
	return ret;
    }


    /*=====================================
      String hexToDecR(String) -- converts base-10 input to hexadecimal, recursively
      pre:  s represents non-negative hexadecimal number
      post: returns String of bits
      eg  
      hexToDecR("0") -> 0
      hexToDecR("1") -> 1
      hexToDecR("10") -> 2
      hexToDecR("11") -> 3
      hexToDecR("1110") -> 14
      =====================================*/
    public static int hexToDecR( String s ) { 
	if (s.length() <= 1) { //length of 1 means 2^0, units
	    return HEXDIGITS.indexOf(s);
	} else {
	    return ((int)Math.pow(16,s.length()-1)) * HEXDIGITS.indexOf(s.substring(0,1)) + hexToDecR(s.substring(1));
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal hexadecimal values
      =============================================*/
    public boolean equals( Object other ) { 
	if (other instanceof Hexadecimal) {
	    return _decNum == ((Hexadecimal) other)._decNum;
	}
	return false;
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if (other == null) {
	    throw new NullPointerException("\nObject is null" + " | compareTo() input not assigned a value");
	}
	if (!(other instanceof Hexadecimal)) {
	    throw new ClassCastException("\nClasses do not match" + " | compareTo() input not a Hexadecimal");
	}
	if (_decNum == ((Hexadecimal) other)._decNum) {
	    return 0;
	} else if (_decNum < ((Hexadecimal) other)._decNum) {
	    return -1;
	} else {
	    return 1;
	}
    }


    //main method for testing
    public static void main( String[] args ) {


	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal b1 = new Hexadecimal(5);
	Hexadecimal b2 = new Hexadecimal(5);
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(7);
	Hexadecimal b5 = null;

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos

	System.out.println("\nconverter methods...");
	System.out.println(decToHexR(28));
	System.out.println(hexToDecR("1C"));
       	System.out.println(decToHex(28));
	System.out.println(hexToDec("1C"));

	System.out.println("\nthrowing errors");
	System.out.println( b1.compareTo(b5) ); //should throw NullPointerException error
	System.out.println( b1.compareTo("0") ); //should throw ClassCastException error

    }//end main()

} //end class
