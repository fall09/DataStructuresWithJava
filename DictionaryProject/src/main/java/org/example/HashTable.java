package org.example;
import java.util.ArrayList;
class HashTable<T> {
    static final int size = 300000;
    Object[] table;

    public HashTable() {
        table = new Object[size];
    }

    private int hash(String key) {
        //  simple hash function
        int hashCode = key.toLowerCase().hashCode(); //turn all of them to lower case
        return (hashCode & 0x7FFFFFFF) % size; //  non-negative index
    }

    public void insert(T item) {
        int index = hash(item.toString());
        while (table[index] != null) {
            index = (index + 1) % size;
        }
        table[index] = item;
    }

    public boolean search(T item) {
        int index = hash(item.toString());
        while (table[index] != null) {
            if (table[index].equals(item)) {
                return true;
            }
            index = (index + 1) % size;
        }
        return false;
    }

    public void delete(T item) {
        int index = hash(item.toString());
        int originalIndex = index;

        // check if dictionary contains item
        while (table[index] != null) {
            if (table[index].equals(item)) {
                // delete and return
                table[index] = null;
                return;
            }
            index = (index + 1) % size;

            if (index == originalIndex) {
                return;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                result.append("Index ").append(i).append(": ").append(table[i]).append("\n");
            }
        }
        return result.toString();
    }
    public ArrayList<T> getWords() {
        ArrayList<T> wordList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                wordList.add((T) table[i]);
            }
        }
        return wordList;
    }


}
