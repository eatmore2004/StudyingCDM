package first_semester.lab4;

import java.util.Scanner;

public class Main {

    private static int count_of_iterations = 0;

    static void hanoi(int n, char from_rod, char to_rod, char helper_rod) {
        if (n == 1) {
            System.out.println("(" + ++count_of_iterations + ") Take disk 1 from rod " + from_rod + " to rod " + to_rod);
            return;
        }
        hanoi(n - 1, from_rod, helper_rod, to_rod);
        System.out.println("(" + ++count_of_iterations + ") Take disk " + n + " from rod " + from_rod + " to rod " + to_rod);
        hanoi(n - 1, helper_rod, to_rod, from_rod);
    }

    private static String getHumanDuration(long duration) {
        long millis = duration / 1000000 % 1000;
        long second = (duration / 1000000 / 1000) % 60;
        long minute = (duration / 1000000 / (1000 * 60)) % 60;
        long hour = (duration / 1000000 / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d.%03d", hour, minute, second, millis);
    }

    public static void main(String[] args) {
        System.out.println("Write count of elements: ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); // For maximum efficiency set n < 25
        long startTime, endTime, duration;
        startTime = System.nanoTime();
        hanoi(n, 'A', 'C', 'B');
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("Duration of task : " + getHumanDuration(duration) + "ms");
        System.out.println("\nTotal iterations := " + count_of_iterations);
    }
}
