# OOP.Assignment02
It is generally expected that method 4 (using a thread pool) will have the best performance, followed by method 3 (using separate threads) and then method 2 (using a single thread).

The reason for this is that a thread pool allows for efficient reuse of threads, so the overhead of creating and destroying threads is minimized. In contrast, method 3 creates a new thread for each file, which can be slower due to the overhead of creating and destroying threads. Method 2 is the slowest of the three because it only uses a single thread, so it cannot take advantage of multiple processors or cores and is limited by the speed of a single CPU.

To measure the performance of these methods, you can use the System.currentTimeMillis() function to record the start and end times of each method and calculate the elapsed time. It is important to run each method multiple times and take the average elapsed time to account for any variations due to other processes running on the system.

It is also important to use a large enough dataset (a large number of text files) to get a meaningful comparison of the performance of the different methods. If the dataset is too small, the differences in performance may not be significant.

In the README file, you can provide a detailed explanation of the performance results, including the specific elapsed times for each method and any observations or insights you have about the reasons for the differences in performance.

here's a diagram for the functions: 
![Screenshot (52)](https://user-images.githubusercontent.com/118915763/212127146-8b7bef28-c906-4abd-89c0-8434601ea976.png)
