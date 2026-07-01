# Java OOP Interview Notes

Use this with [OOPS.java](/Users/appajidheeraj/Desktop/Code_DSA/OOPS.java). The Java file is for writing/running examples. This note is for quick explanation before interviews.

## OOP Definition

Object-Oriented Programming is a programming style where we model software using classes and objects. A class defines state and behavior. An object is an instance of that class.

## 1. Class

A class is a blueprint or template. It defines:

- Fields: data/state
- Methods: behavior/actions
- Constructors: object initialization logic

Example: `Car` class has `model`, `color`, `speed`, and methods like `accelerate()`.

## 2. Object

An object is a real instance of a class created using `new`.

Important object ideas:

- State: current field values
- Behavior: methods the object can perform
- Identity: unique memory identity even if two objects have same data

## 3. Constructor

A constructor is called automatically when an object is created.

Rules:

- Constructor name must match the class name.
- It has no return type, not even `void`.
- It initializes object state.
- If no constructor is written, Java gives a default no-arg constructor.

Types:

- Default/no-arg constructor: `Student()`
- Parameterized constructor: `Student(int rollNumber, String name)`
- Copy constructor style: `Student(Student other)`
- Private constructor: used when object creation must be controlled, such as Singleton

## 4. Encapsulation

Encapsulation means bundling data and methods inside one class and controlling access to the data.

Standard pattern:

- Make fields `private`.
- Expose safe methods like getters, setters, deposits, withdrawals, update methods, etc.
- Validate data before changing object state.

Why it matters:

- Prevents invalid data.
- Makes code easier to maintain.
- Protects implementation details from outside code.

Example: `BankAccount` does not allow direct balance editing. It allows `deposit()` and `withdraw()` with validation.

## 5. Inheritance

Inheritance lets one class reuse another class's fields and methods using `extends`.

Java class inheritance types:

- Single: `Dog extends Animal`
- Multilevel: `Dog extends Mammal extends Animal`
- Hierarchical: `Dog` and `Cat` both extend `Mammal`

Java does not support multiple inheritance with classes. A class cannot extend two classes. Java uses interfaces to achieve multiple capability contracts.

## 6. Polymorphism

Polymorphism means one name or reference can take many forms.

### Compile-Time Polymorphism: Overloading

Method overloading means same method name with different parameters.

Allowed differences:

- Different number of parameters
- Different parameter types
- Different parameter order

Not enough:

- Only changing return type is not valid overloading.

Example: `Calculator.add(int, int)`, `Calculator.add(int, int, int)`, `Calculator.add(double, double)`.

### Runtime Polymorphism: Overriding

Method overriding means a child class gives its own implementation of a parent method.

Rules:

- Parent-child relationship required.
- Method signature must be same.
- Return type must be same or covariant.
- Access cannot become more restrictive.
- Use `@Override` to let compiler catch mistakes.

Example:

```java
Animal animal = new Dog("Bruno");
animal.sound(); // Dog version runs at runtime
```

This works because method dispatch depends on the actual object, not only the reference type.

## 7. Abstraction

Abstraction means showing what an object does while hiding how it does it.

Java supports abstraction through:

- Abstract classes
- Interfaces

Use abstraction when caller code should depend on a common contract instead of exact implementation.

## 8. Abstract Class

An abstract class is a partial blueprint.

It can have:

- Abstract methods without body
- Concrete methods with body
- Fields
- Constructors

Rules:

- Declared using `abstract`.
- Cannot be instantiated directly.
- Child class must implement abstract methods or also become abstract.

Use when classes share a base identity and common behavior.

Example: `Payment` defines `pay()` and reusable `printReceipt()`.

## 9. Interface

An interface is a contract for a class.

It can have:

- Abstract methods
- `default` methods
- `static` methods
- Constants, which are implicitly `public static final`

Rules:

- A class uses `implements`.
- A class can implement multiple interfaces.
- Interface methods are public contracts, so implementation methods must be `public`.

Use when you want to describe capability.

Example: `Notification`, `Printable`, `Scannable`.

## 10. Interface vs Abstract Class

| Topic | Abstract Class | Interface |
|---|---|---|
| Keyword | `extends` | `implements` |
| Multiple support | Only one class can be extended | Multiple interfaces can be implemented |
| Constructor | Yes | No |
| Instance fields | Yes | No normal instance fields |
| Methods | Abstract and concrete | Abstract, default, static |
| Best use | Shared base behavior | Capability/contract |

## 11. Association

Association means two classes are connected and objects communicate with each other.

Example: `Course` has a `Teacher`.

## 12. Aggregation

Aggregation is a weak has-a relationship.

Example: `Course` has a `Teacher`, but the teacher can exist without the course.

Interview phrase: independent lifecycles.

## 13. Composition

Composition is a strong has-a relationship.

Example: `House` creates and owns its `Room`. The room is part of the house.

Interview phrase: dependent lifecycle.

## 14. Access Modifiers

| Modifier | Same Class | Same Package | Subclass different package | Everywhere |
|---|---|---|---|---|
| `private` | Yes | No | No | No |
| default | Yes | Yes | No | No |
| `protected` | Yes | Yes | Yes | No |
| `public` | Yes | Yes | Yes | Yes |

Quick memory:

- `private`: only inside same class
- default: same package
- `protected`: same package plus subclasses
- `public`: everywhere

## 15. Important Keywords

- `this`: current object reference
- `super`: parent class reference
- `extends`: inherit from a class
- `implements`: implement an interface
- `abstract`: incomplete class/method
- `final`: cannot be changed, overridden, or inherited depending on where used
- `static`: belongs to class, not object
- `private`: accessible only inside same class
- `public`: accessible from everywhere

## Common Interview Questions

### What is the difference between overloading and overriding?

Overloading is same method name with different parameters in the same class. It is compile-time polymorphism.

Overriding is same method signature in parent and child class. It is runtime polymorphism.

### Why does Java not support multiple inheritance with classes?

To avoid ambiguity such as the Diamond Problem, where two parent classes may provide the same method and Java would not know which one to inherit.

### How does Java support multiple inheritance behavior?

Through interfaces. A class can implement multiple interfaces.

### When should I use an abstract class?

Use an abstract class when related classes share a base identity and common reusable behavior.

### When should I use an interface?

Use an interface when you want to define a capability or contract that many unrelated classes can implement.

### What is encapsulation in one line?

Encapsulation is hiding object data using private fields and exposing safe operations through methods.

### What is abstraction in one line?

Abstraction hides internal implementation and exposes only essential behavior.

## Practice Prompts

Try writing these without looking:

1. Create a `Vehicle` parent class and `Bike`, `Car` child classes.
2. Demonstrate method overloading with `area()`.
3. Demonstrate method overriding with `Animal.sound()`.
4. Create an interface `Playable` and implement it in `Guitar`.
5. Create an abstract class `Shape` with abstract method `area()`.
6. Show aggregation with `Department` and `Professor`.
7. Show composition with `Computer` and `Processor`.
8. Create a class with private fields and public getters/setters.
