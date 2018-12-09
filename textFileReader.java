import java.io.*;
import java.util.*;

public class textFileReader {
    public static void main(String [] args) {
        String fileName = "test.txt";
        String line, id, name;
		line = id = name = null;

		Boolean addTeachers, addStudents, addCourses;
		addTeachers = addStudents = addCourses = false;
		
		// String[] qualification = new String[0];

		int marks = 0;
		int index = -1;

		List<Teacher> teachers = new ArrayList<Teacher>();
        List<Course> courses = new ArrayList<Course>();
        List<Student> students = new ArrayList<Student>();
		List<String> qualifications = new ArrayList<String>();
		
        try {
            FileReader fileReader = 
                new FileReader(fileName);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                if(line.isEmpty() == false){
					if(line.charAt(0) != '%'){
						if (line.contains("Teachers:")){
							addTeachers = true;
							addStudents = addCourses = false;
							index = -1;
						}
						else if (line.contains("Students:")){
							addTeachers = addCourses = false;
							addStudents = true;
							index = -1;
						}
						else if (line.contains("Courses:")){
							addTeachers = addStudents = false;
							addCourses = true;
							index = -1;
						}
						else{
							if (line.contains("\t\t")) {
								String [] parts = line.split(": ");
								if(line.contains("ID")){
									id = parts[1];
								}
								else if(line.contains("Name") || line.contains("Title")){
									name = parts[1];
								}
								else if(line.contains("TotalMarks")){
									marks = Integer.parseInt(parts[1]);
								}
								else if(line.contains("Qualifications")){
									String qs = parts[1];
									qs.replaceAll("\\s+","");
									//System.out.println(qs);
									int prev = 2;
									for(int i=2; i<qs.length(); i++){
										if(qs.charAt(i) == ',' || qs.charAt(i) == ']'){
											String subs = qs.substring(prev, i);
											prev = i+2;
											qualifications.add(subs);
										}
									}
								}
								
								
							}
							else if (line.contains("\t")) {
								index++ ;
								if(index >=1){
									if (addTeachers == true){
										Teacher teacher = new Teacher();
										teacher.set(id,name, qualifications);
										teachers.add(teacher);
										qualifications.clear();
									}
									else if (addCourses == true){
										Course course = new Course();
										course.set(id,name, marks);
										courses.add(course);
									}
									else if (addStudents == true){
										Student student = new Student();
										student.set(id,name);
										students.add(student);
									}
								}
								
							}
						}
					}
				}
				else{
					if (addTeachers == true){
						Teacher teacher = new Teacher();
						teacher.set(id,name, qualifications);
						teachers.add(teacher);
						qualifications.clear();
					}
					else if (addCourses == true){
						Course course = new Course();
						course.set(id,name, marks);
						courses.add(course);
					}
					else if (addStudents == true){
						Student student = new Student();
						student.set(id,name);
						students.add(student);
					}
				}
            }   
            bufferedReader.close();
			for(int i=0; i<teachers.size(); i++){
				teachers.get(i).print();
			}
			System.out.println("...");
			for(int i=0; i<courses.size(); i++){
				courses.get(i).print();
			}
			System.out.println("...");
			for(int i=0; i<students.size(); i++){
				students.get(i).print();
			}
			
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
    }
}
