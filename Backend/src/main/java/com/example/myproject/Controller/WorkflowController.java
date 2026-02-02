package com.example.myproject.Controller;

import com.example.myproject.Model.Task;
//import com.example.myproject.Model.TaskDependency;
import com.example.myproject.Model.Workflow;
import com.example.myproject.Service.TaskDependencyService;
import com.example.myproject.Service.TaskService;
import com.example.myproject.Service.WorkflowService;
import com.example.myproject.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

    private final WorkflowService workflowService;
    private final TaskService taskService;
    private final TaskDependencyService taskDependencyService;

    public WorkflowController(WorkflowService workflowService,
                              TaskService taskService, TaskDependencyService taskDependencyService) {
        this.workflowService = workflowService;
        this.taskService = taskService;
        this.taskDependencyService = taskDependencyService;

    }

    //WORKFLOW MAPPINGS

    @PostMapping
    public Workflow createWorkflow(@RequestBody Workflow workflow) {
        return workflowService.createWorkflow(workflow);
    }


    @GetMapping
    public List<Workflow> getAllWorkflows() {
        return workflowService.getAllWorkflows();
    }

    @GetMapping("/{workflowId}")
    public Workflow getByIdWorkflows(@PathVariable Long workflowId) {
        return workflowService.getByIdWorkflows(workflowId);
    }

    @DeleteMapping("/{workflowId}")
    public void deleteWorkflows(@PathVariable Long workflowId) {
        workflowService.deleteWorkflows(workflowId);
    }

    @PutMapping("/{workflowId}")
    public Workflow updateWorkflowsPut(@PathVariable Long workflowId, @RequestBody WorkflowUpdateDTO dto){
        return workflowService.updateWorkflows(workflowId, dto);
    }

    @PatchMapping("/{workflowId}")
    public Workflow updateWorkflowsPatch(@PathVariable Long workflowId, @RequestBody WorkflowUpdateDTO dto){
        return workflowService.updateWorkflows(workflowId, dto);
    }

    //TASK MAPPINGS

    @PostMapping("/{workflowId}/tasks")
    public Task addTask(@PathVariable Long workflowId,
                        @RequestBody Task task) {
        return taskService.createTask(workflowId, task);
    }

    @GetMapping("/{workflowId}/tasks")
    public List<TaskResponseDTO> getTasks(@PathVariable Long workflowId) {
        return taskService.getTasksByWorkflow(workflowId);
    }

    @GetMapping("/{workflowId}/tasks/{taskId}")
    public TaskResponseDTO getByTaskId(@PathVariable Long taskId) {
        return taskService.getByTaskId(taskId);
    }

    @DeleteMapping("/{workflowId}/tasks/{taskId}")
    public void deletedTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping("/{workflowId}/tasks/{taskId}")
    public TaskResponseDTO updateTasksPut(@PathVariable Long taskId, @RequestBody TaskUpdateDTO dto){
        return taskService.updateTasks(taskId, dto);
    }

    @PatchMapping("/{workflowId}/tasks/{taskId}")
    public TaskResponseDTO updateTasksPatch(@PathVariable Long taskId, @RequestBody TaskUpdateDTO dto){
        return taskService.updateTasks(taskId, dto);
    }

    // DEPENDENCY MAPPINGS
    @PostMapping("/{workflowId}/dependencies")
    public TaskDependencyResponseDTO addDependency(@RequestBody TaskDependencyRequestDTO taskDependencyRequestDTO,
                                                         @PathVariable Long workflowId){
        return taskDependencyService.addDependency(taskDependencyRequestDTO.getSourceTaskId(),
                taskDependencyRequestDTO.getTargetTaskId(),
                workflowId);

    }

    @GetMapping("/{workflowId}/dependencies")
    public List<TaskDependencyResponseDTO> getAllDependency(@PathVariable Long workflowId){
        return taskDependencyService.getAllDependency(workflowId);
    }

    @DeleteMapping("/{workflowId}/dependencies/{dependencyId}")
    public void deleteDependency(@PathVariable Long dependencyId) {
        taskDependencyService.deleteDependency(dependencyId);
    }

}