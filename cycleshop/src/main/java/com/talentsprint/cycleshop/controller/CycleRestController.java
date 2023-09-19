package com.talentsprint.cycleshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talentsprint.cycleshop.entity.Cycle;
import com.talentsprint.cycleshop.service.CycleService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CycleRestController {
    
    @Autowired
    private CycleService cycleService;

    @GetMapping("/health")
    public String checkhealth() {
        return "healthy";
    }

    @GetMapping("/cycle/list")
    public List<Cycle> all() {
        // System.out.println(authentication.getAuthorities());
        // Jwt jwt = (Jwt) authentication.getPrincipal();
        // System.out.println(jwt.getClaimAsString("scope"));
        return cycleService.listAvailableCycles();
    }

    @PostMapping("/{id}/restock")
    public List<Cycle> restockPost(@PathVariable int id,@RequestParam int count){
        // Jwt jwt = (Jwt) authentication.getPrincipal();
        // System.out.println(jwt.getClaimAsString("scope"));
        cycleService.restockBy(id, count);
        return all();
    }

    @PostMapping("/{id}/borrow")
    public List<Cycle> borrowCycles(@PathVariable int id,@RequestParam int count){
        cycleService.borrowCycle(id, count);
        return all();
    }

    @PostMapping("/{id}/return")
    public List<Cycle> returnCycles(@PathVariable int id,@RequestParam int count,Authentication authentication){
        cycleService.returnCycle(id, count);
        return all();
    }
}
