package org.example.dao.impl;
import org.example.dao.DAO;
import org.example.model.Branch;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class BranchDAO implements DAO<Branch> {
    private static int countBranch;



    private List<Branch> branches;
    {   branches = new ArrayList<>();
        branches.add(new Branch(++countBranch, "auto", 1));
        branches.add(new Branch(++countBranch, "sport", 2));
        branches.add(new Branch(++countBranch, "music", 3));
    }

    @Override
    public List<Branch> getAll() {
        return branches;
    }
    @Override
    public void save(Branch branch) {
        branches.add(new Branch(++countBranch, branch.getName(), branch.getAuthor()));
    }

    @Override
    public void update(int id, Branch updateBranchData) {
     Branch currentBranch = getById(id);
     currentBranch.setName(updateBranchData.getName());
    }

    @Override
    public void delete(int id) {
        branches.removeIf(b -> b.getId() == id);
    }


    @Override
    public Branch getById(int id) {
        return branches.stream().filter(b -> b.getId() == id).findAny().orElse(null);

    }
    public Branch getByName(String branchName){
        return branches.stream().filter(b -> b.getName().equals(branchName)).findAny().orElse(null);

    }



}
