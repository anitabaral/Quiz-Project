/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.Date;


/**
 *
 * @author ANITA
 */
public class Student {

     private int id;
     private String name;
     private String username;
     private String password;
     private int roll;
     private String contactno;
     private String dob;
     private double fee;

    public Student() {
    }

    public Student(Integer id, String name, String username, String password, Integer roll, String contactno,String dob, Double fee) {

        this.id=id;
        this.name=name;
        this.username=username;
        this.roll=roll;
        
        this.password=password;
        this.contactno=contactno;
        this.dob=dob;
        this.fee=fee;
              


                
    }

   
    

    

   

    

    public int getId() {
        return id;
    }
    

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
     
    
}
