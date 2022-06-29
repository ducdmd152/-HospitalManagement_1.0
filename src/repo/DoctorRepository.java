/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import model.Doctor;
import tools.ApplicationDataFormat;
import tools.DateHandler;

/**
 *
 * @author MSI
 */
public class DoctorRepository implements ICrud<String, Doctor> {
    private HashMap<String, Doctor> doctorList = new HashMap();
    private DepartmentRepository deptRepo;
    
    private static File storage = new File("./doctor.dat");

    public DoctorRepository() {
        this.deptRepo = new DepartmentRepository();
    }

    
    public DoctorRepository(DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
    }
    
    @Override
    public int create(Doctor newItem) {
        try {
            doctorList.put(newItem.getDoctorID(), newItem);
            return 0; // Insert ok
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return -1; // Insert error
        }
    }

    @Override
    public HashMap<String, Doctor> read() {
        return doctorList;
    }

    @Override
    public Doctor details(String id) {
       return doctorList.get(id);
    }

    @Override
    public int update(Doctor editItem) {
        try {
            Doctor curItem = doctorList.get(editItem.getDoctorID());
            
            updateFromTo(editItem, curItem);
            return 0; // update ok
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return -1; // update error
        }
        
    }

    @Override
    public int delete(String id) {
        try {
            doctorList.remove(id);
            return 0; // delete ok
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }
    
    public void readFromFile() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(storage));
        
        String line;
        while((line = reader.readLine())!=null) {
            StringTokenizer tokens = new StringTokenizer(line, ",");
            
            String doctorID = tokens.nextToken();
            String name = tokens.nextToken();
            boolean sex = Boolean.parseBoolean(tokens.nextToken());
            String address = tokens.nextToken();
            String departmentID = tokens.nextToken();
            Date createDate = DateHandler.createDateFromPattern(tokens.nextToken(), ApplicationDataFormat.DATE_FORMAT);
            Date lastUpdateDate = DateHandler.createDateFromPattern(tokens.nextToken(), ApplicationDataFormat.DATE_FORMAT);
        
            doctorList.put(doctorID, new Doctor(doctorID, name, sex, address, departmentID, createDate, lastUpdateDate));
        }
        reader.close();
    }
    
    public void writeToFile() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(storage));
        
        SortedMap<String, String> records = new TreeMap<>();
        
        for(Doctor doctor : doctorList.values()) {
            String record = doctorToStringFormatInFile(doctor);
            records.put(doctor.getDoctorID(), record);
        }
        
        for(String record : records.values()) {
            writer.write(record + "\n");
        }
        
        writer.close();
    }

    public List<Doctor> searchDoctorByName(String name) {
        List<Doctor> doctors = new Vector();
        for(Doctor doctor : doctorList.values()) {
            if(doctor.getName().toLowerCase().contains(name.toLowerCase()))
                doctors.add(doctor);
        }
        
        return doctors;
    }
    
    private void updateFromTo(Doctor from, Doctor to) {
//      to.setDoctorID(from.getDoctorID());
        to.setName(from.getName());
        to.setSex(from.getSex());
        to.setAddress(from.getAddress());
        to.setDepartmentID(from.getDepartmentID());
//      to.setCreateDate(from.getCreateDate());
        to.setLastUpdateDate(from.getLastUpdateDate());
    }

    public List<Doctor> getDoctorsBelongTo(String departmentID) {
        List<Doctor> list = new Vector();
        
        for(Doctor doctor : doctorList.values())
            if(doctor.getDepartmentID().equals(departmentID))
                list.add(doctor);
        
        return list;
    }

    private String doctorToStringFormatInFile(Doctor doctor) {
        /// DOC001,An,True,98 Ly Thai To Q3 TP.HCM,DEP01,12-07-2021,23-04-2022
        String doctorID = doctor.getDoctorID();
        String name = doctor.getName();
        String sex = doctor.getSex() ? "True" : "False";
        String address = doctor.getAddress();
        if(address==null) address = "NULL";
        
        String departmentID = doctor.getDepartmentID();
        String createDate = DateHandler.toPatternFormat(doctor.getCreateDate(), ApplicationDataFormat.DATE_FORMAT);
        String lastUpdateDate = DateHandler.toPatternFormat(doctor.getLastUpdateDate(), ApplicationDataFormat.DATE_FORMAT);
        if(lastUpdateDate==null) lastUpdateDate = "NULL";
        String record = String.format("%s,%s,%s,%s,%s,%s,%s", doctorID, name, sex, address, departmentID, createDate, lastUpdateDate);
        
        return record;
    }
}
