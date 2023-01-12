package Part2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * The FutureTaskWrapper class is a wrapper class that extends the FutureTask class and implements the Comparable interface. It is used to store
 * the priority of a task,so that the tasks can be sorted based on their priority when they are stored in a PriorityBlockingQueue
 * (as seen in the CustomExecutor class).
 * @param <T>
 */
public class FutureTaskWrapper<T> extends FutureTask<T> implements Comparable<FutureTaskWrapper<T>> {
    private final int priority;

    /**
     * FutureTaskWrapper(Callable<T> callable, int priority): a constructor that takes a Callable<T> object and an int priority as its parameters.
     * It calls the constructor of the FutureTask class to store the callable object and sets the priority field to the passed in priority parameter.
     * @param callable
     * @param priority
     */
    public FutureTaskWrapper(Callable<T> callable, int priority) {
        super(callable);
        this.priority = priority;
    }

    /**
     * getPriority(): a method that returns the priority field.
     * @return
     */
    public int getPriority() {
        return priority;
    }

    /**
     * compareTo(FutureTaskWrapper<T> other): an overridden method that compares the priority of the current FutureTaskWrapper object
     * to the priority of another FutureTaskWrapper object passed as a parameter. It returns the result of the comparison as an int.
     * @param other the object to be compared.
     * @return
     */
    @Override
    public int compareTo(FutureTaskWrapper<T> other) {
        return Integer.compare(this.priority, other.priority);
    }
}
