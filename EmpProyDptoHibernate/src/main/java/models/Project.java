package models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToMany(mappedBy = "projects") // need mapping by??
	private Set<Worker> workers; // Look warning

	public Set<Worker> getWorkers() {
		return this.workers = new HashSet<Worker>();
	}

	public void addProjectToWorker(Worker worker) {
		this.getWorkers().add(worker);
		worker.getProjects().add(this);

	}
	
	@Override
	public String toString(){
		return id + "\t" + name;
	}
}
