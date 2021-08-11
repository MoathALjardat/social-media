package com.example.socialMedia.demo.services;

import com.example.socialMedia.demo.models.GroupOfUsers;
import com.example.socialMedia.demo.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public int getNumberOfGroups() {
        return (int) groupRepository.count();
    }

    public List<GroupOfUsers> getGroups() {
        return groupRepository.findAll();
    }

    public GroupOfUsers getGroupById(int id) {
        return groupRepository.findById(id).orElse(null);
    }

    public GroupOfUsers addGroup(GroupOfUsers groupOfUsers) {
        return groupRepository.save(groupOfUsers);
    }

    public GroupOfUsers deleteGroup(int id) {
        GroupOfUsers deletedGroupOfUsers = groupRepository.findById(id).orElse(null);
        groupRepository.deleteById(id);
        return deletedGroupOfUsers;
    }

    public GroupOfUsers updateGroup(GroupOfUsers groupOfUsers) {
        groupRepository.save(groupOfUsers);
        return groupOfUsers;
    }
}
