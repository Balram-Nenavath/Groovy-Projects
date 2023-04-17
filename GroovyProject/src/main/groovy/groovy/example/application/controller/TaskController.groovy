package groovy.example.application.controller


import groovy.example.application.entity.Task
import groovy.example.application.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@RestController
@RequestMapping('/tasks')
class TaskController {
	
	@Autowired
	private final TaskService taskService


	@Autowired
	private AuthenticationManager authenticationManager

	

	
	@RequestMapping("/login-check")
	public String home() {
		return 'Spring JWT Demo Groovy Example!!'
	}
	
	//Get All Task Details
	@RequestMapping(value='/findAll',method = RequestMethod.GET)
	List<Task> findAll()
	{
		taskService.findAll()
	}
	
	//Get Task Details by Id
	@RequestMapping(value='/find/{id}',method = RequestMethod.GET)
	Task findById(@PathVariable('id') int id)
	{
		taskService.findById id
	}
	
	//Add a New Task Details
	@RequestMapping(value='/add',method = RequestMethod.POST)
	String saveTask(@RequestBody Task task)
	{
		taskService.saveTask(task)
	}
	
	//Update Task Details
	@RequestMapping(value='/update',method = RequestMethod.PUT)
	String updateTask(@RequestBody Task task)
	{
		taskService.updateTask task
	}
	
	//Delete Task Details by Id
	@RequestMapping(value='/delete/{id}',method = RequestMethod.DELETE)
	String deleteById(@PathVariable('id') int id)
	{
		taskService.deleteById id
	}
	
	//Patch Update Task Details
	//@Operation(summary = "Patch a Task")
	@RequestMapping(value='/patchUpdate/{id}',method = RequestMethod.PATCH)
	String patchUpdateTask(@PathVariable('id') int id,@RequestBody Map<String, Object> fields)
	{
		taskService.patchUpdateTask id,fields
	}
	
	//----Bulk Add/Update/Delete-----
	
	//Add a New Task Details
	@RequestMapping(value='/addAll',method = RequestMethod.POST)
	String saveAllTask(@RequestBody List<Task> task)
	{
		taskService.saveAllTask(task)
		
	}
	
	//Update a New Task Details
	@RequestMapping(value='/updateAll',method = RequestMethod.PUT)
	String updateAllTask(@RequestBody List<Task> task)
	{
		taskService.updateAllTask(task)
		
	}
	
	@DeleteMapping("/deleteAll")
	void  deleteAllTasks(@RequestParam("id") @PathVariable Integer[] ids)
	{
		taskService.deleteAllTasks(ids);
	}
}
