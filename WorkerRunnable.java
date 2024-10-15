public class WorkerRunnable implements Runnable {
    private int row, col;
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] result;

    WorkerRunnable(int row, int col, int[][] matrixA, int[][] matrixB, int[][] result) {
        this.row = row;
        this.col = col;
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
    }

    public void run() {
        result[row][col] = 0;
        for (int i = 0; i < matrixB.length; i++) {
            result[row][col] += matrixA[row][i] * matrixB[i][col];
        }
        System.out.println("Runnable " + Thread.currentThread().getId() + " calculated result[" + row + "][" + col + "] = " + result[row][col]);
    }
}