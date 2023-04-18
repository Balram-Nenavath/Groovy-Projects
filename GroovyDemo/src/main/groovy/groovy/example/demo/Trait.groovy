package groovy.example.demo

/*class Trait {
}*/

//They can be seen as interfaces carrying both default implementations and state.
// A trait is defined using the trait keyword:
//trait only supports public and private methods, neither protected nor package private
trait FlyingAbility {
	String fly() { "I'm flying!" }
}

class Bird implements FlyingAbility {}
def b = new Bird()
assert b.fly() == "I'm flying!"



//Abstract methods inside a trait
trait Greetable {
	abstract String name()
	String greeting() { "Hello, ${name()}!" }
}

class Ramson implements Greetable {
	String name() { 'Ram' }
}

def p = new Ramson()
assert p.greeting() == 'Hello, Ram!'



//traits with  private methods
trait Greeter1 {
	private String greetingMessage() {
		'Hello from a private method!'
	}
	String greet() {
		def m = greetingMessage()
		println m
		m
	}
}
class GreetingMachine implements Greeter1 {}
def g = new GreetingMachine()
assert g.greet() == "Hello from a private method!"
try {
	assert g.greetingMessage()
} catch (MissingMethodException e) {
	println "greetingMessage is private in trait"
}


//Trait itself may implement interfaces
//trait will have private fields inside it
//trait may define  properties

//trait can also define public field but need to be renamed

//can call multiple traits and can override default methods
trait SpeakingAbility {
	String speak() { "I'm speaking!" }
}

class Duck implements FlyingAbility, SpeakingAbility {
	
	String quack() { "Quack!" }
	String speak() { quack() }
}

def d = new Duck()
assert d.fly() == "I'm flying!"
assert d.speak() == "I'm speaking!"
assert d.quack() == "Quack!"
assert d.speak() == "Quack!"