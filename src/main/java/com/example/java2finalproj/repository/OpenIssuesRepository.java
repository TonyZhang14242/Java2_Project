package com.example.java2finalproj.repository;

import com.example.java2finalproj.model.Openissues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenIssuesRepository extends JpaRepository<Openissues, Long> {

}
