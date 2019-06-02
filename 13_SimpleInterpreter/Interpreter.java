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
Write a Java program that is a mini-interpreter for a toy programming language that allows the following:
1. The use of variables that consist of a single letter (e.g. A, a, ...)
2. The use of whole numbers: ( e.g. -1, -20, 0, 1, 200)
3. Assignment (=): ( e.g. A = B, A = 10, )
4. Addition of exactly two elements (variables or constants) (+) : ( e.g. C = A + B, D = 1 + A, ... )
5. The ability to "return" a value when a single variable or constant is on a line by itself ( e.g. A, B, 10)
Your program will take as input a program in the toy programming language such as:
A = 2
B = 8
C = A + B
C
and return the return value of the toy program, in this case, the value 10
*/
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleInterpreter {
	// rom would be the "memory" of the programm.
    public static HashMap<String, Integer> rom = new HashMap<>();

	/**
	 * This function takes as input a string that contains rom variables or numbers, 	 
	 * does the addition, and stores the result into the memory of the program.
	 */
    public static void additionMethod(String strInput){ // a = b||3 + w||6
        String[] temp = strInput.split("="); // temp[0] = <a>, temp[1] = < b + w>
        String[] addElements = temp[1].split("\\+"); // addElements[0] = < b > addElements[1] = < w>
        if (addElements[0].matches("^-?[0-9]*")) { // a = 4 + [\w||\d]
            if (addElements[1].matches("^-?[0-9]*")) {  // a = 5 + 3
                int result = Integer.parseInt(addElements[0]) + Integer.parseInt(addElements[1]);
                rom.put(temp[0], result);
            }else{ // a = 5 + a
                if (rom.containsKey(addElements[1])) {
                    int result = Integer.parseInt(addElements[0]) + rom.get(addElements[1]);
                    rom.put(temp[0], result);
                }
            }
        }else{ // first operand is character a = c + [\w||\d]
            if (addElements[1].matches("^-?[0-9]*")) {  // a = c + 3
                if (rom.containsKey(addElements[0])) {
                    int result = rom.get(addElements[0]) + Integer.parseInt(addElements[1]);
                    rom.put(temp[0], result);
                }
            }else{ // a = c + w
                if (rom.containsKey(addElements[0]) && rom.containsKey(addElements[1])) {
                    int result = rom.get(addElements[0]) + rom.get(addElements[1]);
                    rom.put(temp[0], result);
                }
            }
        }
    }


    public static void main(String[] args) {

        while(true){
            System.out.print("#");
            Scanner sc = new Scanner(System.in);
            String strInput = sc.nextLine();
            if (strInput.equals("exit")) {
                System.exit(0);
            }

			// Create the necessary matchers to understand and categorize the user input.
			// pattern for assignments of numbers to variables
            Pattern patAssignNum = Pattern.compile("^[a-zA-Z][ ]*[=][ ]*-?[0-9]*$");
            // pattern for assignments of variables to variables
			Pattern patAssignVar = Pattern.compile("^[a-zA-Z][ ]*[=][ ]*[a-zA-Z]$");
			// simple variable pattern reader
			Pattern patPrintVar = Pattern.compile("^[a-zA-Z]$");
			// pattern for simple number echo
            Pattern patPrintCon = Pattern.compile("^[-]?[0-9]+$");
            // pattern for addition of variables and numbers (mixed)
			Pattern patAddition = Pattern.compile("^[a-zA-Z][ ]*[=][ ]*([a-zA-Z]|[-]?[0-9]*)[ ]*[+][ ]*([a-zA-Z]|[-]?[0-9]*)$");

            Matcher myMatcherAssignNum = patAssignNum.matcher(strInput);
            Matcher myMatcherAssignVar = patAssignVar.matcher(strInput);
            Matcher myMatcherVar = patPrintVar.matcher(strInput);
            Matcher myMatcherCon = patPrintCon.matcher(strInput);
            Matcher myMatcherAddition = patAddition.matcher(strInput);

			// act accordingly
            if (myMatcherAssignNum.find()) { // A = -14 temp[0]=<A > temp[1]=< -14>
                String[] temp = strInput.split("=");
                temp[0] = temp[0].replace(" ","" );
                temp[1] = temp[1].replace(" ","" );
                rom.put(temp[0], Integer.parseInt(temp[1]));
            } else if (myMatcherAssignVar.find()) { // a = b
                String[] temp = strInput.split("=");
                temp[0] = temp[0].replace(" ","" );
                temp[1] = temp[1].replace(" ", "");
                if (rom.containsKey(temp[0]) && rom.containsKey(temp[1])) {
                    rom.put(temp[0], rom.get(temp[1]));
                }
            } else if (myMatcherVar.find()) {
                strInput = strInput.replace(" ", "");
                if (rom.containsKey(strInput))
                    System.out.println(rom.get(strInput));
                else
                    System.out.println(strInput);
            } else if (myMatcherCon.find()) {
                strInput = strInput.replace(" ", "");
                System.out.println(strInput);
            } else if (myMatcherAddition.find()) {
                additionMethod(strInput);
            }
        }
    }
}
