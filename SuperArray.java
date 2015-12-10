// Jessica Yang
// APCS1 pd9
// HW45 -- Come Together
// 2015-12-09

public class SuperArray {

    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;
    
    //position of last meaningful value
    private int _lastPos;
    
    //size of this instance of SuperArray
    private int _size;

    //~~~~~METHODS~~~~~
    //default constructor – initializes 10-item array
    public SuperArray() {
	_data = new Comparable[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;
    }

    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() {
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }

    //double capacity of this SuperArray
    private void expand() {
	Comparable[] temp = new Comparable[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

    //accessor -- return value at specified index
    public Comparable get( int index ) {
	return _data[index];
    }

    //mutator -- set value at index to newVal,
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) {
	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }

    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) {
	if (_lastPos == _size - 1) {
	    expand();
	}
	_lastPos++;
	_size++;
	_data[_lastPos] = newVal;
    }

    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) {
	if (_size == _data.length) {
	    expand();
	}
	if (index < _size && index > -1) {
	    for ( int i = _lastPos; i > index - 1; i-- ) {
	        _data[i + 1] = _data[i];
	    }
	    _data[index] = newVal;
	    _lastPos++;
	    _size++;
	}
    }

    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) {
	for ( int i = index; i < _lastPos; i++) {
	    _data[i]=_data[i+1];
	}
	_lastPos--;
	_size--;
    }

    //return number of meaningful items in _data
    public int size() {
	return _size;
    }

    // returns index of first instance of c in SuperArray, -1 if not found
    public int linSearch(Comparable c) {
	for (int x = 0; x < _size; x++) {
	    if (c.equals(get(x))) {
		return x;
	    }
	}
	return -1;
    }
    
    // returns true if in ascending order, false otherwise
    public boolean isSorted() {
	for (int x = 1; x < _size; x++) {
	    if ( _data[x].compareTo(_data[x-1]) < 0) {
		return false;
	    }
	}
	return true;
    }
    

    //main method for testing
    public static void main( String[] args ) {
	/*----------------------------------------------------------
	  SuperArray curtis = new SuperArray();
	  System.out.println("Printing empty SuperArray curtis...");
	  System.out.println(curtis);

	  for( int i = 0; i < curtis._data.length; i++ ) {
	  curtis.set(i,i*2);
	  curtis._size++; //necessary bc no add() method yet
	  }

	  System.out.println("Printing populated SuperArray curtis...");
	  System.out.println(curtis);

	  System.out.println("testing get()...");
	  for( int i = 0; i < curtis._size; i++ ) {
	  System.out.print( "item at index" + i + ":\t" );
	  System.out.println( curtis.get(i) );
	  }

	  System.out.println("Expanded SuperArray curtis:");
	  curtis.expand();
	  System.out.println(curtis);
	  ----------------------------------------------------------*/

	SuperArray mayfield = new SuperArray();
	System.out.println("Printing empty SuperArray mayfield...");
	System.out.println(mayfield);

	mayfield.add(new Binary(20));
	mayfield.add(new Hexadecimal(21));
	mayfield.add(new Rational(1,2));
	mayfield.add(new Binary("11001"));
	mayfield.add(new Hexadecimal("3E"));

	System.out.println("Printing populated SuperArray mayfield...");
	System.out.println(mayfield);

	System.out.println("\nTesting linSearch() mayfield...");
	System.out.println(mayfield.linSearch(new Binary(20)));
	System.out.println(mayfield.linSearch(new Hexadecimal(21)));
	System.out.println(mayfield.linSearch(new Rational(1,2)));
	System.out.println(mayfield.linSearch(new Binary("11001")));
	//System.out.println(mayfield.linSearch(new Hexadecimal(0)));
		
	//System.out.println("\nTesting isSorted() mayfield...");
	//System.out.println(mayfield.isSorted());
	
	mayfield.remove(3);
	System.out.println("Printing SuperArray mayfield post-remove...");
	System.out.println(mayfield);
	mayfield.remove(3);
	System.out.println("Printing SuperArray mayfield post-remove...");
	System.out.println(mayfield);

	mayfield.add(3, new Binary(99));
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(2,new Hexadecimal(88));
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(1,new Rational(66,77));
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);

    }//end main

}//end class
