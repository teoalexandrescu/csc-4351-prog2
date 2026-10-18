
typedef int Number;

var int basic;
var int age = 22;
var int[5] numbers;
var int[3] values = {1, 2, 3};

fun int add(int a, int b) {
    return a + b;
}

fun int calculate(int, int);

struct Person {
    int age;
    int height;
}

union Data {
    int number;
    char letter;
}

enum Color {
    RED,
    GREEN,
    BLUE
}

var int* ptr;
var const static int count = 10;

int main() {
    return 0;
}
