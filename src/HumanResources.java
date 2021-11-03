import java.util.*;

public class HumanResources {
    private static List<Staff> staffs;
    private static List<Department> departments;
    static Scanner inpS = new Scanner(System.in);
    // select feature or quit
    static int type;

    public static void main(String[] args) {
        init();
        // back to menu if user choose "y"
        String back = "y";
        do {
            menu();
            type = inputNumber();
            while (type < 1 || type > 8) {
                System.out.print("Vui lòng nhập lại: ");
                type = inputNumber();
            }
            switch (type) {
                case 1:
                    displayStaff();
                    backMenu();
                    break;
                case 2:
                    displayDepartment();
                    backMenu();
                    break;
                case 3:
                    back = displayDepartmentStaff();
                    break;
                case 4:
                    back = addStaff();
                    break;
                case 5:
                    back = findStaff();
                    break;
                case 6:
                    displaySalaryDescending();
                    backMenu();
                    break;
                case 7:
                    displaySalaryAscending();
                    backMenu();
                    break;
                default:
                    break;
            }
            if (!back.equalsIgnoreCase("y")) {
                type = 8;
            }
        }
        while (type != 8);
    }

    /**
     * display menu
     */
    private static void menu() {
        System.out.println("  ****************************");
        System.out.println("1. Hiển thị danh sách nhân viên");
        System.out.println("2. Hiển thị danh sách bộ phận");
        System.out.println("3. Hiển thị danh sách nhân viên theo bộ phận");
        System.out.println("4. Thêm nhân viên mới vào công ty");
        System.out.println("5. Tìm kiếm thông tin nhân viên");
        System.out.println("6. Hiển thị bảng lương theo thứ tự giảm dần");
        System.out.println("7. Hiển thị bảng lương theo thứ tự tăng dần");
        System.out.println("8. Thoát chương trình");
        System.out.print(">> Nhập số tương ứng với chức năng: ");
    }

    /**
     * initial a few staff members
     * add them to 2 lists
     */
    private static void init() {
        staffs = new ArrayList<>();
        departments = new ArrayList<>();
        Department hr = new Department("hr", "HR", 3);
        Department it = new Department("it", "IT", 5);
        Department business = new Department("bs", "Business", 3);
        departments.add(hr);
        departments.add(it);
        departments.add(business);
        Staff tvThang = new Employee("001", "Tran Viet Thang", 25, 4, "22/04", "it", 30, 10);
        Staff tvThanh = new Employee("002", "Tran Viet Thanh", 26, 3, "10/05", "it", 30, 30);
        Staff hvQuang = new Employee("003", "Ho Vang Quang", 28, 2, "17/07", "it", 30, 20);
        Staff nvNam = new Employee("004", "Nguyen Van Nam", 22, 3, "20/09", "it", 30, 12);
        Staff ntThuy = new Employee("005", "Nguyen Thi Thuy", 20, 2, "08/05", "bs", 30, 26);
        Staff nnCuong = new Employee("006", "Ngo Ngoc Cuong", 29, 3, "21/06", "hr", 30, 8);
        Staff vvTuan = new Employee("007", "Vu Van Tuan", 27, 3, "07/12", "hr", 30, 22);
        Staff nnHieu = new Manager("b01", "Nguyen Ngoc Hieu", 30, 5, "12/02", "bs", 30, "Business Leader");
        Staff ndManh = new Manager("b02", "Nong Duc Manh", 31, 5, "15/09", "bs", 30, "Project Leader");
        Staff mvDai = new Manager("t01", "Mai Van Dai", 34, 5, "02/11", "it", 30, "Technical Leader");
        Staff tqTrung = new Manager("p01", "Tran Quang Trung", 33, 5, "06/10", "hr", 30, "Project Leader");
        staffs.addAll(Arrays.asList(tvThang, tvThanh, hvQuang, nvNam, ntThuy, nnCuong, vvTuan, nnHieu, ndManh, mvDai, tqTrung));
        // calculate salary of existing employees
        for (Staff staffSalary : staffs) {
            calSalary(staffSalary);
        }
    }

    /**
     * back to menu or quit
     */
    private static void backMenu() {
        System.out.print("Ấn \"y\" để quay lại menu, ấn phím khác để đóng chương trình: ");
        String replay = inpS.nextLine();
        if (!replay.equalsIgnoreCase("y")) {
            type = 8;
        }
    }

    /**
     * check if input is number or not
     *
     * @return number value
     */
    private static int inputNumber() {
        Scanner inpN = new Scanner(System.in);
        while (!inpN.hasNextInt()) {
            System.out.print("Vui lòng nhập giá trị là số: ");
            inpN.nextLine();
        }
        return inpN.nextInt();
    }

    /**
     * feature 1: display all staff members
     */
    private static void displayStaff() {
        System.out.printf("%-8s%-20s%-8s%-14s%-15s%-12s%-20s%-18s%-20s%n", "ID", "Tên", "Tuổi", "Hệ số lương", "Ngày vào làm", "Bộ phận", "Số ngày nghỉ phép", "Số giờ làm thêm", "Chức vụ");
        for (Staff employee : staffs) {
            // get department name
            String departmentName = getDepartmentName(employee.getDepart());
            employee.displayInformation(departmentName, false);
        }
    }

    /**
     * get department name to show
     *
     * @param departmentId department id
     * @return department name
     */
    private static String getDepartmentName(String departmentId) {
        for (Department depart : departments) {
            if (depart.getDepartmentId().equals(departmentId)) {
                return depart.getDepartmentName();
            }
        }
        return "";
    }

    /**
     * feature 2: display department info
     */
    private static void displayDepartment() {
        System.out.printf("%-15s%-15s%s%n", "Mã bộ phận", "Tên bộ phận", "Số lượng nhân viên");
        for (Department depart : departments) {
            System.out.println(depart.toString());
        }
    }

    /**
     * feature 3: display employees by department
     *
     * @return user choice
     */
    private static String displayDepartmentStaff() {
        // to choose again, back to menu, or quit
        String selectNew;
        do {
            System.out.println("1. Bộ phận HR");
            System.out.println("2. Bộ phận IT");
            System.out.println("3. Bộ phận Business");
            System.out.print(">> Nhập số tương ứng với bộ phận: ");
            // select department
            int select = inputNumber();
            while (select < 1 || select > 3) {
                System.out.print("Vui lòng nhập lại: ");
                select = inputNumber();
            }
            switch (select) {
                case 1:
                    System.out.printf("%-8s%-20s%-8s%-14s%-15s%-12s%-20s%-18s%-20s%n", "ID", "Tên", "Tuổi", "Hệ số lương", "Ngày vào làm", "Bộ phận", "Số ngày nghỉ phép", "Số giờ làm thêm", "Chức vụ");
                    for (Staff staff : staffs) {
                        if (staff.getDepart().equalsIgnoreCase("hr")) {
                            // get department name
                            String departmentName = getDepartmentName(staff.getDepart());
                            staff.displayInformation(departmentName, false);
                        }
                    }
                    break;
                case 2:
                    System.out.printf("%-8s%-20s%-8s%-14s%-15s%-12s%-20s%-18s%-20s%n", "ID", "Tên", "Tuổi", "Hệ số lương", "Ngày vào làm", "Bộ phận", "Số ngày nghỉ phép", "Số giờ làm thêm", "Chức vụ");
                    for (Staff staff : staffs) {
                        if (staff.getDepart().equalsIgnoreCase("it")) {
                            // get department name
                            String departmentName = getDepartmentName(staff.getDepart());
                            staff.displayInformation(departmentName, false);
                        }
                    }
                    break;
                case 3:
                    System.out.printf("%-8s%-20s%-8s%-14s%-15s%-12s%-20s%-18s%-20s%n", "ID", "Tên", "Tuổi", "Hệ số lương", "Ngày vào làm", "Bộ phận", "Số ngày nghỉ phép", "Số giờ làm thêm", "Chức vụ");
                    for (Staff staff : staffs) {
                        if (staff.getDepart().equalsIgnoreCase("bs")) {
                            // get department name
                            String departmentName = getDepartmentName(staff.getDepart());
                            staff.displayInformation(departmentName, false);
                        }
                    }
                    break;
                default:
                    break;
            }
            System.out.print("Ấn \"b\" để chọn lại, ấn \"y\" để quay lại menu, ấn phím khác để đóng chương trình: ");
            selectNew = inpS.nextLine();
        } while (selectNew.equalsIgnoreCase("b"));
        return selectNew;
    }

    /**
     * feature 4: add staff members
     *
     * @return user choice
     */
    private static String addStaff() {
        // to choose again, back to menu, or quit
        String selectNew;
        do {
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Thêm quản lý");
            System.out.print(">> 1 or 2: ");
            int inputSelect = inputNumber();
            while (inputSelect != 1 && inputSelect != 2) {
                System.out.print("Vui lòng nhập lại: ");
                inputSelect = inputNumber();
            }
            System.out.print("ID: ");
            String inputId = inpS.nextLine();
            while (isInvalidId(inputId)) {
                System.out.print("Vui lòng nhập lại: ");
                inputId = inpS.nextLine();
            }
            System.out.print("Tên: ");
            String inputName = inpS.nextLine();
            System.out.print("Tuổi: ");
            int inputAge = inputNumber();
            System.out.print("Hệ số lương: ");
            int inputCoeSalary = inputNumber();
            System.out.print("Ngày vào làm việc: ");
            String inputDateIn = inpS.nextLine();
            System.out.println("Bộ phận:");
            System.out.println("1. HR\n2. IT\n3. Business");
            System.out.print(">> Nhập số tương ứng: ");
            int inputDepartNum = inputNumber();
            while (inputDepartNum < 1 || inputDepartNum > 3) {
                System.out.print("Vui lòng nhập lại: ");
                inputDepartNum = inputNumber();
            }
            String inputDepart;
            if (inputDepartNum == 1) {
                inputDepart = "hr";
            } else if (inputDepartNum == 2) {
                inputDepart = "it";
            } else {
                inputDepart = "bs";
            }
            updateNumberOfStaff(inputDepart);
            System.out.print("Số ngày nghỉ phép: ");
            int inputPaidLeave = inputNumber();
            if (inputSelect == 1) {
                System.out.print("Số giờ làm thêm: ");
                int inputOverTime = inputNumber();
                Staff newEmployee = new Employee(inputId, inputName, inputAge, inputCoeSalary, inputDateIn, inputDepart, inputPaidLeave, inputOverTime);
                // add member to list
                staffs.add(newEmployee);
                // calculate salary for employee
                calSalary(newEmployee);
            } else {
                System.out.println("Chức vụ:");
                System.out.println("1. Business Leader\n2. Project Leader\n3. Technical Leader");
                int inputPositionNum = inputNumber();
                while (inputPositionNum < 1 || inputPositionNum > 3) {
                    inputPositionNum = inputNumber();
                }
                String inputPosition = "";
                switch (inputPositionNum) {
                    case 1:
                        inputPosition = "Business Leader";
                        break;
                    case 2:
                        inputPosition = "Project Leader";
                        break;
                    case 3:
                        inputPosition = "Technical Leader";
                        break;
                    default:
                        break;
                }
                Staff newManager = new Manager(inputId, inputName, inputAge, inputCoeSalary, inputDateIn, inputDepart, inputPaidLeave, inputPosition);
                // add member to list
                staffs.add(newManager);
                // calculate salary for manager
                calSalary(newManager);
            }
            System.out.print("Ấn \"b\" để chọn lại, ấn \"y\" để quay lại menu, ấn phím khác để đóng chương trình: ");
            selectNew = inpS.nextLine();
        } while (selectNew.equalsIgnoreCase("b"));
        return selectNew;
    }

    /**
     * check if ID already exists
     *
     * @param inputId ID from input
     * @return true or false
     */
    private static boolean isInvalidId(String inputId) {
        for (Staff staff : staffs) {
            // compare ID in Staff object with ID from input
            if (staff.getId().equalsIgnoreCase(inputId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * update number of staff members
     *
     * @param deptId department ID from input
     */
    private static void updateNumberOfStaff(String deptId) {
        for (Department dept : departments) {
            // if match ID in list, increase number
            if (dept.getDepartmentId().equals(deptId)) {
                dept.setEmployeeNumber(dept.getEmployeeNumber() + 1);
            }
        }
    }

    /**
     * calculate salary
     *
     * @param newStaff new member
     */
    private static void calSalary(Staff newStaff) {
        // casting Staff data type to ICalculator data type
        ICalculator calStaff = (ICalculator) newStaff;
        // calculate salary
        long salary = calStaff.calculateSalary();
        // set salary for new staff
        newStaff.setSalary(salary);
    }

    /**
     * add search results to new list
     *
     * @param input search input
     * @return matching list
     */
    private static List<Staff> listFound(String input) {
        // new list after search
        List<Staff> listFound = new ArrayList<>();
        for (Staff staff : staffs) {
            if (staff.getId().toLowerCase().contains(input.toLowerCase()) || staff.getName().toLowerCase().contains(input.toLowerCase())) {
                listFound.add(staff);
            }
        }
        return listFound;
    }

    /**
     * feature 5: find staff members
     *
     * @return user choice
     */
    private static String findStaff() {
        // to choose again, back to menu, or quit
        String selectNew;
        do {
            System.out.print("Nhập vào ID hoặc tên để tìm kiếm: ");
            String inputIdOrName = inpS.nextLine();
            if (listFound(inputIdOrName).size() != 0) {
                System.out.printf("%-8s%-20s%-8s%-14s%-15s%-12s%-20s%-18s%-20s%s%n", "ID", "Tên", "Tuổi", "Hệ số lương", "Ngày vào làm", "Bộ phận", "Số ngày nghỉ phép", "Số giờ làm thêm", "Chức vụ", "Lương");
                for (Staff staff : listFound(inputIdOrName)) {
                    // get department name
                    String departmentName = getDepartmentName(staff.getDepart());
                    staff.displayInformation(departmentName, true);
                }
            } else {
                System.out.println("Không tìm thấy!");
            }
            System.out.print("Ấn \"b\" để tìm lại, ấn \"y\" để quay lại menu, ấn phím khác để đóng chương trình: ");
            selectNew = inpS.nextLine();
        } while (selectNew.equalsIgnoreCase("b"));
        return selectNew;
    }

    /**
     * feature 6: display salary descending
     */
    private static void displaySalaryDescending() {
        // sort list
        staffs.sort(Comparator.comparingLong(Staff::getSalary));
        // reverse direction
        Collections.reverse(staffs);
        System.out.printf("%-8s%-8s%-20s%s%n", "Stt", "ID", "Tên", "Lương");
        // order number
        int index = 1;
        for (Staff staff : staffs) {
            System.out.printf("%-8d", index);
            staff.displaySalary();
            index++;
        }
    }

    /**
     * feature 7: display salary ascending
     */
    private static void displaySalaryAscending() {
        // sort list
        staffs.sort(Comparator.comparingLong(Staff::getSalary));
        System.out.printf("%-8s%-8s%-20s%s%n", "Stt", "ID", "Tên", "Lương");
        // order number
        int index = 1;
        for (Staff staff : staffs) {
            System.out.printf("%-8d", index);
            staff.displaySalary();
            index++;
        }
    }
}