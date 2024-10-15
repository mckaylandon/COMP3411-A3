public class main {
    public static void main (String[] args) {
        // Sample matrices
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] matrixB = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };

        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int rowsB = matrixB.length;
        int colsB = matrixB[0].length;

        // Result matrix with appropriate size
        int[][] result = new int[rowsA][colsB];

        // Validate if multiplication is possible
        if (colsA != rowsB) {
            System.out.println("Matrix multiplication is not possible due to incompatible dimensions.");
            return;
        }

        // Create threads for each element in the result matrix using WorkerThread
        Thread[][] threadArray = new Thread[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                threadArray[i][j] = new WorkerThread(i, j, matrixA, matrixB, result);
                threadArray[i][j].start();
            }
        }

        // Wait for all threads to complete
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                try {
                    threadArray[i][j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Now using WorkerRunnable for the same operation
        Thread[][] runnableArray = new Thread[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                runnableArray[i][j] = new Thread(new WorkerRunnable(i, j, matrixA, matrixB, result));
                runnableArray[i][j].start();
            }
        }

        // Wait for all threads to complete
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                try {
                    runnableArray[i][j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Print matrices and result
        System.out.println("Matrix A:");
        printMatrix(matrixA);

        System.out.println("Matrix B:");
        printMatrix(matrixB);

        System.out.println("Result Matrix:");
        printMatrix(result);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
