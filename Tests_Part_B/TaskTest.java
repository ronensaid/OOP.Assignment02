package Part2;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;


public class TaskTest {
    public static final Logger logger = LoggerFactory.getLogger(TaskTest.class);

    @Test
    public void partialTest() {
        CustomExecutor customExecutor = new CustomExecutor();
        var task = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        var sumTask = customExecutor.submit(task);
        final int sum;
        try {
            sum = (int) sumTask.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        logger.info(() -> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = () -> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = () -> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };

        var priceTask = customExecutor.submit(() -> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        var reverseTask = customExecutor.submit(callable2, TaskType.IO);
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = (Double) priceTask.get();
            reversed = String.valueOf(reverseTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        logger.info(() -> "Reversed String = " + reversed);
        logger.info(() -> String.valueOf("Total Price = " + totalPrice));
        logger.info(() -> "Current maximum priority = " + customExecutor.getLowestValue());// get the lowest val
        customExecutor.shutdown();
    }


    @Test
    public void testTask() throws ExecutionException, InterruptedException {
        CustomExecutor executor = new CustomExecutor();
        // Create a Callable task
        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Task completed";
        };

        // Create a Task object with TaskType and the Callable task
        Task<String> taskObject = new Task<>(TaskType.COMPUTATIONAL, task);

        // Submit the task to an executor
        Future<String> future = executor.submit(taskObject);

        // Get the result of the task
        String result = future.get();
        assertEquals("Task completed", result);

        // Check the task type
        assertEquals(TaskType.COMPUTATIONAL, taskObject.getTaskType());

        // Check the task priority
        assertEquals(1, taskObject.getPriority());
    }
}


