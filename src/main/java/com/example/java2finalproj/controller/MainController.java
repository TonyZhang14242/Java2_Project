package com.example.java2finalproj.controller;

import com.example.java2finalproj.model.Closeissues;
import com.example.java2finalproj.model.Commits;
import com.example.java2finalproj.model.Releases;
import com.example.java2finalproj.service.CloseIssuesService;
import com.example.java2finalproj.service.CommitsService;
import com.example.java2finalproj.service.OpenIssuesService;
import com.example.java2finalproj.service.ReleasesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class MainController {
    private final OpenIssuesService openIssuesService;

    private final CloseIssuesService closeIssuesService;

    private final CommitsService commitsService;

    private final ReleasesService releasesService;

    private String regex = "[a-zA-Z]+";
    private Map<String, Integer> countMap = new HashMap<>();
    private Pattern pattern = Pattern.compile(regex);

    public MainController(OpenIssuesService openIssuesService, CloseIssuesService closeIssuesService, CommitsService commitsService, ReleasesService releasesService) {
        this.openIssuesService = openIssuesService;
        this.closeIssuesService = closeIssuesService;
        this.commitsService = commitsService;
        this.releasesService = releasesService;
    }

    public String getCommitDistribution() {
        //6-12,12-18,18-6
        List<Commits> commitsList = commitsService.getCommits();
        int morning = 0;
        int afternoon = 0;
        int night = 0;
        for (int i = 0; i < commitsList.size(); i++) {
            int hour = commitsList.get(i).getTime().getHours();
            if (hour < 12 && hour >= 6) {
                morning++;
            } else if (hour >= 18 || hour < 6) {
                night++;
            } else {
                afternoon++;
            }
        }
        String res = morning + " " + afternoon + " " + night;
        return res;
    }

    public int getDeveloperNumber() {
        List<Commits> commitsList = commitsService.getCommits();
        List<String> developers = new ArrayList<>();
        for (int i = 0; i < commitsList.size(); i++) {
            if (!developers.contains(commitsList.get(i).getName())) {
                developers.add(commitsList.get(i).getName());
            }
        }
        return developers.size();
    }

    public List<CommitBetweenRelease> getCommitsBetweenReleases() {
        List<Releases> releases = releasesService.getReleases();
        List<Commits> commitsList = commitsService.getCommits();
        List<CommitBetweenRelease> commitBetweenReleases = new ArrayList<>();
        Collections.sort(releases, Comparator.comparing(releases1 -> releases1.getTime().getTime()));
        int[] num = new int[releases.size() + 1];
        for (int i = 0; i < commitsList.size(); i++) {
            for (int j = 0; j < releases.size(); j++) {
                if (commitsList.get(i).getTime().getTime() <= releases.get(0).getTime().getTime()) {
                    num[0]++;
                    break;
                }
                if (commitsList.get(i).getTime().getTime() <= releases.get(j).getTime().getTime() && commitsList.get(i).getTime().getTime() > releases.get(j - 1).getTime().getTime()) {
                    num[j]++;
                    break;
                }
                if (commitsList.get(i).getTime().getTime() > releases.get(releases.size() - 1).getTime().getTime()) {
                    num[releases.size()]++;
                    break;
                }
            }
        }
        //System.out.println(Arrays.stream(num).sum());
        //System.out.println(Arrays.toString(num));
        commitBetweenReleases.add(new CommitBetweenRelease("none", releases.get(0).getName(), num[0]));
        for (int i = 1; i < releases.size(); i++) {
            commitBetweenReleases.add(new CommitBetweenRelease(releases.get(i - 1).getName(), releases.get(i).getName(), num[i]));
        }
        commitBetweenReleases.add(new CommitBetweenRelease(releases.get(releases.size() - 1).getName(), "none", num[num.length - 1]));
        return commitBetweenReleases;

    }

    public List<Developers> getTopDevelopers() {
        List<Commits> commitsList = commitsService.getCommits();
        List<Developers> topdevelopers = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < commitsList.size(); i++) {
            if (!map.containsKey(commitsList.get(i).getName())) {
                map.put(commitsList.get(i).getName(), 0);
            } else {
                map.put(commitsList.get(i).getName(), map.get(commitsList.get(i).getName()) + 1);
            }
        }
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder())).limit(3).forEach(
                stringIntegerEntry -> topdevelopers.add(new Developers(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()))
        );
        return topdevelopers;
    }

    public String getAvgIssueSolvingTime() {
        List<Closeissues> closeissues = closeIssuesService.getCloseIssues();
        long sum = 0;
        long interval;
        for (int i = 0; i < closeissues.size(); i++) {
            interval = closeissues.get(i).getCloseat().getTime() - closeissues.get(i).getCreatetime().getTime();
            sum += interval;
        }
        interval = sum / closeissues.size();
        int days = (int) (interval / (1000 * 60 * 60 * 24));
        int hours = (int) ((interval / 1000) - days * (24 * 60 * 60)) / (60 * 60);
        int minutes = (int) ((interval / 1000 - hours * (60 * 60) - days * (24 * 60 * 60)) / 60);
        int second = (int) (interval / 1000 - days * (24 * 60 * 60) - hours * (60 * 60) - minutes * 60);
        return days + "d" + hours + "h" + minutes + "m";
    }

    public String getMaxIssueTime() {
        List<Closeissues> closeissues = closeIssuesService.getCloseIssues();
        long max = 0;
        for (int i = 0; i < closeissues.size(); i++) {
            long interval = closeissues.get(i).getCloseat().getTime() - closeissues.get(i).getCreatetime().getTime();
            if (interval > max)
                max = interval;
        }
        long interval = max;
        int days = (int) (interval / (1000 * 60 * 60 * 24));
        int hours = (int) ((interval / 1000) - days * (24 * 60 * 60)) / (60 * 60);
        int minutes = (int) ((interval / 1000 - hours * (60 * 60) - days * (24 * 60 * 60)) / 60);
        int second = (int) (interval / 1000 - days * (24 * 60 * 60) - hours * (60 * 60) - minutes * 60);
        return days + "d" + hours + "h" + minutes + "m";
    }

    public String getMinIssueTime() {
        List<Closeissues> closeissues = closeIssuesService.getCloseIssues();
        long min = Long.MAX_VALUE;
        for (int i = 0; i < closeissues.size(); i++) {
            long interval = closeissues.get(i).getCloseat().getTime() - closeissues.get(i).getCreatetime().getTime();
            if (interval < min)
                min = interval;
        }
        long interval = min;
        int days = (int) (interval / (1000 * 60 * 60 * 24));
        int hours = (int) ((interval / 1000) - days * (24 * 60 * 60)) / (60 * 60);
        int minutes = (int) ((interval / 1000 - hours * (60 * 60) - days * (24 * 60 * 60)) / 60);
        int second = (int) (interval / 1000 - days * (24 * 60 * 60) - hours * (60 * 60) - minutes * 60);
        return minutes + "m" + second + "s";
    }

    public List<TopIssueWord> processIssueFreqPrepare() {
        List<String> names = new ArrayList<>();
        List<TopIssueWord> res = new ArrayList<>();
        openIssuesService.getOpenIssues().forEach(openissues -> {
            names.add(openissues.getTitle());
        });
        closeIssuesService.getCloseIssues().forEach(closeissues -> {
            names.add(closeissues.getTitle());
        });
        names.forEach(this::getPop3Word);
        countMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .limit(3).forEach(stringIntegerEntry -> res.add(new TopIssueWord(stringIntegerEntry.getKey(), stringIntegerEntry.getValue())));
        return res;

    }

    public void getPop3Word(String line) {
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            word = word.toLowerCase();
            if (countMap.get(word) == null) {
                countMap.put(word, 1);
            } else {
                int count = countMap.get(word);
                countMap.put(word, count + 1);
            }
        }

    }

    @RequestMapping("/trojan")
    public String getIssueCntAll(Model model) {
        model.addAttribute("avgissuesolving", getAvgIssueSolvingTime());
        model.addAttribute("maxissuesolving", getMaxIssueTime());
        model.addAttribute("minissuesolving", getMinIssueTime());
        model.addAttribute("repoName", "trojan");
        model.addAttribute("devnumber", getDeveloperNumber());
        model.addAttribute("releasenum", releasesService.getReleases().size());
        model.addAttribute("commitsnumber", commitsService.getCommits().size());
        model.addAttribute("commitdistribution", getCommitDistribution());
        model.addAttribute("openissues", openIssuesService.getOpenIssues().size());
        model.addAttribute("closeissues", closeIssuesService.getCloseIssues().size());
        return "index";
    }

    @RequestMapping("/trojan/commitsbtwrelease")
    public String commitsBetweenRelease(Model model) {
        model.addAttribute("repoName", "trojan");
        model.addAttribute("commitbetweenrelease", getCommitsBetweenReleases());
        return "combtwrls";
    }

    @RequestMapping("/trojan/topdevelopers")
    public String topDevelopers(Model model) {
        model.addAttribute("repoName", "trojan");
        model.addAttribute("developers", getTopDevelopers());
        return "topdevelopers";
    }

    @RequestMapping("/trojan/topissuewords")
    public String topIssueWords(Model model) {
        model.addAttribute("repoName", "trojan");
        model.addAttribute("topissuewords", processIssueFreqPrepare());
        return "topissuewords";
    }


}
