package sorter;

import java.util.*;

public class DataSorter {

    static Scanner sc = new Scanner(System.in);
    static int[] data = {};
    static Map<String, long[]> results = new LinkedHashMap<>();

    public static void main(String[] args) {
        int choice;

        while (true) {
            System.out.println("\n--- Data Sorter: Sorting Algorithm Comparison Tool ---");
            System.out.println("1. Enter numbers manually");
            System.out.println("2. Generate random numbers");
            System.out.println("3. Perform Bubble Sort");
            System.out.println("4. Perform Merge Sort");
            System.out.println("5. Perform Quick Sort");
            System.out.println("6. Compare all algorithms (show performance table)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> enterNumbers();
                case 2 -> generateNumbers();
                case 3 -> runSort("Bubble Sort");
                case 4 -> runSort("Merge Sort");
                case 5 -> runSort("Quick Sort");
                case 6 -> showComparisonTable();
                case 7 -> {
                    System.out.println("Exiting program... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    static void enterNumbers() {
        System.out.print("Enter how many numbers: ");
        int n = sc.nextInt();
        data = new int[n];
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) data[i] = sc.nextInt();
        System.out.println("Data successfully entered!");
    }

    static void generateNumbers() {
        System.out.print("Enter number of random elements to generate: ");
        int n = sc.nextInt();
        Random rand = new Random();
        data = new int[n];
        for (int i = 0; i < n; i++) data[i] = rand.nextInt(100);
        System.out.println("Random numbers generated: " + Arrays.toString(data));
    }

    static void runSort(String algorithm) {
        if (data.length == 0) {
            System.out.println("Please enter or generate data first!");
            return;
        }

        int[] arr = Arrays.copyOf(data, data.length);
        long startTime = System.nanoTime();
        long steps = 0;

        switch (algorithm) {
            case "Bubble Sort" -> steps = BubbleSort.bubbleSort(arr);
            case "Merge Sort" -> steps = MergeSort.mergeSort(arr, 0, arr.length - 1);
            case "Quick Sort" -> steps = QuickSort.quickSort(arr, 0, arr.length - 1);
        }

        long endTime = System.nanoTime();
        long timeTaken = (endTime - startTime) / 1_000_000;

        results.put(algorithm, new long[]{timeTaken, steps});

        System.out.println("\nSorted Output (" + algorithm + "): " + Arrays.toString(arr));
        System.out.println("Execution Time: " + timeTaken + " ms");
        System.out.println("Steps Count: " + steps);
    }

    static void showComparisonTable() {
        if (results.isEmpty()) {
            System.out.println("No results yet! Please perform sorting first.");
            return;
        }

        System.out.println("\n--- Performance Comparison Table ---");
        System.out.printf("%-15s | %-10s | %-10s%n", "Algorithm", "Time (ms)", "Steps");
        System.out.println("---------------------------------------------");

        for (var entry : results.entrySet()) {
            System.out.printf("%-15s | %-10d | %-10d%n",
                    entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
        }
    }
}
