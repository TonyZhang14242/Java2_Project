package com.example.java2finalproj.repository;

import com.example.java2finalproj.model.Releases;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReleasesRepository extends JpaRepository<Releases,Long> {
    public List<Releases> getReleasesById(int id);
    public List<Releases> getReleasesByName(String name);
    public List<Releases> getReleasesByIdAndName(int id, String name);
}
