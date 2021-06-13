package com.vudqfx.humanresources;

import com.vudqfx.humanresources.department.Department;
import com.vudqfx.humanresources.department.Position;
import com.vudqfx.humanresources.employee.Employee;
import com.vudqfx.humanresources.manager.Manager;
import com.vudqfx.humanresources.staff.Staff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HumanResources {
    public static final String ERROR_MESSAGE = "Entered incorrect. Please re-enter! ";
    public static final String STRING_LINE = "---------------------------------------------------------------------" +
            "---------------------------------------------------------";

    public static final int MAX_NUMBER_999 = 999;
    public static final int MAX_NUMBER_100 = 100;

    public static Scanner input;
    public static ArrayList<Staff> staffList; // danh sách nhân viên
    public static ArrayList<Department> departments; // danh sachs phòng ban
    public static ArrayList<Position> positions; // danh sách chức vụ

    public static void main(String[] args) {
        input = new Scanner(System.in);

        staffList = new ArrayList<>();//khởi tạo mảng lưu thông tin nhân viên
        departments = new ArrayList<>(); // khởi tạo mảng lưu thông tin phòng ban
        positions = new ArrayList<>(); // khoi tao mang luu thong tin chuc vu

        createDepartment();//tạo danh sách phòng ban
        createPosition(); // tao danh sach cac chuc vu
        createStaff(); //tao danhh sach nhan vien

        boolean checkOut = false;

        do {
            int answer = questionMain();

            switch (answer){
                case 1: //hiển thị danh sách nhân viên trong công ty
                    System.out.println("1. Company staff information.");
                    showStaffList(staffList);
                    break;
                case 2: // hiển thị các bộ phận trong công ty
                    System.out.println("2. Department information.");
                    showDepartmentList();
                    break;
                case 3: // hiển thị các nhân viên theo từng bộ phận
                    System.out.println("3. List staff in each department.");
                    showStaffDepartmentList();
                    break;
                case 4: // thêm nhân viên mới
                    System.out.println("4. Add new staff.");
                    addStaff();
                    break;
                case 5: // tìm kiếm thông tin nhân viên
                    System.out.println("5. Search staff.");
                    searchInformationStaff();
                    break;
                case 6: // hiển thị bảng lương của nhân viên
                    System.out.println("6. List salary table.");
                    showSalaryTable(staffList);
                    break;
                case 7: // hiển thị bảng lương của nhân viên theo thứ tự giảm dần
                    System.out.println("7. Salary table sort in descending order.");
                    sortSalary(true);
                    showSalaryTable(staffList);
                    break;
                case 8: // hiển thị bảng lương của nhân viên theo thứ tự tăng dần
                    System.out.println("8. Salary table sort in ascending order.");
                    sortSalary(false);
                    showSalaryTable(staffList);
                    break;
                case 0: // thoát khỏi chương trình
                    checkOut = true;
                    break;
                default:

            }
        }
        while(!checkOut);
    }

    /* tạo phòng ban trong công ty */
    public static void createDepartment(){
        Department d1 = new Department(randomId(MAX_NUMBER_100, ""), "Business", 0);
        Department d2 = new Department(randomId(MAX_NUMBER_100, ""), "Project", 0);
        Department d3 = new Department(randomId(MAX_NUMBER_100, ""), "Technical", 0);
        departments.add(d1);
        departments.add(d2);
        departments.add(d3);
    }

    /* tạo danh sách các vụ */
    public static void createPosition(){
        Position p1 = new Position("1", "Business Leader", 8000000, "Business");
        Position p2 = new Position("2", "Project Leader", 5000000, "Project");
        Position p3 = new Position("3", "Technical Leader", 6000000, "Technical");
        positions.add(p1);
        positions.add(p2);
        positions.add(p3);
    }

    /* tạo mẫu danh sách nhân viên */
    public static void createStaff(){
        Staff st1 = new Employee(randomId(MAX_NUMBER_999, "E"), "Nguyen Van A", 26, 2.0,
                "20/10/2017", departments.get(0), 4, 2, 3000000);
        departments.get(0).setEmployee(departments.get(0).getEmployee() + 1);
        Staff st2 = new Employee(randomId(MAX_NUMBER_999, "E"), "Nguyen Van B", 25, 1.75,
                "20/10/2018", departments.get(0), 3, 4, 3000000);
        departments.get(0).setEmployee(departments.get(0).getEmployee() + 1);
        Staff st3 = new Manager(randomId(MAX_NUMBER_999, "E"), "Hong Hoa", 30, 3.2,
                5000000, "05/10/2015", departments.get(0), 2, positions.get(0));
        departments.get(0).setEmployee(departments.get(0).getEmployee() + 1);
        Staff st4 = new Employee(randomId(MAX_NUMBER_999, "E"), "Ly Anh", 33, 3.0,
                "24/07/2016", departments.get(1), 4, 4, 3000000);
        departments.get(1).setEmployee(departments.get(1).getEmployee() + 1);
        staffList.add(st1);
        staffList.add(st2);
        staffList.add(st3);
        staffList.add(st4);
    }

    /* CHUC NANG THEM NHAN VIEN MOI */
    /* phương thức thêm nhân viên mới */
    public static void addStaff(){
        boolean answer;
        do {
            System.out.println("- Add new staff.");
            System.out.println("1. Add Employee.      2. Add Manager.");
            System.out.print("- Do you want selection: ");
            int question = scannerInputInteger();
            switch (question) {
                case 1://them nhan vien binh thuong
                    addEmployee();
                    break;
                case 2: //them nhan vien la quan ly
                    addManager();
                    break;
                default:
                    //System.out.println("");

            }
            System.out.print("Do you want continue (y/yes) or other key to close? ");
            answer = question();
        }
        while(answer);
    }

    /* phương thức thêm nhân viên mới là nhân viên bình thường */
    public static void addEmployee(){
        System.out.println("1. Add Employee.");
        String id = createStaffId(MAX_NUMBER_999, "E");
        //System.out.println(id);
        System.out.print("- Enter employee name: ");
        String name = input.nextLine();
        System.out.print("- Enter employee age: ");
        int age = scannerInputInteger();
        Department department = choseDepartment();
        System.out.print("- Enter employee pay rate: ");
        double payRate = scannerInputDouble();
        System.out.print("- Enter employee base salary: ");
        double baseSalary = scannerInputDouble();
        String joinDay = checkScannerInputDay();
        System.out.print("- Number day off: ");
        int numDayOff = scannerInputInteger();
        System.out.print("- Over timer: ");
        int overtimeHour = scannerInputInteger();

        /* tạo nhân viên mới với thông tin được nhập từ bàn phím */
        Staff staffEmployee = new Employee(id, name, age, payRate, joinDay, department, numDayOff, overtimeHour, baseSalary);
        staffList.add(staffEmployee); //thêm nhân viên mới vào danh sách

    }

    /* phương thức thêm nhân viên mới là quản lý */
    public static void addManager(){
        System.out.println("2. Add Manager.");
        String id = createStaffId(MAX_NUMBER_999, "E");
        //System.out.println(id);
        System.out.print("- Enter manager name: ");
        String name = input.nextLine();
        System.out.print("- Enter manager age: ");
        int age = scannerInputInteger();
        Department department = choseDepartment();
        System.out.println("- Position: ");
        Position position = chosePosition(department.getName());
        System.out.print("- Enter manager pay rate: ");
        double payRate = scannerInputDouble();
        System.out.print("- Enter manager base salary: ");
        double baseSalary = scannerInputDouble();
        String joinDay = checkScannerInputDay();
        System.out.print("- Number day off: ");
        int numDayOff = scannerInputInteger();

        /* tạo nhân viên mới với thông tin được nhập từ bàn phím */
        Staff staffManager = new Manager(id, name, age, payRate, baseSalary, joinDay, department, numDayOff, position);

        staffList.add(staffManager); //thêm nhân viên mới vào danh sách
    }

    /*-------------------*/

    /* phương thức tìm nhân viên*/
    private static void searchInformationStaff(){
        System.out.print("- Enter keyword for search: ");
        String strSearch = input.nextLine();
        ArrayList<Staff> receiveStaffs = searchStaff(strSearch);
        if(receiveStaffs.size() > 0){
            System.out.println("- Find " + receiveStaffs.size() + " result with key word : " + strSearch);
            showSalaryTable(receiveStaffs);
        }else{
            System.out.println("- Do not find staff with key word: " + strSearch);
        }
    }

    /* hiển thị danh sách tất cả các nhân viên */
    public static void showStaffList(ArrayList<Staff> staffList){
        headerStaffTable("COMPANY STAFF INFORMATION");
        for(Staff st : staffList){
            st.displayInformation();
        }
        System.out.println("----------------------------------------------------------------------");
    }

    /* hiển thị danh sách các phòng ban */
    public static void showDepartmentList(){
        System.out.println("###DEPARTMENT INFORMATION###");
        System.out.println("---------------------------------");
        Formatter formatter = new Formatter();
        System.out.println(formatter.format("|%4s|%17s|%5s|", "ID", "Name", "Employee"));
        System.out.println("---------------------------------");

        for(Department department : departments){
            System.out.println(department.toString());
        }

        System.out.println("---------------------------------");
    }

    /* phương thức hiển thị bảng lương của nhân viên */
    private static void showSalaryTable(ArrayList<Staff> staffList){
        headerSalaryTable("SALARY TABLE OF STAFF");
        for(Staff st : staffList){
            System.out.println(st.toString());
        }
        System.out.println(STRING_LINE);
    }

    /* phương thức cho phép chọn chức vụ */
    public static Position chosePosition(String strPosition){
        // hien thi danh sach chuc vu
        showPositionList(strPosition);
        Position position;
        do{
            System.out.print("- Enter ID to chose position: ");
            String id = input.nextLine();
            int i = checkIndexPosition(id, strPosition);
            if(i > -1){
                position = positions.get(i);
                break;
            }
        }
        while (true);
        return position;
    }

    /* hiển thị danh sách chức vụ */
    private static void showPositionList(String strPosition) {
        for(Position p : positions){
            if(p.getKeywordDepartment().equals(strPosition)){
                System.out.print(p.getId() + ". " + p.getPosition() + "\t");
            }
        }
        System.out.println();
    }

    /* phương thức cho phép chọn phòng ban */
    public static Department choseDepartment(){
        showDepartmentList();
        Department department;
        do{
            System.out.print("- Enter ID to chose department: ");
            String id = input.nextLine();
            int i = checkDepartmentIdInt(id);
            //System.out.println(i);
            if(i > -1){
                department = departments.get(i);
                int j = departments.get(i).getEmployee() + 1;
                departments.get(i).setEmployee(j);
                //System.out.println(departments.get(i).getEmployee());
                break;
            }
        }
        while(true);

        return department;
    }

    /* phương thức hiển thị danh sách nhân viên theo phòng ban */
    public static void showStaffDepartmentList(){
        for(Department department : departments){
            if(department.getEmployee() > 0) {
                headerStaffTable("LIST STAFF IN EACH DEPARTMENT : " + department.getName());
                for (Staff staff : staffList) {
                    Department d = staff.getDepartment();
                    if (department.getId().equals(d.getId())) {
                        //hien thi danh sach nhan vien
                        staff.displayInformation();
                    }
                }
                System.out.println("----------------------------------------------------------------------");
                System.out.println("\n");
            }
        }
    }

    /* phương thức tìm nhân viên theo tên hoặc theo mã */
    public static ArrayList<Staff> searchStaff(String strSearch){
        ArrayList<Staff> receiveStaffs = new ArrayList<>();// mang luu danh sach tim thay
        strSearch = strSearch.toLowerCase();
        for(Staff staff : staffList){
            if(staff.getId().contains(strSearch) || staff.getName().toLowerCase().contains(strSearch)){
                receiveStaffs.add(staff);
            }
        }
        return receiveStaffs; // rta ve danh sach nhan vien tim thay
    }

    /* phương thức sắp xếp nhân viên theo bảng lương */
    private static void sortSalary(boolean decSort){
        //sử dụng Comparator với phương thức compare(Object obj1, Object obj2)
        // để so sánh đối tượng đầu tiên với đối tượng thức 2

        // cach 1:
        Comparator<Staff> ascending = new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2) {
                if(decSort){ // nếu decSort = true thì sắp xếp theo thứ tự giảm dần
                    return (int)(s2.calculateSalary() - s1.calculateSalary());
                }else { // ngược lại, sắp xếp theo thứ tự tăng dần
                    return (int) (s1.calculateSalary() - s2.calculateSalary());
                }
            }
        };
        staffList.sort(ascending);

        /*
        // cach 2:
        Comparator<Staff> asc = (s1, s2) -> (int)(s1.calculateSalary() - s2.calculateSalary());
        Collections.sort(staffList, asc);

        // cach 3: Sử dụng Comparator với biểu thức lambda
        staffList.sort((s1, s2) -> (int)(s1.calculateSalary() - s2.calculateSalary()));
         */
    }

    /* sắp xếp nhân viên theo tuổi */
    private static void sortAge(){

        Collections.sort(staffList, new Comparator<Staff>() {
            @Override
            public int compare(Staff staff, Staff t1) {
                return staff.getAge() > t1.getAge() ? 1 : -1;
            }
        });
    }

    /* hiển thị chức năng chính của chương trình */
    public static int questionMain(){
        System.out.println();
        System.out.println("1. Company staff information.");
        System.out.println("2. Department information.");
        System.out.println("3. List staff in each department.");
        System.out.println("4. Add new staff.");
        System.out.println("5. Search staff.");
        System.out.println("6. List salary table.");
        System.out.println("7. Salary table sort in ascending order.");
        System.out.println("8. Salary table sort in descending order.");
        System.out.println("0. Exit.");
        System.out.println();
        System.out.print("Enter a number to selection: ");
        return scannerInputInteger();
    }

    public static void headerSalaryTable(String title){
        Formatter formatter = new Formatter();
        System.out.println("###    "+title+"    ###");
        System.out.println(STRING_LINE);
        System.out.println(formatter.format("|%4s|%17s|%3s|%12s|%17s|%10s|%7s|%8s|%8s|%13s|%15s|", "ID"
                , "Name", "Age", "Department", "Position", "Start Date"
                , "Day off", "Overtime", "Pay Rate", "Base salary", "Salary"));
        System.out.println(STRING_LINE);
    }

    private static void headerStaffTable(String title) {
        Formatter formatter = new Formatter();
        System.out.println("###    "+title+"    ###");
        System.out.println("----------------------------------------------------------------------");
        System.out.println(formatter.format("|%4s|%17s|%3s|%12s|%17s|%10s|", "ID"
                , "Name", "Age", "Department", "Position", "Start Date"));
        System.out.println("----------------------------------------------------------------------");
    }

    /* phương thức tạo mã phòng ban */
    public static String createStaffId(int maxNumber, String sId){
        String id = "";
        boolean check = true;
        while(check){//true thì tiếp tục
            id = randomId(maxNumber, sId);// tạo mã ngẫu nhiên
            check = checkStaffId(id); // kiểm tra nếu trùng với mã đã tạo thì tạo lại
        }
        return id; // trả về mã phòng ban kiểu String
    }

    /* phương thức kiểm tra mã nhân viên */
    public static boolean checkStaffId(String staffId){
        boolean check = false;
        for(Staff staff : staffList){ // duyệt trong danh sách nhân viên
            if(staff.getId().equals(staffId)){ // nếu có mã trùng với mã cần tìm thì thoát
                check = true;
                break;
            }
        }
        return check;
    }

    /* phương thức kiểm tra mã phòng ban trả về kiểu boolean */
    public static boolean checkDepartmentId(String departmentId){
        boolean check = false;
        for(Department department : departments){
            if(department.getId().equals(departmentId)){
                check = true;
                break;
            }
        }
        return check;
    }

    /* phương thức kiểm tra mã phòng ban trả về vị trí tìm thấy trong mảng */
    public static int checkDepartmentIdInt(String departmentId){
        //boolean check = false;
        int i = 0;
        int s = departments.size();
        for(; i < s; i++){
            if(departments.get(i).getId().equals(departmentId)){
                break;
            }
        }
        if(i == s){
            i = -1;
        }
        return i;
    }

    /* phương thức kiểm tra mã phòng ban trả về vị trí tìm thấy trong mảng */
    public static int checkIndexPosition(String positionId, String department){
        int i = 0;
        int s = positions.size();
        for(; i < s; i++){
            if(positions.get(i).getId().equals(positionId)
                    && positions.get(i).getKeywordDepartment().equals(department)){
                break;
            }
        }
        if(i == s){
            i = -1;
        }
        return i;
    }

    /* phương thức kiểm tra nhập ngày tháng */
    public static String checkScannerInputDay(){
        boolean check = false;
        String joinDay;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false); // set false để kiểm tra tính hợp lệ của date. VD: tháng 2 phải có 28-29 ngày, năm có 12 tháng,....
        do {
            System.out.print("- Enter start date of work: ");
            joinDay = input.nextLine();
            try {
                df.parse(joinDay); // parse joinDay thành kiểu Date
                check = true;
            } catch (ParseException e) { // lỗi nếu joinDay ko hợp lệ
                System.out.println("Invalid date. Please re-enter! ");
                check = false;
            }
        }
        while(!check);
        return joinDay;
    }

    /* phuong thức tạo mã nhân viên hoặc mã phòng ban*/
    public static String randomId(int maxNumber, String str){
        int rId = random(maxNumber);
        //boolean check = true;
        String id = rId + str;
        if (rId < 10){
            id = "00" + rId;
        }
        else if(rId < 100){
            id = "0" + rId;
        }

        return id;
    }


    /* phương thức tạo câu hỏi, hỏi người dùng muốn tiếp tục */
    public static boolean question(){
        //System.out.print("Do you want to play again? ");
        String question = input.nextLine();
        question = question.trim().toLowerCase();
        if(question.equals("y") || question.equals("yes")){
            return true;
        }
        return false;
    }

    /* kiem tra nhap tu ban phim kieu Integer */
    public static int scannerInputInteger(){
        int rec = 0;
        boolean checkInput = false;

        do{
            String scannerInput = input.nextLine();
            try{
                rec = Integer.valueOf(scannerInput);//chuyển tử kiểu String sang Interger
                checkInput = true;
            }catch (Exception exception){
                System.out.print(ERROR_MESSAGE);
                checkInput = false;
            }
        }
        while (!checkInput);

        return rec;
    }

    /* kiem tra nhap tu ban phim kieu Integer */
    public static double scannerInputDouble(){
        double rec = 0.0;
        boolean checkInput = false;

        do{
            String scannerInput = input.nextLine();
            try{
                rec = Double.valueOf(scannerInput);//chuyển tử kiểu String sang Double
                checkInput = true;
            }catch (Exception exception){
                System.out.print(ERROR_MESSAGE);
                checkInput = false;
            }
        }
        while (!checkInput);

        return rec;
    }

    /* tạo số ngẫu nhiên từ 0 đến maxNumber */
    public static int random(int maxNumber){
        Random random = new Random();
        return random.nextInt(maxNumber) + 1;
    }
}
