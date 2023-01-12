package Part2;

import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The CustomExecutor class extends the ThreadPoolExecutor class and overrides its submit() methods to add the functionality of prioritizing
 * tasks based on their priority. The CustomExecutor class uses a PriorityBlockingQueue to store tasks and a LinkedList and an Integer to store
 * the priority values of the tasks. A ReentrantLock is used to synchronize access to the priority values.
 */
public class CustomExecutor extends ThreadPoolExecutor {
    private final LinkedList<Integer> values;
    private Integer lowestValue;
    private ReentrantLock lock;

    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2,
                Runtime.getRuntime().availableProcessors() - 1, 300, TimeUnit.MILLISECONDS
                , new PriorityBlockingQueue<>());
        this.values = new LinkedList<Integer>();
        this.lowestValue = null;
        this.lock = new ReentrantLock();
    }

    /**
     * submit(Callable<T> callable, TaskType type): a method that takes a Callable<T> object and a TaskType as its parameters.
     * It creates a Task<T> object with the TaskType and Callable task, creates a FutureTaskWrapper<T> object with the Task<T> object
     * and the priority of the task. It then updates the priority of the task by calling the updatePriority() method and adds
     * the FutureTaskWrapper<T> object to the thread pool for execution.
     * @param callable
     * @param type
     * @return futuretask
     * @param <T>
     */

    public <T> Future<T> submit(Callable<T> callable, TaskType type) {
        Task<T> task = new Task<T>(type, callable);
        FutureTaskWrapper<T> futureTask = new FutureTaskWrapper<>(task, task.getPriority());
        updatePriority(task.getPriority(), true);
        super.execute(futureTask);
        return futureTask;
    }

    /**
     * submit(Callable<T> task): an overridden method that takes a Callable<T> object as its parameter.
     * It creates a FutureTaskWrapper<T> object with the Callable<T> object and the priority of the task.
     * It then updates the priority of the task by calling the updatePriority() method
     * @param task the task to submit
     * @return futurer task
     * @param  <T>
     */
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        FutureTaskWrapper<T> futureTask = new FutureTaskWrapper<>(task, Task.createTask(task).getPriority());
        updatePriority(Task.createTask(task).getPriority(), true);
        super.execute(futureTask);
        return futureTask;
    }

    /**
     * updatePriority(Integer value, boolean isAdd): a method that takes an Integer value and a boolean as its parameters.
     * It locks the values and lowestValue fields and checks if the isAdd parameter is true. If it is, it adds the value
     * to the values list and updates the lowestValue field if necessary. If isAdd is false, it removes the value from the
     * values list and updates the lowestValue field if necessary.
     * @param value
     * @param isAdd
     */
    public void updatePriority(Integer value, boolean isAdd) {
        lock.lock();
        try {
            if (isAdd) {
                values.add(value);
                if (lowestValue == null || value < lowestValue) {
                    lowestValue = value;
                }
            } else {
                values.remove(value);
                if (value == lowestValue) {
                    lowestValue = null;
                    for (Integer v : values) {
                        if (lowestValue == null || v < lowestValue) {
                            lowestValue = v;
                        }
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * getLowestValue(): a method that returns the value of the lowestValue field.
     * @return
     */
    public Integer getLowestValue() {
        lock.lock();
        try {
            return lowestValue;
        } finally {
            lock.unlock();
        }
    }
}


