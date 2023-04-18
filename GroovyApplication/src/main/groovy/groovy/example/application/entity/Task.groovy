package groovy.example.application.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import groovy.transform.ToString


@Entity
@Table(name="tasks")
@JsonIgnoreProperties(["metaClass"])
//@ToString
class Task {
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + "]"
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id
	
	@NotNull(message = "Title cannot be null")
	@Column(nullable=false)
	String title
	
	public Task() {
		super()
		// TODO Auto-generated constructor stub
	}

	public Task(int id, @NotNull(message = "Title cannot be null") String title,
			@NotBlank(message = "Description is mandatory") String description) {
		super()
		this.id = id
		this.title = title
		this.description = description
	}

	@NotBlank(message = "Description is mandatory")
	@Column(nullable=false)
	String description

	@Override
	public int hashCode() {
		return Objects.hash(description, id, title)
	}


	public int getId() {
		return id
	}

	public void setId(int id) {
		this.id = id
	}

	public String getTitle() {
		return title
	}

	public void setTitle(String title) {
		this.title = title
	}

	public String getDescription() {
		return description
	}

	public void setDescription(String description) {
		this.description = description
	}
}

