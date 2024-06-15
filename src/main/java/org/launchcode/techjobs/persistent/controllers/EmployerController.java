package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller // indicates that this class handles HTTP requests.
@RequestMapping("employers") //specifies the base URL path for all endpoints in this controller.
public class EmployerController {

    //
    @Autowired
    private EmployerRepository employerRepository;
// injected an EmployerRepository bean using @Autowired. allows interaction with the database and perform CRUD operations on Employer entities
    @GetMapping("/")
    //index method handles requests to the root URL (/employers/)
//retrieves all employers from the repository and adds them to the model.
    public String index(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index"; //view name returned
    }
//

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        //displayAddEmployerForm method handles requests to /employers/add
        //prepares an empty Employer object and adds it to the model.
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                         Errors errors, Model model) {
//method handles form submissions when adding a new employer
        if (errors.hasErrors()) { //if errors, it returns to the add form view
            return "employers/add";
        }
        //validates the submitted newEmployer object using @Valid and checks for errors.
        employerRepository.save(newEmployer);
        //saves the new employer to the repository and redirects to the index page.
        return "redirect:/employers/";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
        //method handles requests to /employers/view/{employerId}
        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        //retrieves an employer by ID from the repositor

//*        Optional optEmployer = null;
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
//if employer exists, it adds it to the model and returns the view "employers/view"
            return "employers/view";

        } else {
            return "redirect:../"; //if not redirects to root url
        }
    }
}