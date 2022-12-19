package com.example.java2finalproj.service;

import com.example.java2finalproj.model.Commits;
import com.example.java2finalproj.repository.CommitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitsService {
    private final CommitsRepository commitsRepository;

    @Autowired
    public CommitsService(CommitsRepository commitsRepository) {
        this.commitsRepository = commitsRepository;
    }

    public List<Commits> getCommits(){
        return commitsRepository.findAll();
    }

    public List<Commits> getCommitsByName(String name) {
        return commitsRepository.getCommitsByName(name);
    }
    public List<Commits> getCommitsById(int id){
        return commitsRepository.getCommitsById(id);
    }

    public List<Commits> getCommitsByIdAndName(String name, int id){
        return commitsRepository.getCommitsByNameAndId(name, id);
    }
}
