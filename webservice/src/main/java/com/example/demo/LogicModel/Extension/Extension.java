package com.example.demo.LogicModel.Extension;

import java.util.Set;
import java.util.TreeSet;

import com.example.demo.LogicModel.Literal;

public class Extension {
    Set<Literal> plusDelta = new TreeSet<Literal>();
    Set<Literal> minusDelta = new TreeSet<Literal>();
    Set<Literal> plusPartial = new TreeSet<Literal>();
    Set<Literal> minusPartial = new TreeSet<Literal>();
    
    public Extension() {};

    public Extension(Set<Literal> plusDelta, Set<Literal> minusDelta, Set<Literal> plusPartial,
            Set<Literal> minusPartial) {
        this.plusDelta = plusDelta;
        this.minusDelta = minusDelta;
        this.plusPartial = plusPartial;
        this.minusPartial = minusPartial;
    }
    public Set<Literal> getPlusDelta() {
        return plusDelta;
    }
    public void setPlusDelta(Set<Literal> plusDelta) {
        this.plusDelta = plusDelta;
    }
    public Set<Literal> getMinusDelta() {
        return minusDelta;
    }
    public void setMinusDelta(Set<Literal> minusDelta) {
        this.minusDelta = minusDelta;
    }
    public Set<Literal> getPlusPartial() {
        return plusPartial;
    }
    public void setPlusPartial(Set<Literal> plusPartial) {
        this.plusPartial = plusPartial;
    }
    public Set<Literal> getMinusPartial() {
        return minusPartial;
    }
    public void setMinusPartial(Set<Literal> minusPartial) {
        this.minusPartial = minusPartial;
    }
    public String getPlusDeltaString() {
        return this.printSetString(this.plusDelta);
    }
    public String getMinusDeltaString() {
        return this.printSetString(this.minusDelta);
    }
    public String getPlusPartialString() {
        return this.printSetString(this.plusPartial);
    }
    public String getMinusPartialString() {
        return this.printSetString(this.minusPartial);
    }
    public String printSetString(Set<Literal> set) {
        if (set.isEmpty()) {
            return "∅";
        }
        else {
            String s = set.toString();
            return s.substring(1, s.length()-1);
        }
    }
    
}
