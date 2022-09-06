require "./defeasible.rb"

srand(2718)

# load a theory, e.g.

#simplechain(10000)
#circle(1000000)
#nchain(2,2)
#doublechain(10)
#theorywidth(10)
#nperbody(34)
#EvenOdd(10)
#tree3(3)

# pennDutchCompiled
# pennDutch
# trafficRules
# hotAndWet
# teamDefeat
#platypus
# presumptionInnocence
# socialNetwork
# presumptionInnocenceCompensation
#!MISSING! siberianHuskies
# nautiluses

# theory1
# theory2
# theory3
# theory4
# theory5
# theory6
# theory7
# theory8
# theory9
# theory10
# theory11
# theory12

# or populate a theory

time = Time.now
(1..2).each do |counter|
    puts "((((( #{counter} )))))"
    clean_theory
    filename = "theories/theory#{counter}"
    #filename = "theories/theory"
    %%
    puts "------#{filename}"
    Rule.new("r1", "b#{counter}")
    Rule.new("r4", "c")
    Rule.new("r2", "a", "b3")
    Rule.new("r3", "~a", "c")
    sup("r2","r3")
    %
    randomtheory
    
    #theory
    output_theory(filename)
    output_prove("theories/theories_times_#{time}")
    output_conclusions("theories/theories_conclusions_#{time}")
end


# show the content of a theory
#theory
#output_theory(dir = "theories/", filename = filename)

#compute the positive extension of a thoery
#prove

# show the extension of a thoery
#show_conclusions


