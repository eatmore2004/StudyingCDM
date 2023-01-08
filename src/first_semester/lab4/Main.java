package first_semester.lab4;

public class Main {

    private static int count_of_iterations = 0;
    private static final int HANOI_COUNT = 10;
    private static final int FIBO_COUNT = 41;

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

        long startTime, endTime;

        // Hanoi Towers O(2^N)
        startTime = System.nanoTime();
        hanoi(HANOI_COUNT, 'A', 'C', 'B');
        endTime = System.nanoTime();
        System.out.println("\nDuration of task : " + getHumanDuration(endTime - startTime) + "ms");
        System.out.println("Total hanoi iterations := " + count_of_iterations);

        // Iterative O(N)
        startTime = System.nanoTime();
        System.out.println("\nIterative Fibonacci := " + fibo_i(FIBO_COUNT));
        endTime = System.nanoTime();
        System.out.println("Duration is: " + getHumanDuration(endTime - startTime) + "ms");

        // Recursive O(2^N)
        startTime = System.nanoTime();
        System.out.println("\nRecursive Fibonacci := " + fibo_r(FIBO_COUNT));
        endTime = System.nanoTime();
        System.out.println("Duration is: " + getHumanDuration(endTime - startTime) + "ms");

        // Closed form formula 0(1)
        startTime = System.nanoTime();
        System.out.println("\nClosed form formula Fibonacci := " + fibo_c(FIBO_COUNT));
        endTime = System.nanoTime();
        System.out.println("Duration is: " + getHumanDuration(endTime - startTime) + "ms");

    }

    private static int fibo_i(int n) {
        int num1 = 0, num2 = 1;
        int counter = 0;
        while (counter < n) {
            int num3 = num2 + num1;
            num1 = num2;
            num2 = num3;
            counter = counter + 1;
        }
        return num1;
    }

    private static int fibo_c(int n) {
        double a = (1 + Math.sqrt(5)) / 2;
        double b = (1 - Math.sqrt(5)) / 2;
        return (int) (1/Math.sqrt(5) * (Math.pow(a,n) - Math.pow(b,n)));
    }

    private static int fibo_r(int n) {
        if (n <= 1)
            return n;
        return fibo_r(n - 1) + fibo_r(n - 2);
    }


}
