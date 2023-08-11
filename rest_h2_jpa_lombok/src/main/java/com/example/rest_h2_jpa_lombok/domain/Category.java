package com.example.rest_h2_jpa_lombok.domain;


import lombok.*;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortComparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Category {

    @Id //Primary Key definition
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}