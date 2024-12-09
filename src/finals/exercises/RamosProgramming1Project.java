/*
Name: Ramos, Ricky Marc S.
Date: 07/12/24
Class: CS112 9313
 */

package finals.exercises;

import java.util.Arrays;
import java.util.Random;
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

    public static void miscellaneousRoutines() {
        boolean backToMainMenu = false;

        do {
            showMiscellaneousRoutinesMenu();

            switch (chooseOption(1, 11)) {
                case 1 -> playNumberGuessingGame();
                case 2 -> covid19SelfEvaluation();
                case 3 -> calculateBillsDistribution();
                case 4 -> computeInvestmentInterest();
                case 5 -> calculateIncomeTax();
                case 6 -> computeInsectGrowthRate();
                case 7 -> computeWaterBill();
                case 8 -> computeElectricBill();
                case 9 -> computeMobileLoadBalance();
                case 10 -> convertTemperature();
                case 11 -> backToMainMenu = true;
            }
        } while (!backToMainMenu);
    }

    private static void convertTemperature() {
        do {
            System.out.println("\n-- TEMPERATURE CONVERSION --");
            int choice = getInteger("Choose conversion: 1. Fahrenheit to Celsius, 2. Celsius to Fahrenheit: ");
            double temperature, convertedTemperature;

            if (choice == 1) {
                temperature = getDouble("Enter temperature in Fahrenheit: ");
                convertedTemperature = (temperature - 32) * 5 / 9;
                System.out.printf("\n%.2f Fahrenheit is %.2f Celsius%n", temperature, convertedTemperature);
            } else if (choice == 2) {
                temperature = getDouble("Enter temperature in Celsius: ");
                convertedTemperature = (temperature * 9 / 5) + 32;
                System.out.printf("\n%.2f Celsius is %.2f Fahrenheit%n", temperature, convertedTemperature);
            } else {
                System.out.println("\nInvalid choice! Please enter 1 or 2.");
            }
        } while (useProgramAgain());
    }

    private static void computeMobileLoadBalance() {
        do {
            System.out.println("\n-- MOBILE PHONE LOAD BALANCE COMPUTATION --");
            double initialLoad = getDouble("Enter the initial load balance (in PHP): ");
            double amountUsed = getDouble("Enter the amount used (in PHP): ");

            if (amountUsed > initialLoad) {
                System.out.println("\nInsufficient balance!");
            } else {
                double remainingBalance = initialLoad - amountUsed;
                System.out.printf("\nThe remaining balance is: %.2f PHP%n", remainingBalance);
            }
        } while (useProgramAgain());
    }

    private static void computeElectricBill() {
        String consumerName = getName("Enter the name of the consumer");
        int previousReading = getReading("Enter the electricity reading last month", 0);
        int presentReading = getReading("Enter the current electricity reading", previousReading);
        char consumerType = getConsumerTypeElectricity("Enter consumer type (i for industrial and r for residential)");

        displayBill2(consumerName, consumerType, previousReading, presentReading);
    }

    public static char getConsumerTypeElectricity(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(message + ": ");
                char input = scanner.nextLine().charAt(0);
                char consumerType = Character.toLowerCase(input);

                if (consumerType == 'r' || consumerType == 'i') {
                    return consumerType;
                }
                System.out.println("You must enter r for residential or c for industrial");
            } catch (Exception e) {
                System.out.println("You must enter a valid character");
            }
        }
    }

    public static void displayBill2(String name, char type, int previousReading, int presentReading) {
        System.out.printf("%n%-30s%-30s%n","Name of Consumer :",name);
        if (type == 'r') {
            System.out.printf("%-30s%-30s%n", "Type of Consumer :", "Residential");
        } else if (type == 'i') {
            System.out.printf("%-30s%-30s%n", "Type of Consumer :", "Industrial");
        }

        int consumption = presentReading - previousReading;

        System.out.printf("%-30s%d%s%n", "Previous Reading :", previousReading, " kilowatt-hours");
        System.out.printf("%-30s%d%s%n", "Present Reading :", presentReading, " kilowatt-hours");
        System.out.printf("%-30s%d%s%n", "Kilowatt-Hours Consumed :", consumption, " kilowatt-hours");

        double amountDue = computeAmountDue2(type, consumption);

        System.out.printf("%-30sPHP %.2f%n", "Amount Due from Consumer :", amountDue);
    }

    public static double computeAmountDue2(char type, int consumption) {
        double amountDue = 0;

        if (type == 'r') {
            amountDue = computeResidentialBill2(consumption);
        } else if (type == 'i') {
            amountDue = computeIndustrialBill2(consumption);
        }

        return amountDue;
    }

    private static double computeResidentialBill2(int consumption) {
        int minConsumption = 200; // cut-off for minimum bill for residential consumers
        double minBill = 2227.2d; // minimum bill for <= 200 Kilowatt-Hours used
        double rate = 11.1360d; // cost of 1 Kilowatt-hour above the minimum consumption

        if (consumption <= minConsumption) {
            return minBill;
        } else {
            return minBill + (consumption - minConsumption) * rate;
        }
    }

    private static double computeIndustrialBill2(int consumption) {
        int minConsumption = 500; // cut-off for minimum Bill for industrial consumers
        double minBill = 5203.65d; // minimum bill for <= 500 Kilowatt-Hours used
        double rate = 10.4073d; // cost of 1 Kilowatt-hour above the minimum consumption

        if (consumption <= minConsumption) {
            return minBill;
        } else {
            return minBill + (consumption - minConsumption) * rate;
        }
    }

    private static void computeWaterBill() {
        String consumerName = getName("Enter the name of the consumer");
        int previousReading = getReading("Enter the water reading last month", 0);
        int presentReading = getReading("Enter the current water reading", previousReading);
        char consumerType = getConsumerType("Enter consumer type (c for commercial and r for residential)");

        displayBill(consumerName, consumerType, previousReading, presentReading);
    }

    public static String getName(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(message + ": ");
        return scanner.nextLine();
    }

    public static int getReading(String message, int lowerLimit) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(message + ": ");
                int reading = Integer.parseInt(scanner.nextLine());
                if (reading > lowerLimit) {
                    return reading;
                }
                System.out.println("The reading cannot be less than " + lowerLimit);
            } catch (Exception e) {
                System.out.println("You entered an invalid number");
            }
        }
    }

    public static char getConsumerType(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(message + ": ");
                char input = scanner.nextLine().charAt(0);
                char consumerType = Character.toLowerCase(input);

                if (consumerType == 'r' || consumerType == 'c') {
                    return consumerType;
                }
                System.out.println("You must enter r for residential or c for commercial");
            } catch (Exception e) {
                System.out.println("You must enter a valid character");
            }
        }
    }

    public static void displayBill(String name, char type, int previousReading, int presentReading) {
        System.out.printf("%n%-30s%-30s%n","Name of Consumer :",name);
        if (type == 'r') {
            System.out.printf("%-30s%-30s%n", "Type of Consumer :", "Residential");
        }
        if (type == 'c') {
            System.out.printf("%-30s%-30s%n", "Type of Consumer :", "Commercial");
        }

        int consumption = presentReading - previousReading;

        System.out.printf("%-30s%d%s%n", "Previous Reading :", previousReading, " cubic meters");
        System.out.printf("%-30s%d%s%n", "Present Reading :", presentReading, " cubic meters");
        System.out.printf("%-30s%d%s%n", "Cubic Meters Consumed :", consumption, " cubic meters");

        double amountDue = computeAmountDue(type, consumption);

        System.out.printf("%-30sPHP %.2f%n", "Amount Due from Consumer :", amountDue);
    }

    public static double computeAmountDue(char type, int consumption) {
        double amountDue = 0;

        if (type == 'r') {
            amountDue = computeResidentialBill(consumption);
        } else if (type == 'c') {
            amountDue = computeCommercialBill(consumption);
        }

        return amountDue;
    }

    private static double computeResidentialBill(int consumption) {
        int minConsumption = 12; // cut-off for minimum bill for residential consumers
        double minBill = 280.00; // minimum bill for <= 12 Cubic Meters used
        double rate = 30.00d; // cost of 1 Cubic Meter above the minimum consumption

        if (consumption <= minConsumption) {
            return minBill;
        } else {
            return minBill + (consumption - minConsumption) * rate;
        }
    }

    private static double computeCommercialBill(int consumption) {
        int minConsumption = 30; // cut-off for minimum Bill for commercial consumers
        double minBill = 1000.00; // minimum bill for <= 30 Cubic Meters used
        double rate = 50.00d; // cost of 1 Cubic Meter above the minimum consumption

        if (consumption <= minConsumption) {
            return minBill;
        } else {
            return minBill + (consumption - minConsumption) * rate;
        }
    }

    private static void computeInvestmentInterest() {
        do {
            System.out.println("\n-- INTEREST OF MONEY INVESTED --");
            double principal = getDouble("Enter the principal amount: ");
            double rate = getDouble("Enter the interest rate (in percentage): ");
            int time = getInteger("Enter the time period (in years): ");

            double interest = (principal * rate * time) / 100;
            double totalAmount = principal + interest;

            System.out.printf("\nThe interest earned is: %.2f%n", interest);
            System.out.printf("The total amount after interest is: %.2f%n", totalAmount);
        } while (useProgramAgain());
    }

    private static void calculateIncomeTax() {
        do {
            System.out.println("\n-- INCOME TAX COMPUTATION --");
            double income = getDouble("Enter the annual income: ");
            double tax;

            if (income <= 250000) {
                tax = 0;
            } else if (income <= 500000) {
                tax = (income - 250000) * 0.05;
            } else if (income <= 1000000) {
                tax = (income - 500000) * 0.1 + 12500; // tax for the first bracket
            } else {
                tax = (income - 1000000) * 0.2 + 12500 + 50000; // tax for the first two brackets
            }

            System.out.printf("\nThe income tax to be paid is: %.2f%n", tax);
        } while (useProgramAgain());
    }

    private static void computeInsectGrowthRate() {
        do {
            int initialSize = getInteger("Initial population: ");
            int currentSize = getInteger("Current population: ");

            double rateOfGrowth = (currentSize - initialSize) * 100.0 / initialSize;

            System.out.print("\nThe rate of growth is " + rateOfGrowth + "%");
            System.out.println(" because (" + currentSize + " - " + initialSize + ") * 100 / " + initialSize + " is " +
                    rateOfGrowth + "%");
        } while (useProgramAgain());
    }

    private static void calculateBillsDistribution() {
            do {
                System.out.println("\n-- BILLS DISTRIBUTION --");
                double amount = getDouble("Enter the amount of money: ");
                int bill500 = (int) (amount / 500);
                amount %= 500;
                int bill200 = (int) (amount / 200);
                amount %= 200;
                int bill100 = (int) (amount / 100);
                amount %= 100;
                int bill50 = (int) (amount / 50);
                amount %= 50;
                int bill20 = (int) (amount / 20);
                amount %= 20;
                int bill10 = (int) (amount / 10);
                amount %= 10;
                int bill5 = (int) (amount / 5);
                amount %= 5;
                int bill1 = (int) (amount);

                System.out.println("\nBills distribution: ");
                System.out.println("500 bills: " + bill500);
                System.out.println("200 bills: " + bill200);
                System.out.println("100 bills: " + bill100);
                System.out.println("50 bills: " + bill50);
                System.out.println("20 bills: " + bill20);
                System.out.println("10 bills: " + bill10);
                System.out.println("5 bills: " + bill5);
                System.out.println("1 bills: " + bill1);
            } while (useProgramAgain());
        }

    private static void covid19SelfEvaluation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("COVID-19 Self-Assessment");
        System.out.println("Please answer the following questions with 'yes' or 'no'.\n");

        System.out.print("Do you have a fever? (yes/no): ");
        String fever = scanner.nextLine().toLowerCase();

        System.out.print("Do you have a cough? (yes/no): ");
        String cough = scanner.nextLine().toLowerCase();

        System.out.print("Are you experiencing shortness of breath? (yes/no): ");
        String breath = scanner.nextLine().toLowerCase();

        System.out.print("Do you have a sore throat? (yes/no): ");
        String soreThroat = scanner.nextLine().toLowerCase();

        System.out.print("Have you been in close contact with someone who tested positive for COVID-19? (yes/no): ");
        String contact = scanner.nextLine().toLowerCase();

        System.out.print("Have you recently traveled to a COVID-19 hotspot? (yes/no): ");
        String travel = scanner.nextLine().toLowerCase();

        if (fever.equals("yes") || cough.equals("yes") || breath.equals("yes") || soreThroat.equals("yes") ||
                contact.equals("yes") || travel.equals("yes")) {
            System.out.println("\nYou may be at risk for COVID-19. It is recommended that you contact your " +
                    "healthcare provider for further evaluation.");
        } else {
            System.out.println("\nYou are currently showing no symptoms. " +
                    "Continue to follow health guidelines and monitor your health.");
        }
    }

    private static void playNumberGuessingGame() {
        do {
            Random random = new Random();
            int secretNum = random.nextInt(1000 + 1);
            int guessCount = 1;

            System.out.println(secretNum);

            System.out.println("""
                      This is a simple number guessing game application.
                      I have a secret number that is in the range 1 to 1000.
                      You have to guess my secret number.
                      The limit is 20 tries.
                      """);

            while (true) {
                int guess = getInteger("Enter your guess #" + guessCount);

                if (guess < secretNum) {
                    System.out.println("Try a higher number");
                } else if (guess > secretNum) {
                    System.out.println("Try a lower number");
                } else {
                    System.out.println("\nCongratulations!\nYou guessed it in " + guessCount + " tries!");
                    break;
                }

                if (guessCount == 20) {
                    System.out.println("Sorry! You reached 20 tries without correctly guessing the number.");
                    break;
                }

                guessCount++;
            }
        } while (useProgramAgain());
    }

    public static void showMiscellaneousRoutinesMenu() {
        System.out.println("\nMISCELLANEOUS ROUTINES ");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Number Guessing Game");
        System.out.println("2. Covid19 Self-Assessment Procedure ");
        System.out.println("3. Bills distribution of an amount of Money");
        System.out.println("4. Interest of Money Invested ");
        System.out.println("5. Income Tax Computation");
        System.out.println("6. Insect Population Growth Rate");
        System.out.println("7. Water Bill computation.");
        System.out.println("8. Electric Bill computation.");
        System.out.println("9. Mobile Phone Load Balance Computation.");
        System.out.println("10. Temperature Conversion (Fahrenheit to Celsius or Celsius to Fahrenheit)");
        System.out.println("11. Back to Main Menu");
        System.out.println("------------------------------------------------");
    }

    private static void recordingRoutines() {
        boolean backToMainMenu = false;

        do {
            showRecordingRoutinesMenu();

            switch (chooseOption(1, 6)) {
                case 1 -> addAndSortStudentList();
                case 2 -> sortSalesmenList();
                case 3 -> sortStudentsByName();
                case 4 -> sortStudentsByGrade();
                case 5 -> backToMainMenu = true;
            }
        } while (!backToMainMenu);
    }

    public static void sortSalesmenList() {
        do {
            Scanner scanner = new Scanner(System.in);

            int n = getInteger("How many salesmen will be sorted? ");
            String[] names = new String[n];
            double[] sales = new double[n];

            // Populate arrays names and sales
            for (int z = 0; z < n; z++) {
                System.out.print("Enter name of salesman " + (z + 1) + ": ");
                names[z] = scanner.nextLine();
                sales[z] = getDouble("Enter total sales of " + names[z] + ": ");
            }

            // Sort salesmen based on sales
            sortByNum(names, sales);

            // Display sorted list of salesmen
            System.out.println();
            for (int i = 0; i < names.length; i++) {
                System.out.printf("Salesman: %s, Sales: %.2f%n", names[i], sales[i]);
            }

        } while (useProgramAgain());
    }

    public static void addAndSortStudentList() {
        do {
            Scanner scanner = new Scanner(System.in);

            String[] names;
            int n = getInteger("How many names will be sorted? ");
            names = new String[n];

            // Populate array names
            for (int z = 0; z < names.length; z++) {
                System.out.print("Enter name of student " + (z + 1) + ": ");
                names[z] = scanner.nextLine();
            }
            System.out.println("\n" + Arrays.toString(sortByName(names)));
        } while (useProgramAgain());
    }

    public static void sortStudentsByGrade() {
        do {
            Scanner scanner = new Scanner(System.in);

            int n = getInteger("How many students will be sorted? ");
            String[] names = new String[n];
            double[] grades = new double[n];

            // Populate arrays names and grades
            for (int z = 0; z < names.length; z++) {
                System.out.print("Enter name of student " + (z + 1) + ": ");
                names[z] = scanner.nextLine();
                double grade;

                while (true) {
                    grade = getDouble("Enter grade of " + names[z] + ": ");
                    if (grade >= 0 && grade <= 100) {
                        break;
                    }
                    System.out.println("Grade must be between 0 and 100");
                }
                grades[z] = grade;
            }

            sortByNum(names, grades);

            System.out.println();
            for (int i = 0; i < names.length; i++) {
                System.out.printf("Name: %s, Grade: %.2f%n", names[i], grades[i]);
            }
        } while (useProgramAgain());
    }

    public static void sortByNum(String[] names, double[] grades) {
        int n = names.length;
        String tempName;
        double tempGrade;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (grades[j] < grades[j + 1]) { // Sort in descending order by grade
                    // Swap grades
                    tempGrade = grades[j];
                    grades[j] = grades[j + 1];
                    grades[j + 1] = tempGrade;

                    // Swap corresponding names
                    tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                }
            }
        }
    }

    public static void sortStudentsByName() {
        do {
            Scanner scanner = new Scanner(System.in);

            int n = getInteger("How many students will be sorted? ");
            String[] names = new String[n];
            double[] grades = new double[n];

            // Populate arrays names and grades
            for (int z = 0; z < names.length; z++) {
                System.out.print("Enter name of student " + (z + 1) + ": ");
                names[z] = scanner.nextLine();

                double grade;
                while (true) {
                    grade = getDouble("Enter grade of " + names[z] + ": ");
                    if (grade >= 0 && grade <= 100) {
                        break;
                    }
                    System.out.println("Grade must be between 0 and 100");
                }
                grades[z] = grade;
            }

            // Sort the students based on names
            sortString(names, grades);

            // Display sorted result
            for (int i = 0; i < names.length; i++) {
                System.out.printf("Name: %s, Grade: %.2f%n", names[i], grades[i]);
            }
        } while (useProgramAgain());
    }

    public static void sortString(String[] names, double[] grades) {
        int n = names.length;
        String tempName;
        double tempGrade;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (names[j].compareTo(names[j + 1]) > 0) { // Sort in ascending order by name
                    // Swap names
                    tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;

                    // Swap corresponding grades
                    tempGrade = grades[j];
                    grades[j] = grades[j + 1];
                    grades[j + 1] = tempGrade;
                }
            }
        }
    }

    public static String[] sortByName(String[] names) {
        int n = names.length;
        String tempName;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (names[j].compareTo(names[j + 1]) > 0) { // Sort in ascending order by name
                    // Swap names
                    tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                }
            }
        }
        return names;
    }

    private static void showRecordingRoutinesMenu() {
        System.out.println("\nRECORDING ROUTINES ");
        System.out.println("-----------------------------------------------");
        System.out.println("1. Accept and sort list of students");
        System.out.println("2. Accept and sort list of Salesmen");
        System.out.println("3. Accept pairs of names and grades and sort list according to name");
        System.out.println("4. Accept pairs of names and grades and sort list according to grade");
        System.out.println("5. Back to Main Menu");
        System.out.println("------------------------------------------------");
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
                System.out.printf("Enter a number between %d and %d%n", min, max);
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

public static double getDouble(String message) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        try {
            System.out.print(message);
            return Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input. Must be a number.");
        }
    }
}

    public static void mathRoutines() {
        boolean backToMainMenu = false;

        do {
            showMathRoutinesMenu();

            switch (chooseOption(1, 20)) {
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
                case 16 -> calculatePower();
                case 17 -> calculateGCD();
                case 18 -> calculateLCM();
                case 19 -> convertDecimalToBinary();
                case 20 -> backToMainMenu = true;
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (!backToMainMenu);
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
        System.out.println("16. Calculate the power of a number");
        System.out.println("17. Determine the GCD of two numbers");
        System.out.println("18. Determine the LCM of two numbers");
        System.out.println("19. Convert a decimal number to binary");
        System.out.println("20. Back to Main Menu");
        System.out.println("-----------------------------------------------");
    }

    public static void checkParity() {
        do {
            System.out.println("\n-- PARITY CHECKER --");
            int n = getInteger("Enter a number: ");
            System.out.println("\n" + n + " is " + (n % 2 == 0 ? "even" : "odd"));
        } while (useProgramAgain());
    }

    public static void calculateSumOfSeries() {
        do {
            System.out.println("\n--SUM OF SERIES CALCULATOR");
            int n = getInteger("Enter a number: ");
            double sum = 0;

            if (n > 1 && n % 2 == 0) {
                for (int ctr = n; ctr > 1; ctr -= 2)
                    sum += (1.0 / ctr);
            }
            System.out.printf("\nThe sum of the series %d is: %f%n", n, sum);
        } while (useProgramAgain());
    }

    public static void findNumberFactors() {
        do {
            System.out.println("\n-- FACTOR NUMBER FINDER --");
            int n = getInteger("Enter a number: ");

            System.out.println();
            for (int i = 1; i <= n; i++) {
                if ( n % i == 0) {
                    System.out.print(i);
                }
            }
        } while (useProgramAgain());
    }

    public static void checkPrimeNumber() {
        do {
            System.out.println("\n-- PRIME NUMBER CHECKER --");
            int n = getInteger("Enter a number: ");
            boolean isPrime = true;

            System.out.println();
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
        } while (useProgramAgain());
    }

    private static void calculateCircleArea() {
        do {
            System.out.println("\n-- SHAPE AREA CALCULATOR --");
            int radius = getInteger("Enter radius of circle: ");
            double area = 3.14 * (radius * radius);

            System.out.println("\nThe area of the circle is " + area);
        } while (useProgramAgain());
    }

    private static void calculateSquareArea() {
        do {
            System.out.println("\n-- SHAPE AREA CALCULATOR --");
            int side = getInteger("Enter side of square: ");
            int area = side * side;

            System.out.println("\nThe area of the square is " + area);
        } while (useProgramAgain());
    }

    private static void calculateTriangleArea() {
        do {
            System.out.println("\n-- SHAPE AREA CALCULATOR --");
            int height = getInteger("Enter height of triangle: ");
            int base = getInteger("Enter base of triangle: ");
            int area = (height * base) / 2;

            System.out.println("\nThe area of the triangle is " + area);
        } while (useProgramAgain());
    }

    private static void calculateParallelogramArea(String shape) {
        do {
            System.out.println("\n-- SHAPE AREA CALCULATOR --");
            int length = getInteger("Enter length of parallelogram: ");
            int width = getInteger("Enter width of parallelogram: ");
            int area = length * width;

            System.out.printf("\nThe area of the %s is %d%n", shape, area);
        } while (useProgramAgain());
    }

    private static void calculateTrapezoidArea() {
        do {
            System.out.println("\n-- SHAPE AREA CALCULATOR --");
            int base1 = getInteger("Enter base 1 of trapezoid: ");
            int base2 = getInteger("Enter base 2 of trapezoid: ");
            int height = getInteger("Enter height of trapezoid: ");
            int area = ((base1 + base2) / 2) * height;

            System.out.println("\nThe area of the trapezoid is " + area);
        } while (useProgramAgain());
    }

    private static void checkPerfectNumber() {
        do {
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
                    System.out.println("\n" + n + " is a perfect number");
                }
            } else {
                System.out.println("\n" + n + " must be a positive integer");
            }
        } while (useProgramAgain());
    }

    private static void generateMultiplicationTable() {
        do {
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
                column = getInteger("Enter column: ");

                if (column > 30) {
                    System.out.println("\nColumn input is too large. Provide a smaller number.\n");
                } else if (column <= 0) {
                    System.out.println("Column input should be a positive number.");
                }
            } while (column > 30 || column <= 0);

            // Print multiplication table after user inputs are validated
            System.out.println();
            for (int i = 1; i <= row; i++) {
                for (int j= 1; j <= column; j++) {
                    System.out.printf("%8d", i * j);
                }
                System.out.println();
            }
        } while (useProgramAgain());
    }

    private static void solveQuadraticEquation() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n-- QUADRATIC SOLVER --");
            System.out.print("Enter coefficient of x^2 (a): ");
            int a = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter coefficient of x (b): ");
            int b = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter the constant (c): ");
            int c = Integer.parseInt(scanner.nextLine());

            double discriminant = b*b-4*a*c;

            if (discriminant >= 0) {
                double root1 = (-b + Math.sqrt(discriminant))/(2*a);
                double root2 = (-b - Math.sqrt(discriminant))/(2*a);

                System.out.printf("%nThe roots of (%d)x*2 + (%d)x + (%d) = 0 are %.2f and %.2f%n",
                        a, b, c, root1, root2);
            } else {
                System.out.printf("%nThe roots of (%d)x*2 + (%d)x + (%d) = 0 are imaginary numbers%n",
                        a,b ,c);
            }
        } while (useProgramAgain());
    }

    private static void generateFibonacciSequence() {
        do {
            System.out.println("\n-- FIBONACCI SEQUENCE GENERATOR --");
            int numOfTerms = getInteger("Enter desired number of terms: ");
            int firstTerm = getInteger("Enter first term: ");
            int secondTerm = getInteger("Enter second term: ");
            System.out.println();

            System.out.print(firstTerm + " " + secondTerm + " ");

            for (int i = 2; i < numOfTerms; i++) {
                int sum = firstTerm + secondTerm;
                firstTerm = secondTerm;
                secondTerm = sum;

                System.out.print(sum + " ");
            }
            System.out.println();
        } while (useProgramAgain());
    }

    public static void generatePascalsTriangle() {
        do {
            System.out.println("\n-- PASCAL'S TRIANGLE GENERATOR --");
            int rows = getInteger("Enter number of rows: ");
            int[] currentRow = new int[rows];

            System.out.println();
            for (int i = 0; i < rows; i++) {
                int[] previousRow = currentRow.clone(); // Copy currentRow to previousRow
                currentRow[0] = 1; // First element of each row is always 1

                for (int j = 1; j <= i; j++) {
                    if (j == i) {
                        currentRow[j] = 1; // Last element of each row is always 1
                    } else {
                        currentRow[j] = previousRow[j - 1] + previousRow[j]; // Calculate value
                    }
                }

                // Add padding to center-align the triangle
                int spaces = rows - i - 1; // Calculate leading spaces
                for (int s = 0; s < spaces; s++) {
                    System.out.print(" ");
                }

                // Print the current row
                for (int j = 0; j <= i; j++) {
                    System.out.print(currentRow[j] + " ");
                }
                System.out.println();
            }
        } while (useProgramAgain());
    }

    private static void calculatePower() {
        do {
            System.out.println("\n-- POWER CALCULATOR --");
            int base = getInteger("Enter the base: ");
            int exponent = getInteger("Enter the exponent: ");
            int result = 1;

            for (int i = 0; i < Math.abs(exponent); i++) {
                result *= base;
            }

            if (exponent < 0) {
                System.out.printf("%n%d raised to the power of %d is: 1/%.2f%n", base, exponent, 1.0 / result);
            } else {
                System.out.printf("%n%d raised to the power of %d is: %d%n", base, exponent, result);
            }
        } while (useProgramAgain());
    }

    private static void calculateGCD() {
        do {
            System.out.println("\n-- GREATEST COMMON DIVISOR CALCULATOR --");
            int num1 = getInteger("Enter the first number: ");
            int num2 = getInteger("Enter the second number: ");

            int gcd = num1;
            int remainder = num2;

            while (remainder != 0) {
                int temp = gcd % remainder;
                gcd = remainder;
                remainder = temp;
            }

            System.out.printf("The GCD of %d and %d is: %d%n", num1, num2, gcd);
        } while (useProgramAgain());
    }

    private static void calculateLCM() {
        do {
            System.out.println("\n-- LEAST COMMON MULTIPLE CALCULATOR --");
            int num1 = getInteger("Enter the first number: ");
            int num2 = getInteger("Enter the second number: ");

            int gcd = num1;
            int remainder = num2;

            // Calculate GCD first
            while (remainder != 0) {
                int temp = gcd % remainder;
                gcd = remainder;
                remainder = temp;
            }

            // LCM formula
            int lcm = (num1 * num2) / gcd;

            System.out.printf("The LCM of %d and %d is: %d%n", num1, num2, lcm);
        } while (useProgramAgain());
    }

    private static void convertDecimalToBinary() {
        do {
            System.out.println("\n-- DECIMAL TO BINARY CONVERTER --");
            int decimal = getInteger("Enter a decimal number: ");
            String binary = "";

            if (decimal == 0) {
                binary = "0";
            } else {
                while (decimal > 0) {
                    binary = (decimal % 2) + binary;
                    decimal /= 2;
                }
            }

            System.out.printf("\nThe binary representation is: %s%n", binary);
        } while (useProgramAgain());
    }

    public static boolean useProgramAgain() {
        Scanner scanner = new Scanner(System.in);

        char option;
        do {
            System.out.print("\nUse program again? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            option = input.charAt(0);
            if (option == 'y' || option == 'n') {
                return option == 'y';
            }

            System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
        } while (true);
    }
}
