package groovy.example.application.service

import groovy.example.application.entity.Task
import org.springframework.security.core.userdetails.UserDetails

interface TaskService {
	List<Task> findAll()
	
	Task findById(int id)
	
	String saveTask(Task task)
	
	String updateTask(Task task)
	
	String deleteById(int id)
	
	String patchUpdateTask(int id, Map<String, Object> fields)
	
	List<Task> saveAllTask(List<Task> task)
	
	List<Task> updateAllTask(List<Task> task)
	
	Integer[] deleteAllTasks(Integer[] ids)
	
	UserDetails loadUserByUsername(String username)
}
