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

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/")
    public String index(Model model) {
        // Retrieve all skills from the repository and add them to the model
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";// Return the view name for the skills listing page
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
//        model.addAttribute("title", "Add Skill");
        model.addAttribute(new Skill()); // Create a new Skill object and add it to the model
        return "skills/add";// Return the view name for the add skill form
    }

    @PostMapping("add")
    public String processAddSkillFrom(@ModelAttribute @Valid Skill skill, Errors errors, Model model) {
        // Validate the submitted skill
        if (errors.hasErrors()) {
            return "skills/add"; // Return to the add skill form with validation errors
        }

        // Save the valid skill to the repository
        skillRepository.save(skill);
        return "redirect:skills/";// Redirect to the skills listing page
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        Optional<Skill> optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {            // Add the skill to the model and return the view for viewing a single skill
//            Skill skill = (Skill) optSkill.get();
//            model.addAttribute("skill", skill);
            model.addAttribute("skill", optSkill.get());
            return "skills/view";
        } else {
            return "redirect:skills/"; // Redirect to the skills listing page if skill not found
        }
    }
}

