/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import repo.DoctorRepository;

/**
 *
 * @author MSI
 */
public class DoctorInputter {
    private DoctorRepository doctRepo;

    public DoctorInputter() {
        doctRepo = new DoctorRepository();
    }
    
    public DoctorInputter(DoctorRepository doctRepo) {
        this.doctRepo = doctRepo;
    }

    public DoctorRepository getDoctRepo() {
        return doctRepo;
    }

    public void setDoctRepo(DoctorRepository doctRepo) {
        this.doctRepo = doctRepo;
    }
    
    public static String inputDoctorID() {
        String doctorID = null;
        String input;
        boolean OK;
        OK = true;
        do {
            input = Console.inputStr("Input doctor id\n");
            try {
                if(!DoctorInputValidation.checkDoctorIDFormat(input)) {
                    throw new Exception("Wrong doctor format id!");
                }
                doctorID = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
                OK = false;
            }
        } while(!OK);
        return doctorID;
    }

    public String inputNotExistedDoctorID() {
        String doctorID = null;
        String input;
        boolean OK;
        OK = true;
        do {
            input = inputDoctorID();
            try {
                if(doctRepo.details(input)!=null) {
                    throw new Exception("Duplicated doctor id!");
                }
                doctorID = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
                OK = false;
            }
        } while(!OK);
        return doctorID;
    }
    
    public String inputExistedDoctorID() {
        String doctorID = null;
        String input;
        boolean OK;
        OK = true;
        do {
            input = inputDoctorID();
            try {
                if(doctRepo.details(input)==null) {
                    throw new Exception("Duplicated doctor id!");
                }
                doctorID = input;
                OK = true;
            } catch (Exception e) {
                System.out.println("Invalid input");
                OK = false;
            }
        } while(!OK);
        return doctorID;
    }

    public static String inputName() {
        String name = null;

        String input;
        boolean OK;
        OK = true;
        do {
            input = Console.inputStr("Input doctor name\n");
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

        return name;
    }

    public static Boolean inputSex() {
        boolean sex = true;
        boolean OK = true;
        do {
            String input = Console.inputStr("Input sex\n");
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
        
        return sex;
    }

    public static String inputAddress() {
        String address = null;
        String input;
        boolean OK;
        OK = true;
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

        return address!=null ? address : "NULL";
    }
}
