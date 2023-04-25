package cohen.cameron.tasks.valueobjects;

import lombok.Value;

@Value
public class ErrorInfo {
    String code;
    String field;
    String rejectedValue;
    String message;

}
