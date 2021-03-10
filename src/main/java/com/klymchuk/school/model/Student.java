package com.klymchuk.school.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"clazz", "journals"})
@Entity
@Table(name = "student")
public class Student extends User{

    @Column(name = "image_url")
    String imageUrl;

    @OneToMany(mappedBy = "student")
    private List<Journal> journals;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

}
