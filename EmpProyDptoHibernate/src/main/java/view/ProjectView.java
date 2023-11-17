package view;

import java.util.List;

import library.IO;
import models.Department;
import models.Project;

public class ProjectView {

	final String options =  """
			
			1. Create a project
			2. Show projects
			3. Update a project
			4. Delete a project
			5. FindByID
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
	
	public void readProject(Project project) {
		System.out.println(project);
		
	}
	
	public void show(List<Project> projects) {
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
        } else {
            System.out.println("List of Projects:");
            for (Project project : projects) {
                System.out.println(project.toString());
            }
        }
	}
	
	public Integer findProjectById() {
		IO.print("Clave ?: ");
		Integer clave = IO.readInt();
		
		return clave;
	}
	
	public Project updateProject(Project project) {
		String name;
		IO.println("Name?: ");
		name = IO.readString();

		
		//Worker can't change id!
		project.setName(name);
		
		return project;
	}
	
	
	
}
