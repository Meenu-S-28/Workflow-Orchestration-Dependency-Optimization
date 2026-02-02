package com.example.myproject.Controller;

import com.example.myproject.Model.Task;
import com.example.myproject.Model.TaskDependency;
import com.example.myproject.Model.Workflow;
import com.example.myproject.Service.TaskDependencyService;
import com.example.myproject.Service.TaskService;
import com.example.myproject.Service.WorkflowService;
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

    @PostMapping
    public Workflow createWorkflow(@RequestBody Workflow workflow) {
        return workflowService.createWorkflow(workflow);
    }


    @GetMapping
    public List<Workflow> getAllWorkflows() {
        return workflowService.getAllWorkflows();
    }


    @PostMapping("/{workflowId}/tasks")
    public Task addTask(@PathVariable Long workflowId,
                        @RequestBody Task task) {
        return taskService.createTask(workflowId, task);
    }

    @GetMapping("/{workflowId}/tasks")
    public List<Task> getTasks(@PathVariable Long workflowId) {
        return taskService.getTasksByWorkflow(workflowId);
    }

    @PostMapping("/{workflowId}/dependencies")
    public TaskDependency addDependency(@RequestBody Long sourceTaskID,
                                        @RequestBody Long targetTaskID,@PathVariable Long workflowId){
        return taskDependencyService.addDependency(sourceTaskID, targetTaskID ,workflowId);
    }

    @GetMapping("/{workflowId}/dependencies")
    public List<TaskDependency> getAllDependency(@PathVariable Long workflowId){
        return taskDependencyService.getAllDependency(workflowId);
    }

}