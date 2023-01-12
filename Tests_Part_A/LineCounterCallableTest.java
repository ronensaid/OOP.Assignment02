package Part1;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LineCounterCallableTest {

    @Test
    public void testGetNumOfLinesThreadPool() {
        String[] fileNames = {"test_file_1.txt", "test_file_2.txt", "test_file_3.txt"};

        // Create test files
        createTestFile("test_file_1.txt", 5);
        createTestFile("test_file_2.txt", 10);
        createTestFile("test_file_3.txt", 15);

        int expectedNumLines = 5 + 10 + 15;
        int actualNumLines = LineCounterCallable.getNumOfLinesThreadPool(fileNames);
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

