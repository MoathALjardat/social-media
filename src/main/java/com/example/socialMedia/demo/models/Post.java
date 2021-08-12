package com.example.socialMedia.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Post {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    String tittle;
    String body;

    boolean isPrivate;
    boolean isAccepted;

    @ManyToOne()
    @NotNull
    @JoinColumn(nullable = false)
    User writer;

    @ManyToOne()
    @JoinColumn(nullable = true)
    GroupOfUsers groupOfUsers;

    @OneToMany()
    @JsonIgnore
    List<Comment> comments;
}
