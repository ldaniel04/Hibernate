package view;

import library.IO;
import models.Department;

public class DepartmentView {
	
final String options =  """
			
			1. Create a department
			2. Show departments
			3. Update a department
			4. Delete a department
			-1. Exit
			
			""";
	
	
	public Integer getOption() {
		Integer option;
		
		IO.println(options);
		option = IO.readInt();
		return option;
		
		
	}
	
	public Department create() {
		
		IO.print("name: ?");
		String name = IO.readString();
		
		Department department = Department.builder().name(name).build();
		
		return department;
		
	}

}
