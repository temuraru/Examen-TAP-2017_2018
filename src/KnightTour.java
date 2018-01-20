import java.util.Scanner;

/**
 * Backtracking example:
 *      Compute the Knight jumps on a chess table of a size provided as input
 * Observation:
 *      For boards of size > 8, the processing time is huge (I stopped it after 20 minutes!)
 * Run:
 *      E:\temur\work\java\TAP\src>java KnightTour
 * Output example:
 Enter board size: 8
 Solution:
 0	59	38	33	30	17	8	63
 37	34	31	60	9	62	29	16
 58	1	36	39	32	27	18	7
 35	48	41	26	61	10	15	28
 42	57	2	49	40	23	6	19
 47	50	45	54	25	20	11	14
 56	43	52	3	22	13	24	5
 51	46	55	44	53	4	21	12
 Time Elapsed:	0 seconds = 237 miliseconds!
 Total calls:	8250733!


 Enter board size: 6
 Solution:
 0	15	6	25	10	13
 33	24	11	14	5	26
 16	1	32	7	12	9
 31	34	23	20	27	4
 22	17	2	29	8	19
 35	30	21	18	3	28
 Time Elapsed:	0 seconds = 12 miliseconds!
 Total calls:	248169!
*/
class KnightTour {
    private static int size;
    private static int calls;

    public static void main(String args[]) {
        System.out.print("Enter board size: ");
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();

        int solution[][] = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                solution[i][j] = -1;
            }
        }
        calls = 0;
        solveTour(solution);
    }

    /**
     * Validate the current (x, y) position on the board:
     * - validate the x, y coordinates (to be on the board)
     * - it should have the value -1 (no other knight has been placed on it)
     * @param x int
     * @param y int
     * @param sol array
     * @return int
     */
    private static int isSafe(int x, int y, int sol[][]) {
        if (x >= 0 && x < size && y >= 0 && y < size && sol[x][y] == -1)
            return 1;
        else
            return -1;
    }

    private static void solveTour(int solution[][]) {
        solution[0][0] = 0;    //starting point
        int diffCoordX[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int diffCoordY[] = {1, 2, 2, 1, -1, -2, -2, -1};
        long startTime = System.nanoTime();
        if (solveTourNextMove(0, 0, 1, solution, diffCoordX, diffCoordY) == 1) {
            System.out.println("=================================== ");
            System.out.println("Solution:");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(solution[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("=================================== ");
        } else {
            System.out.println("Solution doesn't exist");
            System.out.println("=================================== ");
        }

        long finalTime = System.nanoTime() - startTime;
        int timeElapsed = (int)(finalTime / 1000000000);
        int timeElapsedMili = (int)(finalTime / 1000000);
        System.out.println("Time Elapsed:\t" + timeElapsed + " seconds = " + timeElapsedMili + " miliseconds!");
        System.out.print("Total calls:\t" + calls + "! ");
    }

    /**
     *
     * @param x Current X position
     * @param y Current Y position
     * @param count The count of already positioned knights so far
     * @param solution The solution array (the chess board)
     * @param diffCoordX Knight jump on the X coordinate
     * @param diffCoordY Knight jump on the Y coordinate
     * @return
     */
    private static int solveTourNextMove(int x, int y, int count, int solution[][], int diffCoordX[], int diffCoordY[]) {
        calls++;
        if (count == size * size) {
            return 1;
        }

        int possibleX, possibleY;
        for (int i = 0; i < 8; i++) {
            possibleX = x + diffCoordX[i];
            possibleY = y + diffCoordY[i];
            if (isSafe(possibleX, possibleY, solution) == 1) {
                solution[possibleX][possibleY] = count;
                if (solveTourNextMove(possibleX, possibleY, count + 1, solution, diffCoordX, diffCoordY) == 1) {
                    return 1;
                } else {
                    solution[possibleX][possibleY] = -1;
                }
            }
        }

        return -1;
    }
}