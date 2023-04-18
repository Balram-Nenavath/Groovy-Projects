package groovy.example.demo

import groovy.transform.AnnotationCollector

/*class ObjectO {
 }*/

class Foo {
	static int i
}

assert Foo.class.getDeclaredField('i').type == int.class
//assert Foo.class.getDeclaredField('i').type == String.class
assert Foo.i.class != int.class && Foo.i.class == Integer.class

println("-------------------------------------")
interface Greeter { void greet() }
interface Salute { void salute() }

class A implements Greeter, Salute {
	void greet() { println "Hello, I'm A!" }
	void salute() { println "Bye from A!" }
}
class B implements Greeter, Salute {
	void greet() { println "Hello, I'm B!" }
	void salute() { println "Bye from B!" }
	void exit() { println 'No way!' }
}
def list = [new A(), new B()]
list.each {
	it.greet()
	it.salute()
	//it.exit()
}

//constructor calling with constructor
class PersonConstructor {
	String name
	Integer age

	PersonConstructor(name, age) {
		this.name = name
		this.age = age
	}
}

def person1 = new PersonConstructor('Sai', 1)
def person2 = ['Sai', 2] as PersonConstructor //as keyword
PersonConstructor person3 = ['Sai', 3]
println(person1.getProperties())
println(person2.getAge())
println(person3.getName())



//constructor calling without constructor
class PersonWOConstructor {
	String name
	Integer age

}
def person4 = new PersonWOConstructor()
def person5 = new PersonWOConstructor(name: 'Ram')
def person6 = new PersonWOConstructor(age: 1)
def person7 = new PersonWOConstructor(name: 'Ram', age: 2)
println(person4)
println(person5.age)
println(person6.name)
println(person7.name)


def foo(Map args, Integer number) { "${args.name}: ${args.age}, and the number is ${number}" }
foo(name: 'Marie', age: 1, 23)
foo(23, name: 'Marie', age: 1)

def foo1(Integer number,Map args) { "${args.name}: ${args.age}, and the number is ${number}" }
//foo1(name: 'Marie', age: 1, 23)  
//map argument should be kept as first argument else it will throw method missing exception
foo1(23, [name: 'Marie', age: 1]) // can avoid above exception with this

//n number of args can be passed using ... varargs
def foo2(Object... args) { args.length }
assert foo2() == 0
assert foo2(1) == 1
assert foo2(1, 2) == 2

//Multimethods ... method selection algorithm
def method(Object o1, Object o2) { 'o/o' }
def method(Integer i, String  s) { 'i/s' }
def method(String  s, Integer i) { 's/i' }
assert method('foo', 42) == 's/i'
List<List<Object>> pairs = [['foo', 1], [2, 'bar'], [3, 4]]
assert pairs.collect { a, b -> method(a, b) } == ['s/i', 'i/s', 'o/o']


//property accessing 
class Nelson {
	String name
	void name(String name) {
		this.name = "Nenavath $name"
	}
	String title() {
		this.name
	}
}
def p = new Nelson()
p.name = 'Balram'
assert p.name == 'Balram'
p.name('Balram')
assert p.title() == 'Nenavath Balram'


//Annotations on a property, @Lazy will confirm that there is no eager
//so execution wait and the first assertion lowercount==0, then uppercase and count increment
//if lazy is removed directly it will execute and count increases causing first assertion error
class Animal {
	int lowerCount = 0
	@Lazy 
	String name = { lower().toUpperCase() }()
	String lower() { lowerCount++; 'balram' }
}

def a = new Animal()
assert a.lowerCount == 0
assert a.name == 'BALRAM'
assert a.lowerCount == 1

@interface Page {
	int statusCode()
}

@Page(statusCode=404)
void notFound() {
	// ...
}

//Meta annotations
//@Timeout(after=3600)
//@Dangerous(type='explosive')
//@Explosive(after=0)
@AnnotationCollector
public @interface Explosive {}