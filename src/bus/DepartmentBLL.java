/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import java.util.Collection;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;
import model.Department;
import repo.DepartmentRepository;
import repo.DoctorRepository;
import tools.ApplicationDataFormat;
import tools.Console;
import tools.DateHandler;
import tools.DepartmentInputValidation;
import tools.DepartmentInputter;

/**
 *
 * @author MSI
 */
public class DepartmentBLL {
    private DepartmentRepository deptRepo =  new DepartmentRepository();
    private DoctorRepository doctRepo;
    
    public DepartmentBLL() {
        try {
            deptRepo.readFromFile();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public DepartmentBLL(DoctorRepository doctRepo) {
        this.doctRepo = doctRepo;
        try {
            deptRepo.readFromFile();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public DepartmentBLL(DepartmentRepository deptRepo, DoctorRepository doctRepo) {
        this.deptRepo = deptRepo;
        this.doctRepo = doctRepo;
        try {
            deptRepo.readFromFile();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    public DepartmentRepository getDeptRepo() {
        return deptRepo;
    }

    public void setDeptRepo(DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
    }
    
    public void showDepartmentList() {
        Collection<Department> departments = deptRepo.read().values();
        SortedMap<String, String> records = new TreeMap<>();
        
        for(Department department : departments) {
            String departmentID = department.getDepartmentID();
            String name = department.getName();
            String createDate = DateHandler.toPatternFormat(department.getCreateDate(), ApplicationDataFormat.DATE_FORMAT);
//            System.out.println(department.getCreateDate());
            String lastUpdateDate = DateHandler.toPatternFormat(department.getLastUpdateDate(), ApplicationDataFormat.DATE_FORMAT);
            if(lastUpdateDate==null) lastUpdateDate = "Not yet";
            String record = String.format("%s=====%s=====%s=====%s", departmentID, name, createDate, lastUpdateDate);
            records.put(departmentID, record);
        }
        
        System.out.println("List of department in hospital");
        System.out.println("ID=====Name=====Create date=====Last update date");
        for(String record : records.values()) {
            System.out.println(record);
        }
        System.out.println("Number of departments: " + departments.size());
    }
    
    public void addNewDepartment() {
        DepartmentInputter deptInputter = new DepartmentInputter(deptRepo);
        String departmentID = deptInputter.inputNotExistedDepartmentID();
        String name = deptInputter.inputName();
        
        Department department = new Department(departmentID, name, new Date(), null);
        
        deptRepo.create(department);
    }

    
    public void updateDepartment() {
        if(deptRepo.isEmpty()) {
            System.out.println("Department Repository is EMPTY!");
            return;
        }
        DepartmentInputter deptInputter = new DepartmentInputter(deptRepo);
        String departmentID = deptInputter.inputExistedDepartmentID();
        Department department = deptRepo.details(departmentID);
        String name = department.getName();
        
        boolean OK = true;
        do {
            String input = Console.inputStr("Input department name\n");
            if(input.isBlank()) break;
            
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
        
        deptRepo.update(new Department(departmentID, name, null, new Date()));
    }

    public void deleteDepartment() {
        if(deptRepo.isEmpty()) {
            System.out.println("Department Repository is EMPTY!");
            return;
        }
        DepartmentInputter deptInputter = new DepartmentInputter(deptRepo);
        String departmentID = deptInputter.inputExistedDepartmentID();
        
        if(!doctRepo.getDoctorsBelongTo(departmentID).isEmpty()) {
            System.out.println("Cannot delete the department!");
            return;
        }
        
        deptRepo.delete(departmentID);
    }
    
    public void searchDepartmentbyID() {
        if(deptRepo.isEmpty()) {
            System.out.println("Department Repository is EMPTY!");
            return;
        }
        DepartmentInputter deptInputter = new DepartmentInputter(deptRepo);
        String departmentID = deptInputter.inputExistedDepartmentID();
        
        Department department = deptRepo.details(departmentID);
        
        System.out.println(department);
    }
    
    public void storeDataToFile() {
        try {
            deptRepo.writeToFile();
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
