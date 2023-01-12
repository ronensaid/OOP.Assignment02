package Part2;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;


class CustomExecutorTest {
    private final CustomExecutor executor;

    public CustomExecutorTest() {
        executor = new CustomExecutor();
    }


    @Test
    void testSubmit() {
        Future<String> futureT1 = executor.submit(() -> "Hello");
        Future<Integer> futureT2 = executor.submit(() -> 12, TaskType.COMPUTATIONAL);
        Task<Boolean> t3 = Task.createTask(() -> true, TaskType.IO);
        Future<Boolean> futureT3 = executor.submit(t3);
        try {
            assertEquals("Hello", futureT1.get());
            assertEquals(12, futureT2.get());
            assertEquals(true, futureT3.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updatePriority() {
        PriorityBlockingQueue<FutureTaskWrapper> queue = new PriorityBlockingQueue<>();  // create a new instance of the PriorityBlockingQueue for each test
        Task<String> task0 = Task.createTask(() -> {
            sleep(1);
            return "Task 0 has been executed.";
        }, TaskType.OTHER);
        Task<Boolean> task1 = Task.createTask(() -> {
            sleep(1);
            return true;
        }, TaskType.COMPUTATIONAL);
        Task<String> task2 = Task.createTask(() -> {
            sleep(1);
            return "Task 2 has been executed";
        });
        Task<Integer> task3 = Task.createTask(() -> {
            sleep(1);
            return 3;
        });
        Task<Integer> task4 = Task.createTask(() -> {
            sleep(1);
            return 3;
        });
        Task<Integer> task5 = Task.createTask(() -> {
            sleep(1);
            return 5;
        }, TaskType.COMPUTATIONAL);
        Task<Double> task6 = Task.createTask(() -> {
            sleep(1);
            return 0.333333;
        }, TaskType.IO);
        executor.submit(task0);
        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);
        executor.submit(task4);
        executor.submit(task5);
        executor.submit(task6);
        executor.submit(task3);
        executor.submit(task4);
        executor.submit(task5);
        executor.submit(task6);
        assertTimeout(Duration.ofSeconds(5), () -> {
            int currentPriority = TaskType.OTHER.getPriorityValue();
            while (queue.peek() != null) {
                FutureTaskWrapper<Task> wrapper = queue.poll();
                Task customexecutor = wrapper.get(1, TimeUnit.SECONDS);
                assertTrue(customexecutor.getTaskType().getPriorityValue() >= currentPriority);
                currentPriority = customexecutor.getTaskType().getPriorityValue();
            }
        });

    }


    @Test
    public void testGetLowestValue() {
        CustomExecutor executor = new CustomExecutor();
        executor.updatePriority(5, true);
        executor.updatePriority(1, true);
        executor.updatePriority(10, true);
        executor.updatePriority(3, true);
        assertEquals(1, (int) executor.getLowestValue());
    }

}