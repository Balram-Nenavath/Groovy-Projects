package groovy.example.demo

//Arithmetic operations


def age = ""
age = 40

//It is dynamically typed

def (String a,int b,Double c) =[0,20,30]
println a
println b
println c

// The basic integer math operators
println("5 + 4 = " + (5 + 4))
println("5 - 4 = " + (5 - 4))
println("5 * 4 = " + (5 * 4))
println("5 / 4 = " + (5.intdiv(4)))
println("5 % 4 = " + (5 % 4))
println("2 ** 3 = "+ (2 ** 3))

//assignment operation
def a_4 = 4
a_4 += 3
assert a_4 == 7 == true

//unary operator
assert -(-1) == 1

//Prefix increment 
def e = 1
def f = ++e + 3

assert e == 2 && f == 5 == true

// Floating point math operators with words
println("5.2 + 4.4 = " + (5.2.plus(4.4)))
println("5.2 - 4.4 = " + (5.2.minus(4.4)))
println("5.2 * 4.4 = " + (5.2.multiply(4.4)))
println("5.2 / 4.4 = " + (5.2 / 4.4))

// Order of operations
println("3 + 2 * 5 = " + (3 + 2 * 5))
println("(3 + 2) * 5 = " + ((3 + 2) * 5))

// Increment and decrement
println("age++ = " + (age++))
println("++age = " + (++age))
println("age-- = " + (age--))
println("--age = " + (--age))

// Largest values
println("Biggest Int " + Integer.MAX_VALUE)
println("Smallest Int " + Integer.MIN_VALUE)

println("Biggest Float " + Float.MAX_VALUE)
println("Smallest Float " + Float.MIN_VALUE)

println("Biggest Double " + Double.MAX_VALUE)
println("Smallest Double " + Double.MIN_VALUE)


// Math Functions
def randNum = 2.0
println("Math.abs(-2.45) = " + (Math.abs(-2.45)))
println("Math.round(2.45) = " + (Math.round(2.45)))
println("randNum.pow(3) = " + (randNum.pow(3)))
println("3.0.equals(2.0) = " + (3.0.equals(2.0)))
println("randNum.equals(Float.NaN) = " + (randNum.equals(Float.NaN))) 
println("Math.sqrt(9) = " + (Math.sqrt(9)))
println("Math.cbrt(27) = " + (Math.cbrt(27)))
println("Math.ceil(2.45) = " + (Math.ceil(2.45)))
println("Math.floor(2.45) = " + (Math.floor(2.45)))
println("Math.min(2,3) = " + (Math.min(2,3)))
println("Math.max(2,3) = " + (Math.max(2,3)))

// Number to the power of e
println("Math.log(2) = " + (Math.log(2)))

// Base 10 logarithm
println("Math.log10(2) = " + (Math.log10(2)))

// Degrees and radians
println("Math.toDegrees(Math.PI) = " + (Math.toDegrees(Math.PI)))
println("Math.toRadians(90) = " + (Math.toRadians(90)))

// can use sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
println("Math.sin(0.5 * Math.PI) = " + (Math.sin(0.5 * Math.PI)))

// Generate random value from 1 to 100
println("Math.abs(new Random().nextInt() % 100) + 1 = " + (Math.abs(new Random().nextInt() % 100) + 1))




//  LISTS 

def primes = [2,3,5,7,11,13]

// Get a value at an index
println("2nd Prime " + primes[1])
println("3rd Prime " + primes.get(2))

// They can hold anything
def employee = ['Balram', 40, 6.25, [1,2,3]]

println("2nd Number from employee " + employee[3][1])

// Get the length
println("Length " + primes.size())

// Add an index
primes.add(17)

// Append to the right
primes<<19
primes.add(23)

// Concatenate 2 Lists
primes + [29,31]

// Remove the last item
primes - [31]

// Check if empty
println("Is empty " + primes.isEmpty())

// Get 1st 3
println("1st 3 " + primes[0..2])

println(primes)

// Get matches
println("Matches " + primes.intersect([2,3,7]))

// Reverse
println("Reverse " + primes.reverse())

// Sorted
println("Sorted " + primes.sort())

// Pop last item
println("Last " + primes.pop())

//  MAPS 
// List of objects with keys versus indexes

def Map = [
  'name' : 'Ram',
  'age' : 24,
  'address' : 'Badangpet, Hyderabad',
  'list' : [1,2,3]
]

// Access with key
println("Name " + Map['name'])
println("Age " + Map.get('age'))
println("List Item " + Map['list'][1])

// Add key value
Map.put('city', 'Secunderabad')

// Check for key
println("Has City " + Map.containsKey('city'))

// Size
println("Size " + Map.size())

//  RANGE 
// Ranges represent a range of values in shorthand notation

def oneTo10 = 1..10 //from 1 to 10 
def aToZ = 'a'..'z'; // from a to z
def zToA = 'z'..'a'; // from z to a

println("one to ten : "+oneTo10)
println("a to z :"+aToZ)
println("z to a : "+zToA)


println("get character at 2  : "+ aToZ.get(2))
// Get size
println("Size " + oneTo10.size())

// get index
println("2nd Item " + oneTo10.get(1))

// Check if range contains
println("Contains 11 " + oneTo10.contains(11))

// Get last item
println("Get Last " + oneTo10.getTo())

println("Get First " + oneTo10.getFrom())

//  CONDITIONALS 
// Conditonal Operators : ==, !=, >, <, >=, <=

// Logical Operators : && || !

def ageOld = 6

if(ageOld == 5){
  println("Go to School")
} else if((ageOld > 5) && (ageOld < 18)) {
  printf("Go to Section %d \n", (ageOld - 5))
} else {
  println("Go to College")
}

def canVote = true

// Ternary operator
println(canVote ? "Eligible to Vote" : "Not Eligible to Vote")
println(canVote ?: "Not Eligible to Vote")

// Switch statement
switch(ageOld) {
  case 16: println("You can drive")
  case 18:
	println("You can vote")

	// Stops checking the rest if true
	break
  default: println(" N A ")
}

// Switch with list options
switch(ageOld){
  case 0..6 : println("Pupil") 
  break
  case 7..12 : println("Child") 
  break
  case 13..18 : println("Teenager") 
  break
  default : println("Adult")
}

//  LOOPs 
// While loop

def i = 0

while(i < 10){

  // If i is odd skip back to the beginning of the loop
  if(i % 2){
	i++
	continue
  }

  // If i equals 8 stop looping
  if(i == 8){
	break
  }

  println(i)
  i++
}

// Normal for loop
for (i=0; i < 5; i++) {
  println(i)
}

// for loop with a range with groovy 
for(j in 2..6){
  println(j)
}

// for loop with a list (Same with string)
def randList = [10,12,13,14]

for(j in randList){
  println(j)
}

// for loop with a map
def emps = [
  100 : "Ram",
  101 : "Krishna",
  102 : "Sai"
]

for(emp in emps){
  println("$emp.value : $emp.key ")
}

//  METHODS 

sayHelloDemo()

// Pass parameters
println("5 + 4 = " + getSum(5,4))

// Demonstrate pass by value
def myName = "Balram"
passByValue(myName)
println("In Main : " + myName)

// Pass a list for doubling
def listToDouble = [1,2,3,4]
listToDouble = doubleList(listToDouble)
println(listToDouble)

// Pass unknown number of elements to a method
def nums = sumAll(1,2,3,4)
println("Sum : " + nums)

// Calculate factorial (Recursion)
def fact4 = factorial(4)
println("Factorial 4 : " + fact4)


//  OOP 

def man = new Person('Balram', 24)


println("${man.name} is their ${man.age}")

// Change an object attribute with a setter
man.setAge(25)
println("${man.name} and their new age is ${man.age}")

man.name()

println(man.toString())

// With inheritance a class can inherit all fields
// and methods of another class
def Balram = new Emp('Balram', 26, 'Evoke')

man.theirAge()
Balram.defineNewAge()




//  METHODS 

// Define them with def and static so that it is shared
// by all instances of the class
static def sayHelloDemo() {
  println("Hello World, Demo");
}

// Methods can receive parameters that have default values
static def getSum(num1=0, num2=0){
  return num1 + num2;
}

// Any object passed to a method is pass by value
static def passByValue(name){

  // name here is local to the function and can't
  // be accessed outside of it
  name = "In Function";
  println("Name : " + name);
}

// Receive and return a list
static def doubleList(list){

  // Collect performs a calculation on every item in the list
  def newList = list.collect { it * 2};
  return newList;
}

// Pass unknown number of elements to a method
static def sumAll(int... num){
  def sum = 0;

  // Performs a calculation on every item with each
  num.each { sum += it; }
  return sum;
}

// Calculate factorial (Recursion)
static def factorial(num){
  if(num <= 1){
	return 1;
  } else {
	return (num * factorial(num - 1));
  }
}
println("-----------------------------------")
class creature
{
	String str
}

def cat = new creature(str: 'cat')
def copyCat = cat
def lion = new creature(str: 'cat')

cat.equals(lion)
//assert cat.equals(lion) // Java logical equality
//assert cat == lion      // Groovy shorthand operator

assert cat.is(copyCat)  // Groovy identity
assert cat === copyCat  // operator shorthand
assert cat !== lion     // negated operator shorthand