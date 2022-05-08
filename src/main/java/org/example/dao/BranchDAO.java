package org.example.dao;
import org.example.model.Branch;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class BranchDAO {
    private static int countBranch;



    private List<Branch> branches;
    {   branches = new ArrayList<>();
        branches.add(new Branch(++countBranch, "auto", 1));
        branches.add(new Branch(++countBranch, "sport", 2));
        branches.add(new Branch(++countBranch, "music", 3));
    }

    public int newBranch(Branch branch) {
        branches.add(new Branch(++countBranch, branch.getName(), branch.getAuthor()));
        return countBranch;
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
