package cohen.cameron.tasks.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        this("No task associated with UUID");
    }


    public TaskNotFoundException(String message) {
        super(message);
    }
}
