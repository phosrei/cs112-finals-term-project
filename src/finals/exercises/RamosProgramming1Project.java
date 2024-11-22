package finals.exercises;

import java.util.Scanner;

public class RamosProgramming1Project {
    public static void main(String[] args) {
        boolean programRunning = true;

        showIntroduction();
        do {
            showMainMenu();
            switch (chooseOption(1, 4)) {
                case 1 -> mathRoutines();
                case 2 -> recordingRoutines();
                case 3 -> miscellaneousRoutines();
                case 4 -> programRunning = false;
            }
        } while (programRunning);
    }

    private static void miscellaneousRoutines() {
        // TODO
    }

    private static void recordingRoutines() {
        // TODO
    }

    public static void showIntroduction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------------");
        System.out.println("Department of Computer Science and Computer Applications");
        System.out.println("School of Accountancy Management Computing and Information Studies");
        System.out.println("Saint Louis University");
        System.out.println("Baguio City\n");
        System.out.println("Ricky Marc Ramos");
        System.out.println("Programmer");
        System.out.println("-------------------------------------------------------------------");

        System.out.print("\nENTER ANY KEY TO CONTINUE: ");
        scanner.nextLine();
    }

    public static void showMainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("-------------------------");
        System.out.println("1. Math Routines");
        System.out.println("2. Recording Routines");
        System.out.println("3. Miscellaneous Routines");
        System.out.println("4. Exit");
        System.out.println("-------------------------");
    }

    public static int chooseOption(int min, int max) {
        while (true) {
            String message = "\nSelect an option (" + min + "-" + max + "): ";
            int choice = getInteger(message);

            if (choice >= min && choice <= max) {
                return choice;
            } else {
                System.out.printf("Enter a number between %d and %d", min, max);
            }
        }
    }

    public static int getInteger(String message) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Must be a number.");
            }
        }
    }

    public static void mathRoutines() {
        boolean backToMainMenu = false;

        do {
            showMathRoutinesMenu();

            switch (chooseOption(1, 16)) {
                case 1 -> checkParity();
                case 2 -> calculateSumOfSeries();
                case 3 -> findNumberFactors();
                case 4 -> checkPrimeNumber();
                case 5 -> calculateCircleArea();
                case 6 -> calculateSquareArea();
                case 7 -> calculateTriangleArea();
                case 8 -> calculateParallelogramArea("rectangle");
                case 9 -> calculateTrapezoidArea();
                case 10 -> calculateParallelogramArea("parallelogram");
                case 11 -> checkPerfectNumber();
                case 12 -> generateMultiplicationTable();
                case 13 -> solveQuadraticEquation();
                case 14 -> generateFibonacciSequence();
                case 15 -> generatePascalsTriangle();
                case 16 -> backToMainMenu = true;
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (!backToMainMenu);
    }

    public static boolean useProgramAgain() {
        Scanner scanner = new Scanner(System.in);

        char option;
        do {
            System.out.print("Use program again? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

                option = input.charAt(0);
                if (option == 'y' || option == 'n') {
                    return option == 'y';
                }

            System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
        } while (true);
    }

    private static void generatePascalsTriangle() {
        // TODO
    }

    private static void generateFibonacciSequence() {
        do {
            int numOfTerms = getInteger("Enter desired number of terms: ");
            int firstTerm = getInteger("Enter first term: ");
            int secondTerm = getInteger("Enter second term: ");

            System.out.print(firstTerm + " " + secondTerm + " ");

            for (int i = 2; i < numOfTerms; i++) {
                int sum = firstTerm + secondTerm;
                firstTerm = secondTerm;
                secondTerm = sum;

                System.out.print(sum + " ");
            }
        } while (useProgramAgain());
    }

    private static void solveQuadraticEquation() {
        // TODO
    }

    private static void generateMultiplicationTable() {
        System.out.println("\n-- MULTIPLICATION TABLE GENERATOR --");

        int row;
        int column;

        do { // Validation of row
            row = getInteger("Enter row: ");

            if (row > 30) {
                System.out.println("Row input is too large. Provide a smaller number.");
            } else if (row <= 0) {
                System.out.println("Row input should be a positive number.");
            }
        } while (row > 30 || row <= 0);

        do { // Validation of column
            System.out.print("Enter column: ");
            column = getInteger("Enter column: ");

            if (column > 30) {
                System.out.println("\nColumn input is too large. Provide a smaller number.\n");
            } else if (column <= 0) {
                System.out.println("Column input should be a positive number.");
            }
        } while (column > 30 || column <= 0);

        // Print multiplication table after user inputs are validated
        for (int i = 1; i <= row; i++) {
            for (int j= 1; j <= column; j++) {
                System.out.printf("%8d", i * j);
            }
            System.out.println();
        }
    }

    private static void checkPerfectNumber() {
        System.out.println("\n-- PERFECT NUMBER FINDER --");
        int n = getInteger("Enter a number: ");

        if (n > 1) {
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    sum += i;
                }
            }
            if (n == sum) {
                System.out.println(n + " is a perfect number");
            }
        } else {
            System.out.println(n + " must be a positive integer");
        }
    }

    private static void calculateTrapezoidArea() {
        System.out.println("\n-- SHAPE AREA CALCULATOR --");
        int base1 = getInteger("Enter base 1 of trapezoid: ");
        int base2 = getInteger("Enter base 2 of trapezoid: ");
        int height = getInteger("Enter height of trapezoid: ");
        int area = ((base1 + base2) / 2) * height;

        System.out.println("The area of the trapezoid is " + area);
    }

    private static void calculateParallelogramArea(String shape) {
        System.out.println("\n-- SHAPE AREA CALCULATOR --");
        int length = getInteger("Enter length of rectangle: ");
        int width = getInteger("Enter width of rectangle: ");
        int area = length * width;

        System.out.printf("The area of the %s is %d", shape, area);
    }
    
    private static void calculateTriangleArea() {
        System.out.println("\n-- SHAPE AREA CALCULATOR --");
        int height = getInteger("Enter height of triangle: ");
        int base = getInteger("Enter base of triangle: ");
        int area = (height * base) / 2;

        System.out.println("The area of the triangle is " + area);
    }

    private static void calculateSquareArea() {
        System.out.println("\n-- SHAPE AREA CALCULATOR --");
        int side = getInteger("Enter side of square:");
        int area = side * side;

        System.out.println("The are of the square is " + area);
    }

    private static void calculateCircleArea() {
        System.out.println("\n-- SHAPE AREA CALCULATOR --");
        int radius = getInteger("Enter radius of circle: ");
        double area = 3.14 * (radius * radius);

        System.out.println("The area of the circle is " + area);
    }

    public static void showMathRoutinesMenu() {
        System.out.println("\nMATH ROUTINES");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Determine whether an integer is odd or even");
        System.out.println("2. Determine sum of a series");
        System.out.println("3. Determine the factors of a number");
        System.out.println("4. Determine if a number is prime");
        System.out.println("5. Determine the area of a circle");
        System.out.println("6. Determine the area of a square");
        System.out.println("7. Determine the area of a triangle");
        System.out.println("8. Determine the area of a rectangle");
        System.out.println("9. Determine the area of a trapezoid");
        System.out.println("10. Determine the area of a parallelogram");
        System.out.println("11. Determine if an integer is a perfect number");
        System.out.println("12. Generate a multiplication table");
        System.out.println("13. Determine the roots of a quadratic equation");
        System.out.println("14. Generate a Fibonacci Sequence");
        System.out.println("15. Generate a Pascal's Triangle");
        System.out.println("16. Back to Main Menu");
        System.out.println("-----------------------------------------------");
    }

    public static void checkParity() {
        System.out.println("\n-- PARITY CHECKER --");

        do {
            int n = getInteger("Enter a number: ");
            System.out.println(n + " is " + (n % 2 == 0 ? "even" : "odd"));
        } while (useProgramAgain());
    }

    public static void calculateSumOfSeries() {
        int n = getInteger("Enter a number: ");
        double sum = 0;

        if (n > 1 && n % 2 == 0) {
            for (int ctr = n; ctr > 1; ctr -= 2)
                sum += (1.0 / ctr);
        }
        System.out.printf("The sum of the series %d is: %f", n, sum);
    }

    public static void findNumberFactors() {
        System.out.println("\n-- FACTOR NUMBER FINDER --");
        int n = getInteger("Enter a number: ");

        for (int i = 1; i <= n; i++) {
            if ( n % i == 0) {
                System.out.print(i);
            }
        }
    }

    public static void checkPrimeNumber() {
        System.out.println("\n-- PRIME NUMBER CHECKER --");
        int n = getInteger("Enter a number: ");
        boolean isPrime = true;

        if (n > 1) {
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            } if (isPrime) {
                System.out.println(n + " is a prime number");
            } else {
                System.out.println(n + " is not a prime number");
            }
        } else {
            System.out.println(n + " is not a prime number");
        }
    }
}
