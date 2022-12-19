package com.example.java2finalproj.repository;

import com.example.java2finalproj.model.Commits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CommitsRepository extends JpaRepository<Commits, Long> {
    public List<Commits> getCommitsByNameAndId(String name, int id);
    public List<Commits> getCommitsByName(String name);
    public List<Commits> getCommitsById(int id);
}
