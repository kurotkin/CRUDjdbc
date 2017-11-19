package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vitaly on 19.11.2017.
 */
public class Company {
    private Long id;
    private String name;
    private Set<Project> projects = new HashSet<>();

    public Company() {
    }

    public Company(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(Long id, String name, Set<Project> projects) {
        this.id = id;
        this.name = name;
        this.projects = projects;
    }

    public Company withId(Long id) {
        this.id = id;
        return this;
    }

    public Company withName(String name) {
        this.name = name;
        return this;
    }

    public Company withProjects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
