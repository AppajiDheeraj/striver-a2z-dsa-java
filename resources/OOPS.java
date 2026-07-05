/*
 * Java OOP practical notebook
 *
 * Run:
 *   javac OOPS.java
 *   java OOPS
 *
 * This file is intentionally written like an interview-practice script:
 * each section demonstrates one OOP concept that you may be asked to write
 * and explain on a whiteboard or in a coding round.
 */
public class OOPS {
    public static void main(String[] args) {
        System.out.println("1) Classes and Objects");
        Car car = new Car("Honda City", "White");
        car.accelerate(20);
        car.printDetails();

        System.out.println("\n2) Constructors");
        Student defaultStudent = new Student();
        Student namedStudent = new Student(101, "Dheeraj");
        Student copiedStudent = new Student(namedStudent);
        defaultStudent.printProfile();
        namedStudent.printProfile();
        copiedStudent.printProfile();

        System.out.println("\n3) Encapsulation");
        BankAccount account = new BankAccount("ACC1001", 5000);
        account.deposit(1500);
        account.withdraw(1200);
        System.out.println(account.getAccountNumber() + " balance = " + account.getBalance());

        System.out.println("\n4) Inheritance");
        Dog dog = new Dog("Bruno");
        dog.eat();
        dog.walk();
        dog.bark();

        System.out.println("\n5) Polymorphism - Overloading");
        Calculator calculator = new Calculator();
        System.out.println("add(int, int) = " + calculator.add(10, 20));
        System.out.println("add(double, double) = " + calculator.add(10.5, 20.5));
        System.out.println("add(int, int, int) = " + calculator.add(10, 20, 30));

        System.out.println("\n6) Polymorphism - Overriding");
        Animal animal = new Dog("Runtime Bruno");
        animal.sound();

        System.out.println("\n7) Abstraction with Abstract Class");
        Payment cardPayment = new CardPayment("VISA");
        cardPayment.pay(999);
        cardPayment.printReceipt();

        System.out.println("\n8) Interface Implementation");
        Notification email = new EmailNotification();
        email.send("Your order has shipped");
        email.preview("Your order has shipped");

        System.out.println("\n9) Multiple Interfaces");
        SmartPrinter printer = new SmartPrinter();
        printer.print("DSA notes");
        printer.scan("Aadhaar copy");

        System.out.println("\n10) Association, Aggregation, Composition");
        Teacher teacher = new Teacher("Anita Ma'am");
        Course course = new Course("Java OOP", teacher);
        course.printCourse();

        House house = new House("Bengaluru");
        house.printHouse();

        System.out.println("\n11) Private Constructor / Singleton");
        AppConfig config = AppConfig.getInstance();
        System.out.println(config.getAppName());
    }
}

/*
 * Class and Object:
 * A class is a blueprint. An object is a real instance created from that blueprint.
 * Object state lives in fields, and object behavior lives in methods.
 */
class Car {
    private String model;
    private String color;
    private int speed;

    Car(String model, String color) {
        this.model = model;
        this.color = color;
        this.speed = 0;
    }

    void accelerate(int increaseBy) {
        speed += increaseBy;
    }

    void printDetails() {
        System.out.println(color + " " + model + " is moving at " + speed + " km/h");
    }
}

/*
 * Constructors:
 * A constructor initializes a newly created object.
 * Java supports default/no-arg constructors, parameterized constructors,
 * copy-constructor style, and private constructors for controlled creation.
 */
class Student {
    private int rollNumber;
    private String name;

    Student() {
        this(0, "Unknown");
    }

    Student(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    Student(Student other) {
        this.rollNumber = other.rollNumber;
        this.name = other.name;
    }

    void printProfile() {
        System.out.println("Student{rollNumber=" + rollNumber + ", name='" + name + "'}");
    }
}

/*
 * Encapsulation:
 * Keep fields private and expose controlled operations.
 * This prevents invalid states such as negative balances.
 */
class BankAccount {
    private final String accountNumber;
    private double balance;

    BankAccount(String accountNumber, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.balance = openingBalance;
    }

    String getAccountNumber() {
        return accountNumber;
    }

    double getBalance() {
        return balance;
    }

    void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
        balance -= amount;
    }
}

/*
 * Inheritance:
 * A child class reuses fields and methods from a parent class using extends.
 * Java supports single, multilevel, and hierarchical inheritance for classes.
 */
class Animal {
    protected String name;

    Animal(String name) {
        this.name = name;
    }

    void eat() {
        System.out.println(name + " eats food");
    }

    void sound() {
        System.out.println(name + " makes a sound");
    }
}

class Mammal extends Animal {
    Mammal(String name) {
        super(name);
    }

    void walk() {
        System.out.println(name + " walks on land");
    }
}

class Dog extends Mammal {
    Dog(String name) {
        super(name);
    }

    void bark() {
        System.out.println(name + " barks");
    }

    @Override
    void sound() {
        System.out.println(name + " says: woof");
    }
}

class Cat extends Mammal {
    Cat(String name) {
        super(name);
    }

    @Override
    void sound() {
        System.out.println(name + " says: meow");
    }
}

/*
 * Method Overloading:
 * Same method name, different parameter list.
 * This is compile-time polymorphism because the compiler selects the method.
 */
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }
}

/*
 * Abstraction:
 * Payment exposes what must happen, while subclasses decide how it happens.
 * Abstract classes can contain both abstract and concrete methods.
 */
abstract class Payment {
    abstract void pay(double amount);

    void printReceipt() {
        System.out.println("Receipt generated");
    }
}

class CardPayment extends Payment {
    private final String cardNetwork;

    CardPayment(String cardNetwork) {
        this.cardNetwork = cardNetwork;
    }

    @Override
    void pay(double amount) {
        System.out.println("Paid " + amount + " using " + cardNetwork + " card");
    }
}

/*
 * Interface:
 * An interface defines a contract. A class implements that contract.
 * Default methods allow reusable behavior inside interfaces.
 */
interface Notification {
    void send(String message);

    default void preview(String message) {
        System.out.println("Preview: " + message);
    }
}

class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}

/*
 * Multiple interface implementation:
 * Java does not allow one class to extend multiple classes, but it allows
 * one class to implement multiple interfaces.
 */
interface Printable {
    void print(String text);
}

interface Scannable {
    void scan(String documentName);
}

class SmartPrinter implements Printable, Scannable {
    @Override
    public void print(String text) {
        System.out.println("Printing: " + text);
    }

    @Override
    public void scan(String documentName) {
        System.out.println("Scanning: " + documentName);
    }
}

/*
 * Association:
 * Objects know about each other and collaborate.
 *
 * Aggregation is a weak has-a relationship. Teacher can exist without Course.
 * Composition is a strong has-a relationship. Room is created and owned by House.
 */
class Teacher {
    private final String name;

    Teacher(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Course {
    private final String title;
    private final Teacher teacher;

    Course(String title, Teacher teacher) {
        this.title = title;
        this.teacher = teacher;
    }

    void printCourse() {
        System.out.println(title + " is taught by " + teacher.getName());
    }
}

class Room {
    private final String type;

    Room(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }
}

class House {
    private final String city;
    private final Room livingRoom;

    House(String city) {
        this.city = city;
        this.livingRoom = new Room("Living room");
    }

    void printHouse() {
        System.out.println("House in " + city + " has a " + livingRoom.getType());
    }
}

/*
 * Private Constructor:
 * Used when object creation must be controlled, such as a Singleton.
 */
class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();
    private final String appName;

    private AppConfig() {
        this.appName = "Code DSA Java OOP";
    }

    static AppConfig getInstance() {
        return INSTANCE;
    }

    String getAppName() {
        return appName;
    }
}
