### Knapsack-Like Problem
----------------------------
Given two Lists of Lists of numbers (List\<List\<Integer\>\>\>, or a(b)), and an integer X,
where b List<Integer> has two elements, key @index 0 and value @index 1,
find the best b pairs (can be more than one and elements of pairs must belong to different lists),
which are their value summary is closest to X (but not bigger than X).
The lists are unsorted.

-----------------------------
Solution runs in O(2NlogN + N + N) => O(NlogN), where 2NlogN is the time required to sort the two lists, 
plus N for the creation of a unified array, plus N for the final traversal and result creation.

Space Complexity is O(2(M + N)) where M=list1.size() and N=list2.size().