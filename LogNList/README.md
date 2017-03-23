## LogNList
#### data structure that implements java.util.List with O(log N) time for:
* add(Object obj) 
* add(int index, Object obj)
* remove(int index)
* set(int index, Object obj)

To guarantee O(logN) time, the data struture is implemented using self-balancing tree (AVL tree) and argumented with size attribute in TreeNode.

Unit testing with Junit is included.
