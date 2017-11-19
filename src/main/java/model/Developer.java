package model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vitaly on 19.11.2017.
 */
public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialty;
    private BigDecimal salary;
    private Set<Skill> skills = new HashSet<>();
    private Set<Project> projects = new HashSet<>();



    public Developer() {
    }

    public Developer(Long id, String firstName, String lastName, String specialty, BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.salary = salary;
    }

    public Developer withId(Long id) {
        this.id = id;
        return this;
    }

    public Developer withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Developer withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Developer withSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public Developer withSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public Developer withSkills(Set<Skill> skills) {
        this.skills = skills;
        return this;
    }

    public Developer withProjects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
