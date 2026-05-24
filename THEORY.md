## Anonymous classes: difference from lambda, examples.

### 1. Diving into Anonymous Classes

An anonymous class is an unnamed subclass or interface implementation that is created directly at the point of use. Before the advent of lambdas (Java 8), this was the most convenient way to implement an interface or abstract class "one-off."

**Classic example:**

```java
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Greetings from an anonymous class!");
    }
};
r.run();
```

Here we declared and immediately implemented the Runnable interface—without a separate file or class name. Such implementations were often used for event handlers, comparators, threads, and other tasks where behavior needed to be quickly "slipped in."

If a lambda is an "on-the-fly expression," then an anonymous class is a "little actor without a name," playing a cameo role and disappearing.

### 2. Comparison with Lambda Expressions

#### Syntax

**Anonymous class:**

```java
Comparator<String> comp = new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
};
```

**Lambda expression:**

```java
Comparator<String> comp = (a, b) -> a.length() - b.length();
```

The difference is obvious: lambdas are more compact—you don't need to explicitly specify types, method names, or unnecessary curly braces if the action is simple.

#### Functionality

- An anonymous class is a fully-fledged object. You can declare fields, additional methods, and override Object methods (toString, equals, etc.).
- A lambda expression implements a single abstract method of a functional interface. You cannot declare your own fields or additional methods within it.

#### When should you choose which?

- A lambda is when you need to briefly implement a single method of a functional interface.
- An anonymous class is when you need to:
- implement multiple methods (e.g., of an abstract class);
- declare fields for state;
- override Object methods (e.g., toString);
- use inheritance/access features (e.g., to protected members of a superclass).

### 3. Scope and the this keyword

Here lies a common pitfall:

- in an anonymous class, this refers to an instance of the anonymous class;
- in a **lambda expression**, this refers to the outer class in which the lambda is declared.

#### Example: Compare behavior

```java
public class Outer {
    String name = "Outer class";
    
    void test() {
        Runnable anon = new Runnable() {
            String name = "Anonymous class";
            @Override
            public void run() {
                System.out.println(this.name); // "Anonymous class"
            }
        };
        Runnable lambda = () -> System.out.println(this.name); // "Outer class"
        
        anon.run();
        lambda.run();
    }
}
```

**Output:**

```
Anonymous class
Outer class
```

In an anonymous class, this refers to the anonymous class itself (its name field is taken). In a lambda, this is Outer.

### 4. When to use anonymous classes?

#### If you need to implement more than one method

Lambda only works with functional interfaces (exactly one abstract method). If an interface/abstract class requires implementing multiple methods, you need an anonymous class.

```java
abstract class Animal {
    abstract void say();
    abstract void jump();
}

Animal cat = new Animal() {
    @Override
    void say() {
        System.out.println("Meow!");
    }
    @Override
    void jump() {
        System.out.println("Jump!");
    }
};
```

#### If you need to store state (fields)

```java
Runnable r = new Runnable() {
    int counter = 0;
    @Override
    public void run() {
        counter++;
        System.out.println("Called " + counter + " times");
    }
};
r.run(); // Called 1 time
r.run(); // Called 2 times
```

#### If you need to override Object methods

```java
Comparator<String> comp = new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
    @Override
    public String toString() {
        return "String length comparator";
    }
};
System.out.println(comp); // Comparator by string length
```

### 5. Examples: Comparator and Runnable — Lambda vs. Anonymous Class

#### Sorting Strings by Length

**Anonymous Class:**

```java
List<String> words = Arrays.asList("cat", "elephant", "mouse", "tiger");
words.sort(new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
});
System.out.println(words);
```

**Lambda Expression:**

```java
List<String> words = Arrays.asList("cat", "elephant", "mouse", "tiger");
    words.sort((a, b) -> a.length() - b.length());
    System.out.println(words);
```

**The result is the same**, but the code with the lambda is shorter and easier to read.

#### Runnable: starting a thread

**Anonymous class:**

```java
Thread t1 = new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("Thread via anonymous class");
    }
});
t1.start();
```

**Lambda expression:**

```java
Thread t2 = new Thread(() -> System.out.println("Thread via lambda"));
t2.start();
```

#### Anonymous class with fields

```java
Runnable r = new Runnable() {
    int count = 0;
    @Override
    public void run() {
        count++;
        System.out.println("Called " + count + " times");
    }
};
r.run(); // Called 1 time
r.run(); // Called 2 times
```

This is not allowed in a lambda—it's not possible to declare a field.

### 6. Features: Scope, Variables, and Final

In both anonymous classes and lambda expressions, local variables of an outer method can only be used if they are final or "effectively final" (they don't change after initialization). However, there is a nuance with names:

- in an anonymous class, you can declare a variable with the same name as one in the outer scope ("shadowing");
- in a lambda, you can't: the name must not conflict with the name of the outer variable.

**Example:**

```java
int x = 10;
Runnable r = new Runnable() {
    @Override
    public void run() {
        int x = 20; // OK: shadows the outer variable
        System.out.println(x); // 20
    }
};
r.run();

Runnable l = () -> {
    // int x = 30; // Compilation error: variable already defined
    System.out.println(x); // 10
};
l.run();
```

### 7. When is a lambda better, and when is an anonymous class indispensable?

#### Lambda expressions are your choice if:

- you need to implement a short function for a functional interface;
- you don't need to store state;
- you don't need to override Object methods;
- the implementation is used "here and now" and is simple.

#### An anonymous class is necessary if:

- you need to implement an interface with several methods or an abstract class;
- you need to declare fields or additional methods;
- you need to override toString, equals, and hashCode;
- you need access to protected members of the superclass.

### 8. Practice: Comparison with Examples

#### Task 1: Filtering a List with a Predicate

**Anonymous Class:**

```java
List<String> animals = Arrays.asList("cat", "elephant", "mouse", "tiger");
animals.removeIf(new Predicate<String>() {
    @Override
    public boolean test(String s) {
        return s.length() < 4;
    }
});
System.out.println(animals); // [elephant, mouse, tiger]
```

**Lambda Expression:**

```java
List<String> animals = Arrays.asList("cat", "elephant", "mouse", "tiger");
    animals.removeIf(s -> s.length() < 4);
    System.out.println(animals); // [elephant, mouse, tiger]
```

#### Task 2: Comparing the scope of this

```java
public class Demo { 
    String name = "Demo"; 
    
    void check() { 
        Runnable anon = new Runnable() { 
            String name = "Anon"; 
            @Override 
            public void run() { 
                System.out.println(this.name); // "Anon" 
            } 
        }; 
        
        Runnable lambda = () -> System.out.println(this.name); // "Demo" 
        
        anon.run(); 
        lambda.run(); 
    } 
    
    public static void main(String[] args) { 
        new Demo().check(); 
    }
}
```

### 9. Common Mistakes When Working with Anonymous Classes and Lambda Expressions

**Mistake №1: Expecting a lambda to implement multiple methods.** Lambdas only work with functional interfaces (one abstract method). If you have more methods, use an anonymous class.

**Mistake №2: Confusion about the scope of this.** In a lambda, this is the outer class, while in an anonymous class, it is the anonymous class itself. This can easily result in the "wrong" fields and values.

**Mistake №3: Attempting to declare fields in a lambda.** You can't declare your own fields in a lambda—you can only use variables of the outer context (final/effectively final). Use an anonymous class for state.

**Mistake №4: Variable shadowing.** In an anonymous class, you can declare a local variable with the same name as an outer variable—this is shadowing. This is not allowed in a lambda; the compiler will generate an error.

**Mistake №5: Overly complex logic in a lambda.** If the lambda body becomes longer than 3-5 lines, readability suffers. It's better to move the code into a separate method or use an anonymous class (if state or multiple methods are needed).
