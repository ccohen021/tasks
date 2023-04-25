package cohen.cameron.tasks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
public class CreateTaskDto {
    @JsonProperty(value = "description", required = true)
    @NotNull(message="Required description value missing")
    private String description;

}
