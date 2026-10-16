import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuadrilateralTest {
    

    @Test
    void testPointDistance() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);

        assertEquals(5, p1.distanceTo(p2), 0.000001);
    }

    @Test
    void testSquareType() {
        Quadrilateral q = new Quadrilateral(
                new Point(0, 0),
                new Point(2, 0),
                new Point(2, 2),
                new Point(0, 2)
        );

        assertEquals(QuadrilateralType.SQUARE, q.getType());
    }

    @Test
    void testRectangleType() {
        Quadrilateral q = new Quadrilateral(
                new Point(0, 0),
                new Point(5, 0),
                new Point(5, 2),
                new Point(0, 2)
        );

        assertEquals(QuadrilateralType.RECTANGLE, q.getType());
    }

    @Test
    void testRhombusType() {
        Quadrilateral q = new Quadrilateral(
                new Point(0, 0),
                new Point(5, 0),
                new Point(8, 4),
                new Point(3, 4)
        );

        assertEquals(QuadrilateralType.RHOMBUS, q.getType());
    }

    @Test
    void testArbitraryType() {
        Quadrilateral q = new Quadrilateral(
                new Point(0, 0),
                new Point(4, 0),
                new Point(3, 3),
                new Point(0, 2)
        );

        assertEquals(QuadrilateralType.ARBITRARY, q.getType());
    }

    @Test
    void testSquareArea() {
        Quadrilateral q = new Quadrilateral(
                new Point(0, 0),
                new Point(3, 0),
                new Point(3, 3),
                new Point(0, 3)
        );

        assertEquals(9, q.getArea(), 0.000001);
    }

    @Test
    void testSquarePerimeter() {
        Quadrilateral q = new Quadrilateral(
                new Point(0, 0),
                new Point(3, 0),
                new Point(3, 3),
                new Point(0, 3)
        );

        assertEquals(12, q.getPerimeter(), 0.000001);
    }

    @Test
    void testRectangleAreaAndPerimeter() {
        Quadrilateral q = new Quadrilateral(
                new Point(0, 0),
                new Point(5, 0),
                new Point(5, 2),
                new Point(0, 2)
        );

        assertEquals(10, q.getArea(), 0.000001);
        assertEquals(14, q.getPerimeter(), 0.000001);
    }

    @Test
    void testRhombusAreaAndPerimeter() {
        Quadrilateral q = new Quadrilateral(
                new Point(0, 0),
                new Point(5, 0),
                new Point(8, 4),
                new Point(3, 4)
        );

        assertEquals(20, q.getArea(), 0.000001);
        assertEquals(20, q.getPerimeter(), 0.000001);
    }

    @Test
    void testInvalidQuadrilateral() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quadrilateral(
                        new Point(0, 0),
                        new Point(1, 1),
                        new Point(2, 2),
                        new Point(3, 3)
                )
        );
    }

    @Test
    void testCrossedQuadrilateral() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quadrilateral(
                        new Point(0, 0),
                        new Point(2, 2),
                        new Point(0, 2),
                        new Point(2, 0)
                )
        );
    }

    @Test
    void testToString() {
        Quadrilateral q = new Quadrilateral(
                new Point(0, 0),
                new Point(2, 0),
                new Point(2, 2),
                new Point(0, 2)
        );

        assertTrue(q.toString().contains("SQUARE"));
        assertTrue(q.toString().contains("4.0"));
        assertTrue(q.toString().contains("8.0"));
    }
}

