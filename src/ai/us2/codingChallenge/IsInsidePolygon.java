package ai.us2.codingChallenge;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;

public class IsInsidePolygon {

    static int INFINITY = 1000000;

    static class Coordinate {
        int x;
        int y;
        static Coordinate of(int x, int y) {
            Coordinate c = new Coordinate();
            c.x = x;
            c.y = y;
            return c;
        }
        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
    enum State {
        Inside, Outside
    }

    static boolean isBetween2Points(Coordinate p, Coordinate q, Coordinate r) {
        if (q.x <= Math.max(p.x, r.x) &&
                q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) &&
                q.y >= Math.min(p.y, r.y)) {
            return true;
        }
        return false;
    }

    /*
    This is to find how 3 points are placed in a 2d plane.
    Formula to find orientation copied from here : https://stackoverflow.com/questions/17592800/how-to-find-the-orientation-of-three-points-in-a-two-dimensional-space-given-coo
    if the val >0 --> clockwise; else anticlockwise
     */
    static int isClockWise(Coordinate p, Coordinate q, Coordinate r) {
        int val = (q.y - p.y) * (r.x - q.x)
                - (q.x - p.x) * (r.y - q.y);

        if (val == 0) {
            return 0; // colinear
        }
        return (val > 0) ? 1 : -1; // clockwise or anti wise
    }

    /*
    On a 2D Plane, 2 Line Segments are said to be intersecting if Their sets are clockwise and anti clockwise
    Logic taken from here: https://algorithmtutor.com/Computational-Geometry/Check-if-two-line-segment-intersect/
     */
    static boolean isIntersecting(Coordinate edge1, Coordinate edge2,
                                  Coordinate rayStart, Coordinate rayEnd) {
        int o1 = isClockWise(edge1, edge2, rayStart);
        int o2 = isClockWise(edge1, edge2, rayEnd);
        int o3 = isClockWise(rayStart, rayEnd, edge1);
        int o4 = isClockWise(rayStart, rayEnd, edge2);

        if (o1 != o2 && o3 != o4) {
            return true;
        }
        if (o1 == 0 && isBetween2Points(edge1, rayStart, edge2)) {
            return true;
        }

        return false;
    }

    /*
        Theory --> For any Point to be inside a polygon, if we create a ray from the point to infinity either on X axes or y axes,
        it should intersect the polygon odd number of times only, and for a regular polygon only once.

        Code Logic -->
        1. If polygon has size <3; Throw error as its not a polygon
        2. Create a Ray parallel to X axis towards infinity (NOTE: To avoid overflows, took Infinty as 10000;
        3A. For each line segment/line created by polygon, check if ray intersects it. if YES, Increase count
        3B. If Point falls on the Line itself, Return true.
        [More details on Method isIntersecting]
        4. If Count is Odd --> point is inside, else outside

     */
    static String isInside(List<Coordinate> polygon, Coordinate p) throws InvalidAttributesException {
        boolean isInside = false;
        //Polygon needs atleast 3 sides.
        if (polygon.size() < 3) {
            throw new InvalidAttributesException("Polygon needs atleast 3 sides");
        }

        Coordinate rayEnd = Coordinate.of(INFINITY, p.y);
        int count = 0;

        for (int i = 0; i < polygon.size(); i++) {
            int next = i + 1;
            if (i == polygon.size() - 1) {
                next = 0;
            }
            if (isIntersecting(polygon.get(i), polygon.get(next), p, rayEnd)) {
                if (isClockWise(polygon.get(i), p, polygon.get(next)) == 0
                        && isBetween2Points(polygon.get(i), p, polygon.get(next))) {
                    isInside = true;
                    break;
                }

                count++;
            }
        }

        isInside = isInside || (count % 2 == 1);
        if (isInside) {
            return State.Inside.toString();
        } else {
            return State.Outside.toString();
        }
    }


    public static void main(String[] args) throws InvalidAttributesException {
        List<Coordinate> rectangle = List.of(Coordinate.of(1, 1),
                Coordinate.of(1, 5),
                Coordinate.of(10, 5),
                Coordinate.of(10, 1));
        Coordinate p = Coordinate.of(3, 3);
        System.out.println("Coordinate " + p + " is " + isInside(rectangle, p));
        Coordinate q = Coordinate.of(7, 5);
        System.out.println("Coordinate " + q + " is " + isInside(rectangle, q));
        Coordinate r = Coordinate.of(5, 6);
        System.out.println("Coordinate " + r + " is " + isInside(rectangle, r));



        /*
        Testing some more cases
        1. Triangle
        2. Rectangle with a slant angle
         */
        System.out.println("For Triangle (1,5),(3,10),(5,-5)");
        List<Coordinate> triangle = List.of(Coordinate.of(1, 5), Coordinate.of(3, 10), Coordinate.of(5, -5));
        p = Coordinate.of(2, 7);
        System.out.println("Coordinate " + p + " is " + isInside(triangle, p));
        q = Coordinate.of(3, -3);
        System.out.println("Coordinate " + q + " is " + isInside(triangle, q));
        r = Coordinate.of(5, -5);
        System.out.println("Coordinate " + r + " is " + isInside(triangle, r));

        System.out.println(("For Slant Rectangle (-3,-3),(2,2),(2,1),(-3,-4)"));
        List<Coordinate> rectangleSlant = List.of(Coordinate.of(-3, -3), Coordinate.of(2, 2), Coordinate.of(2, 1), Coordinate.of(-3, -4));
        p = Coordinate.of(0, 0);
        System.out.println("Coordinate " + p + " is " + isInside(rectangleSlant, p));
        q = Coordinate.of(0, -3);
        System.out.println("Coordinate " + q + " is " + isInside(rectangleSlant, q));
        r = Coordinate.of(2, 3);
        System.out.println("Coordinate " + r + " is " + isInside(rectangleSlant, r));

    }

}
