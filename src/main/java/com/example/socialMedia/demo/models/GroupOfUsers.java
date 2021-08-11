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
public class GroupOfUsers {
    @Id
    @GeneratedValue
    int id ;

    String groupName ;

    @ManyToMany
    @JsonIgnore
    List<User> users ;

    @ManyToOne
    @JoinColumn(name="id", referencedColumnName = "id", insertable = false, updatable = false)
    User groupAdmin ;

    @OneToMany
    @JsonIgnore
    List<Post> posts ;

    boolean isAccepted ;

}
