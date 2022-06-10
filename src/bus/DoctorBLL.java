/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import model.Doctor;
import repo.DepartmentRepository;
import repo.DoctorRepository;
import tools.Console;
import tools.DepartmentInputValidation;
import tools.DepartmentInputter;
import tools.DoctorInputValidation;
import tools.DoctorInputter;

/**
 *
 * @author MSI
 */
public class DoctorBLL {
    private DoctorRepository doctRepo = new DoctorRepository();
    private DepartmentRepository deptRepo;

    public DoctorBLL() {
        deptRepo = new DepartmentRepository();
        try {
            doctRepo.readFromFile();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public DoctorBLL(DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
        try {
            doctRepo.readFromFile();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public DoctorBLL(DoctorRepository doctRepo, DepartmentRepository deptRepo) {
        this.doctRepo = doctRepo;
        this.deptRepo = deptRepo;
        try {
            doctRepo.readFromFile();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }


    public DoctorRepository getDoctRepo() {
        return doctRepo;
    }

    public void setDoctRepo(DoctorRepository doctRepo) {
        this.doctRepo = doctRepo;
    }
    
    public void showDoctorList() {
        Collection<Doctor> doctors = doctRepo.read().values();
        SortedMap<String, String> records = new TreeMap<>();
        
        for(Doctor doctor : doctors) {
            String doctorID = doctor.getDoctorID();
            String name = doctor.getName();
            String sex = doctor.getSex() ? "male" : "female";
            String address = doctor.getAddress();
            String department = deptRepo.details(doctor.getDepartmentID()).getName();
            
            String record = String.format("%s=====%s=====%s=====%s=====%s", doctorID, name, sex, address, department);
            records.put(doctorID, record);
        }
        
        System.out.println("List of doctor in hospital");
        System.out.println("ID=====Name=====Sex=====Address=====Department");
        for(String record : records.values()) {
            System.out.println(record);
        }
        System.out.println("Number of doctors: " + doctors.size());
    }
    
    public void addNewDoctor() {
        if(deptRepo.isEmpty()) {
            System.out.println("No department to add doctor!");
            return;
        }
        DoctorInputter doctInputter = new DoctorInputter(doctRepo);
        DepartmentInputter deptInputter = new DepartmentInputter(deptRepo);
        
        String doctorID = doctInputter.inputNotExistedDoctorID();
        String name = doctInputter.inputName();
        Boolean sex = doctInputter.inputSex();
        String address = doctInputter.inputAddress();
//        String departmentID = deptInputter.inputDepartmentID();
        String departmentID = deptInputter.inputExistedDepartmentID();
        
        Doctor doctor = new Doctor(doctorID, name, sex, address, departmentID, new Date(), null);
        
        doctRepo.create(doctor);
    }
    
    public void updateDoctor() {
        DoctorInputter doctInputter = new DoctorInputter(doctRepo);
        
        String doctorID = doctInputter.inputExistedDoctorID();
        Doctor doctor = doctRepo.details(doctorID);
        
        String name = doctor.getName();
        Boolean sex = doctor.getSex();
        String address = doctor.getAddress();
        String departmentID = doctor.getDepartmentID();
        
        String input;
        boolean OK;
        
        OK = true;
        do {
            input = Console.inputStr("Input doctor name\n");
            if(input.isBlank()) break;
            try {
                if(!DoctorInputValidation.checkNameLength(input)) {
                    throw new Exception("Unaccepted length for a name!");
                }
                name = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
                OK = false;
            }
        } while(!OK);
        
        do {
            input = Console.inputStr("Input sex\n");
            if(input.isBlank()) break;
            try {
                if(!DoctorInputValidation.checkSexFormat(input)) {
                    throw new Exception();
                }
                sex = input.trim().toUpperCase().equals("TRUE");
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
                OK = false;
            }
        } while(!OK);
        
        do {
            input = Console.inputStr("Input address\n");
            if(input.isBlank()) break;
            try {
                if(!DoctorInputValidation.checkAddressFormat(input)) {
                    throw new Exception("Wrong address format!");
                }
                address = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
                OK = false;
            }
        } while(!OK);
        
        OK = true;
        do {
            input = Console.inputStr("Input department id\n");
            if(input.isBlank()) break;
            try {
                if(!DepartmentInputValidation.checkDepartmentIDFormat(input)) {
                    throw new Exception("Wrong department format id!");
                }
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
        
        doctRepo.update(new Doctor(doctorID, name, sex, address, departmentID, null, new Date()));
    }
    
    public void deleteDoctor() {
        DoctorInputter doctInputter = new DoctorInputter(doctRepo);
        String doctorID = doctInputter.inputExistedDoctorID();
        
        doctRepo.delete(doctorID);
    }
    
    public void searchDepartmentbyName() {
        String name = DoctorInputter.inputName();
        
        List<Doctor> doctors = doctRepo.searchDoctorByName(name);
        
        for(Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }
    
    public void storeDataToFile() {
        try {
            doctRepo.writeToFile();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
