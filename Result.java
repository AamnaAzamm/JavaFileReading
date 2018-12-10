import java.util.*;
public class Result {
	private int course, marks;
	private List<Integer> teachers, students;
	public void Result() {
		this.course = 0;
		this.marks = 0;
	}
	public void Result(int course, int marks, List<Integer> listTeachers, List<Integer> listStudents) {
		this.course = course;
		this.marks = marks;
		this.teachers = new ArrayList<Integer>(listTeachers);
		this.students = new ArrayList<Integer>(listStudents);
	}
	public void set(int course, int marks, List<Integer> listTeachers, List<Integer> listStudents) {
		this.course = course;
		this.marks = marks;
		this.teachers = new ArrayList<Integer>(listTeachers);
		this.students = new ArrayList<Integer>(listStudents);	
	}
	public void print() {
		System.out.println("Result = Course index: " + course + " Marks:" + marks);
		System.out.println("Teacher Instructors = ");
		for(int i=0; i<teachers.size(); i++){
				System.out.println(teachers.get(i));
		}
		System.out.println("Student Instructors = ");
		for(int i=0; i<students.size(); i++){
				System.out.println(students.get(i));
		}
	}
	public int getCourse(){
		return course;
	}
	public Boolean hasTeacherInstructors(){
		return (teachers.size() != 0);
	}
	public Boolean hasStudentInstructors(){
		return (students.size() != 0);
	}
	public List<Integer> getTeachers(){
		return teachers;
	}
	public List<Integer> getStudents(){
		return students;
	}
	public int getMarks(){
		return marks;
	}
}