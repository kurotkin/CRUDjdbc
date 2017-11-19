package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vitaly on 19.11.2017.
 */
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<Project> projects = new HashSet<>();

    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, Set<Project> projects) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.projects = projects;
    }

    public Customer withId(Long id) {
        this.id = id;
        return this;
    }

    public Customer withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Customer withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Customer withProjects(Set<Project> projects) {
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

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
