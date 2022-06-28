package com.example.services;

import com.example.dao.RoleDaoImpl;
import com.example.model.Role;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl {
    private final RoleDaoImpl roleDao;

    @Autowired
    public RoleServiceImpl(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Transactional
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Transactional
    public void removeRole(int id) {
        roleDao.removeRole(id);
    }

    public Role findById(int id) {
        Optional<Role> opt = roleDao.getRoleById(id);
        if (opt.isEmpty()) {
            return null;
        } else {
            return opt.get();
        }
    }
    public List<Role> listRoles() {
        return roleDao.listRoles();
    }
    public Optional<Role> findByRoleName(String roleName) {
        return roleDao.findByRoleName(roleName);
    }


//    @Transactional
//    public Role loadRoleByName(String roleName) throws UsernameNotFoundException {
////        Role role = findByRoleName(roleName).get();
////        if (role == null) {
////            throw new UsernameNotFoundException(String.format("Role '%s' not found", roleName));
////        }
////        return new org.springframework.security.core.userdetails.Role(role.getName());
//        return roleDao.findByRoleName(roleName).orElseThrow(() -> new IllegalArgumentException("Role not found"));
//    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
    public Collection<Role> getRoles(String[] roles) {
        Collection<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(findByRoleName(role).get());
        }
        return roleSet;
    }

}
