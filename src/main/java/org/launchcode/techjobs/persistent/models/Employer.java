package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {
    @NotBlank
    @Size(min=1,max=100,message = "Entered location cannot be greater than 100 characters")
    private String location;
    
    // NEED TO RE-VISIT THESE ANNOTATIONS------------------------
    @OneToMany
    //-----------------------------------------------------
    //@JoinColumn(name="job_id")
    //@JoinColumn
    //------------------------------------------------------------
    private final List<Job> jobs=new ArrayList<>();

    public Employer(){}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
