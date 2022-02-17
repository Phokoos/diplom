package task.exception.handler;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@AllArgsConstructor
@Data
public class ValidationExceptionDto implements Serializable {

    private String name;
    private String message;

    public ValidationExceptionDto(FieldError error) {
        this.name = error.getField();
        this.message = error.getDefaultMessage();
    }
}
