package com.example.demo.LogicModel.Extension;
import com.example.demo.Pair;
import com.example.demo.LogicModel.Theory;

public interface ExtensionComputator {
    Theory theory = new Theory();
    Extension extension = new Extension();

    Pair<Theory, Extension> computeExtension(Theory theory, Extension extension);

    default Pair<Theory, Extension> computeExtension() {
        return this.computeExtension(theory, extension);
    };
    
    default Pair<Theory, Extension> computeExtension(Theory theory) {
        return this.computeExtension(theory, new Extension());
    };

}
