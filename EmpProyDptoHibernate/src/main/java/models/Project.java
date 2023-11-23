package models;

import java.util.HashSet;
import java.util.Objects;
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
	@Builder.Default
	@ManyToMany(mappedBy = "projects")
	private Set<Worker> workers = new HashSet<Worker>();;

	/**
	 * Asocia este proyecto con un trabajador específico. Agrega este proyecto a
	 * la coleccion de trabajadores asociados a un proyecto dado y agrega ese
	 * trabajador a la coleccion de proyectos asociados a este proyecto
	 * 
	 * @param project
	 */
	public void addProjectToWorker(Worker worker) {
		this.getWorkers().add(worker);
		worker.getProjects().add(this);
	}
	
	@Override
	public String toString(){
		return id + "\t" + name;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
