import java.util.*;
public class Teacher {
	private String StaffID, Name;
	private List<String> Qualifications;
	// String[] qualification = new String[0];
	public void Teacher() {
		this.StaffID = null;
		this.Name = null;
	}
	public void Teacher(String id, String name, List<String> list) {
		this.StaffID = id;
		this.Name = name;
		this.Qualifications = new ArrayList<String>(list);
	}
	public void set(String id, String name, List<String> list) {
		this.StaffID = id;
		this.Name = name;
		this.Qualifications = new ArrayList<String>(list);
	}
	public void print() {
		System.out.println("Teacher = " + StaffID + Name);
		for(int i=0; i<Qualifications.size(); i++){
				System.out.println(Qualifications.get(i));
		}
	}
	public String getName(){
		return Name;
	}
}