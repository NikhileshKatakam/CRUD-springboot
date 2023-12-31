package dev.nik.Crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping
	public List<Task> getAllTasks()
	{
		return taskRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable Long id)
	{
		return taskRepository.findById(id).orElse(null);
	}
	
	@PostMapping
	public Task createTask(@RequestBody Task task)
	{
		return taskRepository.save(task);
	}
	
	@PutMapping("/{id}")
	public Task updateTask(@PathVariable Long id, @RequestBody Task updatedtask)
	{
		Task existtask = taskRepository.findById(id).orElse(null);
		if(existtask != null)
		{
			existtask.setTitle(updatedtask.getTitle());
			existtask.setDescription(updatedtask.getDescription());
			return taskRepository.save(existtask);
		}
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable Long id)
	{
		taskRepository.deleteById(id);
	}
}
