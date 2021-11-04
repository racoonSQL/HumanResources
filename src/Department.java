public class Department {
    private String departmentId;
    private String departmentName;
    private int employeeNumber;

    public Department(String departmentId, String departmentName, int employeeNumber) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeNumber = employeeNumber;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * display information of department
     *
     * @return info string
     */
    @Override
    public String toString() {
        return String.format("%-15s%-15s%d", departmentId, departmentName, employeeNumber);
    }
}