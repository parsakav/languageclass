package com.parsakav.langclass.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "language")
@Access(AccessType.FIELD)
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column(name="lang_name")
    private String langname;
    //cascade =CascadeType.ALL
    @OneToMany(mappedBy = "language",cascade = CascadeType.ALL)
    private Set<Course> Course;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLangname() {
        return langname;
    }

    public void setLangname(String langname) {
        this.langname = langname;
    }

    public Set<Course> getCourse() {
        return Course;
    }

    public void setCourse(Set<Course> peroid) {
        this.Course = peroid;
    }
}
