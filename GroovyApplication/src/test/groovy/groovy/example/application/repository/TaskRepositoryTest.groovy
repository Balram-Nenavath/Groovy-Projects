package groovy.example.application.repository

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit.jupiter.SpringExtension


import static org.junit.jupiter.api.Assertions.*

import groovy.example.application.entity.Task
import org.assertj.core.api.Assertions
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskRepositoryTest {

	@Autowired
	TaskRepository repo
    @BeforeEach
    void setUp() {
		println 'Repo Test started'
    }

    @Test
    void findAll() {
		
		List<Task> t = repo.findAll()
		Assertions.assertThat(t.size(),6)
    }

    @Test
    void findById() {
		Task t = getTask()
		assert(t.getTitle().equals("RAMU"))
    }

    @Test
	@Rollback(false)
    void save() {
		Task t = getTask()
		Task t1= repo.save(t)
		assertTrue(t1.asBoolean())
		
    }
	
	private Task getTask() {
		Task t = new Task()
		t.setTitle("RAMU")
		t.setDescription("NONE")
		return t
	 }
	
	@AfterEach
	void tearDown() {
		println 'Repo Test completed'
	}

}