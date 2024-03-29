package com.example.sales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Entity
@Table(name = "sales_operations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class SalesOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(name = "total", nullable = false)
    private double total;

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