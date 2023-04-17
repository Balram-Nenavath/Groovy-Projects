package groovy.example.application.service

import java.lang.reflect.Field

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

import groovy.example.application.entity.Airport
import groovy.example.application.entity.Task
import groovy.example.application.exception.NoSuchTaskExistsException
import groovy.example.application.exception.TaskAlreadyExistsException
import groovy.example.application.repository.TaskRepository
import javax.validation.Valid
import javax.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils
import org.springframework.web.client.RestTemplate

@Service
class TaskServiceImpl implements TaskService{

	@Value('${api-key}')
	String apikey
	@Value('${api-host}')
	String apiHost
	@Value('${airport-url}')
	String airportUrl
	
	@Autowired
	private RestTemplate template
	
	@Autowired
	TaskRepository taskRepository

	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class)
	
	@Override
	List<Task> findAll()
	{
		try {
			logger.info("Fetching all Tasks !")
			taskRepository.findAll()
		}
		catch(Exception e)
		{
			throw new NoSuchTaskExistsException("No Such Task exists!!"+e)
		}
	}

	@Override
	Task findById(int id)
	{
		try {
			logger.info("Fetching task with id : "+ taskRepository.findById(id).getId().toString())
			taskRepository.findById(id)
		}
		catch(Exception e)
		{
			throw new NoSuchTaskExistsException("No Such Task exists!!"+e)
		}
	}

	@Override
	String saveTask(@Valid Task task)
	{
		try {
		taskRepository.save(task)
		logger.info("Task Saving is in progress ...")
		return "Task added Successfully !"
		}
		catch(Exception e)
		{
			throw new TaskAlreadyExistsException("Task Already Exists!!"+e)
		}
	}

	@Override
	String updateTask(Task task) {
		Task existingTask  = taskRepository.findById(task.getId())
    	if (existingTask == null) {
    		throw new NoSuchTaskExistsException(
    				"No Such Task exists!!")
    	}
    	else {
			logger.info("Task updating is in progress ...")
    		existingTask.setTitle(task.getTitle())
    		existingTask.setDescription(task.getDescription())
    		taskRepository.save(existingTask)
    		return "Task updated Successfully"
    	}
	}

	@Override
	String deleteById(int id) {
		// TODO Auto-generated method stub
		def existingTask = taskRepository.findById(id)
		if(existingTask == null)
		{
			throw new NoSuchTaskExistsException("No Such Task exists!!")
		}else {
		taskRepository.deleteById(id)
		logger.info("Task deletion is in progress ..."+ id)
		return "Task deleted succesfully !"
	}
	}

	@Override
	public String patchUpdateTask(int id, Map<String, Object> fields) {

		def existingTask = findById(id)
		if(existingTask == null)
			{
				throw new NoSuchTaskExistsException("No Such Task exists!!")
			}
			else
			{
				logger.info("Task patch updating is in progress ...")
				existingTask.is(fields.each { key,value ->
			def field = ReflectionUtils.findField(Task.class, key)
			field.setAccessible(true)
			ReflectionUtils.setField(field, existingTask, value)
		})
		Task taskResp = taskRepository.save(existingTask)
		return "Task Patch Updated Successfully !"
	}
	}

	
	//------Bulk add/update/delete----------
	
	@Override
	@Transactional
	public List<Task> saveAllTask(List<Task> task) throws RuntimeException {
		List<Task> temp = new ArrayList<>()
		for(Task t : task) {
			if(t.getTitle()!=null && t.getDescription()!=null)
			{
				temp.add(t)
			}
			else
			{
				throw new RuntimeException("Exception in saving all tasks : Rolling Back")
			}
		}
		logger.info("Tasks saving is in progress ...")
		taskRepository.saveAll(temp)
		return temp
	}

	@Override
	@Transactional
	public List<Task> updateAllTask(List<Task> task) throws RuntimeException {
		List<Task> temp = new ArrayList<>()
		for(Task t : task) {
			if(t.getTitle()!=null && t.getDescription()!=null)
			{
				temp.add(t)
			}
			else
			{
				throw new RuntimeException("Exception in updating all tasks : Rolling Back")
			}
		}
		logger.info("Tasks updating is in progress ...")
		taskRepository.saveAll(temp)
		return temp
	}


	@Override
	@Transactional
	public Integer[] deleteAllTasks(Integer... ids) {
		List<Integer> temp = new ArrayList<>()
		for(Integer id : ids)
		{
			temp.add(id)
		}
		logger.info("Tasks deletion is in progress ...")
		taskRepository.deleteAllById(temp)
	}

	
/*	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		//Logic to get the user form the Database
		logger.info("Get the user form the Database ...")
		def user = new User("balram","{noop}balram@1998",new ArrayList<>())
		if (user == null) throw new UsernameNotFoundException(userName)
		return user

		//return new User("balram","{noop}balram@1998",new ArrayList<>())
	}*/


	@Override
	public String postDetailsofAirport(Airport air) {
		HttpHeaders headers = new HttpHeaders()
		headers.set("X-RapidAPI-Key",apikey)
		headers.set("X-RapidAPI-Host", apiHost)

		HttpEntity<?> requestEntity = new HttpEntity<>(air,headers)

		String url ="https://rapidapi.com/Active-api/api/airport-info"
		logger.info("Posting details of Airport !")
		ResponseEntity<String> response = template.postForEntity(url, requestEntity, String.class)
		
		//ResponseEntity<String> response = template.exchange(
			//	url, HttpMethod.POST, requestEntity, String.class)
		return response.getBody()
	}
	
	
	public String getDetailsofAirport(String iata, String icao)
	{
		HttpHeaders headers = new HttpHeaders()
		headers.set("X-RapidAPI-Key",apikey)
		headers.set("X-RapidAPI-Host", apiHost)

		HttpEntity<?> requestEntity = new HttpEntity<>(headers)

		String url=airportUrl+"iata="+iata+"&icao="+icao
		logger.info("Fetching details of Airport !")
		ResponseEntity<String> response = template.exchange(
				url, HttpMethod.GET, requestEntity, String.class)
		return response.getBody()
	}

}
