package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;
//injected EmployerRepository, SkillRepository, and JobRepository beans using @Autowired - allows interaction with database and perform CRUD operations on Employer, Skill, and Job entities.

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    } //retrieves all jobs from the repository and adds them to the model. It returns the view "index".

    @GetMapping("add")
    public String displayAddJobForm(Model model) { //handles requests to /add
        model.addAttribute(new Job()); //prepares an empty Job object and adds it to the model.
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        //retrieves all employers and skills from their respective repositories and adds them to the model
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
//method handles form submissions when adding new job and validates submitted newJob object using @Valid and checks for errors
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }

        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        if(optEmployer.isPresent()) {
            Employer employer= optEmployer.get();
            newJob.setEmployer(employer);
        }

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> optJob = jobRepository.findById(jobId); //retrieves a job by ID from repository
        if (optJob.isPresent()) {
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);
//if exists adds it to the model and returns the view
        }
        return "view";
    }

}