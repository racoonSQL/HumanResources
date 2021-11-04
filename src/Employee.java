import java.text.DecimalFormat;

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

    /**
     * display information of staff members
     *
     * @param departName name of department
     * @param isDisplaySalary display salary or not
     */
    @Override
    public void displayInformation(String departName, boolean isDisplaySalary) {
        System.out.printf("%-8s%-20s%-8d%-14d%-15s%-12s%-20d%-18d%-20s", this.getId(), this.getName(), this.getAge(), this.getCoeSalary(), this.getDateIn(), departName, this.getPaidLeave(), this.getOverTime(), "NA");
        if (isDisplaySalary) {
            // format before display
            DecimalFormat format = new DecimalFormat("#,###");
            String formattedSalary = format.format(this.getSalary());
            System.out.printf("%s%n", formattedSalary);
        } else {
            System.out.println();
        }
    }

    /**
     * calculate salary
     *
     * @return salary
     */
    @Override
    public long calculateSalary() {
        return this.getCoeSalary() * 3000000L + overTime * 200000L;
    }
}