// specifications for what a list of ints can do

public interface ListInt {

    // add
    void add(int newVal);

    // add-at-index (fails for index >= size)
    void add(int index, int newVal);

    // remove
    void remove(int index);

    // size
    int size();

    // get value at index
    int get(int index);

    // set value at index
    int set(int index, int newVal);

}
