package Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This implementation creates a separate LineCounterThread for each file in the fileNames array.
 * Each thread counts the number of lines in its respective file and stores the result in a local variable.
 * The getNumOfLinesThreads function waits for all the threads to finish and then adds up the number of
 * lines counted by each thread to get the total number of lines in all the files.
 */
public class LineCounterThread extends Thread {
    private String fileName;
    private int numLines;

    public LineCounterThread(String fileName) {
        this.fileName = fileName;
        this.numLines = 0;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.readLine() != null) {
                numLines++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumLines() {
        return numLines;
    }

    public static int getNumOfLinesThreads(String[] fileNames) {
        LineCounterThread[] threads = new LineCounterThread[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            threads[i] = new LineCounterThread(fileNames[i]);
            threads[i].start();
        }

        int numLines = 0;
        for (LineCounterThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            numLines += thread.getNumLines();
        }

        return numLines;
    }

}


