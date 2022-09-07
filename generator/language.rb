$rules = Array.new
$compiledRules = Array.new
$rulesIndex = Hash.new
$literals = Hash.new
$conclusions = Array.new
$support = Array.new
$superiority = Array.new
$superiority_rels = Array.new
$no_defeater = Array.new

Negation = /\A~(\S+)/

class String  
  def negation?
    Negation.match(self)
  end
  def absolute
    if Negation.match(self)
      return $1
    else
      return self
    end
  end
end

class Rule  
  attr_accessor :head, :body, :arrow, :name
  def initialize(name, head, *body)
    $rulesIndex[name]= self
    @name = name
    @head = head
    @body = Array.new
    @arrow = "=>"
    @body.concat(body)
    $rules.push(self)
  end
  def printRule
    puts "rule(\"#{self.head}\"" + ", \"#{self.body.join(",")}\")"
  end 
  def showRule
    puts "#{self.body.join(",") } #{self.arrow} #{self.head}"    
  end
  def toString
    return "#{self.body.join(",") } #{self.arrow} #{self.head}"
  end
  def makeXML
    print "<Rule label=\"#{self.name}\" strenght=\"DEFEASIBLE\">\n"
    print "\t<head>\n"
    if self.head.negation?
      print "\t\t<Not />"
    else
      print "\t\t"
    end
    print "<Atom><Rel>#{self.head.absolute}</Rel></Atom>\n"
    print "\t</head>\n"
    print "</Rule>\n\n"
  end
end

class CompiledRule < Rule
  def initialize(head,*body)
    @head = head
    @body = Array.new
    @body.concat(body)
    @arrow = "=>"
    $compiledRules.push(self)
  end
end

class Superiority
  attr_accessor :inf, :sup
  def initialize(inf,sup)
    @inf = inf
    @sup = sup
    $superiority_rels.push(self)
    $superiority.push([inf,sup])
  end
  def showRel
    puts "#{self.inf} > #{self.sup}"    
  end
  def toString
    return "#{self.inf} > #{self.sup}" 
  end
end

def removeSuperiority
  $rules.each do |r|
    unless $superiority.flatten.include?(r.name)
      CompiledRule.new(r.head,r.body) 
    else
      CompiledRule.new(r.head,"~inf+"+r.name)
      CompiledRule.new("~inf+"+r.name,r.body)
      CompiledRule.new(r.head,"~inf-"+r.name)
      CompiledRule.new("~inf-"+r.name,r.body)
    end
  end
  $compiledRules.each {|rule| rule.body.flatten! }
  $superiority.each { |inf,sup| CompiledRule.new("inf+"+sup, "~inf+"+inf) 
    CompiledRule.new("inf-"+sup, "~inf-"+inf)}
end

class NoDefeater < Rule
  def initialize(head,*body)
    @head = head
    @body = Array.new
    @body.concat(body)
    @arrow = "=>"
    $no_defeater.push(self)
  end
end

def removeDefeater
  $compiledRules.each do |r|
    if r.head.negation?
      NoDefeater.new(r.head.absolute+"-",r.body)
      NoDefeater.new(r.head+"+",r.body)
      NoDefeater.new(r.head,r.head.absolute+"-")
    else
      NoDefeater.new(r.head+"+",r.body)
      NoDefeater.new("~"+r.head+"-",r.body)
      NoDefeater.new(r.head,r.head+"+")
    end
  end
  $no_defeater.each { |rule|  rule.body.flatten! }
end

def rule(name, head, *body)
  Rule.new(name,head,*body)
end

def crule(head, *body)
  Rule.new("x",head,*body)
end

def sup(inf,supe)
  Superiority.new(inf,supe)
end  
