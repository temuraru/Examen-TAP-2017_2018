import java.util.Scanner;

class KnightTour {
    private static int size;

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
        solveTour(solution);
    }

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
            System.out.println("Solution:");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(solution[i][j] + "\t");
                }
                System.out.println();
            }
        } else {
            System.out.println("Solution doesn't exist");
        }

        long finalTime = System.nanoTime() - startTime;
        int timeElapsed = (int)(finalTime / 1000000000);
        int timeElapsedMili = (int)(finalTime / 1000000);
        System.out.println("Time Elapsed:\t\t\t" + timeElapsed + " seconds = " + timeElapsedMili + " miliseconds!");
    }

    private static int solveTourNextMove(int x, int y, int count, int solution[][], int diffCoordX[], int diffCoordY[]) {
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