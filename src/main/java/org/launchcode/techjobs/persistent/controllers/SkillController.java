package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//Created by Shelly//

@Controller //designates this class as a controller
@RequestMapping("skills") //specifies base url path for endpoints in this controller
public class SkillController {

    @Autowired //allows interaction with database and perform CRUD operations on SKill entities
    private SkillRepository skillRepository;

    @GetMapping("/")
    public String index(Model model) { //index method handles requests to root url /skills/
        // Retrieve all skills from the repository and add them to the model
        model.addAttribute("title", "All Skills");
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";// Return the view name for the skills listing page
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) { //method handles requests to /skills/add
        model.addAttribute(new Skill()); // Create a new Skill/prepare empty Skill object and add it to the model
        return "skills/add";// Return the view name for the add skill form
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill, Errors errors, Model model) {
        // handles form submission when adding new skill
        // Validate the submitted newSkill
        if (errors.hasErrors()) {
            return "skills/add"; // Return to the add skill form if errors
        }

        // otherwise saves valid newSkill to the repository
        skillRepository.save(newSkill);
        return "redirect:/skills/";
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) { //method handles requests to /skills/view/{skillId}

        Optional<Skill> optSkill = skillRepository.findById(skillId); //retrieves a skill by ID from repository
        if (optSkill.isPresent()) { //if skill exists adds it to the model and return view skills/view
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        } else {
            return "redirect:../"; // Redirect to the skills listing page if skill not found
        }
    }
}