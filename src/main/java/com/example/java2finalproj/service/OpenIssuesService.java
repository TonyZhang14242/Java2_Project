package com.example.java2finalproj.service;

import com.example.java2finalproj.model.Openissues;
import com.example.java2finalproj.repository.OpenIssuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenIssuesService {
    private final OpenIssuesRepository openIssuesRepository;

    @Autowired
    public OpenIssuesService(OpenIssuesRepository openIssuesRepository) {
        this.openIssuesRepository = openIssuesRepository;
    }

    public List<Openissues> getOpenIssues(){
        return openIssuesRepository.findAll();
    }
}
