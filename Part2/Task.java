package Part2;

import java.util.concurrent.Callable;

/**
 * This class implements both the Callable interface, which allows the task to be executed and return a result,
 * and the Comparable interface, which allows the task to be compared to other tasks based on their priority.
 * The Task class also provides two static methods createTask(Callable<T> task) and createTask(Callable<V> callable, TaskType type)
 * to create the Task object and set the type of the task, if not provided the default task type is TaskType.OTHER.
 * @param <T>
 */
public class Task<T> implements Callable<T>, Comparable<Task<T>> {
    private final TaskType type;
    private final Callable<T> callable;

    public Task(TaskType type, Callable<T> callable) {
        this.type = type;
        this.callable = callable;
    }

    /**
     * Task(Callable<T> task): a constructor that takes a Callable<T> object as its parameter.
     * It checks if the Callable<T> object is an instance of the Task class, if it's not it sets the type to TaskType.COMPUTATIONAL
     * otherwise it gets the type from the passed callable.
     * @param task
     */
    public Task(Callable<T> task) {
        if(!(task instanceof Task<T>))
            this.type = (TaskType.COMPUTATIONAL);
        else
            this.type = ((Task<Object>) task).type;

        this.callable = task;
    }

    /**
     * createTask(Callable<T> task): a static method that takes a Callable<T> object as its parameter.
     * It creates a new Task<T> object with the Callable<T> object and the TaskType.OTHER and returns it.
     * @param task
     * @return
     * @param <T>
     */
    public static <T> Task<T> createTask(Callable<T> task) {
        return createTask(task, TaskType.OTHER);
    }

    /**
     * createTask(Callable<V> callable, TaskType type): a static generic method that takes a Callable<V> object and
     * a TaskType object as its parameters. It creates a new Task<V> object with the Callable<V> object and the TaskType and returns it.
     * @param callable
     * @param type
     * @return
     * @param <V>
     */
    public static <V> Task<V> createTask(Callable<V> callable, TaskType type) {
        return new Task<>(type, callable);
    }

    /**
     * call(): an overridden method that returns the result of the call() method of the callable field.
     * @return
     * @throws Exception
     */
    @Override
    public T call() throws Exception {
        return callable.call();
    }

    /**
     * getPriority(): a method that returns the priority value of the task by calling the getPriorityValue() method
     * of the TaskType object stored in the type field.
     * @return
     */
    public int getPriority() {
        return type.getPriorityValue();
    }

    /**
     * getTaskType(): a method that returns the TaskType object stored in the type field.
     * @return
     */
    public TaskType getTaskType() {
        return type;
    }

    /**
     * compareTo(Task<T> other): an overridden method that compares the priority value of the current task to
     * the priority value of another task passed as a parameter. It returns the result of the comparison as an int.
     * @param other the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Task<T> other) {
        return Integer.compare(this.getTaskType().getPriorityValue(),other.getTaskType().getPriorityValue());
    }
}