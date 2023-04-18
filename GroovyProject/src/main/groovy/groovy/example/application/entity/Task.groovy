package groovy.example.application.entity

import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull


@Entity
@Table(name="tasks")
class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id
	
	@NotNull
	@Column(nullable=false)
	String title
	
	@NotNull
	@Column(nullable=false)
	String description
}

