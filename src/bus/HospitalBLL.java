/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import repo.DepartmentRepository;
import repo.DoctorRepository;

/**
 *
 * @author MSI
 */
public class HospitalBLL {
    private DepartmentBLL departmentBLL;
    private DoctorBLL doctorBLL;

    public HospitalBLL() {
        DepartmentRepository deptRepo = new DepartmentRepository();
        DoctorRepository doctRepo = new DoctorRepository();
        
        departmentBLL = new DepartmentBLL(deptRepo, doctRepo);
        doctorBLL = new DoctorBLL(doctRepo, deptRepo);
   }

    public DepartmentBLL getDepartmentBLL() {
        return departmentBLL;
    }

    public void setDepartmentBLL(DepartmentBLL departmentBLL) {
        this.departmentBLL = departmentBLL;
    }

    public DoctorBLL getDoctorBLL() {
        return doctorBLL;
    }

    public void setDoctorBLL(DoctorBLL doctorBLL) {
        this.doctorBLL = doctorBLL;
    }
    
   
}
