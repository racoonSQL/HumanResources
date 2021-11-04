import java.text.DecimalFormat;

public abstract class Staff {
    private String id;
    private String name;
    private int age;
    private int coeSalary;
    private String dateIn;
    private String depart;
    private int paidLeave;
    private long salary;

    protected Staff(String id, String name, int age, int coeSalary, String dateIn, String depart, int paidLeave) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.coeSalary = coeSalary;
        this.dateIn = dateIn;
        this.depart = depart;
        this.paidLeave = paidLeave;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getCoeSalary() {
        return coeSalary;
    }

    public String getDateIn() {
        return dateIn;
    }

    public String getDepart() {
        return depart;
    }

    public int getPaidLeave() {
        return paidLeave;
    }

    public long getSalary() {
        return salary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCoeSalary(int coeSalary) {
        this.coeSalary = coeSalary;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setPaidLeave(int paidLeave) {
        this.paidLeave = paidLeave;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public abstract void displayInformation(String departName, boolean isisDisplaySalary);

    /**
     * display salary
     */
    public void displaySalary() {
        // format before display
        DecimalFormat format = new DecimalFormat("#,###");
        String formattedSalary = format.format(salary);
        System.out.printf("%-8s%-20s%s%n", id, name, formattedSalary);
    }
}