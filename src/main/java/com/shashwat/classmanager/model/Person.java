package com.shashwat.classmanager.model;

import jakarta.persistence.*;

// @Entity tells Spring/Hibernate: "this class maps to a database table."
// Without this, JPA ignores the class completely — it's just a plain Java class.
@Entity

// This is the inheritance strategy we just discussed.
// JOINED means: Person gets its own table, and Student/Faculty each get
// their OWN table too, linked back to Person's table via a shared ID (foreign key).
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    // @Id marks this field as the PRIMARY KEY of the table.
    // Every JPA entity needs exactly one @Id field.
    @Id
    // @GeneratedValue tells the database to auto-generate this value
    // (e.g. 1, 2, 3...) — we never set it manually ourselves.
    // strategy = IDENTITY means "let the database handle auto-incrementing."
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // IMPORTANT: JPA requires a no-argument constructor on every entity.
    // Hibernate uses this internally (via reflection) when it loads data
    // FROM the database and reconstructs your Java objects — it creates
    // an empty object first, then fills in the fields itself.
    // We don't call this ourselves — it's just required to exist.
    protected Person() {
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}