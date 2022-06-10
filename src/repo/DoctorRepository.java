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
import java.util.StringTokenizer;
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
    private static File storage = new File("./doctor.dat");

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
        for(Doctor doctor : doctorList.values())
            writer.write(doctor.toString() + "\n");
        writer.close();
    }

    public List<Doctor> searchDoctorByName(String name) {
        List<Doctor> doctors = new Vector();
        for(Doctor doctor : doctorList.values()) {
            if(doctor.getName().equals(name))
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
}
