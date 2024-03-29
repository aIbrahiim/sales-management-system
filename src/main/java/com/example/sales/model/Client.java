package com.example.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

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