package com.aimprosoft.sandbox.database.department;

/**
 * @author BaLiK on 25.03.19
 */
public class Department {
    private Long ID;
    private String name;

    public Department(Long id) {
        ID=id;
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
