package com.example.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "creation_date", nullable = true)
    private Date creationDate;

    @Column(name = "last_modified_date", nullable = true)
    private Date lastModifiedDate;

    @PrePersist
    public void onPrePersist() {
        this.creationDate = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.lastModifiedDate = new Date();
    }

    @PreRemove
    public void onPreRemove() {
        log.info("Preparing to delete entity with id {}", this.id);
    }
}
