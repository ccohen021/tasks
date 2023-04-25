package cohen.cameron.tasks.controller;

import cohen.cameron.tasks.dto.CreateTaskDto;
import cohen.cameron.tasks.dto.UpdateTaskDto;
import cohen.cameron.tasks.entities.Task;
import cohen.cameron.tasks.services.TaskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody @Valid CreateTaskDto dto) {
        log.info("requested to POST /task for a new task with the description '{}'", dto.getDescription());
        return taskService.addTask(dto);
    }

    @GetMapping(value = "/task/{uuid}")
    public Task getTask(@PathVariable UUID uuid) {
        log.info("requested to GET /task/{uuid} to get task '{}'", uuid);
        return taskService.getTask(uuid);
    }

    @GetMapping(value = "/tasks")
    public List<Task> getTasks() {
        log.info("requested to GET /tasks to get all tasks");
        return taskService.getTasks();
    }

    @PatchMapping(value = "/task")
    public Task editTask(@Valid @RequestBody UpdateTaskDto dto) {
        log.info("requested to PATCH /tasks with body '{}'", dto);
        return taskService.editTask(dto);
    }

    @DeleteMapping(value = "/task/{uuid}")
    public Task deleteTask(@PathVariable UUID uuid) {
        log.info("requested to DELETE /tasks/{uuid} with uuid '{}'", uuid);
        return taskService.deleteTask(uuid);
    }

}
