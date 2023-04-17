package groovy.example.demo

class Emp extends Person{
	def dept;
   
	// Constructor Method
	def Emp(name, age, dept){
   
	  super(name, age);
	  this.dept = dept;
	}
   
	def defineNewAge(){
	  println("${name} and his new age from Emp is ${age}");
	}
  }