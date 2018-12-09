public class Student {
	private String StudentID, Name;
	// String[] qualification = new String[0];
	public void Student() {
		this.StudentID = null;
		this.Name = null;
	}
	public void Student(String id, String name) {
		this.StudentID = id;
		this.Name = name;
	}
	public void set(String id, String name) {
		this.StudentID = id;
		this.Name = name;
	}
	public void print() {
		System.out.println("Student = " + StudentID + Name);
	}
}