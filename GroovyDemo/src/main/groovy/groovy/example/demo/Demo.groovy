package groovy.example.demo

import groovy.transform.CompileStatic
import groovy.transform.ToString
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.Date
import java.sql.Date as SQLDate

/*class Demo {
}*/

		for(j in 0..4){ println j }
		0.upto(3) { println "$it" }
		4.times{ println "$it" }
		greet('Balram')
		def a='ram'
		def b='ram'
		println a.is(b)
		println a==b
		println a.equals(b)
		 println("******************")
		(0..<10).stream().map { 2**it }.forEach { println it }
		println("*******************")
		
		def "null"() { true }  // not recommended; potentially confusing
		assert this.null()
	
		def name = 'Balram' // a plain string
		def greeting = "Hello ${name}"
		//assert greeting.toString() == 'Hello Krishna'
		assert greeting.toString() == 'Hello Balram'
		
		
		def sum = "The sum of 2 and 3 equals ${2 + 3}"
		assert sum.toString() == 'The sum of 2 and 3 equals 5'
		
		def person = [name: 'Krishna', age: 24]
		assert "$person.name is $person.age years old" == 'Krishna is 24 years old'
		
		assert '$5' == "\$5"
		

static "abstract"()
{
	true
}

	
	
	String greet(String otherPerson) {
		println "Hello ${otherPerson}"
	 }

	 
//  STRINGS
	 
	 def bname = "Balram"
	 println(bname[1..5,2])
	 // A string surrounded by single quotes is taken literally
	 // but backslashed characters are recognized
	 println('I am ${bname}\n') //single quotes wont get the value
	 println("I am $bname\n") // it will work without { } also but keeping it is preferred
	 
	 // Triple quoted strings continue over many lines
	 def multString = '''Balram Nenavath
  Groovy and Arithmetic Operations'''
	 
	 println(multString)
	 
	 // You can access a string by index
	 println("3rd ind of Name " + bname[3])
	 println("index of r " + bname.indexOf('r'))
	 
	 // You can also get a slice from here to here
	 println("1st 3 Characters " + bname[0..2])
	 
	 // Get specific Characters
	 println("Every Other Character " + bname[0,2,4])
	 
	 // Get characters starting at index
	 println("Substr at 1 " + bname.substring(1))
	 
	 // Get characters at index up to another
	 println("Substr at 1 to 4 " + bname.substring(1,4))
	 
	 // Concatenate strings
	 println("My Name " + bname)
	 println("My Name is ".concat(bname))
	 
	 // Repeat a string
	 def repeatStr = "Evoke Technologies Hyderabad " * 2
	 println(repeatStr)
	 
	 // Check for equality
	 println("Balram == Balram : " + ('Balram'.equals('Balram')))
	 println("Balram == Balram : " + ('Balram'.equalsIgnoreCase('Balram')))
	 
	 // Get length of string
	 println("Size " + repeatStr.length())
	 
	 // Remove first occurance
	 println(repeatStr - "Technologies")
	 
	 // Split the string
	 println(repeatStr.split(' '))
	 println(repeatStr.toList())
	 
	 // Replace all strings
	 println(repeatStr.replaceAll('Hyderabad', 'U.S'))
	 
	 // Uppercase and lowercase
	 println("Uppercase " + bname.toUpperCase())
	 println("Lowercase " + bname.toLowerCase())
	 
	 // <=> returns -1 if 1st string is before 2nd
	 // 1 if the opposite and 0 if equal
	 println("Groovy <=> Maven " + ('Groovy' <=> 'Maven'))
	 println("Maven <=> Groovy " + ('Maven' <=> 'Groovy'))
	 println("Groovy <=> Groovy " + ('Groovy' <=> 'Groovy'))
	 
	 //  OUTPUT
	 // With double quotes we can insert variables
	 def randString = "Random"
	 println("A $randString string")
	 
	 // You can do the same thing with printf
	 printf("A %s string \n", randString)
	 
	 // Use multiple values
	 printf("%-10s %d %.2f %10s \n", ['Stuff', 10, 1.234, 'Random'])
	 
	 
//The "Elvis operator" is a shortening of the ternary operator.
	 
	 def user_name=''
	// displayName = user_name ? user_name : 'Anonymous'
	 displayName = user_name ?: 'Anonymous'
	 println(displayName)
	 
	 //Direct field access operator
	 class User {
		 public final String name
		 User(String name) { this.name = name}
		 String getName() { "Name: $name" }
	 }
	 def user = new User('Bob')
	 assert user.name == 'Name: Bob'
	 
	 //use of .@ forces usage of the field instead of the getter

	 assert user.@name == 'Bob'
	 
	 
	 //method pointer operation  (.&) can be used to store a reference to a method in a variable,
	 // in order to call it later:
	 def str = 'example of method reference'
	 def fun = str.&toUpperCase
	 def upper = fun()
	 assert upper == str.toUpperCase()
	 
	 def doSomething(String str) { str.toUpperCase() }
	 def doSomething(Integer x) { 2*x }
	 def reference = this.&doSomething
	 assert reference('foo') == 'FOO'
	 assert reference(123)   == 246
	 
	 //safe navigation operator, used to avoid null pointer exception ?.
	 
	 def krish = Arithmetic.find { it.sum == 123 }
	 def neem = krish?.name
	 assert neem == null
	 
	 //pattern operator ~
	 def p = ~/foo/
	 assert p instanceof Pattern
	 
	 
	 
	 //find operator =~
	 def text = "Hello world, text to match"
	 def m = text =~ /match/
	 assert m instanceof Matcher
	 if (!m) {
		 throw new RuntimeException("Oops, text not found!")
	 }
	 
	 //match operator ==~
	 m = text ==~ /match/
	 assert m instanceof Boolean
	 if (m) {
		 throw new RuntimeException("Should not reach that point!")
	 }
	 
	 
	 //spread operator *.
	 // It is equivalent to calling the action on each item and collecting the result into a list
	 
	 class Car {
		 String make
		 String model
	 }
	 def cars = [
			new Car(make: 'Peugeot', model: '508'),
			new Car(make: 'Renault', model: 'Clio')]
	 def makes = cars*.make
	 assert makes == ['Peugeot', 'Renault']
	 
	 
	 
	 //spreading method arguments
	 
	 int function(int x, int y, int z) {
		 x*y+z
	 }
	 
	 def args=[4,5,6]
	 assert function(*args)==26
	 
	 //spreading list arguments
	 
	 def items = [4,5]
	 def list = [1,2,3,*items,6]
	 print(list)
	 assert list == [1,2,3,4,5,6]
	 
	 
	 //membership operator
	 
	 def list1 = ['Ram','Kishore','Sai']
	 assert ('Sai' in list1)
	 
	 
	 //spread map elements
	 
	 def m1 = [c:3, d:4]
	 def map = [a:1, b:2, *:m1]
	 assert map == [a:1, b:2, c:3, d:4]
	 
	 
	 //identity operator
	 def list4 = ['Groovy 1.8','Groovy 2.0','Groovy 2.3']
	 def list5 = ['Groovy 1.8','Groovy 2.0','Groovy 2.3']
	 assert list4 == list5
	 assert !list4.is(list5)
	
	 
	 
	 Date utilDate = new Date(1000L)
	 println utilDate
	 SQLDate sqlDate = new SQLDate(1000L)
	 println sqlDate
	 
	 assert utilDate instanceof java.util.Date
	 assert sqlDate instanceof java.sql.Date
	 
	 