package ua.nure.ponomarenko.task2;

import java.security.*;

public class ListImpl implements List  {

	// Demo
	public static void main(String[] args) {
		List list0 = new ListImpl();
		System.out.println("Create list: " + list0);
		System.out.println();

		list0.add(2);
		System.out.println("Add an element (2):" + list0);
		System.out.println();

		list0.addAll(new ListImpl(new Object[] {4, 5, 6}));
		System.out.println("Add all elements ({4, 5, 6}): " + list0);
		System.out.println();

		System.out.println("List contains element (3): " + list0.contains(3));
		System.out.println("List contains element (4): " + list0.contains(4));
		System.out.println();

		System.out.println("Get object by index (0): " + list0.get(0));
		System.out.println();

		System.out.println("Get index of object (4): " + list0.indexOf(4));
		System.out.println();

		list0.removeAt(0);
		System.out.println("Remove object at index (0): " + list0);
		System.out.println();	

		list0.remove(4);
		System.out.println("Remove object (4): " + list0);
		System.out.println();		

		System.out.println("Size of list: " + list0.size());
		System.out.println();

		list0.clear();
		System.out.println("Clear list: " + list0);
		System.out.println();

		list0.addAll(new ListImpl(new Object[] {4, 5, 6, 7, 8}));
		System.out.println("Add all elements ({4, 5, 6, 7, 8}): " + list0);
		System.out.println("Print elements using iterator removing all elements:");
		for (Iterator i = list0.iterator(); i.hasNext();) {
		    System.out.println(i.next());
			i.remove();
		}
		System.out.println("List: " + list0);
	}


	private final static int DEFAULT_ARRAY_SIZE = 100;
	private final static int DEFAULT_MAGNIFICATION_FACTOR = 2;

	// Properties

	private Object[] array;
	private int count;

	// Initialization
	public ListImpl() {
		array = new Object[DEFAULT_ARRAY_SIZE];
	}

	public ListImpl(Object[] array) {
		this.array = array;
		count = array.length;
	}

	// P8ublic methods

	public void add(Object element) {
		if (count == array.length) {
			extendArray();
		}
		array[count++] = element;
	}

	public void addAll(List list) {
		int totalCount = count + list.size();
		if (totalCount > array.length) {
			extendArrayTo(totalCount * DEFAULT_MAGNIFICATION_FACTOR);
		}
		for (Object object: list) {
			add(object);
		}
	}

	public int lengthOfArray() {
		return array.length;
	}

	public void clear() {
		count = 0;
		array = new Object[DEFAULT_ARRAY_SIZE];
	}

	public boolean contains(Object element) {
		for (Object object: this) {
			if (object.equals(element)) {
				return true;
			}
		}
		return false;
	}

	public Object get(int index) {
		if (!isValidIndex(index)) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	public int indexOf(Object element) {
		for (int i = 0; i < count; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	public Object removeAt(int index) {
		if (!isValidIndex(index)) {
			throw new IndexOutOfBoundsException();
		}
		Object object = array[index];
		// Shift left to one position all the elements
		// that are on the right side of the element that is being removed
		for (int i = index; i < count - 1; i++) {
			array[i] = array[i + 1];
		}
		count--;
		return object;
	}

	public boolean remove(Object element) {
		for (int i = 0; i < count; i++) {
			if (array[i].equals(element)) {
				// Shift left to one position all the elements
				// that are on the right side of the element that is being removed
				for (int j = i; j < count - 1; j++) {
					array[j] = array[j + 1];
				}
				count--;
				return true;
			}
		}
		return false;
	}

	public int size() {
		return count;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");

		for (int i = 0; i < count; i++) {
			stringBuilder.append(array[i].toString());
			if (i < count - 1) {
				stringBuilder.append(", ");
			}
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	public IteratorImpl iterator() {
		return new IteratorImpl();
	}

	// Private methods
	private void extendArray() {
		extendArrayTo(count * DEFAULT_MAGNIFICATION_FACTOR);
	}

	private void extendArrayTo(int newSize) {
		if (newSize <= array.length) {
			throw new InvalidParameterException("New size to extend the array can't be less than initial size.");
		}
		Object[] newArray = new Object[newSize];
		// Copy content of initial array to extended array
		for (int i = 0 ; i < count; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

	private boolean isValidIndex(int index) {
		return index >= 0 && index < count;
	}

	public class IteratorImpl implements Iterator {
		int cursor = 0;			// index of next element to return
		int lastReturned = -1;	// index of last element returned; -1 if no such
		boolean didCallRemoveOnCurrentPosition = false;

		public boolean hasNext() {
			return cursor != count;
		}

		public Object next() {
			Object next = get(cursor);
			lastReturned = cursor;
			cursor++;
			didCallRemoveOnCurrentPosition = false;
			return next;
		}

		public void remove() {
			if (lastReturned < 0) {
				throw new IllegalStateException("Remove() methods can't be called, because the next() method has not been called yet.");
			} else if (didCallRemoveOnCurrentPosition == true) {
				throw new IllegalStateException("Remove() method has already been called on current iteration.");
			}
			removeAt(lastReturned);
			didCallRemoveOnCurrentPosition = true;
			cursor--;
		}
	}
}