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
Given a list of files. Return all the unique lines from all files.
*/
public class UniqueLinesAtFiles {

    public static void findUniqueLines(String[] listOfFiles) {
        if (listOfFiles.length < 1) return;
        HashSet<String> mySet = new HashSet<>();
        HashSet<String> myBUSet = new HashSet<>();
        for (String str : listOfFiles) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(str));
                String line;
                while ((line = br.readLine()) != null) {
                    if (myBUSet.contains(line)) continue;
                    else if (mySet.contains(line)) {
                        mySet.remove(line);
                        myBUSet.add(line);
                    } else {
                        mySet.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("The unique lines are:\n");
        for (String str : mySet) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        String[] listOfFiles = {"file1", "file2"};
        findUniqueLines(listOfFiles);
    }
}