package com.example.java2finalproj.service;

import com.example.java2finalproj.model.Closeissues;
import com.example.java2finalproj.repository.CloseIssuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloseIssuesService {
    private final CloseIssuesRepository closeIssuesRepository;

    @Autowired
    public CloseIssuesService(CloseIssuesRepository closeIssuesRepository) {
        this.closeIssuesRepository = closeIssuesRepository;
    }


    public List<Closeissues> getCloseIssues(){
        return closeIssuesRepository.findAll();
    }
}
