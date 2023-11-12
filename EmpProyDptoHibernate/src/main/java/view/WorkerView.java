package view;

import library.IO;
import models.Worker;

public class WorkerView {

	
final String options =  """
			
			1. Create a worker
			2. Show workers
			3. Update a worker
			4. Delete a worker
			-1. Exit
			
			""";
	
	
	public Integer getOption() {
		Integer option;
		
		IO.println(options);
		option = IO.readInt();
		return option;
		
		
	}
	
	public Worker create() {
		
		IO.print("Name ?: ");
		String name = IO.readString();
		IO.print("Salary: ? ");
		Double salary = IO.readDouble();
		
		Worker worker = Worker.builder().name(name).salary(salary).build();
		
		return worker;
		
		
	}
}
