package it.univr.houdini.model;
import static it.univr.houdini.model.Literal.ExtensionCase;
import static it.univr.houdini.model.Literal.ExtensionCase.*;
import static it.univr.houdini.model.Literal.ExtensionState.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    private String literalsInvolvedInSupRelCycles = "";
    private String literalsValidation = "";

    public Validator () { }
    
    /**
     * Computes the validation of the literals in the theory.
     * @return A reference to the object itself.
     */
    public Validator validate (List<Literal> literals) {
        // when we validate a literal, we immediately validate its opposite, so we want to remember
        // and if we encounter the opposite later again in the list, we skip it
        Set<Literal> alreadyCheckedLiterals = new HashSet<>();
        // at every iteration, elements of the array are overwritten (so we use the same memory)
        for(Literal literal : literals) {
            if (alreadyCheckedLiterals.contains(literal)) {continue;} // if we already met this literal as an opposite
            String literalString = literal.toString();
            String oppositeString = (literal.isPositive()) ? "~" + literal.getLabel() : literal.getLabel();
            ExtensionCase literalCase = literal.getExtensionCase();
            ExtensionCase oppositeCase = (literal.getOpposite() != null) ? literal.getOpposite().getExtensionCase() : CASE_F;
            
            String coupleValidation;
            if (
                (literalCase == CASE_A && oppositeCase == CASE_A) ||
                (literalCase == CASE_A && oppositeCase == CASE_C) ||
                (literalCase == CASE_C && oppositeCase == CASE_A) ||
                (literalCase == CASE_A && oppositeCase == CASE_E) ||
                (literalCase == CASE_E && oppositeCase == CASE_A) ||
                (literalCase == CASE_A && oppositeCase == CASE_F) ||
                (literalCase == CASE_F && oppositeCase == CASE_A) ||
                (literalCase == CASE_B && oppositeCase == CASE_F) ||
                (literalCase == CASE_F && oppositeCase == CASE_B) ||
                (literalCase == CASE_C && oppositeCase == CASE_C) ||
                (literalCase == CASE_C && oppositeCase == CASE_F) ||
                (literalCase == CASE_F && oppositeCase == CASE_C) ||
                (literalCase == CASE_D && oppositeCase == CASE_F) ||
                (literalCase == CASE_F && oppositeCase == CASE_D) ||
                (literalCase == CASE_E && oppositeCase == CASE_E) ||
                (literalCase == CASE_E && oppositeCase == CASE_F) ||
                (literalCase == CASE_F && oppositeCase == CASE_E) ||
                (literalCase == CASE_F && oppositeCase == CASE_F)
            ) {
                coupleValidation = "Possible";
            } else if (
                (literalCase == CASE_A && oppositeCase == CASE_B) ||
                (literalCase == CASE_B && oppositeCase == CASE_A) ||
                (literalCase == CASE_A && oppositeCase == CASE_D) ||
                (literalCase == CASE_D && oppositeCase == CASE_A) ||
                (literalCase == CASE_B && oppositeCase == CASE_B) ||
                (literalCase == CASE_B && oppositeCase == CASE_C) ||
                (literalCase == CASE_C && oppositeCase == CASE_B) ||
                (literalCase == CASE_B && oppositeCase == CASE_D) ||
                (literalCase == CASE_D && oppositeCase == CASE_B) ||
                (literalCase == CASE_C && oppositeCase == CASE_D) ||
                (literalCase == CASE_D && oppositeCase == CASE_C)
            ) {
                coupleValidation = "NP(1)";
            } else if (
                (literalCase == CASE_C && oppositeCase == CASE_E) ||
                (literalCase == CASE_E && oppositeCase == CASE_C)
            ) {
                coupleValidation = "NP(2)";
            } else if (
                (literalCase == CASE_B && oppositeCase == CASE_E) ||
                (literalCase == CASE_E && oppositeCase == CASE_B) ||
                (literalCase == CASE_D && oppositeCase == CASE_E) ||
                (literalCase == CASE_E && oppositeCase == CASE_D) ||
                (literalCase == CASE_D && oppositeCase == CASE_D)
            ) {
                coupleValidation = "NP(3): this theory presents cycles in superiority relations.";
                literalsInvolvedInSupRelCycles += (literal.isPositive()) ? // positive first
                    literalString + ", " + oppositeString + ", "
                    :
                    oppositeString + ", " + literalString + ", "
                ;
            } else {
                // this should never occour: all 36 possible combinations have been considered
                // if this occours, there must have been some CASE_X literals
                coupleValidation = "There must be an error with the case of a literal (probably CASE_X).";
            }

            literalsValidation += (literal.isPositive()) ?  // positive first
                "{(" + literalString + "  " + ((literal.getDeltaState() == PLUS) ? "+Δ" : "-Δ") +
                "  " + ((literal.getPartialState() == PLUS) ? "+∂" : "-∂") + "  " + literalCase + "), (" +
                oppositeString + "  " + ((literal.getOpposite() == null) ? "-Δ" : ((literal.getDeltaState() == PLUS) ? "+Δ" : "-Δ")) +
                "  " + ((literal.getOpposite() == null) ? "-∂" : ((literal.getPartialState() == PLUS) ? "+∂" : "-∂")) + "  " + oppositeCase + "), " + coupleValidation + "}, "
                :
                "{(" + oppositeString + "  " + ((literal.getOpposite() == null) ? "-Δ" : ((literal.getDeltaState() == PLUS) ? "+Δ" : "-Δ")) +
                "  " + ((literal.getOpposite() == null) ? "-∂" : ((literal.getPartialState() == PLUS) ? "+∂" : "-∂")) + "  " + oppositeCase + "), (" +
                literalString + "  " + ((literal.getDeltaState() == PLUS) ? "+Δ" : "-Δ") +
                "  " + ((literal.getPartialState() == PLUS) ? "+∂" : "-∂") + "  " + literalCase + "), " + coupleValidation + "}, "
            ;

            if (literal.getOpposite() != null) {alreadyCheckedLiterals.add(literal.getOpposite());}
            // System.out.println(literal.toString() + " {delta: " + deltaState + " partial: " + partialState + " case: " + extensionCase +"}");
        }

        return this;
    }

    public String getLiteralsInvolvedInSupRelCycles() {
        if (literalsInvolvedInSupRelCycles.equals("")) {return "∅";}
        else {
            return literalsInvolvedInSupRelCycles.substring(0, literalsInvolvedInSupRelCycles.length() - 2); // remove last ", "
        }
    }

    public String getLiteralsValidation() {
        if (literalsValidation.equals("")) {return "∅";}
        else {
            return literalsValidation.substring(0, literalsValidation.length() - 2); // remove last ", "
        }
    }

}
