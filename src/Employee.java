public class Employee extends Staff implements ICalculator {
    private int overTime;

    public Employee(String id, String name, int age, int coeSalary, String dateIn, String depart, int paidLeave, int overTime) {
        super(id, name, age, coeSalary, dateIn, depart, paidLeave);
        this.overTime = overTime;
    }

    public int getOverTime() {
        return overTime;
    }

    public void setOverTime(int overTime) {
        this.overTime = overTime;
    }

    @Override
    public void displayInformation(String departName, boolean isDisplaySalary) {
        System.out.print(this.getId() + ", " + this.getName() + ", " + this.getAge() + ", " + this.getCoeSalary() + ", " + this.getDateIn() + ", " + departName + ", " + this.getPaidLeave() + ", " + this.getOverTime());
        if (isDisplaySalary) {
            System.out.println(", " + this.getSalary());
        } else {
            System.out.println();
        }
    }

    @Override
    public long calculateSalary() {
        return this.getCoeSalary() * 3000000L + overTime * 200000L;
    }
}