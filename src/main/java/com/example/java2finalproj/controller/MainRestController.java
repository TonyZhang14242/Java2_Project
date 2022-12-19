package com.example.java2finalproj.controller;

import com.example.java2finalproj.model.Commits;
import com.example.java2finalproj.model.Releases;
import com.example.java2finalproj.service.CloseIssuesService;
import com.example.java2finalproj.service.CommitsService;
import com.example.java2finalproj.service.OpenIssuesService;
import com.example.java2finalproj.service.ReleasesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trojan")
public class MainRestController {
    private final OpenIssuesService openIssuesService;
    private final CloseIssuesService closeIssuesService;
    private final CommitsService commitsService;

    private final ReleasesService releasesService;

    public MainRestController(OpenIssuesService openIssuesService, CloseIssuesService closeIssuesService, CommitsService commitsService, ReleasesService releasesService) {
        this.openIssuesService = openIssuesService;
        this.closeIssuesService = closeIssuesService;
        this.commitsService = commitsService;
        this.releasesService = releasesService;
    }

    @GetMapping("/issues")
    public <T> List<T> getIssuesByState(@RequestParam(value = "status") Optional<String> status){
        if (status.isPresent()){
            if (status.get().equals("open")){
                return (List<T>) openIssuesService.getOpenIssues();
            }
            else
                return (List<T>) closeIssuesService.getCloseIssues();
        }
        else {
            List<T> listtmp = new ArrayList<>();
            listtmp.addAll((Collection<? extends T>) closeIssuesService.getCloseIssues());
            listtmp.addAll((Collection<? extends T>) openIssuesService.getOpenIssues());
            return listtmp;
        }
    }

    @GetMapping("/commits")
    public List<Commits> getCommitsByNameOrId(@RequestParam(value = "name") Optional<String> name, @RequestParam("id") Optional<Integer> id){
        if (name.isPresent() && id.isPresent()){
            return commitsService.getCommitsByIdAndName(name.get(), id.get());
        }
        else if (name.isPresent()){
            return commitsService.getCommitsByName(name.get());
        }
        else if (id.isPresent()){
            return commitsService.getCommitsById(id.get());
        }
        else return commitsService.getCommits();
    }

    @GetMapping("/releases")
    public List<Releases> getReleasesByIdOrName(@RequestParam(value = "name") Optional<String> name, @RequestParam("id") Optional<Integer> id){
        if (name.isPresent() && id.isPresent()){
            return releasesService.getReleasesByIdAndName(id.get(), name.get());
        }
        else if (name.isPresent()){
            return releasesService.getReleasesByName(name.get());
        }
        else if (id.isPresent()){
            return releasesService.getReleasesById(id.get());
        }
        else return releasesService.getReleases();
    }
}
