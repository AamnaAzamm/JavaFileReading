import java.io.*;
import java.util.*;

public class textFileReader {
    public static void main(String [] args) {
        String fileName = "test.txt";
        String line, id, name;
			line = id = name = null;

		Boolean addTeachers, addStudents, addCourses, addResult;
		addTeachers = addStudents = addCourses = addResult = false;
		
		// String[] qualification = new String[0];

		int marks = 0;
		int stMarks = 0;
		int stCourse = -1;
		int index = -1;
		int resIndex = -1;
		
		List<Teacher> teachers = new ArrayList<Teacher>();
        List<Course> courses = new ArrayList<Course>();
        List<Student> students = new ArrayList<Student>();
		List<Result> results = new ArrayList<Result>();
		List<String> qualifications = new ArrayList<String>();
		List<Integer> stTeacher = new ArrayList<Integer>();
		List<Integer> stStudent = new ArrayList<Integer>();
		
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
							resIndex = index = -1;
						}
						else if (line.contains("Students:")){
							addTeachers = addCourses = addResult = false;
							addStudents = true;
							resIndex = index = -1;
						}
						else if (line.contains("Courses:")){
							addTeachers = addStudents = false;
							addCourses = true;
							resIndex = index = -1;
						}
						else{
							if (line.contains("\t\t\t\t")) {
								String [] parts = line.split(": ");
								if(line.contains("Course")){
									String [] subs = line.split("/");
									stCourse = Integer.parseInt(subs[2]);
								}
								else if(line.contains("Instructors")){
									String [] subs = line.split("/");
									for(int i=1; i<subs.length; i=i+2){
										String str = subs[i+1];
										str = str.replaceAll("]$","");
										str = str.replaceAll(", $","");
										
										if(subs[i].contains("Teachers")){
											stTeacher.add(Integer.parseInt(str));
										}
										else if(subs[i].contains("Students")){
											stStudent.add(Integer.parseInt(str));
										}
									}
								}
								else if(line.contains("Marks")){
									stMarks = Integer.parseInt(parts[1]);
								}
							}
							else if (line.contains("\t\t\t")) {
								resIndex++;
								// System.out.println("resIndex: " + resIndex + " index: " + index + line);
								if(resIndex >= 1){
									if(addResult && (stTeacher.size()!= 0 || stStudent.size() != 0)){
										Result result = new Result();
										result.set(stCourse, stMarks, stTeacher, stStudent);
										results.add(result);
										stTeacher.clear();
										stStudent.clear();
									}
								}
							}
							else if (line.contains("\t\t")) {
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
								else if(line.contains("ExamResults:")){
									addResult = true;
								}
							}
							else if (line.contains("\t")) {
								index++ ;
								if(index >=1){
									if (addTeachers){
										Teacher teacher = new Teacher();
										teacher.set(id,name, qualifications);
										teachers.add(teacher);
										qualifications.clear();										
									}
									else if (addCourses){
										Course course = new Course();
										course.set(id,name, marks);
										courses.add(course);
									}
									else if (addStudents){
										if(addResult  && (stTeacher.size()!= 0 || stStudent.size() != 0)){
											Result result = new Result();
											result.set(stCourse, stMarks, stTeacher, stStudent);
											results.add(result);
											stTeacher.clear();
											stStudent.clear();
										}
										Student student = new Student();
										student.set(id, name, results);
										students.add(student);
										stTeacher.clear();
										stStudent.clear();
										results.clear();
										addResult=false;
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
						student.set(id,name, results);
						students.add(student);
						stTeacher.clear();
						stStudent.clear();
						results.clear();
						addResult = false;
					}
				}
            }   
            bufferedReader.close();
			// System.out.println("...");
			for(int i=0; i<students.size(); i++){
				//students.get(i).print();
				Student student = students.get(i);
				String studentName = student.getName();
				results = student.getResults();
				for(int j=0; j<results.size(); j++){
					Result result = results.get(j);
					int courseIndex = result.getCourse();
					Course course = courses.get(courseIndex-1);
					String courseTitle = course.getTitle();
					System.out.print("Student \"" + studentName + 
							"\" was taught the course \"" + courseTitle + "\"" + 
							"by the instructors: ");
					if(result.hasTeacherInstructors()){
						stTeacher.clear();
						stTeacher = result.getTeachers();
						for(int k =0; k<stTeacher.size(); k++){
							int teacherIndex = stTeacher.get(k);
							String teacherInstructor = teachers.get(teacherIndex-1).getName();
							System.out.print(teacherInstructor);
						}
					}
					if(result.hasStudentInstructors()){
						stStudent.clear();
						stStudent = result.getStudents();		
						for(int k =0; k<stStudent.size(); k++){
							int studentIndex = stStudent.get(k);
							String studentInstructor = students.get(studentIndex-1).getName();
							System.out.print(" and " + studentInstructor);
						}
					}
					System.out.print(". He ");
					int marksObtained = result.getMarks();
					int totalMarks = course.getMarks();
					double percentage = (marksObtained  * 100.0f) / totalMarks; 
					if(percentage >=60){
						System.out.print("\"passed\" ");
					}
					else{
						System.out.print("\"failed\" ");
					}
					System.out.print("the exam by getting a score of "+ marksObtained + " out of " + totalMarks + ".");
					System.out.println("");
					System.out.println("");
				}
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
