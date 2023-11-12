package view;

import library.IO;

public class MainView {
	
	
	static final String mainOptions = """
			
			1. Workers
			2. Departments
			3. Projects
			-1. End program
			""";
	
	public static Integer getOptionMainMenu() {
		
		Integer option = 0;
		
		IO.print(mainOptions);
		option = IO.readInt();
		
		return option;
		
		
	}

}
