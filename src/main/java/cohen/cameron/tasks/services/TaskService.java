package cohen.cameron.tasks.services;

import cohen.cameron.tasks.database.TaskRepository;
import cohen.cameron.tasks.dto.CreateTaskDto;
import cohen.cameron.tasks.dto.UpdateTaskDto;
import cohen.cameron.tasks.exceptions.TaskNotFoundException;
import cohen.cameron.tasks.mappers.TaskMapper;
import cohen.cameron.tasks.entities.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class TaskService {

    private final TaskMapper mapper;
    private final TaskRepository taskRepository;

    public TaskService(TaskMapper mapper, TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }


    public Task addTask(CreateTaskDto dto) {
        Task task = mapper.toEntity(dto);
        return taskRepository.save(task);
    }


    public Task getTask(UUID uuid) {
        Optional<Task> optionalTask = taskRepository.findById(uuid);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException();
        }

        return optionalTask.get();
    }


    public List<Task> getTasks() {
        return taskRepository.findAll();
    }


    public Task deleteTask(UUID uuid) {
        Optional<Task> optionalTask = taskRepository.findById(uuid);

        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException();
        }

        taskRepository.deleteById(uuid);
        return optionalTask.get();
    }


    public Task editTask(UpdateTaskDto dto) {
        Optional<Task> optionalExistingTask = taskRepository.findById(dto.getUuid());

        if (optionalExistingTask.isEmpty()) {
            throw new TaskNotFoundException();
        }

        Task existingTask = optionalExistingTask.get();
        Task newTask = mapper.toEntity(dto);

        // update task with new fields if they exist.
        existingTask.setCompleted(
                Objects.isNull(newTask.getCompleted()) ? existingTask.getCompleted() : newTask.getCompleted()
        );

        existingTask.setDescription(
                Objects.isNull(newTask.getDescription()) ? existingTask.getDescription() : newTask.getDescription()
        );

        return taskRepository.save(existingTask);
    }
}
