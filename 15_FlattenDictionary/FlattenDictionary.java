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
Flatten a Dictionary
A dictionary is a type of data structure that is supported natively in all major interpreted languages such as JavaScript, Python, Ruby and PHP, where it’s known as an Object, Dictionary, Hash and Array, respectively. In simple terms, a dictionary is a collection of unique keys and their values. The values can typically be of any primitive type (i.e an integer, boolean, double, string etc) or other dictionaries (dictionaries can be nested). However, for this exercise assume that values are either an integer, a string or another dictionary.
Given a dictionary dict, write a function flattenDictionary that returns a flattened version of it .
If you’re using a compiled language such Java, C++, C#, Swift and Go, you may want to use a Map/Dictionary/Hash Table that maps strings (keys) to a generic type (e.g. Object in Java, AnyObject in Swift etc.) to allow nested dictionaries.
If a certain key is empty, it should be excluded from the output (see e in the example below).

Example:

input:  dict = {
            "Key1" : "1",
            "Key2" : {
                "a" : "2",
                "b" : "3",
                "c" : {
                    "d" : "3",
                    "e" : {
                        "" : "1"
                    }
                }
            }
        }

output: {
            "Key1" : "1",
            "Key2.a" : "2",
            "Key2.b" : "3",
            "Key2.c.d" : "3",
            "Key2.c.e" : "1"
        }
Important: when you concatenate keys, make sure to add the dot character between them. For instance concatenating Key2, c and d the result key would be Key2.c.d.
*/

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    /**
     * This function iterates through a HashMap, and if the value is not of type String,
     * if calls a helperFunction to that value. The helper function will return a HashMap of
     * String, String, that will be iterated and added into the result.
     * @param dict
     * @return
     */
    static HashMap<String, String> flattenDictionary(HashMap<String, Object> dict) {
        // Initialize the result map.
        HashMap<String, String> result = new HashMap<>();

        // Iterate through all the elements of the @dict
        for(Map.Entry<String, Object> kv : dict.entrySet()) {

            // If an element is an instance of String, then add it to the @result
            if (kv.getValue() instanceof String) {
                result.put(kv.getKey(), (String) kv.getValue());
            } else {
                // Create a temp map
                HashMap<String, Object> temp = new HashMap<>();
                // Populate it with all the flattened values of kv.getKey() map
                helperFunction((HashMap) kv.getValue(), kv.getKey(), temp);
                // Iterate through the values and add the to the result
                for(Map.Entry<String, Object> keyval : temp.entrySet()) {
                    result.put(keyval.getKey(), (String) keyval.getValue());
                }
            }
        }
        return result;
    }

    /**
     * This recursive function, iterates through all the elements of the @dict,
     * and if their value is an instance of key, adds them to temp. If not it calls itself
     * for that value, and for an updated @key, which will keep track of the origin of the values
     * that are being are iterated
     * @param dict
     * @param key
     * @param temp
     */
    static void helperFunction(HashMap<String, Object> dict, String key,
                                                  HashMap<String, Object> temp) {
        for(Map.Entry<String, Object> kv : dict.entrySet()) {
            if (!(kv.getValue() instanceof String)) {
                helperFunction((HashMap) kv.getValue(), key + kv.getKey(), temp);
            } else {
                temp.put(key + "." + kv.getKey(), (String) kv.getValue());
            }
        }
    }

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("1","ena");
        map.put("2", Stream.of(new String[][] {
                {"a", "2a"},
                {"b", "2b"},
        }).collect(Collectors.toMap(data -> data[0], data -> data[1])));

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("3", Stream.of(new String[][] {
                {"a", "3a"},
                {"b", "3b"},
        }).collect(Collectors.toMap(data -> data[0], data -> data[1])));

        map.put("3", map1);

        for (Map.Entry<String, Object> kv : map.entrySet()) {
            System.out.print(kv.getKey() + ", ");
            System.out.println(kv.getValue().toString());
        }
        System.out.println("===================================");
        for (Map.Entry<String, String> kv : flattenDictionary(map).entrySet()) {
            System.out.print(kv.getKey() + ", ");
            System.out.println(kv.getValue());
        }
    }
}