package com.parsakav.langclass.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "course")
@Access(AccessType.FIELD)
public class Course {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "course_id")
private long id;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:ss")

    private Date startdate;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)

    private Date enddate;
    @ManyToOne(optional = false)
    @JoinTable(name = "teacher_period")
    private Teacher teacher;
    @Column(nullable = false)

    private int maxstudent=0;
    @Column(nullable = false)
    private String seconddayinweek;
    @Column(nullable = false)
    private int hour=0;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.EAGER)

    private Set<Lession> lession;

    @ManyToOne
    @JoinTable(name = "language_period")
    private Language language;
    @OneToMany(mappedBy = "courses",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
private Set<User> users;
    @Transient
    private int lessionsize=0;
    @Transient
    private int usersnum=0;

    public int getLessionsize() {
lessionsize=lession.size();
        return lessionsize;
    }

    public int getUsersnum() {
        usersnum=users.size();
        System.out.println("USER NUM"+usersnum);
        return usersnum;
    }


    public String getSeconddayinweek() {
        return seconddayinweek;
    }

    public void setSeconddayinweek(String seconddayinweek) {
        this.seconddayinweek = seconddayinweek;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Lession> getLession() {
        return lession;
    }

    public void setLession(Set<Lession> lession) {
        this.lession = lession;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getMaxstudent() {
        return maxstudent;
    }

    public void setMaxstudent(int maxstudent) {
        this.maxstudent = maxstudent;
    }
}
