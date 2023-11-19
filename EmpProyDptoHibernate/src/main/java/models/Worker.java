package models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workers")

public class Worker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private Double salary;
	@ManyToOne
	private Department depart;
	@ManyToMany 
	@JoinTable(
		    name = "Worker_Project",
		    joinColumns = {@JoinColumn(name = "Worker_ID")},
		    inverseJoinColumns = {@JoinColumn(name = "Project_ID")}
		)
	private Set<Project> projects;  //look warning
	

	public void addBoss(Department depart) {
		
		depart.setBoss(this);
		
	}
	
	
	public Set<Project> getProjects() {
		 return this.projects = new HashSet<Project>();
	}
	
	public void addWorkerToProject(Project project) {
		this.getProjects().add(project);
		project.getWorkers().add(this);
	}
	
	
	@Override
	public String toString(){
		String print;
		if(depart == null) {
			print = id + "\t" + name + "\t" + salary + "\t" + "sin departamento!";
		}else {
			print =  id + "\t" + name + "\t" + salary + "\t" + depart.getName();
		}
		return print;
	}

}


