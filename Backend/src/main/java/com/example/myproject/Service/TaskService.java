package com.example.myproject.Service;

import com.example.myproject.Model.Task;
import com.example.myproject.Model.Workflow;
import com.example.myproject.Repostiory.TaskRepository;
import com.example.myproject.Repostiory.WorkflowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepo;
    private final WorkflowRepository workflowRepo;

    public TaskService(TaskRepository taskRepo, WorkflowRepository workflowRepo) {
        this.taskRepo = taskRepo;
        this.workflowRepo = workflowRepo;
    }

    public Task createTask(Long workflowId, Task task) {

        Workflow workflow = workflowRepo.findById(workflowId)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));

        task.setWorkflow(workflow);
        task.setCreatedAt(LocalDate.now());

        return taskRepo.save(task);
    }

    public List<Task> getTasksByWorkflow(Long workflowId) {
        return taskRepo.findByWorkflowId(workflowId);
    }
}