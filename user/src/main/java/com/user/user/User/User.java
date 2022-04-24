package com.user.user.User;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "User")
@Entity
public class User implements Serializable{

    @Serial
    private static final long SERIALVERSIONUID=12348L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    String doB;

    String number;

    public User() {
    }

    public User(int id, String name, String email, String password, String doB, String number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.doB = doB;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getdoB() {
        return doB;
    }

    public void setdoB(String doB) {
        this.doB = doB;
    }

    public String getnumber() {
        return number;
    }

    public void setnumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "User [doB=" + doB + ", email=" + email + ", name=" + name + ", number=" + number + ", password="
                + password + ", id=" + id + "]";
    }

    

}
