package groovy.example.application.repository

import groovy.example.application.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository extends JpaRepository<Task,Integer> {

	List<Task> findAll()
	
	Task findById(int id)
	
	Task save(Task task)
	
	}

