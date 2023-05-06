package com.task.management.workspace;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
class WorkspaceController {
	

	@Autowired
	private WorkspaceRepository workspaceRepository;
	
	@GetMapping("/workspaces")
	public List<Workspace> getAllWorkspaces() {
		System.out.println("getAllWorkspaces");
		return workspaceRepository.findAll();
	}		
	
	@PostMapping("/workspace")
	public Workspace createWorkspace(@RequestBody Workspace workspace) {
		System.out.println("createWorkspace");
		return workspaceRepository.save(workspace);
	}
	
	@GetMapping("/workspace/{id}")
	public ResponseEntity<Workspace> getWorkspaceById(@PathVariable String id) {
		System.out.println("getWorkspaceById");
		Workspace workspace = workspaceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Workspace not exist with id :" + id));
		return ResponseEntity.ok(workspace);
	}
		
	@PutMapping("/workspace/{id}")
	public ResponseEntity<Workspace> updateWorkspace(@PathVariable String id, @RequestBody Workspace workspaceDetails){
		System.out.println("updateWorkspace");
		Workspace workspace = workspaceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Workspace not exist with id :" + id));
		
		workspace.setName(workspaceDetails.getName());
		workspace.setPassword(workspaceDetails.getPassword());
		System.out.println(workspaceDetails.getPassword());
		Workspace updatedWorkspace = workspaceRepository.save(workspace);
		return ResponseEntity.ok(updatedWorkspace);
	}
	
	@DeleteMapping("/workspace/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteWorkspace(@PathVariable String id){
		System.out.println("deleteWorkspace");
		Workspace workspace = workspaceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Workspace not exist with id :" + id));
		
		workspaceRepository.delete(workspace);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
