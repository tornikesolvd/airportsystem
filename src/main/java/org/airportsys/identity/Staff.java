package org.airportsys.identity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Staff {

    private String staffName;
    private String role;

    public String getStaffName(){
        return staffName;
    }

    public void setStaffName(String staffName){
        this.staffName = staffName;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }
}
