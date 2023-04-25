package cohen.cameron.tasks.mappers;

import cohen.cameron.tasks.dto.CreateTaskDto;
import cohen.cameron.tasks.dto.UpdateTaskDto;
import cohen.cameron.tasks.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    /**
     * Creates a new task
     * @param dto Task description {@link CreateTaskDto}
     * @return newly updated {@link Task}
     */
    public final Task toEntity(CreateTaskDto dto) {
        return Task.builder()
                .description(dto.getDescription())
                .completed(false)
                .build();
    }


    /**
     * Updates an existing task
     * @param dto new potentially partial {@link UpdateTaskDto}
     * @return newly updated {@link Task}
     */
    public final Task toEntity(UpdateTaskDto dto) {
        return Task.builder()
                .uuid(dto.getUuid())
                .description(dto.getDescription())
                .completed(dto.getCompleted())
                .build();
    }
}
