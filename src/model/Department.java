/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.Objects;
import tools.DateHandler;

/**
 *
 * @author MSI
 */
public class Department {
    /// departmentID DEPXX name - from 5 to 20
    // departmentID,	name,	createDate,	lastUpdateDate
    private String departmentID; /// DEPXX
    private String name; /// 5-20
    private Date createDate;
    private Date lastUpdateDate;
    
    public Department() {
    }

    public Department(String departmentID) {
        this.departmentID = departmentID;
    }
    
    public Department(String departmentID, String name, Date createDate, Date lastUpdateDate) {
        this.departmentID = departmentID;
        this.name = name;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return departmentID + "\t" + name + "\t" + DateHandler.toPatternFormat(createDate, "dd/MM/yyyy") + "\t" + DateHandler.toPatternFormat(lastUpdateDate, "dd/MM/yyyy");
    }

    
}
