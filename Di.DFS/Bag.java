import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

//----------------------------------------------------- 
// Title: bag class
// Author: Berke Beyazbenli
// ID: 10022751132
// Section: 1
// Assignment: 1
// Description: bag implementation
class Bag<T extends Comparable<T>> implements Iterable<T> {
    // --------------------------------------------------------
    // Summary: bagimplementation and other methods
    //
    // Precondition:
    //
    // Postcondition:
    // --------------------------------------------------------

    private List<T> items;
    private Map<T, Integer> indexMap;

    public Bag() {
        this.items = new ArrayList<>();
        this.indexMap = new HashMap<>();
    }

    public void add(T item) {
        items.add(item);
        indexMap.put(item, items.size() - 1);
    }

    public int size() {
        return items.size();
    }

    public T get(int index) {
        return items.get(index);
    }

    public List<T> getItems() {
        return items;
    }

    public int getIndex(T item) {
        return indexMap.getOrDefault(item, -1);
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }

    public void remove(T item) {
        int index = indexMap.getOrDefault(item, -1);
        if (index != -1) {
            items.remove(index);
            updateIndexMap(index);
        }
    }

    private void updateIndexMap(int removedIndex) {
        for (int i = removedIndex; i < items.size(); i++) {
            indexMap.put(items.get(i), i);
        }
    }

    public void sort() {
        items.sort(Comparable::compareTo);
        updateIndexMap(-1); // Yeniden indeksleri güncelle
    }

    public boolean contains(int destIndex) {
        return false;
    }
}
