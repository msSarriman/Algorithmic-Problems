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
Given an N x M image with black pixels and white pixels, if a pixel is the only one in its color throughout its entire row and column,
then it is a lonely pixel. Find the number of lonely pixels in black from the image. (O(NM))
*/
import java.util.HashSet;

public class Solution {

    public static int findLonelyPixels(int[][] image, int n, int m) { // 0 for black 1 for white
        HashSet<Integer> mySkipList = new HashSet<>();
        int counter = 0;
        for (int i = 0; i < n; i++) {
            int[] indexZero = {0,0};
            int[] indexOne  = {0,0};
            for (int j = 0; j < m; j++) {
                if (mySkipList.contains(j)) continue;
                if (image[i][j] == 0) {
                    indexZero[0] = j;
                    indexZero[1] += 1;
                } else {
                    indexOne[0] = j;
                    indexOne[1] += 1;
                }
            }
            if (indexZero[1] == 1) {
                int tempIndex = indexZero[0];
                boolean flag = true;
                for (int k = 0; k < m; k++) {
                    if (k == i) continue;
                    if (image[k][tempIndex] == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    counter++;
                    mySkipList.add(tempIndex);
                }
            } else if (indexOne[1] == 1) {
                int tempIndex = indexOne[0];
                boolean flag = true;
                for (int k = 0; k < m; k++) {
                    if (k == i) continue;
                    if (image[k][tempIndex] == 1) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    counter++;
                    mySkipList.add(tempIndex);
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        int[][] myImage = {	{1,1,1,0},
                            {1,0,1,1},
                            {1,1,0,1},
                            {0,1,1,1},
                            {1,1,0,1}};
        int n = 5, m = 4;
        System.out.println("The number of lonely pixels is : " + findLonelyPixels(myImage, n, m));
    }
}