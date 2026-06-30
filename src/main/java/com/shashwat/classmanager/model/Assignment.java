package com.shashwat.classmanager.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate dueDate; // a real date type, not just a String — lets us compare dates later (e.g. for "is this late?")

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    protected Assignment() {
    }

    public Assignment(String title, LocalDate dueDate, ClassRoom classRoom) {
        this.title = title;
        this.dueDate = dueDate;
        this.classRoom = classRoom;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }
}