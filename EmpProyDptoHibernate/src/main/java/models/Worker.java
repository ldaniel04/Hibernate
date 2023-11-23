package models;

import java.util.HashSet;
import java.util.Objects;
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
	@Builder.Default
	@ManyToMany
	@JoinTable(
		    name = "Worker_Project",
		    joinColumns = {@JoinColumn(name = "Worker_ID")},
		    inverseJoinColumns = {@JoinColumn(name = "Project_ID")}
		)
	private Set<Project> projects = new HashSet<Project>();

	public void addBoss(Department depart) {
		depart.setBoss(this);
	}

	/**
	 * Asocia este trabajador con un proyecto específico. Agrega este trabajador a
	 * la coleccion de trabajadores asociados a un proyecto dado y agrega ese
	 * proyecto a la coleccion de proyectos asociados a este trabajador
	 * 
	 * @param project
	 */
	public void addWorkerToProject(Project project) {
		project.getWorkers().add(this);
		this.getProjects().add(project);
	}

	@Override
	public String toString() {
		String print;
		if (depart == null) {
			print = id + "\t" + name + "\t" + salary + "\t" + "sin departamento!";
		} else {
			print = id + "\t" + name + "\t" + salary + "\t" + depart.getName();
		}
		return print;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Worker worker = (Worker) o;
		return Objects.equals(id, worker.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}


