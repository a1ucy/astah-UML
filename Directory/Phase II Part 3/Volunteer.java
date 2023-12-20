public class Volunteer extends Person{
	private int volID;
    private double baseSalary;

	public Volunteer(String last, String first, String middle, int id, double sal) {
		super(last, first, middle);
		volID = id;
        baseSalary = 0;

	}

	public int getID()
	{
		return volID;

	}

    public double getBaseSalary()
    {
        return baseSalary;
    }

}