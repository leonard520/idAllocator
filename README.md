# idAllocator
maintain a id pool of size of 2^16. allocate and free id. use bitmap to reduce memory usage
refer main in PathIdGenerator to learn how to use it
PathIdGenerator.nextId(src1, dst1)
PathIdGenerator.removeId(src1, dst1, 2)

e.g. 
1. allocate 100 ids
then should return 1 ~ 100
2. free the id from 31 to 70
id 31 ~ 70 will be available in pool
3. free the id 80
id 80 will be available in pool
4. allocate another 100 ids
id 31 ~ 70, 80 and 101 ~ 159 will be allocated

edit LARGEST_SIZE to change your max size of your pool
