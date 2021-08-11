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
public class User {

    @Id
    @GeneratedValue
    int id ;

    String username ;
    String password ;

    @OneToMany()
    @JsonIgnore
    List<Post> posts ;

    @ManyToMany
    List<GroupOfUsers> groupsOfUsers;

    boolean isAdmin ;

}
