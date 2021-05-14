package com.klymchuk.school.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "main_teacher")
public class MainTeacher extends User{

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "image_url")
    String imageUrl;

}
