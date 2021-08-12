package com.example.socialMedia.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id ;

    @Column(unique = true)
    String username ;

    String password ;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Post> posts ;

    @ManyToMany
    @JsonIgnore
    List<GroupOfUsers> groups;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    List<GroupOfUsers> groupsAdminInIt;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Comment> commentsWriteIt;

    boolean isAdmin ;
}
