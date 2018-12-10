public class Course {
	private String ID, Title;
	private int TotalMarks;
	// String[] qualification = new String[0];
	public void Course() {
		this.ID = null;
		this.Title = null;
		this.TotalMarks = 0;
	}
	public void Course(String id, String Title, int marks) {
		this.ID = id;
		this.Title = Title;
		this.TotalMarks = marks;
	}
	public void set(String id, String Title, int marks) {
		this.ID = id;
		this.Title = Title;
		this.TotalMarks = marks;
	}
	public void print() {
		System.out.println("Course = " + ID + " " + Title + " " + TotalMarks);
	}
	public String getTitle(){
		return Title;
	}
	public int getMarks(){
		return TotalMarks;
	}
}