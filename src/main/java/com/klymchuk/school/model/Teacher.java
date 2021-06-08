package com.klymchuk.school.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher extends User{

    @Column(name = "image_url")
    String imageUrl;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects = new ArrayList<>();

}
