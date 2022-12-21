package first_semester.lab3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    private static final int LENGTH = 11;
    private static final boolean LOG_RESULTS = true;

    public static void main(String[] args) {

        long startTime,endTime,duration_perm = 0,duration_comb = 0;

        int[] array = IntStream.range(0, LENGTH).toArray();

        if (LOG_RESULTS) System.out.println("\n--------------------------PERMUTATION-------------------------------");
        startTime = System.nanoTime();
        permutate(array,0);
        endTime = System.nanoTime();
        duration_perm = (endTime - startTime);

        if (LOG_RESULTS) System.out.println("\n--------------------------COMBINATION-------------------------------");
        startTime = System.nanoTime();
        combination(array,9);
        endTime = System.nanoTime();
        duration_comb = (endTime - startTime);

        System.out.println("\n\n--------------------------RESULTS-------------------------------");
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Array LENGTH: " + LENGTH);
        System.out.println("Duration of permutation : " + getHumanDuration(duration_perm) + "ms");
        System.out.println("Duration of combination : " + getHumanDuration(duration_comb) + "ms");
        System.out.println("--------------------------------END---------------------------------");
    }

    private static String getHumanDuration(long duration) {
        long millis = duration / 1000000 % 1000;
        long second = (duration / 1000000 / 1000) % 60;
        long minute = (duration / 1000000 / (1000 * 60)) % 60;
        long hour = (duration / 1000000 / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d.%03d", hour, minute, second, millis);
    }


    private static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void combination_factory(int[] arr, int n, int index, int[] data, int i) {
        if (index == data.length) {
            if (LOG_RESULTS) {
                for (int datum : data) System.out.print(datum + " ");
                System.out.println("");
            }
            return;
        }
        if (i >= n)
            return;
        data[index] = arr[i];
        combination_factory(arr, n, index+1, data, i+1);
        combination_factory(arr, n, index, data, i+1);
    }

    private static void combination(int[] a, int k) {
        int[] temp = new int[k];
        combination_factory(a, a.length, 0, temp, 0);
    }

    private static void permutate(int[] a, int start_index) {
        if (start_index == a.length - 1 && LOG_RESULTS){
            System.out.println("");
            for (int j : a) System.out.print(j + " ");
            return;
        }
        for (int i = start_index; i < a.length; i++) {
            swap(a,i,start_index);
            permutate(a,start_index+1);
            swap(a,i,start_index);
        }
    }
}