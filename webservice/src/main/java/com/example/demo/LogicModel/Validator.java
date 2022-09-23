package com.example.demo.LogicModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.demo.LogicModel.Literal.ExtensionCase;
// using static imports can make the code better readable, but pay attention to not overlap values among enums or use keywords
import static com.example.demo.LogicModel.Literal.ExtensionState.*;
import static com.example.demo.LogicModel.Literal.ExtensionCase.*;

public class Validator {

    private String literalsInvolvedInSupRelCycles = "";
    private String literalsValidation = "";

    public Validator () { }
    
    /**
     * Computes the validation of the literals in the theory.
     * @return A reference to the object itself.
     */
    public Validator validate (List<Literal> literals) {
        Set<Literal> alreadyExposedLiterals = new HashSet<>();
        Object[][] couple = new Object[2][4]; // we want a general-type array, we will cast later
        // at every iteration, elements of the array are overwritten (so we use the same memory)
        for(Literal literal : literals) {
            if (alreadyExposedLiterals.contains(literal)) {continue;}
            int thisLiteralRow = 0, oppositeLiteralRow = 1; // we assume this literal is positive
            if (!literal.isPositive()) { // but we want the positive to be the first row, so in case it's not, we switch
                thisLiteralRow = 1;
                oppositeLiteralRow = 0;
            }
            couple[thisLiteralRow][0] = literal.toString(); // first element is the string exposition
            couple[thisLiteralRow][1] = literal.getDeltaState(); // second element is the delta state enum
            couple[thisLiteralRow][2] = literal.getPartialState(); // third element is the partial state enum
            couple[thisLiteralRow][3] = literal.getExtensionCase(); // fourth element is the extension case enum
            if (literal.getOpposite() != null) {
                couple[oppositeLiteralRow][0] = literal.getOpposite().toString();
                couple[oppositeLiteralRow][1] = literal.getOpposite().getDeltaState();
                couple[oppositeLiteralRow][2] = literal.getOpposite().getPartialState();
                couple[oppositeLiteralRow][3] = literal.getOpposite().getExtensionCase();
                alreadyExposedLiterals.add(literal.getOpposite());
            } else {
                couple[oppositeLiteralRow][0] = (literal.isPositive()) ? "~" + literal.getLabel() : literal.getLabel(); // TODO if we change how to store labels, this must change too
                couple[oppositeLiteralRow][1] = MINUS;
                couple[oppositeLiteralRow][2] = MINUS;
                couple[oppositeLiteralRow][3] = CASE_F;
            }
            
            String coupleValidation;
            if (
                ((ExtensionCase)couple[0][3] == CASE_A && (ExtensionCase)couple[1][3] == CASE_A) ||
                ((ExtensionCase)couple[0][3] == CASE_A && (ExtensionCase)couple[1][3] == CASE_C) ||
                ((ExtensionCase)couple[0][3] == CASE_C && (ExtensionCase)couple[1][3] == CASE_A) ||
                ((ExtensionCase)couple[0][3] == CASE_A && (ExtensionCase)couple[1][3] == CASE_E) ||
                ((ExtensionCase)couple[0][3] == CASE_E && (ExtensionCase)couple[1][3] == CASE_A) ||
                ((ExtensionCase)couple[0][3] == CASE_A && (ExtensionCase)couple[1][3] == CASE_F) ||
                ((ExtensionCase)couple[0][3] == CASE_F && (ExtensionCase)couple[1][3] == CASE_A) ||
                ((ExtensionCase)couple[0][3] == CASE_B && (ExtensionCase)couple[1][3] == CASE_F) ||
                ((ExtensionCase)couple[0][3] == CASE_F && (ExtensionCase)couple[1][3] == CASE_B) ||
                ((ExtensionCase)couple[0][3] == CASE_C && (ExtensionCase)couple[1][3] == CASE_C) ||
                ((ExtensionCase)couple[0][3] == CASE_C && (ExtensionCase)couple[1][3] == CASE_F) ||
                ((ExtensionCase)couple[0][3] == CASE_F && (ExtensionCase)couple[1][3] == CASE_C) ||
                ((ExtensionCase)couple[0][3] == CASE_D && (ExtensionCase)couple[1][3] == CASE_F) ||
                ((ExtensionCase)couple[0][3] == CASE_F && (ExtensionCase)couple[1][3] == CASE_D) ||
                ((ExtensionCase)couple[0][3] == CASE_E && (ExtensionCase)couple[1][3] == CASE_E) ||
                ((ExtensionCase)couple[0][3] == CASE_E && (ExtensionCase)couple[1][3] == CASE_F) ||
                ((ExtensionCase)couple[0][3] == CASE_F && (ExtensionCase)couple[1][3] == CASE_E) ||
                ((ExtensionCase)couple[0][3] == CASE_F && (ExtensionCase)couple[1][3] == CASE_F)
            ) {
                coupleValidation = "Possible";
            } else if (
                ((ExtensionCase)couple[0][3] == CASE_A && (ExtensionCase)couple[1][3] == CASE_B) ||
                ((ExtensionCase)couple[0][3] == CASE_B && (ExtensionCase)couple[1][3] == CASE_A) ||
                ((ExtensionCase)couple[0][3] == CASE_A && (ExtensionCase)couple[1][3] == CASE_D) ||
                ((ExtensionCase)couple[0][3] == CASE_D && (ExtensionCase)couple[1][3] == CASE_A) ||
                ((ExtensionCase)couple[0][3] == CASE_B && (ExtensionCase)couple[1][3] == CASE_B) ||
                ((ExtensionCase)couple[0][3] == CASE_B && (ExtensionCase)couple[1][3] == CASE_C) ||
                ((ExtensionCase)couple[0][3] == CASE_C && (ExtensionCase)couple[1][3] == CASE_B) ||
                ((ExtensionCase)couple[0][3] == CASE_B && (ExtensionCase)couple[1][3] == CASE_D) ||
                ((ExtensionCase)couple[0][3] == CASE_D && (ExtensionCase)couple[1][3] == CASE_B) ||
                ((ExtensionCase)couple[0][3] == CASE_C && (ExtensionCase)couple[1][3] == CASE_D) ||
                ((ExtensionCase)couple[0][3] == CASE_D && (ExtensionCase)couple[1][3] == CASE_C)
            ) {
                coupleValidation = "NP(1)";
            } else if (
                ((ExtensionCase)couple[0][3] == CASE_C && (ExtensionCase)couple[1][3] == CASE_E) ||
                ((ExtensionCase)couple[0][3] == CASE_E && (ExtensionCase)couple[1][3] == CASE_C)
            ) {
                coupleValidation = "NP(2)";
            } else if (
                ((ExtensionCase)couple[0][3] == CASE_B && (ExtensionCase)couple[1][3] == CASE_E) ||
                ((ExtensionCase)couple[0][3] == CASE_E && (ExtensionCase)couple[1][3] == CASE_B) ||
                ((ExtensionCase)couple[0][3] == CASE_D && (ExtensionCase)couple[1][3] == CASE_E) ||
                ((ExtensionCase)couple[0][3] == CASE_E && (ExtensionCase)couple[1][3] == CASE_D) ||
                ((ExtensionCase)couple[0][3] == CASE_D && (ExtensionCase)couple[1][3] == CASE_D)
            ) {
                coupleValidation = "NP(3): this theory presents cycles in superiority relations.";
                literalsInvolvedInSupRelCycles += (String)couple[0][0] + ", " + (String)couple[1][0] + ", ";
            } else {
                // this should never occour: all 36 possible combinations have been considered
                // if this occours, there must have been some CASE_X literals
                coupleValidation = "There must be an error with the case of a literal (probably CASE_X).";
            }

            literalsValidation +=
                "{{" + couple[0][0] + "  " + couple[0][1] +
                "  " + couple[0][2] + "  " + couple[0][3] + "}, {" +
                couple[1][0] + "  " + couple[1][1] +
                "  " + couple[1][2] + "  " + couple[1][3] + "}, " + coupleValidation + "}, ";

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
        return literalsValidation;
    }

}
