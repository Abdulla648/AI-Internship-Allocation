package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    // Ithu thaan namma miss panna Main Method!
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(CompanyRepository companyRepo) {
        return args -> {
            if (companyRepo.count() == 0) {
                String[][] defaultCompanies = {
                    // ==========================================
                    // 🏛️ 50 GOVERNMENT IT & TECH SECTORS
                    // ==========================================
                    {"NIC", "Java, Python", "7.0", "50"}, {"C-DAC", "Java, AI, ML", "7.5", "40"}, 
                    {"BEL", "Python, C++", "6.5", "30"}, {"ISRO", "Java, Python, C", "8.0", "20"}, 
                    {"DRDO", "Python, AI, C++", "8.0", "25"}, {"CRIS", "Java, Oracle", "7.0", "30"},
                    {"BSNL", "Networking, Java", "6.5", "100"}, {"MTNL", "Networking, C", "6.0", "50"},
                    {"HAL", "C++, Embedded", "7.5", "20"}, {"BHEL", "Java, SQL", "7.0", "40"},
                    {"ECIL", "C, Embedded Systems", "7.0", "35"}, {"ITI Limited", "Telecom, Java", "6.5", "45"},
                    {"TCIL", "Networking, Python", "6.5", "30"}, {"RailTel", "Java, Cloud, Networking", "7.0", "50"},
                    {"UIDAI", "Java, Big Data, Security", "8.0", "15"}, {"NPCI", "Java, Blockchain, Security", "8.0", "25"},
                    {"C-DOT", "Telecom, C++", "7.5", "20"}, {"STPI", "IT Support, Networking", "6.5", "40"},
                    {"ERNET", "Web Dev, Networking", "6.5", "20"}, {"BARC", "Python, HPC, C++", "8.5", "10"},
                    {"ONGC IT", "SAP, Java", "7.0", "30"}, {"IOCL IT", "Python, Data Analytics", "7.5", "25"},
                    {"NTPC IT", "Java, SQL", "7.0", "20"}, {"SAIL IT", "ERP, Java", "6.5", "30"},
                    {"GAIL IT", "SAP, Python", "7.0", "15"}, {"Digital India Corp", "Java, Web Dev", "7.5", "50"},
                    {"MeitY", "Cybersecurity, Python", "8.0", "20"}, {"NKN", "Networking, Cloud", "7.0", "30"},
                    {"CERT-In", "Cybersecurity, Python", "8.0", "15"}, {"C-MET", "Hardware, Embedded", "7.0", "20"},
                    {"NIELIT", "Java, Web Dev", "6.5", "60"}, {"SAMEER", "RF, Embedded C", "7.5", "15"},
                    {"STQC", "Software Testing", "7.0", "25"}, {"NICSI", "Java, SQL, Cloud", "7.0", "40"},
                    {"REIL", "IT Support, ERP", "6.5", "20"}, {"KELTRON", "C++, Embedded", "6.5", "30"},
                    {"ELCOT", "Java, Web Dev", "6.5", "50"}, {"HARTRON", "Python, SQL", "6.5", "30"},
                    {"Webel", "Networking, Java", "6.5", "25"}, {"K-DISC", "AI, Blockchain", "7.5", "20"},
                    {"MAP_IT", "Web Dev, Java", "6.5", "30"}, {"APTS", "Java, Oracle", "6.5", "40"},
                    {"TSSTS", "Cloud, Python", "7.0", "35"}, {"BSE Tech", "Java, High Speed Trading", "7.5", "20"},
                    {"NSEIT", "Java, Cybersecurity", "7.5", "25"}, {"RBI Tech", "Security, Java", "8.0", "15"},
                    {"SBI Tech", "Java, Mainframe", "7.0", "50"}, {"LIC IT", "Java, SQL", "6.5", "40"},
                    {"India Post IT", "Java, Networking", "6.5", "60"}, {"DoT IT", "Telecom, Security", "7.0", "30"},

                    // ==========================================
                    // 💻 25 PRIVATE PRODUCT-BASED COMPANIES
                    // ==========================================
                    {"Google", "Java, AI, ML", "8.5", "10"}, {"Microsoft", "Java, C#", "8.5", "15"},
                    {"Amazon", "Java, AWS", "8.0", "20"}, {"Zoho", "Java, Python", "7.5", "50"},
                    {"Apple", "Swift, Python", "8.5", "5"}, {"Meta", "React, Python, C++", "8.5", "10"},
                    {"Netflix", "Java, Spring, Cloud", "8.5", "5"}, {"Tesla", "C++, AI, Python", "8.5", "5"},
                    {"Uber", "Java, Go, Python", "8.0", "15"}, {"Airbnb", "Ruby, Java", "8.0", "10"},
                    {"Atlassian", "Java, React", "8.0", "10"}, {"Adobe", "C++, Java", "8.0", "15"},
                    {"Oracle", "Java, SQL", "7.5", "30"}, {"SAP", "ABAP, Java", "7.5", "25"},
                    {"Salesforce", "Apex, Java", "8.0", "20"}, {"Intuit", "Java, Spring", "8.0", "15"},
                    {"Cisco", "C, Python, Networking", "7.5", "30"}, {"Intel", "C, C++, Hardware", "7.5", "20"},
                    {"NVIDIA", "C++, CUDA, AI", "8.5", "15"}, {"VMware", "Java, Go", "8.0", "20"},
                    {"ServiceNow", "Java, JavaScript", "7.5", "25"}, {"Snowflake", "Java, SQL, Cloud", "8.0", "10"},
                    {"Palantir", "Java, Data Science", "8.5", "5"}, {"Freshworks", "Ruby, Java", "7.5", "30"},
                    {"Postman", "NodeJS, React", "7.5", "20"},

                    // ==========================================
                    // 🏢 25 PRIVATE SERVICE-BASED COMPANIES
                    // ==========================================
                    {"TCS", "Java, SQL", "6.5", "200"}, {"Cognizant", "Java, Python", "6.5", "150"},
                    {"Wipro", "Java, Testing", "6.0", "100"}, {"Infosys", "Python, SQL", "6.5", "120"},
                    {"Capgemini", "Java, Cloud", "6.5", "90"}, {"Accenture", "Java, Salesforce", "6.5", "150"},
                    {"IBM Services", "Java, Cloud", "7.0", "80"}, {"Tech Mahindra", "Java, Telecom", "6.5", "100"},
                    {"HCLTech", "Java, Testing", "6.5", "110"}, {"LTI Mindtree", "Java, Data", "7.0", "70"},
                    {"DXC Technology", "Java, SQL", "6.5", "80"}, {"Mphasis", "Java, Cloud", "6.5", "60"},
                    {"Hexaware", "Java, Automation", "6.5", "50"}, {"NTT Data", "Java, SAP", "6.5", "70"},
                    {"CGI", "Java, Oracle", "6.5", "40"}, {"Atos", "Java, Security", "6.5", "50"},
                    {"Sopra Steria", "Java, Cloud", "6.5", "45"}, {"Genpact", "Python, Analytics", "6.5", "60"},
                    {"EXL", "Python, SQL", "6.5", "50"}, {"WNS", "Java, Testing", "6.5", "40"},
                    {"Mastek", "Java, Oracle", "6.5", "30"}, {"Zensar", "Java, Cloud", "6.5", "35"},
                    {"Birlasoft", "Java, SAP", "6.5", "40"}, {"Cyient", "C++, Embedded", "6.5", "45"},
                    {"Tata Elxsi", "C++, UI/UX", "7.0", "30"}
                };

                for (String[] data : defaultCompanies) {
                    Company c = new Company();
                    c.setCompanyName(data[0]);
                    c.setRequiredSkills(data[1]);
                    c.setMinCgpa(Double.parseDouble(data[2]));
                    c.setSeats(Integer.parseInt(data[3]));
                    companyRepo.save(c);
                }
                System.out.println("✅ 100 Companies Auto-Loaded Successfully!");
            }
        };
    }
}