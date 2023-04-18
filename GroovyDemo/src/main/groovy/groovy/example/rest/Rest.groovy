package groovy.example.rest

String takeString(String message) {
	assert message instanceof String
	return message
}

def message = "The message is ${'hello'}"
assert message instanceof GString

println message

def result = takeString(message)
assert result instanceof String
assert result == 'The message is hello'

def key = "a"
def m = ["${key}": "letter ${key}"]

assert m["a"] == null

def numbers = [1, 2, 3]
println numbers
assert numbers instanceof List
assert numbers.size() == 3


def linkedList = [2, 3, 4] as LinkedList
assert linkedList instanceof LinkedList
println linkedList


print 'a' <=> 'd' //compare to 

def multi = [[0, 1], [2, 3]]
assert multi[1][0] == 2


def mapNumbers = [1: 'one', 2: 'two']

assert mapNumbers[1] == 'one'



