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
import java.util.StringTokenizer;
import model.Department;
import tools.ApplicationDataFormat;
import tools.DateHandler;

/**
 *
 * @author MSI
 */
public class DepartmentRepository implements ICrud<String, Department> {
    private HashMap<String, Department> departmentList = new HashMap<>();
    private static File storage = new File("./department.dat");

    public DepartmentRepository() {
    }
    
    public DepartmentRepository(HashMap<String, Department> departmentList) {
        this.departmentList = departmentList;
    }

    public HashMap<String, Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(HashMap<String, Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public int create(Department newItem) {
        try {
            departmentList.put(newItem.getDepartmentID(), newItem);
            return 0;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public HashMap<String, Department> read() {
        return departmentList;
    }

    @Override
    public Department details(String id) {
        return departmentList.get(id);
    }

    @Override
    public int update(Department editItem) {
        try {
            Department curItem = departmentList.get(editItem.getDepartmentID());
            
            updateFromTo(editItem, curItem);
            return 0;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public int delete(String id) {
        try {
            departmentList.remove(id);
            return 0;
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }
    
    public void readFromFile() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(storage));
        
        String line;
        Department department;
        while((line = reader.readLine())!=null) {
            StringTokenizer tokens = new StringTokenizer(line, ",");
            
            String departmentID = tokens.nextToken();
            String name = tokens.nextToken();
            Date createDate = DateHandler.createDateFromPattern(tokens.nextToken(), ApplicationDataFormat.DATE_FORMAT);
            Date lastUpdateDate = DateHandler.createDateFromPattern(tokens.nextToken(), ApplicationDataFormat.DATE_FORMAT);
        
            departmentList.put(departmentID, new Department(departmentID, name, createDate, lastUpdateDate));
        }
        reader.close();
    }
    
    public void writeToFile() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(storage));
        for(Department department : departmentList.values())
            writer.write(department.toString() + "\n");
        writer.close();
    }
    
    private void updateFromTo(Department from, Department to) {
//        to.setDepartmentID(from.getDepartmentID());
        to.setName(from.getName());
//        to.setCreateDate(from.getCreateDate());
        to.setLastUpdateDate(from.getLastUpdateDate());
    }

    public boolean isEmpty() {
        return departmentList.isEmpty();
    }
}
