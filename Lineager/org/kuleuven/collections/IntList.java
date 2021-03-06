package org.kuleuven.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IntList implements List<Integer>, Iterable<Integer>, Serializable {
  private static final long serialVersionUID = 1109817464341796563L;
  private int[] array;
  private static int defaultCapacity = 8;
  private int size = 0;

  public IntList() {
    array = new int[defaultCapacity];
  }
  

  public IntList(IntList copy) {
    this(copy.size);
    System.arraycopy(copy.array, 0, this.array , 0, copy.size);
    this.size = this.array.length;
  }

  public IntList(int initialCapacity) {
    array = new int[initialCapacity];
  }
public IntList(int[] array){
  this.array = array;
  size = array.length;
}
public IntList(Collection<Integer> collection) {
  this(collection.size());
  Iterator<Integer> it = collection.iterator();
  int x = 0;
  while(it.hasNext()) {
    Integer i = it.next();
    this.array[x++] = i;
  }
  this.size = this.array.length;
}
  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer("[");
    if (size > 0)
      buffer.append(Integer.toString(array[0]));
    for (int i = 1; i < size; i++) {
      buffer.append(", ");
      buffer.append(Integer.toString(array[i]));
    }
    buffer.append("]");
    return buffer.toString();
  }

  public void add(int index, int element) {
    if (index < size) {
      if (size + 1 >= array.length)
        grow();

      System.arraycopy(array, index, array, index + 1, size - index);
      array[index] = element;
      size++;
    }
    else if (index == size) {
      add(element);
    }
    else {
      throw new IndexOutOfBoundsException();
    }
  }

  public boolean add(int i) {
    if (size >= array.length)
      grow();
    array[size] = i;
    size++;
    return true;
  }

  public int set(int index, int element) {
    if (index < size) {
      int current = array[index];
      array[index] = element;
      return current;
    }
    else {
      throw new IndexOutOfBoundsException();
    }
  }
  @Override
public boolean addAll(int index, Collection<? extends Integer> collection) {
    int newsize =index + collection.size();
    if (newsize  > array.length)
      setCapacity(newsize);
    size = newsize;
    Iterator<? extends Integer> iterator = collection.iterator();
    
    while (iterator.hasNext()) {
      array[index] = iterator.next();
      index++;
    }
    return false;
  }
  @Override
public boolean addAll(Collection<? extends Integer> collection) {
    if (size + collection.size() > array.length)
      setCapacity(size + collection.size());
    Iterator<? extends Integer> iterator = collection.iterator();
    while (iterator.hasNext()) {
      array[size] = iterator.next();
      size++;
    }
    return true;
  }

  @Override
public Integer remove(int index) throws ArrayIndexOutOfBoundsException {
    if (index < 0 || index >= size)
      throw new ArrayIndexOutOfBoundsException("list[" + index + "] is out of bounds (max " + (size - 1) + ")");
    int returnval = array[index];
    System.arraycopy(array, index + 1, array, index, size - index - 1);
    size--;
    return returnval;
  }

  public int[] toIntArray() {
    return array;
  }

  @Override
public void clear() {
    size = 0;
  }

  public int getInt(int index) throws ArrayIndexOutOfBoundsException {
    if (index < 0 || index >= size)
      throw new ArrayIndexOutOfBoundsException("list[" + index + "] is out of bounds (max " + (size - 1) + ")");
    return array[index];
  }
  @Override
public Integer get(int index) throws ArrayIndexOutOfBoundsException {
    if (index < 0 || index >= size)
      throw new ArrayIndexOutOfBoundsException("list[" + index + "] is out of bounds (max " + (size - 1) + ")");
    return array[index];
  }
  @Override
public int size() {
    return size;
  }

  public void trimToSize() {
    setCapacity(size);
  }

  private void grow() {
    int delta;
    if (array.length > 64)
      delta = array.length / 4;
    else
      delta = 16;
    setCapacity(array.length + delta);
  }

  private void setCapacity(int newCapacity) {
    int[] newArray = new int[newCapacity];
    System.arraycopy(array, 0, newArray, 0, size);
    array = newArray;
    // System.gc(); //Nice idea, but it costs a lot of performance!
  }

  @Override
public Iterator<Integer> iterator() {

    return new IntIterator();
  }

  private class IntIterator implements Iterator<Integer> {
    private int index = 0;

    @Override
	public boolean hasNext() {

      return index < size;
    }

    @Override
	public Integer next() {
      int val = array[index];
      index++;
      return val;
    }

    @Override
	public void remove() {
      if (index > 0) {
        IntList.this.remove(index - 1);
        index--;
      }
    }

  }

  @Override
public IntList subList(int lowerBound, int upperbound) {
    if (lowerBound < 0 || lowerBound > size ||upperbound < 0 || upperbound > size)
      throw new ArrayIndexOutOfBoundsException("lowerbound: " + lowerBound + " or upperbound: " + upperbound + "  is out of bounds (max " + (size - 1) + ")");
    int[] newArray = new int[upperbound-lowerBound];
    System.arraycopy(array, lowerBound, newArray, 0, upperbound-lowerBound);
    IntList result = new IntList(newArray);
    return result;
  }

  @Override
public boolean add(Integer o) {
    return add(o.intValue());
  }

  @Override
public void add(int index, Integer element) {
    add(index,element.intValue());
  }


  @Override
public boolean contains(Object o) {
    System.out.println("Function not implemented!");
    return false;
  }

  @Override
public boolean containsAll(Collection<?> c) {
    System.out.println("Function not implemented!");
    return false;
  }

  @Override
public int indexOf(Object o) {
    System.out.println("Function not implemented!");
    return 0;
  }

  @Override
public boolean isEmpty() {
    System.out.println("Function not implemented!");
    return false;
  }

  @Override
public int lastIndexOf(Object o) {
    System.out.println("Function not implemented!");
    return 0;
  }

  @Override
public ListIterator<Integer> listIterator() {
    System.out.println("Function not implemented!");
    return null;
  }

  @Override
public ListIterator<Integer> listIterator(int index) {
    System.out.println("Function not implemented!");
    return null;
  }

  @Override
public boolean remove(Object o) {
    System.out.println("Function not implemented!");
    return false;
  }

  @Override
public boolean removeAll(Collection<?> c) {
    System.out.println("Function not implemented!");
    return false;
  }

  @Override
public boolean retainAll(Collection<?> c) {
    System.out.println("Function not implemented!");
    return false;
  }

  @Override
public Integer set(int index, Integer element) {
    return set(index,element.intValue());
  }

  @Override
public <T> T[] toArray(T[] a) {
    System.out.println("Function not implemented!");
    return null;
  }

  @Override
public Object[] toArray() {
    
    return null;
  }

  
}
