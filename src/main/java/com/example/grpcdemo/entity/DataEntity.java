package com.example.grpcdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "datatable")
@Getter
@Setter
public class DataEntity {

    @Id
    private Long id;

    @Column(name = "data")
    private String data;
}
