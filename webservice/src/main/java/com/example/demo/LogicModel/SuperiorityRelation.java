package com.example.demo.LogicModel;import java.util.*;

public class SuperiorityRelation {

    private Rule superior;
    private Rule inferior;

    public SuperiorityRelation(Rule superior, Rule inferior) {
        this.superior = superior;
        this.inferior = inferior;
    }

    public Rule getSuperior() {
        return superior;
    }

    public void setSuperior(Rule superior) {
        this.superior = superior;
    }

    public Rule getInferior() {
        return inferior;
    }

    public void setInferior(Rule inferior) {
        this.inferior = inferior;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof SuperiorityRelation)) return false;
        if (!super.equals(object)) return false;
        SuperiorityRelation that = (SuperiorityRelation) object;
        return getSuperior().equals(that.getSuperior()) && getInferior().equals(that.getInferior());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getSuperior(), getInferior());
    }
}