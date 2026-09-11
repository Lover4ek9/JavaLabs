import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== TASK 1 =====");
        task1();

        System.out.println("===== TASK 2 =====");
        task2();

        System.out.println("===== TASK 3 =====");
        task3();
    }

    public static void task1() {
        List<Integer> numbers = new ArrayList<>();

        System.out.println("Write 7 numbers");

        for (int i = 0; i < 7; i++) {
            int number = scanner.nextInt();
            numbers.add(number);
        }

        int count = 0;

        for (int number : numbers) {

            if (number >= 10000 && number <= 99999) {

                int first = number / 10000;
                int middle = (number / 100) % 10;
                int last = number % 10;

                if (middle == first + last) {
                    System.out.println(number);
                    count++;
                }
            }
        }

        System.out.println("Count: " + count);
    }


    public static void task2() {
        List<Integer> numbers = new ArrayList<>();

        System.out.println("Write 7 numbers");

        for (int i = 0; i < 7; i++) {
            int number = scanner.nextInt();
            numbers.add(number);
        }

        boolean found = false;

        for (int number : numbers) {

            if (number >= 100 && number <= 99999) {

                boolean descending = true;

                while (number > 9) {
                    int last = number % 10;
                    int previous = (number / 10) % 10;

                    if (previous <= last) {
                        descending = false;
                        break;
                    }

                    number /= 10;
                }

                if (descending) {
                    found = true;
                }
            }
        }

        if (found) {
            System.out.println("Such numbers exist");
        } else {
            System.out.println("Such numbers do not exist");
        }
    }

    public static void task3() {
        List<Integer> numbers = new ArrayList<>();

        System.out.println("Write 7 numbers");

        for (int i = 0; i < 7; i++) {
            int number = scanner.nextInt();
            numbers.add(number);
        }

        for (int number : numbers) {

            if (number >= 1000 && number <= 9999) {

                int a = number / 1000;
                int b = (number / 100) % 10;
                int c = (number / 10) % 10;
                int d = number % 10;

                if ((a == 2 || a == 3 || a == 7)
                        && (b == 0 || b == 2 || b == 3 || b == 7)
                        && (c == 0 || c == 2 || c == 3 || c == 7)
                        && (d == 0 || d == 2 || d == 3 || d == 7)
                        && a != b && a != c && a != d
                        && b != c && b != d
                        && c != d) {

                    System.out.println(number);
                }
            }
        }
    }
}