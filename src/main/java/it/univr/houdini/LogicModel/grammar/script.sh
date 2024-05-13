#!/bin/bash

java -Xmx500M -cp "/usr/local/lib/antlr-4.10.1-complete.jar" org.antlr.v4.Tool ./Theory.g4 -visitor
javac ./Theory*.java
sed  -i '1i package com.example.demo.LogicModel.grammar;' TheoryBaseListener.java
sed  -i '1i package com.example.demo.LogicModel.grammar;' TheoryBaseVisitor.java
sed  -i '1i package com.example.demo.LogicModel.grammar;' TheoryLexer.java
sed  -i '1i package com.example.demo.LogicModel.grammar;' TheoryListener.java
sed  -i '1i package com.example.demo.LogicModel.grammar;' TheoryParser.java
sed  -i '1i package com.example.demo.LogicModel.grammar;' TheoryVisitor.java
