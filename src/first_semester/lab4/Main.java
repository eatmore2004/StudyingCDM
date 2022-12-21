package first_semester.lab4;

import java.util.Scanner;

public class Main {

    private static int count_of_iterations = 0;
    static void hanoi(int n, char from_rod, char to_rod, char helper_rod)
    {
        if (n == 1)
        {
            System.out.println("(" + ++count_of_iterations + ") Take disk 1 from rod " +  from_rod + " to rod " + to_rod);
            return;
        }
        hanoi(n-1, from_rod, helper_rod, to_rod);
        System.out.println("(" + ++count_of_iterations + ") Take disk " + n + " from rod " +  from_rod + " to rod " + to_rod);
        hanoi(n-1, helper_rod, to_rod, from_rod);
    }

    public static void main(String args[])
    {
        System.out.println("Write count of elements: ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); // For maximum efficiency set n < 25
        hanoi(n,'A','C', 'B');
        System.out.println("\nTotal iterations := " + count_of_iterations);
    }
}
