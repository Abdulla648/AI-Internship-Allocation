package com.example.demo;

import jakarta.persistence.*; 
import java.time.LocalDateTime;

@Entity
public class AllocationHistory {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String studentName;
    private String companyName;
    private LocalDateTime timestamp;

    
    public AllocationHistory() {}

    public AllocationHistory(String studentName, String companyName) {
        this.studentName = studentName;
        this.companyName = companyName;
        this.timestamp = LocalDateTime.now();
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
