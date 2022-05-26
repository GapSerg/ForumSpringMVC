package org.example.dao.impl;
import org.example.dao.DAO;
import org.example.model.Branch;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.annotations.QueryBinder;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Component
public class BranchDAO implements DAO<Branch> {


    private final SessionFactory sessionFactory;

    @Autowired
    public BranchDAO(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }
    private static int countBranch;



    private List<Branch> branches;
    {   branches = new ArrayList<>();
        branches.add(new Branch(++countBranch, "auto", 1));
        branches.add(new Branch(++countBranch, "sport", 2));
        branches.add(new Branch(++countBranch, "music", 3));
    }
    @Transactional(readOnly = true)
    @Override
    public List<Branch> getAll() {
        System.out.println("-------------------getAll------------------");
        return currentSession().createQuery("from org.example.model.Branch", Branch.class).list();
    }


    @Override
    @Transactional
    public void save(Branch branch) {
        System.out.println("-------------------save------------------");
       currentSession().save(branch);
    }

    @Override
    @Transactional
    public void update(int id, Branch updateBranchData) {
        System.out.println("-------------------Update------------------");
        Branch branchToBeUpdated = currentSession().get(Branch.class, id);
        System.out.println("------"+branchToBeUpdated.getName()+"-------");
        branchToBeUpdated.setName(updateBranchData.getName());
        System.out.println("------"+branchToBeUpdated.getName()+"-------");

    }

    @Override
    @Transactional
    public void delete(int id) {
        System.out.println("-------------------delete------------------");

        currentSession().remove(currentSession().get(Branch.class, id));

    }


    @Override
    @Transactional(readOnly = true)
    public Branch getById(int id) {
        System.out.println("-------------------getById-"+id+"-----------------");
        return currentSession().get(Branch.class, id);

    }
    @Transactional(readOnly = true)
    public Branch getByName(String branchName){
        System.out.println("-------------------getByName------------------");
        Query<Branch> q = currentSession().createQuery(
                "from org.example.model.Branch where name = :name",
                Branch.class);
        q.setParameter("name", branchName);
        return  q.list().stream().findAny().orElse(null);

    }

}
