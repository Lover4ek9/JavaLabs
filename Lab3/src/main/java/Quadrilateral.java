public class Quadrilateral {
    private final Point[] points;
    private final QuadrilateralType type;

    public Quadrilateral(Point p1, Point p2,
                         Point p3, Point p4) {
        points = new Point[]{p1, p2, p3, p4};

        if (!isValid()) {
            throw new IllegalArgumentException(
                    "you write something bad"
            );
        }

        type = determineType();
    }

    private boolean isValid() {
        double sign = 0;

        for (int i = 0; i < 4; i++) {
            Point a = points[i];
            Point b = points[(i + 1) % 4];
            Point c = points[(i + 2) % 4];

            double cross =
                    (b.getX() - a.getX()) *
                            (c.getY() - b.getY()) -
                            (b.getY() - a.getY()) *
                                    (c.getX() - b.getX());

            if (Math.abs(cross) < 1e-9) {
                return false;
            }

            if (i == 0) {
                sign = Math.signum(cross);
            } else if (Math.signum(cross) != sign) {
                return false;
            }
        }

        return true;
    }

    private QuadrilateralType determineType() {
        double[] sides = new double[4];

        for (int i = 0; i < 4; i++) {
            sides[i] = points[i].distanceTo(
                    points[(i + 1) % 4]
            );
        }

        boolean equalSides = true;

        for (int i = 1; i < 4; i++) {
            if (Math.abs(sides[i] - sides[0]) > 1e-9) {
                equalSides = false;
            }
        }

        boolean rightAngles = true;

        for (int i = 0; i < 4; i++) {
            Point a = points[i];
            Point b = points[(i + 1) % 4];
            Point c = points[(i + 2) % 4];

            double x1 = a.getX() - b.getX();
            double y1 = a.getY() - b.getY();
            double x2 = c.getX() - b.getX();
            double y2 = c.getY() - b.getY();

            double dot = x1 * x2 + y1 * y2;

            if (Math.abs(dot) > 1e-9) {
                rightAngles = false;
            }
        }

        if (equalSides && rightAngles) {
            return QuadrilateralType.SQUARE;
        }

        if (rightAngles) {
            return QuadrilateralType.RECTANGLE;
        }

        if (equalSides) {
            return QuadrilateralType.RHOMBUS;
        }

        return QuadrilateralType.ARBITRARY;
    }

    public QuadrilateralType getType() {
        return type;
    }

    public double getArea() {
        double sum = 0;

        for (int i = 0; i < 4; i++) {
            Point a = points[i];
            Point b = points[(i + 1) % 4];

            sum += a.getX() * b.getY()
                    - b.getX() * a.getY();
        }

        return Math.abs(sum) / 2;
    }

    public double getPerimeter() {
        double perimeter = 0;

        for (int i = 0; i < 4; i++) {
            perimeter += points[i].distanceTo(
                    points[(i + 1) % 4]
            );
        }

        return perimeter;
    }

    @Override
    public String toString() {
        return type +
                " | Площадь: " + getArea() +
                " | Периметр: " + getPerimeter();
    }
}

