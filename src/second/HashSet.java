package second;
import java.util.Arrays;

class MyHashSet<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] table;
    private int size;

    public MyHashSet() {
        this.table = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private int hash(T key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % table.length;
    }

    public boolean insert(T key) {
        if (key == null) {
            return false; 
        }

        int index = hash(key);

 
        while (table[index] != null) {
            if (table[index].equals(key)) {
                return false; 
            }
            index = (index + 1) % table.length; 
        }

        table[index] = key;
        size++;

        if ((double) size / table.length > 0.75) { 
            resizeTable();
        }

        return true;
    }

    public boolean remove(T key) {
        int index = hash(key);

        while (table[index] != null) {
            if (table[index].equals(key)) {
                table[index] = null;
                size--;

                rehashAfterDeletion(index);
                return true;
            }
            index = (index + 1) % table.length;
        }

        return false; 
    }

    private void rehashAfterDeletion(int deletedIndex) {
        int index = (deletedIndex + 1) % table.length;

        while (table[index] != null) {
            T temp = (T) table[index];
            table[index] = null;
            size--;

            insert(temp); 

            index = (index + 1) % table.length;
        }
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        Object[] newTable = new Object[newCapacity];

        Object[] oldTable = table;
        table = newTable;
        size = 0;

        for (Object element : oldTable) {
            if (element != null) {
                insert((T) element);
            }
        }
    }

    public int size() {
        return size;
    }

    public void printTable() {
        System.out.println(Arrays.toString(table));
    }
}


class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    public MyArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void add(T element) {
        ensureCapacity(size + 1);
        data[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) data[index];
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        T removedElement = (T) data[index];

      
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null; 
        size--;
        return removedElement;
    }

    public void addAll(MyArrayList<? extends T> otherList) {
        ensureCapacity(size + otherList.size);
        for (int i = 0; i < otherList.size; i++) {
            data[size++] = otherList.get(i);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(minCapacity, data.length * 2);
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    public int size() {
        return size;
    }

    // для отладки
    public void printList() {
        System.out.println(Arrays.toString(Arrays.copyOf(data, size)));
    }
}


public class HashSet {
    public static void main(String[] args) {

        MyHashSet<String> myHashSet = new MyHashSet<>();
        myHashSet.insert("Apple");
        myHashSet.insert("Banana");
        myHashSet.insert("Orange");
        myHashSet.insert("Apple"); 

        System.out.println("HashSet size: " + myHashSet.size()); 
        myHashSet.printTable();

        myHashSet.remove("Banana");
        System.out.println("HashSet size after removal: " + myHashSet.size()); 
        myHashSet.printTable();

        
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(10);
        myArrayList.add(20);
        myArrayList.add(30);

        System.out.println("ArrayList size: " + myArrayList.size()); 
        myArrayList.printList();

        System.out.println("Element at index 1: " + myArrayList.get(1)); 

        myArrayList.remove(1);
        System.out.println("ArrayList size after removal: " + myArrayList.size()); 
        myArrayList.printList();

        MyArrayList<Integer> anotherList = new MyArrayList<>();
        anotherList.add(40);
        anotherList.add(50);

        myArrayList.addAll(anotherList);
        System.out.println("ArrayList size after addAll: " + myArrayList.size()); 
        myArrayList.printList();

    }
}
