import java.util.*;

public class HumanResources {
    private static List<Staff> staffs;
    private static List<Department> departments;
    static Scanner inpN = new Scanner(System.in);
    static Scanner inpS = new Scanner(System.in);
    static int type;

    public static void main(String[] args) {
        init();
        String back = "y";
        do {
            menu();
            type = inpN.nextInt();
            while (type < 1 || type > 8) {
                System.out.print("Vui lòng nhập lại: ");
                type = inpN.nextInt();
            }
            switch (type) {
                case 1 -> {
                    displayStaff();
                    backMenu();
                }
                case 2 -> {
                    displayDepartment();
                    backMenu();
                }
                case 3 -> back = displayDepartmentStaff();
                case 4 -> back = addStaff();
                case 5 -> back = findStaff();
                case 6 -> {
                    displaySalaryDescending();
                    backMenu();
                }
                case 7 -> {
                    displaySalaryAscending();
                    backMenu();
                }
                default -> {
                }
            }
            if (!back.equalsIgnoreCase("y")) {
                type = 8;
            }
        }
        while (type != 8);
    }

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
        Staff nnCuong = new Employee("004", "Ngo Ngoc Cuong", 29, 3, "21/06", "hr", 30, 8);
        Staff vvTuan = new Employee("004", "Vu Van Tuan", 27, 3, "07/12", "hr", 30, 22);
        Staff nnHieu = new Manager("b01", "Nguyen Ngoc Hieu", 30, 5, "12/02", "bs", 30, "Business Leader");
        Staff ndManh = new Manager("b02", "Nong Duc Manh", 31, 5, "15/09", "bs", 30, "Project Leader");
        Staff mvDai = new Manager("t01", "Mai Van Dai", 34, 5, "02/11", "it", 30, "Technical Leader");
        Staff tqTrung = new Manager("p01", "Tran Quang Trung", 33, 5, "06/10", "hr", 30, "Project Leader");
        staffs.addAll(Arrays.asList(tvThang, tvThanh, hvQuang, nvNam, ntThuy, nnCuong, vvTuan, nnHieu, ndManh, mvDai, tqTrung));
        for (Staff staffSalary : staffs) {
            calSalary(staffSalary);
        }
    }

    private static void backMenu() {
        System.out.print("Ấn \"y\" để quay lại menu, ấn phím khác để đóng chương trình: ");
        String replay = inpS.nextLine();
        if (!replay.equalsIgnoreCase("y")) {
            type = 8;
        }
    }

    private static void displayStaff() {
        System.out.println();
        for (Staff staff : staffs) {
            // get department name
            String departmentName = getDepartmentName(staff.getDepart());
            staff.displayInformation(departmentName, false);
        }
    }

    private static String getDepartmentName(String departmentId) {
        for (Department depart : departments) {
            if (depart.getDepartmentId().equals(departmentId)) {
                return depart.getDepartmentName();
            }
        }
        return "";
    }

    private static void displayDepartment() {
        for (Department depart : departments) {
            System.out.println(depart.toString());
        }
    }

    private static String displayDepartmentStaff() {
        int select;
        String selectNew;
        do {
            System.out.println("1. Bộ phận HR");
            System.out.println("2. Bộ phận IT");
            System.out.println("3. Bộ phận Business");
            System.out.print(">> Nhập số tương ứng với bộ phận: ");
            select = inpN.nextInt();
            while (select < 1 || select > 3) {
                System.out.print("Vui lòng nhập lại: ");
                select = inpN.nextInt();
            }
            switch (select) {
                case 1:
                    for (Staff staff : staffs) {
                        if (staff.getDepart().equalsIgnoreCase("hr")) {
                            // get department name
                            String departmentName = getDepartmentName(staff.getDepart());
                            staff.displayInformation(departmentName, false);
                        }
                    }
                    break;
                case 2:
                    for (Staff staff : staffs) {
                        if (staff.getDepart().equalsIgnoreCase("it")) {
                            // get department name
                            String departmentName = getDepartmentName(staff.getDepart());
                            staff.displayInformation(departmentName, false);
                        }
                    }
                    break;
                case 3:
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

    private static String addStaff() {
        String selectNew;
        do {
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Thêm quản lý");
            System.out.print(">> 1 or 2: ");
            int inputSelect = inpN.nextInt();
            while (inputSelect != 1 && inputSelect != 2) {
                System.out.print("Vui lòng nhập lại: ");
                inputSelect = inpN.nextInt();
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
            int inputAge = inpN.nextInt();
            System.out.print("Hệ số lương: ");
            int inputCoeSalary = inpN.nextInt();
            System.out.print("Ngày vào làm việc: ");
            String inputDateIn = inpS.nextLine();
            System.out.println("Bộ phận:");
            System.out.println("1. HR\n2. IT\n3. Business");
            System.out.print(">> Nhập số tương ứng: ");
            int inputDepartNum = inpN.nextInt();
            while (inputDepartNum != 1 && inputDepartNum != 2 && inputDepartNum != 3) {
                System.out.print("Vui lòng nhập lại: ");
                inputDepartNum = inpN.nextInt();
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
            int inputPaidLeave = inpN.nextInt();
            if (inputSelect == 1) {
                System.out.print("Số giờ làm thêm: ");
                int inputOverTime = inpN.nextInt();
                Staff newEmployee = new Employee(inputId, inputName, inputAge, inputCoeSalary, inputDateIn, inputDepart, inputPaidLeave, inputOverTime);
                staffs.add(newEmployee);
                calSalary(newEmployee);
            } else {
                System.out.println("Chức vụ:");
                System.out.println("1. Business Leader\n2. Project Leader\n3. Technical Leader");
                int inputPositionNum = inpN.nextInt();
                while (inputPositionNum != 1 && inputPositionNum != 2 && inputPositionNum != 3) {
                    inputPositionNum = inpN.nextInt();
                }
                String inputPosition = "";
                switch (inputPositionNum) {
                    case 1 -> inputPosition = "Business Leader";
                    case 2 -> inputPosition = "Project Leader";
                    case 3 -> inputPosition = "Technical Leader";
                    default -> {
                    }
                }
                Staff newManager = new Manager(inputId, inputName, inputAge, inputCoeSalary, inputDateIn, inputDepart, inputPaidLeave, inputPosition);
                staffs.add(newManager);
                calSalary(newManager);
            }
            System.out.print("Ấn \"b\" để chọn lại, ấn \"y\" để quay lại menu, ấn phím khác để đóng chương trình: ");
            selectNew = inpS.nextLine();
        } while (selectNew.equalsIgnoreCase("b"));
        return selectNew;
    }

    private static boolean isInvalidId(String check) {
        for (Staff staff : staffs) {
            if (staff.getId().equalsIgnoreCase(check)) {
                return true;
            }
        }
        return false;
    }

    private static void updateNumberOfStaff(String deptId) {
        for (Department dept : departments) {
            if (dept.getDepartmentId().equals(deptId)) {
                dept.setEmployeeNumber(dept.getEmployeeNumber() + 1);
            }
        }
    }

    private static void calSalary(Staff newStaff) {
        ICalculator calStaff = (ICalculator) newStaff;
        long salary = calStaff.calculateSalary();
        newStaff.setSalary(salary);
    }

    private static String findStaff() {
        String selectNew;
        do {
            System.out.print("Nhập vào ID hoặc tên để tìm kiếm: ");
            String inputIdOrName = inpS.nextLine();
            boolean isFound = false;
            for (Staff staff : staffs) {
                if (staff.getId().toLowerCase().contains(inputIdOrName.toLowerCase()) || staff.getName().toLowerCase().contains(inputIdOrName.toLowerCase())) {
                    // get department name
                    String departmentName = getDepartmentName(staff.getDepart());
                    staff.displayInformation(departmentName, true);
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Không tìm thấy!");
            }
            System.out.print("Ấn \"b\" để tìm lại, ấn \"y\" để quay lại menu, ấn phím khác để đóng chương trình: ");
            selectNew = inpS.nextLine();
        } while (selectNew.equalsIgnoreCase("b"));
        return selectNew;
    }

    private static void displaySalaryDescending() {
        staffs.sort(Comparator.comparingLong(Staff::getSalary));
        Collections.reverse(staffs);
        int index = 1;
        for (Staff staff : staffs) {
            System.out.print(index + ") ");
            staff.displaySalary();
            index++;
        }
    }

    private static void displaySalaryAscending() {
        staffs.sort(Comparator.comparingLong(Staff::getSalary));
        int index = 1;
        for (Staff staff : staffs) {
            System.out.print(index + ") ");
            staff.displaySalary();
            index++;
        }
    }
}