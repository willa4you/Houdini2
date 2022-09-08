require './language'

$to_print_times = Array.new

def makeCells(literal)
  $literals[literal] = Hash.new
  ["ph", "nh", "pb", "nb"].each {|x| $literals[literal][x] = Array.new}
end

def prepare_prove
  $rules.each do |rule|
    unless $literals.has_key?(rule.head.absolute)
      makeCells(rule.head.absolute)
    end
    if rule.head.negation?
      $literals[rule.head.absolute]["nh"].push(rule)
    else
      $literals[rule.head.absolute]["ph"].push(rule)
    end
    rule.body.each do |lit|
      unless $literals.has_key?(lit.absolute)
        makeCells(lit.absolute)
      end
      if lit.negation?
        $literals[lit.absolute]["nb"].push(rule)
      else
        $literals[lit.absolute]["pb"].push(rule)
      end
    end
  end
end

# def no_support
#   $literals.each_key do |key|
#     if ($literals[key]["ph"].empty? and $literals[key]["nh"].empty?)
#         $literals[key]["pb"].each {|x| discard_rule(x)}
#         $literals[key]["nb"].each {|x| discard_rule(x)} # with $rules.delete(x) ambiguity propagating
#         $literals.delete(key)
#       end
#     end
# end

def no_support
    $literals.each_key do |key|
    if $literals[key].has_key?("ph")
      if $literals[key]["ph"].empty?
         $literals[key]["pb"].each {|x| discard_rule(x)}
         deleteKeyPh = 1
         $literals[key].delete("ph")
      end
    end
    if $literals[key].has_key?("nh")
      if $literals[key]["nh"].empty?
         $literals[key]["nb"].each {|x| discard_rule(x)}
         deleteKeyNh = 1
         $literals[key].delete("nh")
      end
    end
    $literals.delete(key) if (deleteKeyPh and deleteKeyNh)
    if deleteKeyNh or deleteKeyPh
      stop = false
      $stopstop = false
    end
  end
end

def discard_rule(rule)
  $rules.delete(rule)
  $literals.each_key do |key|
    $literals[key]["ph"].delete(rule) if $literals[key].has_key?("ph")
    $literals[key]["nh"].delete(rule) if $literals[key].has_key?("nh")
  end
end

def proved(literal)
  "this is an interesting function if we replace discard_rule(r) with
  $rules.delete(r), then we have DL with ambiguity propagating"
  if literal.negation?
    $literals[literal.absolute]["nb"].each {|r| r.body.delete(literal) }
    $literals[literal.absolute]["pb"].each {|r| discard_rule(r)}
  else
    $literals[literal.absolute]["pb"].each {|r| r.body.delete(literal)}
    $literals[literal.absolute]["nb"].each {|r| discard_rule(r)}
  end
  $literals.delete(literal.absolute)
  stop = false
  $stopstop = false
end

def compute()
  new_conclusions = Array.new
  used_rules = Array.new
  level = 0
  starttime = Time.new
  stop = false
  while true
    no_support
    level += 1
    puts "=====#{level}====="
    if ($rules.empty? or $literals.empty?)
      puts "no more inferences to do"
      endtime = Time.now
      puts "Elapsed time:"
      puts endtime.to_f - starttime.to_f
      break
    end
    $rules.each do |r|
      # r.showRule
      if r.body.empty?
        if r.head.negation?
          unless $literals[r.head.absolute].has_key?("ph")
            new_conclusions.push(r.head)
            puts "add #{r.head} to the proved conclusions"
            # used_rules.push(r)
            $literals[r.head.absolute]["nh"].each { |s| $rules.delete(s) }
          # elsif $literals[r.head.absolute]["ph"].any? { |s| s.body.empty? }
          #   $literals[r.head.absolute]["ph"].each {|s| discard_rule(s)}
          #   used_rules.push(r)
          end
        $literals[r.head.absolute]["pb"].each {|s| discard_rule(s)} #ab
        else
          unless $literals[r.head.absolute].has_key?("nh")
            new_conclusions.push(r.head)
            puts "add #{r.head} to the proved conclusions"
            # used_rules.push(r)
            $literals[r.head.absolute]["ph"].each { |s| $rules.delete(s) }
          # elsif $literals[r.head.absolute]["nh"].any? { |s| s.body.empty? }
          #   $literals[r.head.absolute]["ph"].each {|s| discard_rule(s)}
          #   $literals[r.head.absolute]["nh"].each {|s| discard_rule(s)}
          #   used_rules.push(r)
          end
        $literals[r.head.absolute]["nb"].each {|s| discard_rule(s)} #ab
        end
      end
      # if level == x
      #   r.showRule
      # end
    end
    used_rules.each { |r| $rules.delete(r) }
    used_rules.clear
    if new_conclusions.empty?
      puts "no new conclusions"
      if $stopstop
        endtime = Time.now
        puts "Elapsed time:"
        puts endtime.to_f - starttime.to_f
        break
      end
      if stop
        $stopstop = true
      end
      stop = true
    else
      new_conclusions.uniq.each {|x| proved(x)}
      $conclusions.concat(new_conclusions)
      new_conclusions.clear
    end
  end
end

def save_times(time)
  
  $to_print_times.push(time)
  
end

def output_compute()
  new_conclusions = Array.new
  used_rules = Array.new
  level = 0
  starttime = Time.new
  stop = false
  while true
    no_support
    level += 1
    #puts "=====#{level}====="
    if ($rules.empty? or $literals.empty?)
      #puts "no more inferences to do"
      endtime = Time.now
      #puts "Elapsed time:"
      #puts endtime.to_f - starttime.to_f
      save_times(endtime.to_f - starttime.to_f)
      break
    end
    $rules.each do |r|
      # r.showRule
      if r.body.empty?
        if r.head.negation?
          unless $literals[r.head.absolute].has_key?("ph")
            new_conclusions.push(r.head)
            #puts "add #{r.head} to the proved conclusions"
            # used_rules.push(r)
            $literals[r.head.absolute]["nh"].each { |s| $rules.delete(s) }
          # elsif $literals[r.head.absolute]["ph"].any? { |s| s.body.empty? }
          #   $literals[r.head.absolute]["ph"].each {|s| discard_rule(s)}
          #   used_rules.push(r)
          end
        $literals[r.head.absolute]["pb"].each {|s| discard_rule(s)} #ab
        else
          unless $literals[r.head.absolute].has_key?("nh")
            new_conclusions.push(r.head)
            #puts "add #{r.head} to the proved conclusions"
            # used_rules.push(r)
            $literals[r.head.absolute]["ph"].each { |s| $rules.delete(s) }
          # elsif $literals[r.head.absolute]["nh"].any? { |s| s.body.empty? }
          #   $literals[r.head.absolute]["ph"].each {|s| discard_rule(s)}
          #   $literals[r.head.absolute]["nh"].each {|s| discard_rule(s)}
          #   used_rules.push(r)
          end
        $literals[r.head.absolute]["nb"].each {|s| discard_rule(s)} #ab
        end
      end
      # if level == x
      #   r.showRule
      # end
    end
    used_rules.each { |r| $rules.delete(r) }
    used_rules.clear
    if new_conclusions.empty?
      #puts "no new conclusions"
      if $stopstop
        endtime = Time.now
        #puts "Elapsed time:"
        #puts endtime.to_f - starttime.to_f
        save_times(endtime.to_f - starttime.to_f)
        break
      end
      if stop
        $stopstop = true
      end
      stop = true
    else
      new_conclusions.uniq.each {|x| proved(x)}
      $conclusions.concat(new_conclusions)
      new_conclusions.clear
    end
  end
end
