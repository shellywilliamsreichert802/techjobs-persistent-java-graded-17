
package org.launchcode.techjobs.persistent.models;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    //written by Shelly
    @NotBlank(message = "Location is required")
    @Size(min = 1, max = 255, message = "Location must be between 1 and 255 characters")
    private String location;

    public Employer() {
    }

    public Employer(String location) {
        this.location = location;
    }

    @OneToMany //annotation establishes a one-to-many relationship between Employer and Job.
    @JoinColumn(name = "employer_id") //specifies the foreign key column in the Job table that links to the Employer table.
    private final List<Job> jobs = new ArrayList<>();
//jobs field represents the list of jobs associated with this employer.


    // Getters and setters

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
