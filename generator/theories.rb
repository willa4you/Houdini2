require './language'

def randomtheory(n=3000, w=5, cap=200, p=0.025)
  heads = []
  #first w^2 rules are active
  (1...w+1).each do |i|
    indexes = (1...w+1).map{|q| "a#{(q + w*(i-1))}"}
    head = "b#{i}"
    heads.push(head)
    indexes.each do |l|
      crule(l)
    end
    crule(head, *indexes)
  end
  #remaining rules
  (w+1...n+1).each do |j|
    #sample without replacement
    tail = heads.to_a.shuffle[0...w]
    #with probability p, the first element in the tail is "c" which is always non derivable
    if rand() < p
      tail[0] = "c"
    end
    # Super strange computations in the end but necessary to avoid "b0"
    head = if j <= cap then "b#{j}" else "b#{((j-1) % cap)+1}" end
    if head == "b0"
      raise
    end
    
    heads.push(head)
    crule(head, *tail)
    
    end

end

def simplechain(n)
  (1..n).each do |i|
    crule("a#{i+1}", "a#{i}")
  end
  crule("a1")
end

def circle(n)
  (1..n).each do |i|
    crule("a#{i+1}", "a#{i}")
  end
  crule("a1", "a#{n+1}")
end

def theorywidth(n)
  (1..n).each do |i|
    crule("b#{i}", "a#{i}")
    crule("a#{i}")
  end
end

def nperbody(n)
  (1..n).each do |i|
    crule("e#{i}", "a#{i}", "b#{i}", "c#{i}", "d#{i}")
    ["a", "b", "c", "d"].each { |j| crule("#{j}#{i}") }
  end
end

def EvenOdd(n)
  (1..n).each do |i|
    crule("a#{i+1}", "a#{i}")
    crule("a#{i+2}", "a#{i}")
    crule("~a#{2*i}", "b#{i}")
    crule("b#{i}")
  end
  crule("a1")
end

def doublechain(n)
  crule("a1")
  crule("b1")
  (1..n).each do |i|
    crule("a#{i+1}", "a#{i}", "b#{i}")
    crule("b#{i+1}", "a#{i}", "b#{i}")
  end
end

def nchain(n,m)
  (1..n).each do |i|
    crule("#{i}a1")
    (1..m).each do |j|
      crule("#{i}a#{j+1}", "#{i}a#{j}")
    end
  end
end

def tree3(n)
  (1..3**(n-1)).each do |i|
    crule("a#{i}", "a#{(3*i)+1}", "a#{(3*i)+2}", "a#{(3*i)+3}")
  end
  (3**(n-1)..3**n).each { |j| crule("a#{j}")  }
end

def pennDutchCompiled
    crule("nspd")
    crule("bp", "nspd")
    crule("ngs", "nspd")
    crule("i_nfr1", "bp")
    crule("busa", "i_nfr1")
    crule("i_nfr2", "ngs")
    crule("~busa", "i_nfr2")
    crule("~i_nfr2", "i_nfr1")
end

def pennDutch
  rule("r1", "GermanSpeaker", "nativePennDutchSpeaker") #r1
  rule("r2", "bornPenn", "nativePennDutchSpeaker") #r2
  rule("r3", "bornAmerica", "bornPenn") #r3
  rule("r4", "~bornAmerica", "GermanSpeaker") #r4

  rule("r5", "nativePennDutchSpeaker") #r5

  sup("r3", "r4")
end

def trafficRules
  rule("r1", "rightOfWay_X", "X_onTheRightOf_Y") #r1
  rule("r2", "rightOfWay_Y", "Y_onTheRightOf_X") #r2
  rule("r3", "~rightOfWay_X", "rightOfWay_Y") #r3
  rule("r4", "~rightOfWay_Y", "rightOfWay_X") #r4
  rule("r5", 'rightOfWay_X', "emergency_X", "~emergency_Y") #r5
  rule("r6", "rightOfWay_Y", "emergency_Y", "~emergency_X") #r6
  rule("r7", "~emergency_X") #r7
  rule("r8", "~emergency_Y") #r8

  rule("r9", "emergency_X", "police_X") #r9

  rule("r10", "Y_onTheRightOf_X") #r10
  rule("r11", "police_X") #r11

  sup("r3", "r1")
  sup("r4","r2")
  sup("r5","r3")
  sup("r6","r4")

  sup("r9","r7")

end

def hotAndWet
  rule("r1", "hot") #r1
  rule("r2", "rain") #r2

  rule("r3", "jogging") #r3
  rule("r4", "~jogging", "hot") #r4
  rule("r5", "~jogging", "rain") #r5
  rule("r6", "jogging", "hot", "rain") #r6

  sup("r4", "r3")
  sup("r5", "r3")
  sup("r6", "r4")
  sup("r6", "r5")
end

def socialNetwork
  rule("r1", "Guido_friend_Samir")
  rule("r2", "display_Pic10", "owner_Pic10_Samir", "wedding_Pic10", "Guido_friend_Samir")
  rule("r3", "owner_Pic10_Samir")
  rule("r4", "~wedding_Pic10", "uni_picture_Pic10")
  rule("r5", "uni_picture_Pic10")
end

def teamDefeat
  rule("r1", "b", "a")
  rule("r2", "b", "c")
  rule("r3", "~b", "d")
  rule("r4", "~b", "e")

  rule("r5", "a")
  rule("r6", "c")
  rule("r7", "d")
  rule("r8", "e")

  sup("r1", "r3")
  sup("r2", "r4")
end

def platypus
  rule("r1", "monotreme", "platypus")
  rule("r2", "hasFur", "platypus")
  rule("r3", "laysEgg", "platypus")
  rule("r4", "hasBill", "platypus")

  rule("r5", "mammal", "monotreme") #r5
  rule("r6", "mammal", "hasFur") #r6
  rule("r7", "~mammal", "laysEgg") #r7
  rule("r8", "~mammal", "hasBill") #r8

  rule("r9", "platypus") #r9

  sup("r5", "r7")
  sup("r6", "r8")
end

def presumptionInnocence
  rule("r1", "responsible", "evidenceA")
  rule("r2", "~responsible", "evidenceB")
  rule("r3", "guilty", "responsible")
  rule("r4", "~guilty")

  sup("r3", "r4")

  rule("r5", "evidenceA")
  rule("r6", "evidenceB")
end

def presumptionInnocenceCompensation
  rule("r1", "responsible", "evidenceA")
  rule("r2", "~responsible", "evidenceB")
  rule("r3", "guilty", "responsible")
  rule("r4", "~guilty")

  rule("r5", "compensation", "~guilty")
  rule("r6", "~compensation")


  rule("r7", "evidenceA")
  rule("r8", "evidenceB")
  
  sup("r3", "r4")
  sup("r5", "r6")

end

def nautiluses
  rule("r1", "cephalopods", "nautiluses")
  rule("r2", "molluscs", "cephalopods")
  rule("r3", "haveShells", "nautiluses")
  rule("r4", "~haveShells", "cephalopods")
  rule("r5", "haveShells", "molluscs")

  sup("r3", "r4")
  sup("r4", "r5")

  rule("r6", "nautiluses")
end

def theory1
  crule("q", "p")
  crule("p", "p")
  crule("~q", "~s", "t")
  crule("s", "r")
  crule("r", "r")
  crule("t")
  crule("~s")
  crule("~r")
  crule("~p")

  crule("~q")
  crule("q", "p", "s")
  crule("p", "q")
end

def theory2
  crule("a")
  crule("b","a")
  crule("d", "b")
  crule("c", "b", "d")
end

def theory3
  crule("a")

  crule("b", "a", "c")
  crule("~b")

  crule("~nafb",'b')
  crule("nafb")

  crule("c", "b")
  crule("c", "nafb")
end

def theory4
  crule("a")
  crule("~a","b")
  crule("~b", "~b")
end

def theory5
  rule("r1", "p")
  rule("r2", "q", "p")
  rule("r3", "r", "q")
  rule("r4", "~r", "s")
  rule("r5", "t", "r")
  rule("r6", "~t", "~p")
end


def theory6
  rule("r1", "a")
  rule("r2", "b")
  rule("r3", "c")
  rule("r4", "d")
  rule("r5", "~b", "a")
  rule("r6", "~a", "b")
  rule("r7", "~c", "a")
  rule("r8", "~c", "b")
  rule("r9", "~d", "c")
end

def theory7
  crule("q3","q2")
  crule("q2","q1")
  crule("q1")
  crule("a")
  crule("~a","q1")
  crule("~q2","a")
end


def theory8
  rule("r1", "a")
  rule("r2", "b", "a")
  rule("r3", "c", "b")
  rule("r4", "~a", "c")
end

def theory9
  rule("r1", "~a")
  rule("r2", "a", "b")
  rule("r3", "b", "a")
end

def theory10
  rule("r1", "a")
  rule("r2", "~a")
  sup("r1","r2")
  sup("r2","r1")
end

def theory11
  rule("r1", "f", "g")
  rule("r2", "g", "f")
  rule("r3", "~f")
  sup("r3","r1")
end

def theory12
  rule("r0", "b")
  rule("r1", "~a", "b")
  rule("r2", "a")
end
