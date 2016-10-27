import java.util.LinkedList;
import java.util.Random;

/**
  * Sorting implementation
  * CS 1332 : Fall 2014
  * @author Tilak Patel
  * @version 1.0
  */
public class Sorting implements SortingInterface {

    // Do not add any instance variables.

    @Override
    public <T extends Comparable<? super T>> void bubblesort(T[] arr) {
        for (int k = 0; k < arr.length; k++) {
            boolean isSorted = true;
            for (int i = 0; i < arr.length - k - 1; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                return;
            }
        }
    }

    @Override
    public <T extends Comparable<? super T>> void insertionsort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T value = arr[i];
            int index = i;
            while (index > 0 && arr[index - 1].compareTo(value) > 0) {
                arr[index] = arr[index - 1];
                index--;
            }
            arr[index] = value;
        }
    }

    @Override
    public <T extends Comparable<? super T>> void selectionsort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    @Override
    public <T extends Comparable<? super T>> void quicksort(T[] arr, Random r) {
        quicksort(arr, 0, arr.length - 1, r);
    }

    /**
     * Helper method to quicksort array by partitioning.
     * Uses recursion.
     * @param arr The array passed in to be sorted
     * @param left the left most  index of the array
     * @param right The right most index of the array
     * @param r random pivot
     */
    private <T extends Comparable<? super T>> void quicksort(T[] arr,
            int left, int right, Random r) {
        if (right > left) {
            int newIndex = partition(arr, left, right,
                    r.nextInt(right - left) + left);
            quicksort(arr, left, newIndex - 1, r);
            quicksort(arr, newIndex + 1, right, r);
        }
    }

    /**
     * Helper method to sort partitions of the array
     * @param arr the array to be sorted
     * @param left the left most index of the partition to be sorted
     * @param right the right most index of the partition to be sorted
     * @param pivotIndex random pivot
     */
    private <T extends Comparable<? super T>> int partition(T[] arr,
            int left, int right, int pivotIndex) {
        T pivot = arr[pivotIndex];
        T temp = arr[pivotIndex];
        arr[pivotIndex] = arr[right];
        arr[right] = temp;
        int index = left;
        for (int i = left; i < right; i++) {
            if (arr[i].compareTo(pivot) <= 0) {
                temp = arr[index];
                arr[index++] = arr[i];
                arr[i] = temp;
            }
        }
        temp = arr[index];
        arr[index] = arr[right];
        arr[right] = temp;
        return index;
    }


    @Override
    public <T extends Comparable<? super T>> void mergesort(T[] arr) {
        T[] temp = (T[]) new Comparable[arr.length];
        mergesort(arr, temp, 0, arr.length - 1);
    }

    /**
     * Helper method to perform mergesort given two sub
     * arrays - left and right. Uses recursion to break down
     * the array into left and right parts.
     *
     * @param arr the array
     * @param temp the new temp array
     * @param left the left array
     * @param right the right array
     */
    private <T extends Comparable<? super T>> void mergesort(T[] arr, T[] temp,
            int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergesort(arr, temp, left, mid);
            mergesort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid + 1, right);
        }
    }

    /**
     * Helper method to merge two (left and right)
     * sorted arrays into the given temp array.
     *
     * @param arr the array
     * @param temp the new temp array
     * @param left the left array
     * @param right the right array
     * @param end the right most index
     */
    private <T extends Comparable<? super T>> void merge(T[] arr, T[] temp,
            int left, int right, int end) {
        int lEnd = right - 1;
        int x = left;
        int n = end - left + 1;
        while (left <= lEnd && right <= end) {
            if (arr[left].compareTo(arr[right]) <= 0) {
                temp[x++] = arr[left++];
            } else {
                temp[x++] = arr[right++];
            }
        }
        while (left <= lEnd) {
            temp[x++] = arr[left++];
        }
        while (right <= end) {
            temp[x++] = arr[right++];
        }
        for (int j = 0; j < n; j++, end--) {
            arr[end] = temp[end];
        }
    }

    @Override
    public int[] radixsort(int[] arr) {
        int len = 0;
        int maxDigits = 0;
        for (int i = 0; i < arr.length; i++) {
            String numStr = Integer.toString(arr[i]);
            if (numStr.substring(0, 1).equals("-")) {
                len = numStr.length() - 1;
            } else {
                len = numStr.length();
            }
            if (len > maxDigits) {
                maxDigits = len;
            }
        }

        LinkedList<Integer>[] sortedList = new LinkedList[19];
        for (int i = 0; i < sortedList.length; i++) {
            sortedList[i] = new LinkedList<Integer>();
        }
        int i = 0;
        while (i < maxDigits) {
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j];
                String digit = Integer.toString(num);
                int dig = 0;
                String aStr = null;
                if (digit.length() - i - 1 >= 0) {
                    aStr = digit.substring(digit.length() - i - 1,
                            digit.length() - i);
                }
                if (aStr != null && !aStr.equals("-")) {
                    dig = Integer.parseInt(aStr);
                }
                if (num < 0) {
                    dig *= -1;
                }
                sortedList[dig + 9].add(num);
            }

            int l = 0;
            for (int k = 0; k < sortedList.length; k++) {
                while (!sortedList[k].isEmpty()) {
                    int val = sortedList[k].getFirst();
                    sortedList[k].removeFirst();
                    arr[l++] = val;
                }
            }
            i++;
        }
        return arr;
    }
}
