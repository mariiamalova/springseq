package com.example.dao;

import com.example.model.Role;
import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    public void addRole(Role role);
    public void updateRole(Role role);
    public void removeRole(int id);
    public Optional<Role> getRoleById(int id);
    public List<Role> listRoles();
    public Optional<Role> findByRoleName(String roleName);
}
