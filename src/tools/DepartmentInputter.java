/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import repo.DepartmentRepository;

/**
 *
 * @author MSI
 */
public class DepartmentInputter {
    private DepartmentRepository deptRepo;

    public DepartmentInputter(DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
    }

    public void setDeptRepo(DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
    }
    
    public static String inputDepartmentID() {
        String departmentID = null;
        String input;
        boolean OK;
        OK = true;
        do {
            input = Console.inputStr("Input department id\n");
            try {
                if(!DepartmentInputValidation.checkDepartmentIDFormat(input)) {
                    throw new Exception("Wrong department format id!");
                }
                departmentID = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
                OK = false;
            }
        } while(!OK);
        return departmentID;
    }

    public static String inputName() {
        String name = null;

        String input;
        boolean OK;
        OK = true;
        do {
            input = Console.inputStr("Input department name\n");
            try {
                if(!DepartmentInputValidation.checkNameLength(input)) {
                    throw new Exception("Unaccepted length for a name!");
                }
                name = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
                OK = false;
            }
        } while(!OK);

        return name;
    }

    public String inputExistedDepartmentID() {
        String departmentID = null;
        String input;
        boolean OK;
        OK = true;
        do {
            input = inputDepartmentID();
            try {
                if(deptRepo.details(input)==null) {
                    throw new Exception("Not exist the department id!");
                }
                departmentID = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
                OK = false;
            }
        } while(!OK);
        return departmentID;
    }

    public String inputNotExistedDepartmentID() {
        String departmentID = null;
        String input;
        boolean OK;

        OK = true;
        do {
            input = inputDepartmentID();
            try {
                if(deptRepo.details(input)!=null) {
                    throw new Exception("Duplicated department id!");
                }
                departmentID = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
                OK = false;
            }
        } while(!OK);

        return departmentID;
    }
}
