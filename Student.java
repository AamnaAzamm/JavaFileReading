import java.util.*;

public class Student {
	private String StudentID, Name;
	List<Result> results;
	// String[] qualification = new String[0];
	public void Student() {
		this.StudentID = null;
		this.Name = null;
	}
	public void Student(String id, String name, List<Result> list) {
		this.StudentID = id;
		this.Name = name;
		this.results = new ArrayList<Result>(list);
	}
	public void set(String id, String name, List<Result> list) {
		this.StudentID = id;
		this.Name = name;
		this.results = new ArrayList<Result>(list);
	}
	public void print() {
		System.out.println("Student = " + StudentID + Name);
		for(int i=0; i<results.size(); i++){
				results.get(i).print();
		}
	}
	public List<Result> getResults(){
		return results;
	}
	public String getName(){
		return Name;
	}
}