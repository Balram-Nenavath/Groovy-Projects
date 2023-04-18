package groovy.example.demo



// ---------- CLOSURES ----------
// Closures represent blocks of code that can except parameters
// and can be passed into methods.

// Alternative factorial using a closure
// num is the excepted parameter and call can call for
// the code to be executed
def getFactorial = { num -> (num <= 1) ? 1 : num * call(num - 1) }
println("Factorial 4 : " + getFactorial(4))


// 1st: num = 4 * factorial(3) = 4 * 6 = 24


// A closure can access values outside of it
def greeting = "Goodbye"
def sayGoodbye = {theName -> println("$greeting $theName")}

sayGoodbye("Balram")

// each performs an operation on each item in list
def numList = [1,2,3,4]
numList.each { println(it) }

// Do the same with a map
def employees = [
  'Ram' : 34,
  'Sai' : 35,
  'Krish' : 36
]

employees.each { println("${it.key} : ${it.value}") }

// Print only evens
def randNums = [1,2,3,4,5,6]
randNums.each {num -> if(num % 2 == 0) println(num)}

// find returns a match
def nameList = ['Dog', 'Cat', 'Rat']
def matchEle = nameList.find {item -> item == 'Rat'}
println(matchEle)

// findAll finds all matches
def randNumList = [1,2,3,4,5,6]
def numMatches = randNumList.findAll {item -> item > 4}
println(numMatches)

// any checks if any item matches
println("> 5 : " + randNumList.any {item -> item > 5})

// every checks that all items match
println("> 1 : " + randNumList.every {item -> item > 1})

// collect performs operations on every item
println("Double : " + randNumList.collect { item -> item * 2})

// pass closure to a method
def getEven = {num -> return(num % 2 == 0)}
def evenNums = listEdit(randNumList, getEven)
println("Evens : " + evenNums)

// ---------- CLOSURES ----------
// pass closure to a method

static def listEdit(list, clo){
	return list.findAll(clo)
}
//----------------------------------------------



/**A closure in Groovy is an open, anonymous, block of code that can take arguments,
 return a value and be assigned to a variable.
 A closure may reference variables declared in its surrounding scope
***/
//{ [closureParameters -> ] statements }
/*class Closure {
}*/
def code = { 123 }
assert code()==123
// can be explicit and use the call method:
assert code.call() == 123

def isOdd = { int i -> i%2 != 0 }
assert isOdd(3) == true
assert isOdd.call(2) == false

def isEven = { it%2 == 0 }
assert isEven(3) == false
assert isEven.call(2) == true


def closureWithTwoArgAndDefaultValue = { int a, int b=2 -> a+b }
assert closureWithTwoArgAndDefaultValue(1) == 3

//implicit parameter nothing but it

def greeting1 = { "Hello, $it!" }
assert greeting1('Patrick') == 'Hello, Patrick!'




//varargs ... within a closure
def concat1 = { String... args -> args.join('') }
assert concat1('abc','def') == 'abcdef'


///****************DELEGATION STRATEGY***********************///

//this

class Enclosing5 {
	void run() {
		def whatIsThisObject = { getThisObject() }
		assert whatIsThisObject() == this
		def whatIsThis = { this }
		assert whatIsThis() == this
	}
}


//owner

class Enclosing1 {
	void run() {
		def whatIsOwnerMethod = { getOwner() }
		assert whatIsOwnerMethod() == this
		def whatIsOwner = { owner }
		assert whatIsOwner() == this
	}
}


//Delegate
// the delegate is a user defined object that a closure will use. By default, the delegate is set to owner
class Enclosing2 {
	void run() {
		def cl = { getDelegate() }
		def cl2 = { delegate }
		assert cl() == cl2()
		assert cl() == this
		def enclosed = {
			{ -> delegate }.call()
		}
		assert enclosed() == enclosed
	}
}


//Closures in GStrings ${→ x}

def x = 1
def gs = "x = ${-> x}"
assert gs == 'x = 1'

x = 2
assert gs == 'x = 2'


//Currying - nothing but is the fact of setting the left-most parameter of a closure
//left currying
def nCopies = { int n, String str -> str*n }
def twice = nCopies.curry(2)
assert twice('kri') == 'krikri'
assert twice('kri') == nCopies(2, 'kri')

//right currying
def blah = nCopies.rcurry('ram')
assert blah(2) == 'ramram'
assert blah(2) == nCopies(2, 'ram')

//Memoize - Memoization allows the result of the call of a closure to be cached
//Making the execution faster

fib = { long n -> n<2?n:fib(n-1)+fib(n-2) }.memoize()
assert fib(25) == 75025 // fast!

//Trampoline ()
//Recursive algorithms are often restricted by a physical limit: the maximum stack height.
//For example, if you call a method that recursively calls itself too deep,
//you will eventually receive a StackOverflowException.

def factorial
factorial = { int n, def accu = 1G ->
	if (n < 2) return accu
	factorial.trampoline(n - 1, n * accu)
}
factorial = factorial.trampoline()

assert factorial(1)    == 1
assert factorial(3)    == 1 * 2 * 3
assert factorial(1000) // == 402387260.. plus another 2560 digits