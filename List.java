package ua.nure.ponomarenko.task2;

public interface List extends java.lang.Iterable<Object>  {
	// appends the specified element
	// to the end of this list
	void add(Object el);

	// appends all of the elements
	// in the specified collection to the end of this list
	void addAll(List list);

	// removes all of the elements from this list
	void clear();

	// returns true if
	// this list contains the specified element
	boolean contains(Object el);

	// returns the element
	// at the specified position in this list.
	Object get(int index);

	// returns the index of the first occurrence
	// of the specified element in this list
	int indexOf(Object el);

	// removes the element at the specified position
	// in this list, returns the element previously
	// at the specified position
	// throws IndexOutOfBoundsException
	// if the index is out of range
	Object removeAt(int index);

	// removes the first occurrence
	// of the specified element from this list,
	// returns true if this list contained
	// the specified element
	boolean remove(Object el);

	// returns the number of elements in this list
	int size();

	// returns an iterator
	Iterator iterator();
}