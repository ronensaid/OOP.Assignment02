package Part1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LineCounterThreadTest {

    @Test
    public void testGetNumOfLinesThreads() {
        String[] fileNames = {"test_file_1.txt", "test_file_2.txt", "test_file_3.txt"};

        // Create test files
        createTestFile("test_file_1.txt", 5);
        createTestFile("test_file_2.txt", 10);
        createTestFile("test_file_3.txt", 15);

        int expectedNumLines = 5 + 10 + 15;
        int actualNumLines = LineCounterThread.getNumOfLinesThreads(fileNames);
        assertEquals(expectedNumLines, actualNumLines);
    }

    private void createTestFile(String fileName, int numLines) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < numLines; i++) {
                writer.write("Line " + (i+1) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
