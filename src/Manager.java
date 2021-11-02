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

    @Override
    public void displayInformation(String departName, boolean isDisplaySalary) {
        System.out.print(this.getId() + ", " + this.getName() + ", " + this.getAge() + ", " + this.getCoeSalary() + ", " + this.getDateIn() + ", " + departName + ", " + this.getPaidLeave() + ", " + this.getPosition());
        if (isDisplaySalary) {
            System.out.println(", " + this.getSalary());
        } else {
            System.out.println();
        }
    }

    @Override
    public long calculateSalary() {
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
