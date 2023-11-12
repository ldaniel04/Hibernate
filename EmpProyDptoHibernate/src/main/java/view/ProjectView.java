package view;

import library.IO;
import models.Project;

public class ProjectView {

	final String options =  """
			
			1. Create a project
			2. Show projects
			3. Update a project
			4. Delete a project
			-1. Exit
			""";
	
	
	public Integer getOption() {
		Integer option;
		
		IO.println(options);
		option = IO.readInt();
		return option;
		
		
	}
	
	
	public Project create() {
		
		IO.print("name: ?");
		String name = IO.readString();
		
		Project project = Project.builder().name(name).build();
		
		return project;
		
	}
	
	
	
}
