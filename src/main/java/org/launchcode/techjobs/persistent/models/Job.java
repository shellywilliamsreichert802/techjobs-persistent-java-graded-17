
package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Job extends AbstractEntity {

    @ManyToOne //employer associated with many to one relationship
    private Employer employer;

    @ManyToMany
    private List<Skill> skills; //represents skills needed for the job with many to many rltnshp

    public Job() {
//default constructor
    }

    public Job(Employer employer) {
        this.employer = employer;
    }
//parameterized constructor that takes an employer

    // Initialize the id and value fields.
    public Job(Employer employer, List<Skill> someSkills) {
        super();
        this.employer = employer;
        this.skills = someSkills;
    } //parameterized constructor takes an employer and a list of skills

    // Getters and setters.

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

//    public void addSkill(Skill skill) {
//        this.skills.add(skill);
//        skill.getJobs().add(this);
//    }
//
//    public void removeSkill(Skill skill) {
//        this.skills.remove(skill);
//        skill.getJobs().remove(this);

}
