package org.example.dao;

import org.example.model.Branch;

import java.util.List;

public class BranchDAO {
    private static int countBranch;

    private List<Branch> branches;
    {
        branches.add(new Branch(++countBranch, "auto", 1));
        branches.add(new Branch(++countBranch, "sport", 2));
        branches.add(new Branch(++countBranch, "music", 3));
    }


    public void newBranch(Branch branch) {
        branches.add(new Branch(++countBranch, branch.getName(), branch.getAuthor()));

    }

    public void delete(Branch branch) {
        branches.removeIf(b -> b.getId() == branch.getId());
    }

    public List<Branch> getAll() {
        return branches;
    }

    public Branch getByiD(int id) {
        return branches.stream().filter(b -> b.getId() == id).findAny().orElse(null);

    }

}
