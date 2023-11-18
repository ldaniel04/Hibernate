package models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
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
	private Set<Project> projects = new HashSet<Project>(); //look warning
	
	
	
	public void addBoss(Department depart) {
		
		depart.setBoss(this);
		
	}
	
	public void addWorkerToProject(Project project) {
		
		project.getWorkers().add(this);
	}
	
	
	@Override
	public String toString(){
		String print;
		if(depart == null) {
			print = id + ", " + name + ", " + salary + ", " + "sin departamento!";
		}else {
			print =  id + ", " + name + ", " + salary + ", " + depart.getName();
		}
		return print;
	}

}


