
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        double[][] matrix9 = {
                {2, 1, 3},
                {1, 0, 2},
                {4, 1, 8}
        };

        double[][] matrix18 = {
                {1.2, 2.7, 3.5},
                {4.8, 5.1, 6.9},
                {7.3, 8.6, 9.2},
                {2.4, 3.8, 4.1}
        };

        System.out.println("Task 9:");
        System.out.println("default: " + task9(matrix9));
        System.out.println("Stream API: " + task9Stream(matrix9));

        System.out.println("\nTask 18:");
        System.out.println("Default:");
        printMatrix(task18(matrix18));

        System.out.println("Stream API:");
        printMatrix(task18Stream(matrix18));
    }

    /*
     * Задание 9.
     * Вычислить определитель матрицы.
     */

    public static double task9(double[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        double det = 0;

        for (int j = 0; j < n; j++) {
            double[][] minor = new double[n - 1][n - 1];

            for (int i = 1; i < n; i++) {
                int col = 0;

                for (int k = 0; k < n; k++) {
                    if (k != j) {
                        minor[i - 1][col++] = matrix[i][k];
                    }
                }
            }

            det += (j % 2 == 0 ? 1 : -1)
                    * matrix[0][j]
                    * task9(minor);
        }

        return det;
    }

    //Stream API
    public static double task9Stream(double[][] matrix) {
        int n = matrix.length;

        if (n == 1) {
            return matrix[0][0];
        }

        return IntStream.range(0, n)
                .mapToDouble(j -> {
                    double[][] minor = IntStream.range(1, n)
                            .mapToObj(i -> IntStream.range(0, n)
                                    .filter(k -> k != j)
                                    .mapToDouble(k -> matrix[i][k])
                                    .toArray()
                            )
                            .toArray(double[][]::new);

                    return (j % 2 == 0 ? 1 : -1)
                            * matrix[0][j]
                            * task9Stream(minor);
                })
                .sum();
    }

    /*
     * Задание 18.
     * Округлить все элементы матрицы до целого числа:
     * в строках с четным номером — с недостатком,
     * в строках с нечетным номером — с избытком.
     */

    public static int[][] task18(double[][] matrix) {
        int[][] result = new int[matrix.length][];

        for (int i = 0; i < matrix.length; i++) {
            result[i] = new int[matrix[i].length];

            for (int j = 0; j < matrix[i].length; j++) {
                if ((i + 1) % 2 == 0) {
                    result[i][j] = (int) Math.floor(matrix[i][j]);
                } else {
                    result[i][j] = (int) Math.ceil(matrix[i][j]);
                }
            }
        }

        return result;
    }

    // Способ с использованием Stream API
    public static int[][] task18Stream(double[][] matrix) {
        return IntStream.range(0, matrix.length)
                .mapToObj(i -> Arrays.stream(matrix[i])
                        .mapToInt(value -> (int) (
                                (i + 1) % 2 == 0
                                        ? Math.floor(value)
                                        : Math.ceil(value)
                        ))
                        .toArray()
                )
                .toArray(int[][]::new);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
