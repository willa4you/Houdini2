require "./defeasible.rb"

#srand(2718)
srand(3141)

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

class TheoryIterator
    attr_accessor :theories
    def initialize(*more)
        @theories = Array.new
        @theories.push(*more)
    end
    #  Iterators:  each, call_each
    #
    def each
        @theories.each { |p| yield p }
    end
    def call_each
        @theories.each { |p| yield p.call }
    end
end

ths = [
    method(:pennDutchCompiled),
    method(:trafficRules),
    method(:hotAndWet),
    method(:teamDefeat),
    method(:platypus),
    method(:presumptionInnocence),
    method(:socialNetwork),
    method(:presumptionInnocenceCompensation),
    method(:nautiluses),
    method(:theory1),
    method(:theory2),
    method(:theory3),
    method(:theory4),
    method(:theory5),
    method(:theory6),
    method(:theory7),
    method(:theory8),
    method(:theory9),
    method(:theory10),
    method(:theory11),
    method(:theory12),
]

# or populate a theory
time = Time.now
counter = 1

theories = TheoryIterator.new(*ths)

theories.each do |fn|

    clean_theory

    fn.call

    filename = "theories/theory#{counter}"
    output_theory(filename)

    File.open("theories_times_#{time}.csv", "w")
    output_prove("theories_times_#{time}.csv")

    
    save_conclusions()

    counter += 1
    
end
file = File.open("theories_conclusions_#{time}.csv", "w")
$to_print_conclusions.each do |line|
    file << line << "\n"
end
# show the content of a theory
#theory
#output_theory(dir = "theories/", filename = filename)

#compute the positive extension of a thoery
#prove

# show the extension of a thoery
#show_conclusions


