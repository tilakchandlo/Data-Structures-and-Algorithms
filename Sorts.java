import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Sort {
    public class Sorts {

    public static int[] bubble(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

            /*  String result = "[ ";
                for (int k = 0; k < array.length; k++) {
                    result = result + array[k] + ", ";
                }
                result = result + "]";
                System.out.println(result); */
            }
        }
        return array;
    }

    private static int[] insertion(int[] input) {

        int temp;

        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
                /* String result = "[ ";
                for (int k = 0; k < input.length; k++) {
                    result = result + input[k] + ", ";
                }
                result = result + "]";
                System.out.println(result); */
            }
        }
        return input;
    }

    public static void main(String[] args) {
        int[] array = { 3, 7, 1, 2, 0, 5 };
        //int[] arrayBubble = bubble(array);
        System.out.println("\n");
        int[] arrayInsertion = insertion(array);
    }

}

    /**
     * Implement bubble sort.
     *
     * It should be: inplace stable
     *
     * Have a worst case running time of: O(n^2)
     *
     * And a best case running time of: O(n)
     *
     * @param arr
     */
    public static <T extends Comparable<T>> void bubblesort(T[] arr) {
        boolean switching = true;

        while (switching) {
            switching = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    switching = true;
                }
            }
        }

    }

    /**
     * Implement insertion sort.
     *
     * It should be: inplace stable
     *
     * Have a worst case running time of: O(n^2)
     *
     * And a best case running time of: O(n)
     *
     * @param arr
     */
    public static <T extends Comparable<T>> void insertionsort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
            for (int j = i; j > 0; j--) {
                if (key.compareTo(arr[j - 1]) < 0) {
                    arr[j] = arr[j - 1];
                } else {
                    arr[j] = key;
                    break;
                }
                if (j == 1) {
                    arr[j - 1] = key;
                }
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use the
     * following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be: inplace
     *
     * Have a worst case running time of: O(n^2)
     *
     * And a best case running time of: O(n log n)
     *
     * @param arr
     */
    public static <T extends Comparable<T>> void quicksort(T[] arr, Random r) {
        quicksort(arr, 0, arr.length - 1, r);

    }

    private static <T extends Comparable<T>> void quicksort(T[] arr, int left,
            int right, Random rand) {
        if (right > left) {
            int pivot = partition(arr, left, right, rand);
            quicksort(arr, left, pivot - 1, rand);
            quicksort(arr, pivot + 1, right, rand);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int left,
            int right, Random rand) {
        int l = left;
        int r = right;
        T temp;

        T pivot = arr[left + rand.nextInt(right - left)];

        while (l <= r) {
            while (arr[l].compareTo(pivot) < 0) {
                l++;
            }
            while (arr[r].compareTo(pivot) > 0) {
                r--;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
        return l;
    }

    /**
     * Implement merge sort.
     *
     * It should be: stable
     *
     * Have a worst case running time of: O(n log n)
     *
     * And a best case running time of: O(n log n)
     *
     * @param arr
     * @return
     */

    public static <T extends Comparable<T>> T[] mergesort(T[] arr) {
        if (arr.length > 1) {
            int mid = (arr.length - 1) / 2;
            T[] left = (T[]) new Comparable[mid + 1];
            T[] right = (T[]) new Comparable[arr.length - mid - 1];
            for (int i = 0; i < left.length; i++) {
                left[i] = arr[i];
            }
            for (int j = mid + 1; j < arr.length; j++) {
                right[j - mid - 1] = arr[j];
            }

            if (left.length > 1)
                left = mergesort(left);
            if (right.length > 1)
                right = mergesort(right);
            arr = merge(left, right);
        }
        return arr;
    }

    private static <T extends Comparable<T>> T[] merge(T[] left, T[] right) {
        T[] output = (T[]) new Comparable[left.length + right.length];
        int l = 0;
        int r=0;
        int i=0;
        while ((l < left.length && r < right.length)){
            if ((left[l]).compareTo(right[r]) < 0) {
                output[i] = left[l++];
            } else {
                output[i] = right[r++];
            }
            i++;
        }
        if (l < left.length )
            while (l < left.length )
                output[i++] = left[l++];
        else if (r < right.length)
            while (r < right.length) {
                output[i++] = right[r++];
            }
        return output;
    }

    /**
     * Implement radix sort
     *
     * Hint: You can use Integer.toString to get a string of the digits. Don't
     * forget to account for negative integers, they will have a '-' at the
     * front of the string.
     *
     * It should be: stable
     *
     * Have a worst case running time of: O(kn)
     *
     * And a best case running time of: O(kn)
     *
     * @param arr
     * @return
     */
    public static int[] radixsort(int[] arr) {
        int len = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) { // finding max amount of digits
            String str = Integer.toString(arr[i]);

            if (str.substring(0, 1).equals("-"))
                len = str.length() - 1;
            else
                len = str.length();

            if (len > max)
                max = len;
        }

        LinkedList<Integer>[] sorted = new LinkedList[19];
        for (int i = 0; i < sorted.length; i++)
            sorted[i] = new LinkedList<Integer>();

        int i = 0;
        while (i < max) {
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j];
                String digit = Integer.toString(num);
                int dig = 0;
                String str = null;
                if (digit.length() - i - 1 >= 0) {
                    str = digit.substring(digit.length() - i - 1,
                            digit.length() - i);
                }
                if (str != null && !str.equals("-"))
                    dig = Integer.parseInt(str);
                if (num < 0)
                    dig *= -1;

                sorted[dig + 9].add(num);
            }

            int l = 0;
            for (int k = 0; k < sorted.length; k++) {
                while (!sorted[k].isEmpty()) {
                    int val = sorted[k].getFirst();
                    sorted[k].removeFirst();
                    arr[l++] = val;
                }
            }
            i++;
        }

        return arr;

    }

}
