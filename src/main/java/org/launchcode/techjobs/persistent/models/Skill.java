
package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity//indicates that this class is a JPA entity, which means it corresponds to a database table.
public class Skill extends AbstractEntity {

    //written by Shelly
    @NotBlank(message = "Description is required")
    @Size(max = 75, message = "Field must be 75 characters or less.")
    private String description;
    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();
//A many-to-many relationship with the Job entity. It is mapped by the skills field in the Job class.

    public Skill() { //default constructor
    }

    public Skill(String description, List<Job> jobs) { //parameterized constructor takes description and list of jobs
        this.description = description;
        this.jobs = jobs;
    }

    public List<Job> getJobs() { // Returns the list of jobs associated with this skill.

        return jobs;
    }

    public void setJobs(List<Job> jobs) { //Sets the list of jobs associated with this skill.
        this.jobs = jobs;
    }

    public String getDescription() { //returns skill description
        return description;
    }

    public void setDescription(String description) { //sets skill description
        this.description = description;
    }
}
