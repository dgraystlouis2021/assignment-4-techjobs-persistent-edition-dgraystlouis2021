package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
    // ADDED BY DAG
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "My Jobs");
        // ADDED BY DAG----------------------------
        Iterable<Job> jobs=jobRepository.findAll();
        model.addAttribute("jobs", jobs);
        //----------------------------------------------
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        // ADDED BY DAG------
        Iterable<Employer> employers = employerRepository.findAll();
        Iterable<Skill> skills=skillRepository.findAll();
        //--------------------
        model.addAttribute("title", "Add Job");
        // ADDED BY DAG------
        model.addAttribute("employers", employers);
        model.addAttribute("skills", skills);
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
    // AlTERNATIVE METHOD SIGNATURE
    //public String processAddJobForm(@ModelAttribute @Valid Job newJob,
    //            Errors errors, Model model, @RequestParam int employerId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            Iterable<Employer> employers = employerRepository.findAll();
            model.addAttribute("employers", employers);
            // ADDED BY DAG------
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }
        // ADDED BY DAG------
        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()){
            Employer employer = optEmployer.get();
            newJob.setEmployer(employer);
            //jobRepository.save(newJob);
        }
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        jobRepository.save(newJob);
        //---------------------
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        // ADDED BY DAG----------------------------------
        Optional<Job> optJob=jobRepository.findById(jobId);
        if(optJob.isPresent()){
            Job job=optJob.get();
            model.addAttribute("job",job);
            return "view";
        }
        // RETURN TO LOCALHOST:8080/
        // GO UP 1 LEVEL FROM /VIEW
        return "redirect:../";
        //----------------------------------------------
    }
}
