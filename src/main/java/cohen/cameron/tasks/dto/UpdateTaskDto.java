package cohen.cameron.tasks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@Setter @Getter
@ToString
public class UpdateTaskDto {
    @JsonProperty(value = "uuid", required = true)
    @NotNull(message = "Required task uuid missing")
    private UUID uuid;
    private String description;
    private Boolean completed;
}
