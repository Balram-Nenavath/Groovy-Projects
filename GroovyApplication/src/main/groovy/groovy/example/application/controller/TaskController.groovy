package groovy.example.application.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import groovy.example.application.entity.Airport
import groovy.example.application.entity.Task
import groovy.example.application.exception.ErrorResponse
import groovy.example.application.exception.TaskAlreadyExistsException
import groovy.example.application.service.TaskService
@RestController
@RequestMapping('/tasks')
class TaskController {

	@Autowired
	private final TaskService taskService


	@Autowired
	private AuthenticationManager authenticationManager
	
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class)
	

	@RequestMapping(value='/login-check',method = RequestMethod.GET)
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
	ResponseEntity<?> saveTask(@RequestBody Task task)
	{
		def t = taskService.saveTask(task)
		if(t!=null) {
			return new ResponseEntity<>(t,HttpStatus.OK);
		}

		return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//Update Task Details
	@RequestMapping(value='/update',method = RequestMethod.PUT)
	ResponseEntity<?> updateTask(@RequestBody Task task)
	{
		def t = taskService.updateTask task
		if(t!=null) {
			return new ResponseEntity<>(t,HttpStatus.OK);
		}

		return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
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
	ResponseEntity<?> saveAllTask(@RequestBody List<Task> task)
	{
		List<Task> t =taskService.saveAllTask(task)
		if(t!=null) {
			return new ResponseEntity<>(t,HttpStatus.OK)
		}

		return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR)
	}

	//Update a New Task Details
	@RequestMapping(value='/updateAll',method = RequestMethod.PUT)
	ResponseEntity<?> updateAllTask(@RequestBody List<Task> task)
	{
		List<Task> t= taskService.updateAllTask(task)
		if(t!=null) {
			return new ResponseEntity<>(t,HttpStatus.OK)
		}

		return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR)
	}

	@DeleteMapping("/deleteAll")
	String deleteAllTasks(@RequestParam("id") @PathVariable Integer[] ids)
	{
		taskService.deleteAllTasks(ids);
	}

	@PostMapping("/postAirport")
	public ResponseEntity<String> postAirportDetails(@RequestBody Airport air){
		String resp=taskService.postDetailsofAirport(air)
		return new ResponseEntity<>(resp,HttpStatus.OK)
	}


	@GetMapping("/airportTest")
	public ResponseEntity<String> getAirportDetails(@RequestParam(name = "iata") String iata, @RequestParam(name = "icao") String icao){
		String resp=taskService.getDetailsofAirport( iata,  icao)
		return new ResponseEntity<>(resp,HttpStatus.OK)
	}

	@ExceptionHandler(value	= TaskAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleTaskAlreadyExistsException(TaskAlreadyExistsException ex)
	{
		return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage())
	}
	

}
