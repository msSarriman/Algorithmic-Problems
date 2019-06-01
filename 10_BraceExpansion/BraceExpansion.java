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
SOFTWARE. */
// Brace Expansion,Given a string, perfrom the brace expansion,For example,Input: s = "a{d,c,b}e",output: {ade , ace , abe}

import java.util.LinkedList;

public class BraceExpansion{

    public static void main(String[] args) {
        for (String str : expString("a{b,c,d,f}ep")) {
            System.out.println(str);
        }
    }

    // This solution assumes that there is only one expansion taking place.
    // e.g. string "a{b,c}e{d,i}" is not supported
    public static LinkedList<String> expString(String str) {
        LinkedList<String> myList = new LinkedList<>();
        int indexOfFirstCurly = str.indexOf("{");
        int indexOfSeconCurly = str.indexOf("}");
        int insertAt = indexOfFirstCurly;
        StringBuilder mySb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (i >= indexOfFirstCurly && i <= indexOfSeconCurly) {
                continue;
            }
            mySb.append(str.charAt(i));
        }

        for (int i = (indexOfFirstCurly + 1); i < indexOfSeconCurly; i += 2) {
            mySb.insert(insertAt, str.charAt(i));
            myList.add(mySb.toString());
            mySb.deleteCharAt(insertAt);
        }
        return myList;
    }
}