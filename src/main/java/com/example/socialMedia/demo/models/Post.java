package com.example.socialMedia.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue
    int id;

    String tittle;
    String body;

    boolean isPrivate;
    boolean isAccepted;

    @ManyToOne()
    @JoinColumn(name="id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    User writer;

    @ManyToOne()
    @JoinColumn(name="id", referencedColumnName = "id", insertable = false, updatable = false)
    GroupOfUsers groupOfUsers;

    @OneToMany()
    @JsonIgnore
    List<Comment> comments;
}
