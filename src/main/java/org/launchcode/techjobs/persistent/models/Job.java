package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Job extends AbstractEntity {

    @ManyToMany
    @JoinTable(
            name = "job_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;
//    private List<Skill> skills = new ArrayList<>();

    @ManyToOne
    private Employer employer;

    public Job() {

    }

    public Job(Employer employer) {
        this.employer = employer;
    }


    // Initialize the id and value fields.
    public Job(Employer employer, List<Skill> someSkills) {
        super();
        this.employer = employer;
        this.skills = someSkills;
    }

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

    public void addSkill(Skill skill) {
        this.skills.add(skill);
        skill.getJobs().add(this);
    }

    public void removeSkill(Skill skill) {
        this.skills.remove(skill);
        skill.getJobs().remove(this);
    }
}

