require './theories'
require './engine'

def clean_theory
  $rules = Array.new
  $compiledRules = Array.new
  $rulesIndex = Hash.new
  $literals = Hash.new
  $conclusions = Array.new
  $support = Array.new
  $superiority = Array.new
  $superiority_rels = Array.new
  $no_defeater = Array.new
end

def theory
  puts "===Original theory==="
  $rules.each { |r| r.showRule  }  
  unless $superiority.empty?
    $superiority_rels.each { |s| s.showRel  }  
  end
end

def output_theory(filepath)
  output = File.open(filepath + ".json", "w")
  rules_formatted = if $rules.empty? then "\"\"" else $rules.map{|r| "\"#{r.toString()}\""}.join(",") end
  rules_string = "\"rules\" : [#{rules_formatted}]"
  sup_formatted = if $superiority_rels.empty? then "\"\"" else $superiority_rels.map{|s| "\"#{s.toString()}\""}.join(",") end
  sup_string = "\"supRules\" : [#{sup_formatted}]"
  theory_string = "{\n\t\"version\" : \"0.1\",\n\t\"logicModel\" : { \n\t\t\"facts\" : [\"\"],\n\t\t#{rules_string},\n\t\t#{sup_string}\n\t}\n}"
  output << theory_string
end

def prove(opt='')
  unless $superiority.empty?
    puts "===Remove Superiority==="
    removeSuperiority
    # $compiledRules.each { |r| r.showRule }
  end
  if opt == 'dft'
    puts "===Remove Defeaters==="
    removeDefeater
    $rules = $no_defeater
  end
  puts "===generate data structures==="
  unless $superiority.empty?
    $rules = $compiledRules
  end
  prepare_prove
  compute
end

def output_prove(filepath='', opt='')
  unless $superiority.empty?
    puts "===Remove Superiority==="
    removeSuperiority
    # $compiledRules.each { |r| r.showRule }
  end
  if opt == 'dft'
    puts "===Remove Defeaters==="
    removeDefeater
    $rules = $no_defeater
  end
  puts "===generate data structures==="
  unless $superiority.empty?
    $rules = $compiledRules
  end
  prepare_prove  
  output_compute(filepath)  
end

def show_conclusions
  puts "===Derived Conclusions==="
  puts $conclusions.uniq
end

def output_conclusions(filepath='')
  if filepath != ''
    output = File.open(filepath + ".dt", "a")
    output << $conclusions.uniq.select{|s| !s.include?("inf")} << "\n"
  end
end
