package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Ivlo thaan code! Ulla ethuvum ezhutha thevai illai. 
    // Spring Boot automatic-a save, find, delete operations-ai intha oru vari vazhiya namakku thandhudum.
}