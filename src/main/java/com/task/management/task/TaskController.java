package com.task.management.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.management.group.Group;

@RestController
@RequestMapping("/api/v1/")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		System.out.println("getAllTasks");
		return this.taskRepository.findAll();
	}
	
	@GetMapping("/task/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable String taskId) {
		System.out.println("getTaskById");
		Task task = this.taskRepository.findById(taskId)
				.orElseThrow(() -> new RuntimeException("Task not exist with id :" + taskId));
		return ResponseEntity.ok(task);
	}
	

	@GetMapping("/tasks/{groupId}")
	public ResponseEntity<List<Task>> getTasksByGroup(@PathVariable String groupId) {
		System.out.println("getTasksByGroup");
		Task task = new Task();
		Group group = new Group();
		group.setId(groupId);
		task.setGroup(group);
		Example<Task> example = Example.of(task);
		return ResponseEntity.ok(this.taskRepository.findAll(example));
	}
	
	@PostMapping("/task")
	public Task createTask(@RequestBody Task task) {
		System.out.println("createTask");
		return this.taskRepository.save(task);
	}
	
	@PutMapping("/task")
	public ResponseEntity<Task> updateTask(@PathVariable String taskId, @RequestBody Task taskBody) {
		System.out.println("updateTask");
		Task task = this.taskRepository.findById(taskId)
				.orElseThrow(() -> new RuntimeException("Task not exist with id :" + taskId));
		task.setTitle(taskBody.getTitle());
		task.setColor(taskBody.getColor());
		return ResponseEntity.ok(this.taskRepository.save(task));
	}
	
	@DeleteMapping("/task/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable String taskId) {
		System.out.println("deleteTask");
		Task task = this.taskRepository.findById(taskId)
				.orElseThrow(() -> new RuntimeException("Task not exist with id :" + taskId));
		taskRepository.delete(task);
		Map<String, Boolean> response = new HashMap<>();
		response.put("delete", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
