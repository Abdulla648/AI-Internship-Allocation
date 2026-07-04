package com.example.demo;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String email;
    private String skills;
    private double cgpa;
    
    private String preference;
    private boolean ruralQuota;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    
    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
    
    public String getPreference() { return preference; }
    public void setPreference(String preference) { this.preference = preference; }
    
    public boolean isRuralQuota() { return ruralQuota; }
    public void setRuralQuota(boolean ruralQuota) { this.ruralQuota = ruralQuota; }
}