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
A string consists of '0', '1', and '?'. The question mark can be either a '0' or a '1'. Find 
all possible combinations for a string.
*/

public class FindPermutationsAtString {

	public static int findPermutationsAtString(String str) {
		int counter = 0;
		for (char c : str.toCharArray()) {
			if (c == '?') counter++;
		}
		return (int) Math.pow(2,counter);
   	}
	
    	public static void main(String[] args) {
        	System.out.println(findPermutationsAtString("0011??0?11"));
        	System.out.println(findPermutationsAtString("1?????1"));
        	System.out.println(findPermutationsAtString("0001001????00?1"));
       	 	System.out.println(findPermutationsAtString("????????"));
        	System.out.println(findPermutationsAtString("00000000"));
        	System.out.println(findPermutationsAtString("0?1"));
    	}
}