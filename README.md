# BST and Counting Sort 

* measure complexity of Balanced Binary Search Tree for operations using 100 random generated datasets:
  * insert 
  * find 
  * delete
* implement and investigate Counting Sort algorithm
* figure out when Counting Sort doesn’t perform

## Balanced Binary Search Tree

Implementation [AVLTree.java](bst%2Fsrc%2Fmain%2Fjava%2Fcom%2Fzma%2Fhighload%2Fcourse%2FAVLTree.java)

Run docker container
```shell
docker image build -t bst ./bst
```

```shell
docker --name bst run bst
```

```shell
docker cp bst:/measurements-AVL.csv ./measurements-AVL.csv
```
Measurements result: [measurements-AVL.csv](measurements-AVL.csv)
### Insert time in nanoseconds
![insert.png](screenshots%2Finsert.png)
### Search time in nanoseconds
![search.png](screenshots%2Fsearch.png)
### Delete time in nanoseconds
![delete.png](screenshots%2Fdelete.png)

## Counting Sort 

Implementation [CountingSort.java](counting-sort%2Fsrc%2Fmain%2Fjava%2Fcom%2Fzma%2Fhighload%2Fcourse%2FCountingSort.java)

```shell
docker image build -t sort ./counting-sort
```

```shell
docker run --rm sort
```

#### Output 
```
Tests started
CountingSort for 10 elements in range [0,4]: 6 microseconds
CountingSort for 10 elements in range [0,9502]: 437 microseconds
CountingSort for 10000 elements in range [0,4]: 968 microseconds
CountingSort for 10000 elements in range [0,9999]: 1539 microseconds

MergeSort for 10 elements in range [0,4]: 19 microseconds
MergeSort for 10 elements in range [0,9502]: 8 microseconds
MergeSort for 10000 elements in range [0,4]: 4177 microseconds
MergeSort for 10000 elements in range [0,9999]: 3706 microseconds
Tests finished
```

### Conclusions

Tests show that Counting Sort algorithm is ineffective if the range of values in input array is much larger than number of elements in this array.

```
CountingSort for 10 elements in range [0,9502]: 437 microseconds
```
Comparing to 
```
MergeSort for 10 elements in range [0,9502]: 8 microseconds
```