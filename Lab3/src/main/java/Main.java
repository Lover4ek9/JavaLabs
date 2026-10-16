
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Quadrilateral> figures = new ArrayList<>();

        figures.add(new Quadrilateral(
                new Point(0, 0), new Point(2, 0),
                new Point(2, 2), new Point(0, 2)
        ));

        figures.add(new Quadrilateral(
                new Point(0, 0), new Point(4, 0),
                new Point(4, 4), new Point(0, 4)
        ));

        figures.add(new Quadrilateral(
                new Point(0, 0), new Point(5, 0),
                new Point(5, 2), new Point(0, 2)
        ));

        figures.add(new Quadrilateral(
                new Point(0, 0), new Point(6, 0),
                new Point(6, 3), new Point(0, 3)
        ));

        figures.add(new Quadrilateral(
                new Point(0, 0), new Point(5, 0),
                new Point(8, 4), new Point(3, 4)
        ));

        figures.add(new Quadrilateral(
                new Point(0, 0), new Point(10, 0),
                new Point(16, 8), new Point(6, 8)
        ));

        figures.add(new Quadrilateral(
                new Point(0, 0), new Point(4, 0),
                new Point(3, 3), new Point(0, 2)
        ));

        figures.add(new Quadrilateral(
                new Point(0, 0), new Point(5, 0),
                new Point(4, 4), new Point(0, 3)
        ));

        for (QuadrilateralType type : QuadrilateralType.values()) {

            int count = 0;

            Quadrilateral minArea = null;
            Quadrilateral maxArea = null;
            Quadrilateral minPerimeter = null;
            Quadrilateral maxPerimeter = null;

            for (Quadrilateral figure : figures) {

                if (figure.getType() != type) {
                    continue;
                }

                count++;

                if (minArea == null ||
                        figure.getArea() < minArea.getArea()) {
                    minArea = figure;
                }

                if (maxArea == null ||
                        figure.getArea() > maxArea.getArea()) {
                    maxArea = figure;
                }

                if (minPerimeter == null ||
                        figure.getPerimeter() <
                                minPerimeter.getPerimeter()) {
                    minPerimeter = figure;
                }

                if (maxPerimeter == null ||
                        figure.getPerimeter() >
                                maxPerimeter.getPerimeter()) {
                    maxPerimeter = figure;
                }
            }

            System.out.println("\nТип: " + type);
            System.out.println("Количество: " + count);

            if (count > 0) {
                System.out.println("Мин. площадь: " + minArea);
                System.out.println("Макс. площадь: " + maxArea);
                System.out.println("Мин. периметр: " + minPerimeter);
                System.out.println("Макс. периметр: " + maxPerimeter);
            }
        }
    }
}

