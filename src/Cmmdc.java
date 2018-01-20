import java.util.Scanner;

/**
 * Divide et Impera example
 *      Compute the CMMDC of multiple numbers
 * It splits the inoput array in half, and applies the same method on each sub-array and compute cmmdc on their results
 * Run:
 *      E:\temur\work\java\TAP\src>java Cmmdc
 * Output example
 Cmmdc. Please input the number of elements: 3
 Cmmdc. Please enter the elements:
 56
 12
 48
 CMMDC = 4
 Time Elapsed:		0 seconds = 0 miliseconds!
 The original array:	56 12 48
 */
public class Cmmdc {
    public static void main(String[] args) {
        System.out.println();
        System.out.print("Cmmdc. Please input the number of elements: ");
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();

        int[] data = new int[size];
        System.out.println("Cmmdc. Please enter the elements:");
        for (int i = 0; i < size; i++) {
            data[i] = sc.nextInt();
        }
        sc.close();

        if (size > 0) {
            long startTime = System.nanoTime();

            int cmmdc = divide_et_impera(data, 0, size - 1);
            System.out.println("=================================== ");
            System.out.println("CMMDC = " + cmmdc);
            System.out.println("=================================== ");

            long finalTime = System.nanoTime() - startTime;
            int timeElapsed = (int)(finalTime / 1000000000);
            int timeElapsedMili = (int)(finalTime / 1000000);
            System.out.println("Time Elapsed:\t\t" + timeElapsed + " seconds = " + timeElapsedMili + " miliseconds!");

            System.out.print("The original array:\t");
            for (int i = 0; i < size; i++) {
                int result = data[i] / cmmdc;
                System.out.print(data[i] + "/" + cmmdc + "=" + result + "; \t");
            }
            System.out.println();
        }
    }

    /**
     * Return the cmmdc of 2 integers
     *
     * @param a int
     * @param b int
     * @return int
     */
    private static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    /**
     * Divide et inpera
     *
     * @param data int[]
     * @param min  int
     * @param max  int
     * @return int
     */
    private static int divide_et_impera(int[] data, int min, int max) {
        if (min < max) {
            int middle = (min + max) / 2;

            int d1 = divide_et_impera(data, min, middle);
            int d2 = divide_et_impera(data, middle + 1, max);

            return gcd(d1, d2);
        }

        return (0 <= min && min < data.length ? data[min] : 0);
    }
}