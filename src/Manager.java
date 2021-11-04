import java.text.DecimalFormat;

public class Manager extends Staff implements ICalculator {
    private String position;

    public Manager(String id, String name, int age, int coeSalary, String dateIn, String depart, int paidLeave, String position) {
        super(id, name, age, coeSalary, dateIn, depart, paidLeave);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * display information of staff members
     *
     * @param departName name of department
     * @param isDisplaySalary display salary or not
     */
    @Override
    public void displayInformation(String departName, boolean isDisplaySalary) {
        System.out.printf("%-8s%-20s%-8d%-14d%-15s%-12s%-20d%-18s%-20s", this.getId(), this.getName(), this.getAge(), this.getCoeSalary(), this.getDateIn(), departName, this.getPaidLeave(), "NA", this.getPosition());
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
        // responsibility salary
        int responSalary = 0;
        if (position.equalsIgnoreCase("Business Leader")) {
            responSalary = 8000000;
        } else if (position.equalsIgnoreCase("Project Leader")) {
            responSalary = 5000000;
        } else if (position.equalsIgnoreCase("Technical Leader")) {
            responSalary = 6000000;
        }
        return this.getCoeSalary() * 5000000L + responSalary;
    }
}
