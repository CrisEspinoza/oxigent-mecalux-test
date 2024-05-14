package oxigent.mecalux.mecalux.service;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ShelfCombinations implements Iterator<String> {
    private final char[] allowedTypes;
    private final int maxSize;
    private StringBuilder current;

    ShelfCombinations(char[] allowedTypes, int maxSize) {
        this.allowedTypes = allowedTypes;
        this.maxSize = maxSize;
        this.current = new StringBuilder();
        current.append(String.valueOf(allowedTypes[0]).repeat(Math.max(0, maxSize)));
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public String next() {
        if (current == null) {
            throw new NoSuchElementException("No more combinations available.");
        }
        String result = current.toString();
        current = generateNext(current);
        return result;
    }

    private StringBuilder generateNext(StringBuilder curr) {
        for (int i = maxSize - 1; i >= 0; i--) {
            int pos = indexOf(allowedTypes, curr.charAt(i));
            if (pos < allowedTypes.length - 1) {
                curr.setCharAt(i, allowedTypes[pos + 1]);
                return curr;
            } else {
                curr.setCharAt(i, allowedTypes[0]);
            }
        }
        return null;
    }

    private int indexOf(char[] array, char key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
