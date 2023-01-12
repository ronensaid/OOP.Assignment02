package Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This implementation creates a fixed-size thread pool with a number of threads equal to the length of the fileNames array.
 * It then submits a LineCounterCallable task to the thread pool for each file in the array.
 * The LineCounterCallable class implements the Callable interface and counts the number of lines in a single file.
 * The getNumOfLinesThreadPool function waits for all the tasks to complete and adds up the number of lines counted by
 * each task to get the total number of lines in all the files.
 */
public class LineCounterCallable implements Callable<Integer> {
    private String fileName;

    public LineCounterCallable(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Integer call() throws Exception {
        int numLines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.readLine() != null) {
                numLines++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numLines;
    }


    public static int getNumOfLinesThreadPool(String[] fileNames) {
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);

        int numLines = 0;
        for (String fileName : fileNames) {
            Future<Integer> result = executor.submit(new LineCounterCallable(fileName));
            try {
                numLines += result.get();
            } catch (Exception e) {


                e.printStackTrace();
            }
        }
        executor.shutdown();

        return numLines;
    }
}

