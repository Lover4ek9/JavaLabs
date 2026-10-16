import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    void testDeterminant1x1() {
        double[][] matrix = {{5}};

        assertEquals(5, Main.task9(matrix), 0.000001);
        assertEquals(5, Main.task9Stream(matrix), 0.000001);
    }

    @Test
    void testDeterminant2x2() {
        double[][] matrix = {
                {1, 2},
                {3, 4}
        };

        assertEquals(-2, Main.task9(matrix), 0.000001);
        assertEquals(-2, Main.task9Stream(matrix), 0.000001);
    }

    @Test
    void testDeterminant3x3() {
        double[][] matrix = {
                {2, 1, 3},
                {1, 0, 2},
                {4, 1, 8}
        };

        assertEquals(-1, Main.task9(matrix), 0.000001);
        assertEquals(-1, Main.task9Stream(matrix), 0.000001);
    }

    @Test
    void testZeroDeterminant() {
        double[][] matrix = {
                {1, 2},
                {2, 4}
        };

        assertEquals(0, Main.task9(matrix), 0.000001);
        assertEquals(0, Main.task9Stream(matrix), 0.000001);
    }

    @Test
    void testIdentityMatrix() {
        double[][] matrix = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        assertEquals(1, Main.task9(matrix), 0.000001);
        assertEquals(1, Main.task9Stream(matrix), 0.000001);
    }

    @Test
    void testDeterminant4x4() {
        double[][] matrix = {
                {2, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 5}
        };

        assertEquals(120, Main.task9(matrix), 0.000001);
        assertEquals(120, Main.task9Stream(matrix), 0.000001);
    }

    @Test
    void testRoundingPositive() {
        double[][] matrix = {
                {1.2, 2.7},
                {3.8, 4.1}
        };

        int[][] expected = {
                {2, 3},
                {3, 4}
        };

        assertArrayEquals(expected, Main.task18(matrix));
        assertArrayEquals(expected, Main.task18Stream(matrix));
    }

    @Test
    void testRoundingNegative() {
        double[][] matrix = {
                {-1.2, -2.7},
                {-3.8, -4.1}
        };

        int[][] expected = {
                {-1, -2},
                {-4, -5}
        };

        assertArrayEquals(expected, Main.task18(matrix));
        assertArrayEquals(expected, Main.task18Stream(matrix));
    }

    @Test
    void testRoundingIntegers() {
        double[][] matrix = {
                {1.0, 2.0},
                {3.0, 4.0}
        };

        int[][] expected = {
                {1, 2},
                {3, 4}
        };

        assertArrayEquals(expected, Main.task18(matrix));
        assertArrayEquals(expected, Main.task18Stream(matrix));
    }

    @Test
    void testRoundingFourRows() {
        double[][] matrix = {
                {1.1, 2.1},
                {3.9, 4.9},
                {5.1, 6.1},
                {7.9, 8.9}
        };

        int[][] expected = {
                {2, 3},
                {3, 4},
                {6, 7},
                {7, 8}
        };

        assertArrayEquals(expected, Main.task18(matrix));
        assertArrayEquals(expected, Main.task18Stream(matrix));
    }

    @Test
    void testRoundingSingleRow() {
        double[][] matrix = {
                {1.2, 2.5, 3.9}
        };

        int[][] expected = {
                {2, 3, 4}
        };

        assertArrayEquals(expected, Main.task18(matrix));
        assertArrayEquals(expected, Main.task18Stream(matrix));
    }

    @Test
    void testEmptyMatrix() {
        double[][] matrix = {};

        int[][] expected = {};

        assertArrayEquals(expected, Main.task18(matrix));
        assertArrayEquals(expected, Main.task18Stream(matrix));
    }
}
