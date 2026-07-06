package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api") 
public class MyController {

    @Autowired UserRepository userRepository;
    @Autowired CompanyRepository companyRepository;
    @Autowired HistoryRepository historyRepository;

    @PostMapping("/register")
    public String registerUser(@RequestParam String name, @RequestParam String email, 
                               @RequestParam String skills, @RequestParam double cgpa,
                               @RequestParam(required = false) String preference,
                               @RequestParam(defaultValue = "false") boolean ruralQuota) {
        User user = new User();
        user.setName(name); user.setEmail(email); user.setSkills(skills); 
        user.setCgpa(cgpa); user.setPreference(preference); user.setRuralQuota(ruralQuota);
        userRepository.save(user); 
        return "Student Saved! <br><a href='/welcome.html'>Back to Dashboard</a>";
    }

    @PostMapping("/registerCompany")
    public String registerCompany(@RequestParam String companyName, @RequestParam String requiredSkills, 
                                  @RequestParam double minCgpa, @RequestParam int seats) {
        Company company = new Company();
        company.setCompanyName(companyName); company.setRequiredSkills(requiredSkills); 
        company.setMinCgpa(minCgpa); company.setSeats(seats);
        companyRepository.save(company);
        return "Company Saved! <br><a href='/welcome.html'>Back to Dashboard</a>";
    }

    @GetMapping("/loadMockCompanies")
    public ResponseEntity<String> loadMockData() {
        if (companyRepository.count() == 0) {
            String[][] companies = {
                {"Google", "Java, Python, AI", "8.5", "2"},
                {"Microsoft", "Java, Python, C++", "8.2", "3"},
                {"Amazon", "Java, Python, AWS", "8.0", "4"},
                {"Zoho", "Java, C, Python", "7.5", "5"},
                {"TCS", "Java, SQL", "6.5", "10"},
                {"Cognizant", "Python, SQL, Java", "6.5", "8"},
                {"Wipro", "Java, HTML", "6.0", "10"}
            };
            for (String[] c : companies) {
                Company comp = new Company();
                comp.setCompanyName(c[0]); comp.setRequiredSkills(c[1]);
                comp.setMinCgpa(Double.parseDouble(c[2])); comp.setSeats(Integer.parseInt(c[3]));
                companyRepository.save(comp);
            }
        }
        return ResponseEntity.ok("Real companies with Seats loaded successfully!");
    }

    public static class AllocationResult {
        public String studentName; public String companyName; public String matchReason;
        public AllocationResult(String s, String c, String m) {
            this.studentName = s; this.companyName = c; this.matchReason = m;
        }
    }

    @GetMapping("/runAllocation")
    public List<AllocationResult> runAllocationEngine() {
        List<User> allStudents = (List<User>) userRepository.findAll();
        List<Company> companies = (List<Company>) companyRepository.findAll();
        List<AllocationResult> results = new ArrayList<>();
        
        // Remove Duplicates (Keeps only the latest entry per email)
        Map<String, User> uniqueStudentsMap = new LinkedHashMap<>();
        for (User u : allStudents) uniqueStudentsMap.put(u.getEmail(), u);
        List<User> students = new ArrayList<>(uniqueStudentsMap.values());

        Map<String, Integer> liveSeats = new HashMap<>();
        for(Company c : companies) liveSeats.put(c.getCompanyName(), c.getSeats());

        for (User student : students) {
            List<String> matchedComps = new ArrayList<>();
            StringBuilder reasonLog = new StringBuilder();
            
            for (Company company : companies) {
                int availableSeats = liveSeats.getOrDefault(company.getCompanyName(), 0);
                
                if (availableSeats > 0 && student.getCgpa() >= company.getMinCgpa()) {
                    
                    // Advanced Scoring Logic
                    String[] compSkills = company.getRequiredSkills().toLowerCase().split(",");
                    String[] studSkills = student.getSkills().toLowerCase().split(",");
                    int matchedSkillCount = 0;
                    for (String sSkill : studSkills) {
                        for (String cSkill : compSkills) {
                            if (cSkill.trim().contains(sSkill.trim())) {
                                matchedSkillCount++;
                                break; 
                            }
                        }
                    }
                    
                    if (matchedSkillCount == 0) continue; 
                    
                    double skillScore = Math.min(((double) matchedSkillCount / compSkills.length) * 50.0, 50.0);
                    double cgpaDiff = student.getCgpa() - company.getMinCgpa();
                    double cgpaScore = 15.0 + Math.min((cgpaDiff * 15.0), 15.0); 
                    
                    double prefScore = 0;
                    if (student.getPreference() != null && student.getPreference().equalsIgnoreCase(company.getCompanyName())) {
                        prefScore = 20.0;
                    }

                    double totalScore = skillScore + cgpaScore + prefScore;
                    
                    if (student.isRuralQuota()) {
                        totalScore = Math.min(totalScore + 10.0, 100.0); 
                    }

                    if (totalScore >= 40.0) {
                        matchedComps.add(company.getCompanyName());
                        liveSeats.put(company.getCompanyName(), availableSeats - 1); 
                        
                        reasonLog.append(Math.round(totalScore)).append("% Match with ").append(company.getCompanyName());
                        if (student.isRuralQuota()) reasonLog.append(" (Priority)");
                        reasonLog.append(" | ");
                    }
                }
            }
            
            if (!matchedComps.isEmpty()) {
                String allCompanies = String.join(", ", matchedComps);
                results.add(new AllocationResult(student.getName(), allCompanies, reasonLog.toString()));
                historyRepository.save(new AllocationHistory(student.getName(), allCompanies));
            }
        }
        return results;
    }
}
