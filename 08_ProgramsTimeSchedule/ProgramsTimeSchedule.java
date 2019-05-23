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
Write a program to find if a given program can be executed or not. A program's schedule, is 
represented by P1(10, 5), where 10 is the starting time and 5 is the duration. A program can be scheduled,
if it's schedule is not overlapping any other already scheduled programs.
*/
import java.util.LinkedList;
import java.util.HashMap;

public class ProgramsTimeSchedule {
	public static HashMap<Integer, String> myProgram = new HashMap<>();

	public static void checkAndAdd(Program pr) {
		boolean flag = true;
		for (int i = pr.start; i < pr.start + pr.end; i++) {
			if (myProgram.containsKey(i)) flag = false;
		}
		if (flag) {
			System.out.printf("Program <%s> can be added.\nAdding it...\n", pr.name);
			for (int i = pr.start; i < pr.start + pr.end; i++) {
				myProgram.put(i, pr.name);
			}
		} else {
			System.out.printf("Program <%s> cannot be added.\n", pr.name);
		}
	}


	public static void main(String[] args) {
		LinkedList<Program> myList = new LinkedList<>();
		myList.add(new Program(0,5, "p1"));
		myList.add(new Program(7,5, "p2"));
		myList.add(new Program(6,2, "p3")); // wont be added
		myList.add(new Program(10,2, "p4")); // wont be added
		myList.add(new Program(12,10, "p5"));
		myList.add(new Program(21,10, "p6")); // wont be added
		myList.add(new Program(22,10, "p7"));
		myList.add(new Program(33,5, "p8"));

		for (Program p : myList) {
			checkAndAdd(p);
		}
	}

}

class Program {
	int start;
	int end;
	String name;	

	Program(int start, int end, String name) {
		this.start = start;
		this.end = end;
		this.name = name;
	}
}