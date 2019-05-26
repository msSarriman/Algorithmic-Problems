/*
Given a set of points (class Points{ double x,y; }), find the smallest rectangle by area.
*/

/*
			a
    x--------------u
	|
b	|
	|
	|
    y              z
*/

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Collections;
import java.util.PriorityQueue;

public class FindSmallestRectangle {
    public static HashMap<Double, LinkedList<Point>> sameX = new HashMap<>();
    public static HashMap<Double, LinkedList<Point>> sameY = new HashMap<>();

    public static void findSmallestRectangle(HashSet<Point> mySet) {
        sameX = new HashMap<>();
        sameY = new HashMap<>();
        for (Point p : mySet) {
            if (sameX.containsKey(p.x)) {
                sameX.get(p.x).add(p);
            } else {
                sameX.put(p.x, new LinkedList<>(Collections.singletonList(p)));
            }
            if (sameY.containsKey(p.x)) {
                sameY.get(p.y).add(p);
            } else {
                sameY.put(p.y, new LinkedList<>(Collections.singletonList(p)));
            }
        }
        PriorityQueue<Rectangle> myQueue = new PriorityQueue<>();
        for (Map.Entry<Double, LinkedList<Point>> kv : sameX.entrySet()) {
            if (!(kv.getValue().size() < 2)) {
                LinkedList<LinkedList<Point>> myListXPairs = pairify(kv.getValue());
                LinkedList<Point> myXpoints = null;
                LinkedList<Point> myYpoints = null;
                while(!myListXPairs.isEmpty()) {
                    Point tempX = myListXPairs.pollFirst().get(0);
                    Point tempY = myListXPairs.pollFirst().get(1);
                    myXpoints = findSameAxisPoints(tempX); // x
                    myYpoints = findSameAxisPoints(tempY); // y
                    if (!(myXpoints.size() < 1 || myYpoints.size() < 1)) {
                        for (Point sameXPoint : myXpoints) {
                            for (Point sameYPoint : myYpoints) {
                                if (formRectangle(tempX, tempY, sameXPoint, sameYPoint)) {
                                    double tempArea = calculateArea(tempX, tempY, sameXPoint, sameYPoint);
                                    Rectangle tempRec = new Rectangle(tempX, tempY, sameXPoint, sameYPoint, tempArea);
                                    myQueue.add(tempRec);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!myQueue.isEmpty()) {
            printDetails(myQueue.peek());
        }
    }

    /**
     * P(n,r) where n is the @myList.size() and r == 2
     * @param myList
     * @return
     */
    private static LinkedList<LinkedList<Point>> pairify(LinkedList<Point> myList) {
        LinkedList<LinkedList<Point>> pairs = new LinkedList<>();
        for (int i = 0; i < myList.size(); i++) {
            for (int j = i+1; j < myList.size(); j++) {
                LinkedList<Point> temp = new LinkedList<>();
                temp.add(myList.get(i));
                temp.add(myList.get(j));
                pairs.add(temp);
            }
        }
        return pairs;
    }

    /**
     * Must search sameY dictionary
     * @param p
     * @return
     */
    private static LinkedList<Point> findSameAxisPoints(Point p) {
        return sameY.get(p.y);
    }

    public static void printDetails(Rectangle rec) {
        System.out.printf("The smallest rectangle is represent by the following details:\n" +
                "x:%f,%f\n" +
                "y:%f,%f\n" +
                "u:%f,%f\n" +
                "z:%f,%f\n" +
                "and its area is:%f",
                rec.x.x, rec.x.y,
                rec.y.x, rec.y.y,
                rec.u.x, rec.u.y,
                rec.z.x, rec.z.y,
                rec.area);
    }


    public static boolean formRectangle(Point x, Point y, Point u, Point z) {
        double dxy = Math.sqrt(Math.pow((y.x - x.x),2) + Math.pow((y.y - x.y),2));
        double dxu = Math.sqrt(Math.pow((u.x - x.x),2) + Math.pow((u.y - x.y),2));
        double dzy = Math.sqrt(Math.pow((y.x - z.x),2) + Math.pow((y.y - z.y),2));
        double dzu = Math.sqrt(Math.pow((u.x - z.x),2) + Math.pow((u.y - z.y),2));
        double dxz = Math.sqrt(Math.pow((z.x - x.x),2) + Math.pow((z.y - x.y),2));
        double dyu = Math.sqrt(Math.pow((u.x - y.x),2) + Math.pow((u.y - y.y),2));
        return dxy == dzu && dxu == dzy && dxz == dyu;
    }

    public static double calculateArea(Point x, Point y, Point u, Point z) {
        double dxy = Math.sqrt(Math.pow((y.x - x.x),2) + Math.pow((y.y - x.y),2));
        double dxu = Math.sqrt(Math.pow((u.x - x.x),2) + Math.pow((u.y - x.y),2));
        return dxy * dxu;
    }


}

class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Rectangle implements Comparable{
    Point x, y, u, z;
    double area;

    Rectangle (Point x, Point y, Point u, Point z, double area) {
        this.x = x;
        this.y = y;
        this.u = u;
        this.z = z;
        this.area = area;
    }

    @Override
    public int compareTo(Object o) {
        return (int) (((Rectangle)o).area - this.area);
    }
}