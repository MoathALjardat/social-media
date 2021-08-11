package com.example.socialMedia.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue
    int id ;

    String body ;

    @ManyToOne()
    @JoinColumn(name="id", referencedColumnName = "id", insertable = false, updatable = false)
    Post post ;

    @ManyToOne()
    @JoinColumn(name="id", referencedColumnName = "id", insertable = false, updatable = false)
    User writer ;

}
