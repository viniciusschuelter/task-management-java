package com.task.management.group;

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

import com.task.management.workspace.Workspace;


@RestController
@RequestMapping("/api/v1/")
public class GroupController {
	
	@Autowired
	private GroupRepository groupRepository;
	
	@GetMapping("/groups")
	public List<Group> getAllGroups() {
		System.out.println("getAllGroups");
		return groupRepository.findAll();
	}
	
	@GetMapping("/group/{id}")
	public ResponseEntity<Group> getGroupById(@PathVariable String id) {
		System.out.println("getGroupById");
		Group group = groupRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Group not exist with id :" + id));
		return ResponseEntity.ok(group);
	}
	
	@GetMapping("/groups/{workspaceId}")
	public ResponseEntity<List<Group>> getGroupsByWorkspace(@PathVariable String workspaceId) {
		System.out.println("getGroupsByWorkspace");
		Group group = new Group();  
		Workspace workspace = new Workspace();     
		workspace.setId(workspaceId);
		group.setWorkspace(workspace);                          
		Example<Group> example = Example.of(group);
		return ResponseEntity.ok(groupRepository.findAll(example));
	}
	
	@PostMapping("/group")
	public Group createGroup(@RequestBody Group group) {
		System.out.println("createGroup");
		System.out.println(group.getWorkspace());
		return groupRepository.save(group);
	}
	
	
	@PutMapping("/group")
	public ResponseEntity<Group> updateGroup(@PathVariable String id, @RequestBody Group groupBody){
		System.out.println("updateGroup");
		Group group = groupRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Group not exist with id :" + id));
		group.setTitle(groupBody.getTitle());
		group.setColor(groupBody.getColor());
		return ResponseEntity.ok(groupRepository.save(group));
	}
	

	@DeleteMapping("/group/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteGroup(@PathVariable String id) {
		System.out.println("deleteGroup");
		Group group = groupRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Group not exist with id :" + id));
		groupRepository.delete(group);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
