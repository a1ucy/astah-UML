public class Executive extends Person{
	private int exeID;
	private double baseSalary;


	public Executive(String last, String first, String middle, int id, double sal) {
		super(last, first, middle);
		exeID = id;
		baseSalary = sal;

	}

	public int getID()
	{
		return exeID;

	}

	public double getBaseSalary()
	{
		return baseSalary;

	}

}