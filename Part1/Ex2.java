package Part1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;


public class Ex2 {
    /**
     * This implementation will create n text files on the disk, each containing a random number of lines between 1 and bound, inclusive.
     * Each line will contain the string "World Hello" followed by 10 random lowercase letters.
     * The random number generator is seeded with the value seed so that the same sequence of random numbers
     * will be generated each time the function is called with the same seed.
     *
     * @param n
     * @param seed
     * @param bound
     * @return The names of the created files will be stored in an array and returned by the function.
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] fileNames = new String[n];
        Random random = new Random(seed);

        for (int i = 0; i < n; i++) {
            fileNames[i] = "file_" + (i + 1);
            try {
                File file = new File(fileNames[i]);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                int numLines = random.nextInt(bound) + 1;
                for (int j = 0; j < numLines; j++) {
                    writer.write("World Hello ");
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileNames;
    }

    /**
     * This implementation reads through each file in the fileNames array and counts the number of lines in each file.
     *
     * @param fileNames
     * @return the total number of lines in all the files.
     */
    public static int getNumOfLines(String[] fileNames) {
        int numLines = 0;
        for (String fileName : fileNames) {
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
        return numLines;
    }
}
