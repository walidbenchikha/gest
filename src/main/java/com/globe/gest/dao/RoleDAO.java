package com.globe.gest.dao;

import java.util.List;

import com.globe.gest.exception.DuplicateRoleException;
import com.globe.gest.exception.RoleNotFoundException;
import com.globe.gest.model.Role;

public interface RoleDAO {

    public void addRole(Role role) throws DuplicateRoleException;

    public Role getRole(int id) throws RoleNotFoundException;

    public Role getRole(String roleName) throws RoleNotFoundException;

    public void updateRole(Role role) throws RoleNotFoundException, DuplicateRoleException;

    public void deleteRole(int id) throws RoleNotFoundException;

    public List<Role> getRoles();

	public Role getRole();

}
