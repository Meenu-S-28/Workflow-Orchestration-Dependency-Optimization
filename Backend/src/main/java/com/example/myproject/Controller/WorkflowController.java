package com.example.myproject.Controller;

import com.example.myproject.Model.Task;
//import com.example.myproject.Model.TaskDependency;
import com.example.myproject.Model.Workflow;
//import com.example.myproject.Service.TaskDependencyService;
//import com.example.myproject.Service.TaskService;
import com.example.myproject.Service.WorkflowService;
import com.example.myproject.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

    private final WorkflowService workflowService;


    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

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

}