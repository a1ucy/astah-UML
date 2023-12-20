public class PersonnelFactory {

    public enum PersonnelType {
        EMPLOYEE,
        VOLUNTEER,
        EXECUTIVE,
        SECURITY
    }

    public static Person createPersonnel(PersonnelType type, String last, String first, String middle, int id, double salary) {
        switch (type) {
            case EMPLOYEE:
                return new Employee(last, first, middle, id, salary);
            case VOLUNTEER:
                return new Volunteer(last, first, middle, id, salary);
            case EXECUTIVE:
                return new Executive(last, first, middle, id, salary);
            case SECURITY:
                return new Security(last, first, middle, id, salary);
            default:
                throw new IllegalArgumentException("Unknown personnel type");
        }
    }
}