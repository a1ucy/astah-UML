import java.util.*;
public class Personnel {

	private ArrayList<Person> personList1;

	public Personnel() {
	   personList1 = new ArrayList<Person>();
	}

	public void addPersonnel(Person p)
	{
		personList1.add(p);
	}

	public ArrayList<Person> getPersonList()
	{
		return personList1; 
	}

}