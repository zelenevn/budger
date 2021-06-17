package com.budger.services;

import com.budger.data.entities.Role;
import com.budger.exceptions.RoleDoesNotExistsException;
import com.budger.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> readRolesByAccountLogin(String login) {
        return roleRepository.findByAccountLogin(login);
    }

    public Role readRoleByTitle(String title) {
        Optional<Role> roleOptional = roleRepository.findByTitle(title);
        if (roleOptional.isPresent())
            return roleOptional.get();
        else
            throw new RoleDoesNotExistsException(String.format("Role %s does not exist", title));
    }
}
