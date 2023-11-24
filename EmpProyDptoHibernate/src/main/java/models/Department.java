package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "departments")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@ManyToOne
	private Worker boss;
	
	public void addDepart(Worker worker) {
		
		worker.setDepart(this);
	}
	
	@Override
	public String toString(){
		String print;
		
		if(boss == null) {
			print = id + "\t\t" + name + "\t\t" + "Without Boss!";
		}else {
			print = id + "\t\t" + name + "\t\t" + boss.getName();
		}
		return print;
	}
	
}



