package com.parsakav.langclass.service;

import com.parsakav.langclass.model.Role;
import com.parsakav.langclass.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class RoleServiceImpl implements RoleService{
@Autowired
private RoleRepository roleRepository;


    @Override
    public void saveRole(Role role) {


        roleRepository.save(role);


    }



}
