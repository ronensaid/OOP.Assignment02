# OOP.Assignment02
explanation on the run time complexity in Part1:

It is generally expected that method 4 (using a thread pool) will have the best performance, followed by method 3 (using separate threads) and then method 2 (using a single thread).

The reason for this is that a thread pool allows for efficient reuse of threads, so the overhead of creating and destroying threads is minimized. In contrast, method 3 creates a new thread for each file, which can be slower due to the overhead of creating and destroying threads. Method 2 is the slowest of the three because it only uses a single thread, so it cannot take advantage of multiple processors or cores and is limited by the speed of a single CPU.

To measure the performance of these methods, you can use the System.currentTimeMillis() function to record the start and end times of each method and calculate the elapsed time. It is important to run each method multiple times and take the average elapsed time to account for any variations due to other processes running on the system.

It is also important to use a large enough dataset (a large number of text files) to get a meaningful comparison of the performance of the different methods. If the dataset is too small, the differences in performance may not be significant.


here's a diagram for the functions of part1: 
![Screenshot (52)](https://user-images.githubusercontent.com/118915763/212136428-d64660e6-c83d-4369-81f1-8fabfdcd75e3.png)


here is the diagram for part 2: 
![Screenshot (53)](https://user-images.githubusercontent.com/118915763/212136455-f69d99ef-a6a6-48fd-ae56-1ef831981e7e.png)

