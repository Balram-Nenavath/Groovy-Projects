package groovy.example.demo



//Exception handling

try {
 noArgs(1)
}
catch(NullPointerException ex){

  // Prints exception
  println(ex.toString())

  // Prints error message
  println(ex.getMessage())
}
catch(Exception ex){
  println("Catch Everything")
}
finally {
  println("perform clean up")
}



//FUNCTION

def noArgs()
{
	println "calls the no args function"
}


def singleArg(int x)
{
	println " calling the single Arg $x"
}

def doubleArg(int x, int y)
{
		println("calling the double Args x and y is "+ (x+y))
}


singleArg 500
doubleArg 500, 200

