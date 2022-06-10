/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import tools.DateHandler;

/**
 *
 * @author MSI
 */
public class Doctor {
    private String doctorID; // DOCXXX
    private String name; // from 5 to 25
    private boolean sex; // True/False
    private String address; // from 0 to 50
    private String departmentID; // ref Department
    private Date createDate; // ref (dd/mm/yyyy)
    private Date lastUpdateDate; // ref (dd/mm/yyyy)

    public Doctor() {
    }

    public Doctor(String doctorID, String name, boolean sex, String address, String departmentID, Date createDate, Date lastUpdateDate) {
        this.doctorID = doctorID;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.departmentID = departmentID;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    @Override
    public String toString() {
        return doctorID + "\t " + name + "\t" + sex + "\t" + address + "\t" + departmentID + "\t" + DateHandler.toPatternFormat(createDate, "dd/MM/yyyy") + ",\t" + DateHandler.toPatternFormat(lastUpdateDate, "dd/MM/yyyy");
    }
}