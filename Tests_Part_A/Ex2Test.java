package Part1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.io.File;
import java.util.Random;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Ex2Test {

    @Test
    public void testCreateTextFiles() {
        int n = 3;
        int seed = 123;
        int bound = 5;
        String[] fileNames = Ex2.createTextFiles(n, seed, bound);
        for (String fileName : fileNames) {
            File file = new File(fileName);
            assertTrue(file.exists());
        }

        Random random = new Random(seed);
        int expectedNumLines = 0;
        for (int i = 0; i < n; i++) {
            expectedNumLines += random.nextInt(bound) + 1;
        }
        int actualNumLines = Ex2.getNumOfLines(fileNames);
        assertEquals(expectedNumLines, actualNumLines);
    }
}

