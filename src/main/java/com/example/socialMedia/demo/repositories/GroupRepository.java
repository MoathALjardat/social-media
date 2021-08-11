package com.example.socialMedia.demo.repositories;

import com.example.socialMedia.demo.models.GroupOfUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupOfUsers,Integer> {
}
