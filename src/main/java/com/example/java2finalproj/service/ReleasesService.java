package com.example.java2finalproj.service;

import com.example.java2finalproj.model.Releases;
import com.example.java2finalproj.repository.ReleasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleasesService {
    private final ReleasesRepository releasesRepository;

    @Autowired
    public ReleasesService(ReleasesRepository releasesRepository) {
        this.releasesRepository = releasesRepository;
    }

    public List<Releases> getReleases(){
        return releasesRepository.findAll();
    }

    public List<Releases> getReleasesById(int id){
        return releasesRepository.getReleasesById(id);
    }

    public List<Releases> getReleasesByName(String name){
        return releasesRepository.getReleasesByName(name);
    }

    public List<Releases> getReleasesByIdAndName(int id, String name){
        return releasesRepository.getReleasesByIdAndName(id, name);
    }
}
