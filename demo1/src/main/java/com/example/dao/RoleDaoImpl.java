package com.example.dao;

import com.example.model.Role;
import com.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void removeRole(int id) {
        Query query = entityManager.createQuery("delete FROM Role where id = :id").setParameter("id", id);
        query.executeUpdate();

    }

    @Override
    public Optional<Role> getRoleById(int id) {
        return Optional.of(entityManager.find(Role.class, id));
    }

    @Override
    public List<Role> listRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Optional<Role> findByRoleName(String roleName) {
        Query query = entityManager.createQuery(" SELECT role from Role role where role.name = : name").setParameter("name", roleName);
        List<Role> resultlist = query.getResultList();
        Role role = resultlist.get(0);
        Optional<Role> optUser = Optional.of(role);
        return optUser;
    }
}
