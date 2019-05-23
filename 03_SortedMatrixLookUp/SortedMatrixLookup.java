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
Given a sorted matrix where the number below and right of  you will always be bigger, 
write an algorithm to find if a particular number exist in the matrix. What is the running time of your algorithm.
a   b   c   d
e   f   g   h
i   j   k   l
m   n   o   p
*/
public class FindElementInSortedMatric {

	public static boolean findElement(int[][] matrix, int number) {
		int i;
		for (i = 0; i < matrix.length; i++) {
			if (i+1 < matrix.length && matrix[i+1][0] < number) {
				continue;
			} else if (i+1 < matrix.length && matrix[i+1][0] > number) {
				return scanRow(matrix[i], number);
			} else if (i+1 < matrix.length && matrix[i+1][0] == number) {
				return true;
			}
		}
		return scanRow(matrix[i-1], number);
	}
	
	private static boolean scanRow(int[] row, int number) {
		for (int i : row) {
			if (i == number) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String args[]) {
        int[][] myMatrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int number = 17;
        System.out.println("The element <" + number + "> is on list <" + findElement(myMatrix, number) + ">");
        number = 1;
        System.out.println("The element <" + number + "> is on list <" + findElement(myMatrix, number) + ">");
        number = 8;
        System.out.println("The element <" + number + "> is on list <" + findElement(myMatrix, number) + ">");
        number = -1;
        System.out.println("The element <" + number + "> is on list <" + findElement(myMatrix, number) + ">");
    }
}


