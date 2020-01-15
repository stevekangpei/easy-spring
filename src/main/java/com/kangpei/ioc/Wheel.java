package com.kangpei.ioc;

public class Wheel {

    private String name;

    private String specification;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}
