package org.launchcode.techjobs.persistent.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity{
    @ManyToOne
    private Employer employer;
    //private String skills;
    // ADDED BY DAG
    @ManyToMany
    private List<Skill> skills = new ArrayList<>();

    public Job() {
    }

//    public Job(Employer anEmployer, String someSkills) {
//        super();
//        this.employer = anEmployer;
//        this.skills = someSkills;
//    }

    public Job(Employer anEmployer, List<Skill> someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    //public String getSkills() {
    //    return skills;
    //}

    //public void setSkills(String skills) {
    //    this.skills = skills;
    //}

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
