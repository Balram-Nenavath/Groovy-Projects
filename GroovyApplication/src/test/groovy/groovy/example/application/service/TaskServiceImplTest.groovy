package groovy.example.application.service

import static org.junit.jupiter.api.Assertions.*
import static org.mockito.BDDMockito.given
import static org.mockito.Mockito.*

import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import groovy.example.application.entity.Task
import groovy.example.application.exception.NoSuchTaskExistsException
import groovy.example.application.repository.TaskRepository
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {


	@InjectMocks
	TaskServiceImpl service

	@Mock
	TaskRepository Repo


	List<Task> list = new ArrayList<Task>()

	/*@BeforeEach
	 public void setUp() {
	 }*/

	@Test
	@Order(1)
	void findAll() throws Exception {

		Task t1 = new Task(23,"CSE","Chinna")
		Task t2 = new Task(22,"EEE","Abhi")
		Task t3 = new Task(25,"ECE","Amrit")
		list.add(t1)
		list.add(t2)
		list.add(t3)
		when(Repo.findAll()).thenReturn(list)
		List<Task> tList = service.findAll()
		assertEquals(3, tList.size())
		verify(Repo, times(1)).findAll()
	}


	@Test
	public void deleteById() {

		when(Repo.findById(11)).thenReturn(Optional.of(new Task(11,"BHEEM","NEWMOVIE")))
		service.deleteById(11)
		verify(Repo, times(1)).deleteById(11)

	}

	@Test
	public void deleteById_Error() {

		when(Repo.findById(11)).thenReturn(null)
		assertThrows(NoSuchTaskExistsException.class, {service.deleteById(11)})

	}

	@Test
	void findById() {


		when(Repo.findById(1)).thenReturn(Optional.of(new Task(1,"Lokesh","Gupta")))
		Optional<Task> t = Optional.of(Repo.findById(1))
		verify(Repo, times(1)).findById(1)
	}

	@Test
	void findById_Error() throws Exception{


		when(Repo.findById(1)).thenReturn(null)
		assertThrows(NoSuchTaskExistsException.class, {service.findById(1)})
	}

	@Test
	void saveTask() throws Exception {

		Task t =  new Task(2,"RMF","KOLllood")
		service.saveTask(t)
		verify(Repo, times(1)).save(t)

	}



	@Test
	void updateTask() {
	/*	Task t = new Task(25,"ECE","Amrit")
		Optional<Task> opt = Optional.of(new Task(1,"Lokesh","Gupta"))
		when(Repo.findById(1)).thenReturn(opt)
		opt.get().setTitle(t.getTitle())
		opt.get().setDescription(t.getDescription())
		given(Repo.save(opt.get())).willReturn(opt.get())
		Task updatedTask = service.updateTask(t)
		assertEquals("ECE",updatedTask.getTitle())*/
				
				Task tr = new Task(235, "ECE", "Madhu")
				given(Repo.save(tr)).willReturn(tr)
				tr.setTitle("EEEE")
				tr.setDescription("IOIPP")
				Task ut = service.updateTask(tr)
				assertEquals(ut.getTitle(),"EEEE")
	}



	@Test
	void updateTask_Error() {
		Task t = new Task(25,"ECE","Amrit")

		when(Repo.findById(25)).thenReturn(null)
		assertThrows(NoSuchTaskExistsException.class, {service.updateTask(t)})
	}



	@Test
	void patchUpdateTask() {
		Optional<Task> t = Optional.of(new Task(235,"ECE","Amrit"))
		Task tr = new Task(235, "ECE", "Madhu")
		when(Repo.findById(235)).thenReturn(t)
		Map<String, Object> Map = new HashMap<String, Object>()
		Map.put("description", "Madhu")
		when(Repo.save(any())).thenReturn(tr)
		Task result = service.patchUpdateTask(235, Map)
		assertEquals("Madhu", result.getDescription().toString())

	}


	@Test
	void saveAllTask() {
		List<Task> slist = new ArrayList<>()

		Task t1 =  new Task(2,"RMF","KOLllood")
		Task t2 =  new Task(3,"kMF","KOLlpoed")
		slist.add(t1)
		slist.add(t2)
		service.saveAllTask(slist)
	}

	
	@Test
	void saveAllTask_Failed() {
		List<Task> slist = new ArrayList<>()

		Task t1 =  new Task(2,"RMF","KOLllood")
		Task t2 =  new Task(3,"kMF","KOLlpoed")
		slist.add(t1)
		slist.add(t2)
		when(Repo.saveAll(any())).thenThrow(RuntimeException.class)
		assertThrows(RuntimeException.class, {service.saveAllTask(slist)})
	}
	
	@Test
	void updateAllTask() {

		List<Task> list = new ArrayList<Task>()
		Task t1 =  new Task(2,"RMF","KOLllood")
		Task t2 =  new Task(3,"kMF","KOLlpoed")
		list.add(t1)
		list.add(t2)
		service.updateAllTask(list)
	}
	
	@Test
	void updateAllTask_Failed() {

		List<Task> list = new ArrayList<Task>()
		Task t1 =  new Task(2,"RMF","KOLllood")
		Task t2 =  new Task(3,"kMF","KOLlpoed")
		list.add(t1)
		list.add(t2)
	when(Repo.saveAll(any())).thenThrow(RuntimeException.class)
		assertThrows(RuntimeException.class, {service.updateAllTask(list)})
	}

	@Test
	void deleteAllTasks() {

		Integer[] ids = [1,2]
		service.deleteAllTasks(ids)
	}





}