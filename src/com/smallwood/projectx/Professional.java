package com.smallwood.projectx;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.validator.constraints.Email;

/**
 * Created by bigwood928 on 4/14/14.
 */

@Entity
@Table(name="professionals")
public class Professional {

    private HashSet<String> skills = new HashSet<String>();
    private String firstName ="";
    private String lastName = "";
    private String education="";
    private String email="";
    private String password="";
    private long id=0;

    public static Professional EMPTY = new Professional();

    public Professional() {

    }

    public Professional(String firstName, String lastName, String email, String education, HashSet<String> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.education = education;
        this.skills = skills;
    }


    public HashSet<String> getSkills() {
        return skills;
    }

    public void setSkills(HashSet<String> skills) {
        if(isValidSkill(skills)) {
            this.skills = skills;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(isValidName(firstName)) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(isValidName(lastName)) {
            this.lastName = lastName;
        }
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Email
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public boolean isValidName(String name) {
        return !name.isEmpty();
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isValidSkill(Collection<String> skills) {
        if(skills.isEmpty()) return false;
        boolean allSkillsValid = true;
        for(String skill : skills) {
            if(skill.isEmpty()){
                allSkillsValid = false;
            }
        }
        return allSkillsValid;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Professional that = (Professional) o;

        if (!education.equals(that.education)) return false;
        if (!email.equals(that.email)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (skills != null ? !skills.equals(that.skills) : that.skills != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }


}
