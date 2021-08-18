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
public class GroupOfUsers {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id ;

    String groupName ;

    @ManyToMany
    @JsonIgnore
    List<User> users ;

    @ManyToOne
    @NotNull
    @JoinColumn(nullable = false)
    User groupAdmin ;

    @OneToMany
    @JsonIgnore
    List<Post> posts ;

    @ManyToMany
    @JsonIgnore
    List<User> waitingListOfUsers ;

    Status status ;

}
