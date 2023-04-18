package groovy.example.demo

class Person {
  def name;
  def age;
 
  def name(){
	println("${name} ");
  }
 
  def theirAge(){
	println("${name} and their age is ${age}");
  }
 
  // Constructor Method
  def Person(name, age){
	this.name = name;
	this.age = age;
  }
  
  def increaseAge(Integer years) {
	  this.age += years
  }
}

