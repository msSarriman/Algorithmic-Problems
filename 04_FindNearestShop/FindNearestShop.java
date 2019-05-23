/*
MIT License

Copyright (c) 2019 Emmanouil Sarris

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
/*
04_FindNearestShop 
Given N shop coordinates on a 2d plane, find the nearest shop from a man whose coordinates are given.
*/
import java.util.HashSet;
import java.util.Set;
import java.util.PriorityQueue;

public class FindUniqueLines {

    public static String findNearestShop(Set<Integer[]> myCoordinates, int[] manCoordinates) {
        if (manCoordinates.length < 2) return "Error";
        int xMan = manCoordinates[0];
        int yMan = manCoordinates[1];

        Double nearest = Double.MAX_VALUE;
        Integer[] nearestCoordinates = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        PriorityQueue<Double> myQueue = new PriorityQueue<>();
        for (Integer[] xy : myCoordinates) {
            int xShop = xy[0];
            int yShop = xy[1];
            myQueue.add(Math.pow((xShop - xMan),2) + Math.pow((yShop - yMan),2));
            if (nearest > myQueue.peek()) {
                nearest = myQueue.peek();
                nearestCoordinates = xy;
            }
        }
        return "The nearest shop is <"+ myQueue.poll() +">meters away, at x <"+ nearestCoordinates[0] +"> and y <"+ nearestCoordinates[1] +">";
    }

    public static void main(String[] args) {
        Integer[] c1 = {1, 1};
        Integer[] c2 = {2, 2};
        Integer[] c3 = {4, 0};
        Integer[] c4 = {0, 5};
        int[] manCoordinates = {0, 0};

        Set<Integer[]> mySet = new HashSet<>();
        mySet.add(c1);
        mySet.add(c2);
        mySet.add(c3);
        mySet.add(c4);
        System.out.println(findNearestShop(mySet, manCoordinates));
    }
}