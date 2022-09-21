package com.example.demo.LogicModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.Pair;
import static com.example.demo.LogicModel.Literal.ExtensionState;
import static com.example.demo.LogicModel.Literal.ExtensionCase;
// using static imports can make the code better readable, but pay attention to not overlap values among enums or use keywords
import static com.example.demo.LogicModel.Literal.ExtensionState.*;
import static com.example.demo.LogicModel.Literal.ExtensionCase.*;

public class Validator {

    //private List<Pair<Literal, Case>> literals = new ArrayList<Pair<Literal, Case>>();

    public Validator () { }

    public void validate (Set<Literal> setLiterals) {
        for(Literal literal : setLiterals) {
            ExtensionState deltaState = literal.getDeltaState();
            ExtensionState partialState = literal.getPartialState();
            ExtensionCase extensionCase = literal.getExtensionCase();

            System.out.println(literal.toString() + " {delta: " + deltaState + " partial: " + partialState + " case: " + extensionCase +"}");
        }
    }

}
