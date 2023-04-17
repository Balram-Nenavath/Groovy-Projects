package groovy.example.application.service

import java.lang.reflect.Field

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

import groovy.example.application.entity.Task
import groovy.example.application.exception.NoSuchTaskExistsException
import groovy.example.application.exception.TaskAlreadyExistsException
import groovy.example.application.repository.TaskRepository
import javax.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.util.ReflectionUtils

@Service
class TaskServiceImpl implements TaskService, UserDetailsService{
	
	@Autowired
	private final TaskRepository taskRepository

	@Override
	List<Task> findAll()
	{
		taskRepository.findAll()
	}

	@Override
	Task findById(int id)
	{
		taskRepository.findById(id)
	}

	@Override
	String saveTask(Task task)
	{
		def existingTask = findById(task.getId())
		if(existingTask==null) {
		taskRepository.save(task)
		return "Task added Successfully !"
		}
		else
		{
			throw new TaskAlreadyExistsException("Task already Exists !!")
		}
	}

	@Override
	String updateTask(Task task) {
		// TODO Auto-generated method stub
		def existingTask = findById(task.getId())
		if(existingTask == null)
		{
			throw new NoSuchTaskExistsException("No Such Task exists!!")
		}else {
			existingTask.with { et ->
				title = task.title
				description = task.description
			}
			taskRepository.save existingTask
			return "Task Updated Successfully !"
			//taskRepository.save(task)
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
		taskRepository.deleteAllById(temp)
	}

	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		//Logic to get the user form the Database

		return new User("balram","{noop}balram@1998",new ArrayList<>());
	}
	
}
