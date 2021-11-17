package com.docjpa.laba2.JPA.S.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class ListDocs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_doc;

    private String title_doc, description_doc;

    private LocalDate creation_doc, registration_doc;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String author;

    private int views;

    public Long getId_doc() {
        return id_doc;
    }

    public void setId_doc(Long id_doc) {
        this.id_doc = id_doc;
    }

    public String getTitle_doc() {
        return title_doc;
    }

    public void setTitle_doc(String title_doc) {
        this.title_doc = title_doc;
    }

    public String getDescription_doc() {
        return description_doc;
    }

    public void setDescription_doc(String description_doc) {
        this.description_doc = description_doc;
    }

    public LocalDate getCreation_doc() {
        return creation_doc;
    }

    public void setCreation_doc(LocalDate creation_doc) {
        this.creation_doc = creation_doc;
    }

    public LocalDate getRegistration_doc() {
        return registration_doc;
    }

    public void setRegistration_doc(LocalDate registration_doc) {
        this.registration_doc = registration_doc;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public ListDocs() {
    }

    public ListDocs(String title_doc, String description_doc, LocalDate creation_doc, LocalDate registration_doc, String status, String author ) {

        this.title_doc = title_doc;
        this.description_doc = description_doc;
        this.creation_doc = creation_doc;
        this.registration_doc = registration_doc;
        this.status = status;
        this.author = author;

    }
}
