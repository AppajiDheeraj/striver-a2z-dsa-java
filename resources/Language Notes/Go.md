# 🐹 Go (Golang) — Complete Notes
> For interviews + self-study
> Tags: #golang #programming #interview #concurrency #systems

---

## 1. What is Go?

Go (Golang) is a **statically typed, compiled language** created at **Google** by **Robert Griesemer, Rob Pike, and Ken Thompson**, released in **2009**. It was designed to combine the performance of a compiled language like C with the simplicity and safety of a modern language.

> **Interview line:** Go is a compiled, statically typed, garbage-collected language built for simplicity, fast compilation, and native concurrency via goroutines and channels.

- **Compiled** → Compiles directly to native machine code (no VM/bytecode step like Java).
- **Statically Typed** → Types checked at compile time; no runtime type surprises.
- **Garbage Collected** → Automatic memory management (concurrent, low-latency GC).
- **Fast Compilation** → Go's build graph and tooling are designed for near-instant builds even on huge codebases.
- **Concurrency-first** → Goroutines + channels are core language primitives, not library add-ons.
- **Simplicity by design** → No classes, no inheritance, no generics until 1.18, no exceptions — deliberately minimal.
- **Batteries included tooling** → `go fmt`, `go vet`, `go test`, `go mod` ship with the language.

### Why Go Was Created
- **Problem at Google** → C++ builds were slow at scale, dependency management was messy, and concurrency was hard to get right.
- **Goal** → A language that compiles fast, scales to large codebases/teams, and makes concurrent programming safe and idiomatic.
- **Key Milestones** → Go 1.0 (2012, stability promise) → Go 1.11 (2018, modules) → Go 1.18 (2022, generics) → Go 1.21+ (built-in `min`/`max`, improved GC).

# Go's Fast Compilation: How It Works

Go is engineered for near-instant builds, even on multi-million-line codebases. This design keeps developers in a productive "flow state" by eliminating long compilation wait times. 

#### 1. Strict Build Graph
* **No Circular Dependencies:** Go enforces a strict, one-way dependency tree. DAG
* **Compile Once, Read Once:** When Package A imports Package B, the compiler reads a tiny, pre-compiled summary of B rather than repeatedly parsing its raw source code.
* **Transitive Efficiency:** If A imports B, and B imports C, Package A only parses B's summary and completely ignores C.

#### 2. Simplified Language Grammar
* **No Header Files:** Eliminates the massive file-reading overhead found in C/C++ (no separating `.h` and `.cpp` files).
* **No Heavy Metaprogramming:** Avoids complex macros and heavy templates that force compilers to execute code generation before actual compilation.

#### 3. Aggressive Tooling & Caching
* **Smart Build Cache:** The `go build` toolchain automatically caches compiled packages. 
* **Surgical Recompilation:** When code is modified, Go instantly identifies the exact branch of the build graph affected and recompiles *only* what is strictly necessary.

---

## 2. Toolchain: `go build`, `go run`, `go mod`

### The Compilation Model
```
main.go ──[go build]──► native binary (single static executable)
```

Unlike Java (bytecode + JVM), Go compiles **directly to machine code** for the target OS/architecture. No runtime installation needed on the target machine — you ship one binary.

### Go's Single Static Executable: Deploying Made Easy

When you build a Go program, the compiler packs your code, all your dependencies, and the Go runtime into **one standalone file** (like a `.exe` on Windows or a binary on Linux).

#### 1. The Problem: Dynamic Linking
Most programming languages require the target computer to have pre-installed tools or libraries to run an application:

- **Runtimes required:** Python, Node.js, and Java require their specific runtime environments (such as the JVM) to be installed first.
- **Shared libraries:** C/C++ programs often depend on shared libraries (`.dll` on Windows, `.so` on Linux). If even one required library is missing, the application may fail to start (commonly known as **DLL Hell**).

#### 2. Go's Solution: Static Linking
Go assumes the target machine has nothing installed. During compilation, it bundles your application code, its dependencies, and the Go runtime into a single executable.

- **No runtime installation required** on the target machine.
- **No missing dependency issues** caused by external shared libraries.
- **Portable deployment:** If the binary is built for a specific operating system and CPU architecture, it can run directly on that platform.

#### 3. MRE Analogy
- **Dynamic Linking:** Like taking a can of soup camping and expecting the campsite to provide a can opener, pot, and stove.
- **Static Linking (Go):** Like carrying an MRE (Meal Ready-to-Eat), where the food, heater, and utensils are already included in one package.

#### 4. Why This Matters
Because everything is packaged into one executable, deployment becomes extremely simple. To deploy a Go application to a cloud server (AWS, Google Cloud, Azure, etc.), you typically just copy the binary to the server and execute it—without installing the Go compiler, runtime, or additional dependencies.

> **Interview point:** One of Go's biggest strengths for DevOps, cloud-native applications, containers, and serverless platforms is that distribution is as simple as shipping a single executable.


| Command | Purpose |
|---|---|
| `go run main.go` | Compile + execute immediately (no binary kept) |
| `go build` | Compile into a binary, no execution |
| `go install` | Build and place binary in `$GOPATH/bin` |
| `go mod init <module>` | Initialize a new module (`go.mod` file) |
| `go mod tidy` | Add missing / remove unused dependencies |
| `go fmt` | Auto-format code (Go's opinionated formatter) |
| `go vet` | Static analysis for suspicious code |
| `go test` | Run tests |
| `go get <pkg>` | Add a dependency |

> **Cross-compilation** → `GOOS=linux GOARCH=amd64 go build` produces a Linux binary from any OS. This is a huge advantage for Go in serverless/container/edge tooling (relevant if you're shipping wasmdee-style runtimes).

---

## 3. Hello World — Anatomy

```go
package main

import "fmt"

func main() {
    fmt.Println("Hello, World!")
}
```

| Keyword | Meaning |
|---|---|
| `package main` | Declares this file belongs to the `main` package — required for an executable (as opposed to a library package). |
| `import "fmt"` | Imports the formatting/I-O package. |
| `func main()` | The entry point — Go runtime looks for `main()` inside `package main`. No `public static void` boilerplate. |
| `fmt.Println` | Prints text with a trailing newline. |

> **No semicolons needed** — Go's compiler auto-inserts them based on lexer rules, but you write code without them. Braces `{}` must be on the same line as the statement (mandatory style enforced by the compiler, not just `gofmt`).

### Unexported vs Exported (Go's access modifier system)
Go has no `public`/`private` keywords. Instead:
- **Capitalized identifier** (`func Foo()`, `var Bar`) → **exported** (public, visible outside package).
- **Lowercase identifier** (`func foo()`, `var bar`) → **unexported** (private to the package).

```go
package shapes

func Area() {}   // exported — accessible as shapes.Area()
func area() {}   // unexported — only usable within package "shapes"
```

> **Interview point:** This is Go's entire visibility model — capitalization IS the access modifier. No `protected`, no fine-grained control.

---

## 4. Data Types

Go has built-in primitive types, no boxing/unboxing distinction like Java (though there is an `interface{}` escape hatch).

### Basic Types
| Category | Types | Notes |
|---|---|---|
| **Integers** | `int8, int16, int32, int64`, `uint8...uint64`, `int`, `uint` | `int`/`uint` are platform-dependent (32 or 64-bit) |
| **Floats** | `float32`, `float64` | `float64` is default for decimal literals |
| **Complex** | `complex64`, `complex128` | Rarely used outside scientific computing |
| **Boolean** | `bool` | `true` / `false` |
| **String** | `string` | Immutable byte sequence (UTF-8 by default) |
| **Byte / Rune** | `byte` (alias for `uint8`), `rune` (alias for `int32`) | `rune` represents a Unicode code point |

```go
var age int = 25
var pi float64 = 3.14159
name := "Dheeraj"          // short variable declaration, type inferred
const MaxUsers = 100        // constant
```

> **`:=` vs `var`** → `:=` is short declaration + inference, only usable inside functions. `var` is explicit, usable at package level too.

### Zero Values (Go has NO `null` by default for most types!)
Every declared-but-uninitialized variable gets a **zero value** instead of `null`/garbage:

| Type | Zero Value |
|---|---|
| `int`, `float` | `0` |
| `bool` | `false` |
| `string` | `""` (empty string) |
| pointer, slice, map, channel, function, interface | `nil` |

> **Interview point:** This eliminates a whole class of "uninitialized variable" bugs common in C. `nil` in Go is closer to a typed null pointer — only reference-like types can be `nil`.

---

## 5. Variables, Constants, Pointers

### Variable Declaration Forms
```go
var x int              // zero value: 0
var y int = 10         // explicit
z := 10                // inferred, function-scope only
var a, b = 1, "two"    // multiple, mixed types
```

### Pointers
Go supports pointers but **no pointer arithmetic** (unlike C) — this is a deliberate safety trade-off.

```go
x := 10
p := &x        // p is *int, holds address of x
*p = 20        // dereference and modify
fmt.Println(x) // 20
```

| Symbol | Meaning |
|---|---|
| `&x` | Address-of operator — get pointer to `x` |
| `*p` | Dereference operator — get value pointed to by `p` |
| `*int` | Type: "pointer to int" |

> **Why use pointers?** Passing large structs by pointer avoids copying. Also needed when a function must mutate the caller's variable (Go is pass-by-value everywhere — pointers are how you opt into reference semantics).

```go
func increment(n *int) {
    *n++
}
x := 5
increment(&x)   // x is now 6
```

---

## 6. Functions

```go
func add(a int, b int) int {
    return a + b
}

// shorthand when params share a type
func add(a, b int) int {
    return a + b
}
```

### Multiple Return Values (signature Go feature)
```go
func divide(a, b int) (int, error) {
    if b == 0 {
        return 0, errors.New("division by zero")
    }
    return a / b, nil
}

result, err := divide(10, 2)
if err != nil {
    // handle error
}
```

> **Interview point:** This is how Go replaces exceptions — functions that can fail return `(value, error)` and the caller **must** explicitly check `err`. There's no `try/catch`.

### Named Return Values
```go
func split(sum int) (x, y int) {
    x = sum * 4 / 9
    y = sum - x
    return   // "naked" return — returns x, y automatically
}
```

### Variadic Functions
```go
func sum(nums ...int) int {
    total := 0
    for _, n := range nums {
        total += n
    }
    return total
}
sum(1, 2, 3)          // nums = [1,2,3]
sum([]int{1,2,3}...)  // spread a slice
```

### Closures
Functions in Go are first-class values and can capture surrounding variables.
```go
func counter() func() int {
    count := 0
    return func() int {
        count++
        return count
    }
}
c := counter()
c() // 1
c() // 2
```

### Defer
`defer` schedules a function call to run **just before the enclosing function returns** — regardless of how it returns (normal return, panic, etc.). Deferred calls execute in **LIFO order**.

```go
func readFile() {
    f, _ := os.Open("file.txt")
    defer f.Close()   // guaranteed cleanup, runs last
    // ... use f
}
```

> **Common use:** resource cleanup (closing files, unlocking mutexes, closing DB connections) — Go's answer to `try-with-resources`/`finally`.

---

## 7. Control Flow

Go deliberately has **fewer looping/branching constructs** than most languages.

### The Only Loop: `for`
Go has no `while` or `do-while` — `for` covers all cases.
```go
for i := 0; i < 10; i++ { }      // classic for
for condition { }                 // while-style
for { }                           // infinite loop
for i, v := range slice { }       // range-based (for-each equivalent)
```

### if / else
```go
if x := compute(); x > 0 {   // can include an init statement
    fmt.Println(x)
} else {
    fmt.Println("non-positive")
}
```
> No parentheses required around the condition; braces are mandatory.

### switch
Go's `switch` doesn't fall through by default (opposite of Java/C) — each case auto-breaks.
```go
switch day {
case "Mon", "Tue", "Wed", "Thu", "Fri":
    fmt.Println("Weekday")
case "Sat", "Sun":
    fmt.Println("Weekend")
default:
    fmt.Println("Invalid")
}
```
Use `fallthrough` explicitly if you want the old C-style behavior.

Switch can also be used **without a condition**, acting as a cleaner if-else chain:
```go
switch {
case score >= 90:
    grade = "A"
case score >= 75:
    grade = "B"
default:
    grade = "C"
}
```

---

## 8. Arrays, Slices, Maps

### Arrays — Fixed Size
```go
var arr [5]int              // fixed size, part of the type itself
arr := [3]string{"a","b","c"}
```
> `[5]int` and `[10]int` are **different types**. Arrays are rarely used directly — slices are idiomatic Go.

### Slices — Dynamic, Most-Used Data Structure
A slice is a **view over an underlying array** — it has a pointer, length, and capacity.

```go
s := []int{1, 2, 3}         // slice literal
s = append(s, 4)            // grows dynamically
sub := s[1:3]                // slicing: elements at index 1,2
len(s)                        // current length
cap(s)                        // current capacity (underlying array size)
```

**Internal structure of a slice:**
```
Slice Header
├── pointer → underlying array
├── len     → number of elements in view
└── cap     → max elements before reallocation needed
```

> **Interview trap:** Slicing shares the underlying array. Modifying `sub` can modify `s` if they overlap. `append()` may or may not allocate a new array depending on whether capacity is exceeded — this is a very common Go interview gotcha.

```go
make([]int, 5)        // slice of length 5, zero-valued
make([]int, 5, 10)    // length 5, capacity 10
```

### Maps — Key-Value Store
```go
m := make(map[string]int)
m["age"] = 25
val, ok := m["age"]     // "comma ok" idiom — ok is false if key absent
delete(m, "age")

m2 := map[string]int{"a": 1, "b": 2}  // literal
```

> **Zero value of a map field is `nil`** — reading from a nil map returns zero values safely, but **writing to a nil map panics**. Always `make()` before writing.

> **Maps are unordered** — iteration order is intentionally randomized by the runtime to prevent code from relying on it.

---

## 9. Structs & Methods

Go has **no classes**. Structs + methods + interfaces replace OOP entirely.

### Struct — data grouping
```go
type Employee struct {
    Name   string
    Salary float64
}

e := Employee{Name: "Dheeraj", Salary: 50000}
fmt.Println(e.Name)
```

### Methods — functions with a receiver
```go
func (e Employee) Describe() string {          // value receiver
    return e.Name + " earns " + fmt.Sprint(e.Salary)
}

func (e *Employee) GiveRaise(amount float64) {  // pointer receiver
    e.Salary += amount
}
```

| Receiver Type | Behavior |
|---|---|
| Value receiver `(e Employee)` | Method operates on a **copy** — cannot mutate the original |
| Pointer receiver `(e *Employee)` | Method operates on the **original** via pointer — can mutate |

> **Interview rule of thumb:** Use pointer receivers when the method mutates state, or when the struct is large (avoid copying), or for consistency if any method on the type needs one.

### Embedding — Go's answer to inheritance
```go
type Animal struct {
    Name string
}
func (a Animal) Eat() { fmt.Println(a.Name, "is eating") }

type Dog struct {
    Animal      // embedded struct — NOT inheritance, composition
    Breed string
}

d := Dog{Animal{"Rex"}, "Labrador"}
d.Eat()          // promoted method — accessible directly
```

> **Key distinction from Java:** This is **composition, not inheritance**. `Dog` "has-an" `Animal` whose fields/methods are *promoted* to the outer struct for convenience — there's no `is-a` polymorphic relationship, no virtual dispatch, no method overriding in the OOP sense.

---

## 10. Interfaces

Interfaces in Go are **implicit** — a type satisfies an interface automatically just by implementing its methods. No `implements` keyword.

```go
type Shape interface {
    Area() float64
}

type Circle struct{ Radius float64 }
func (c Circle) Area() float64 { return 3.14 * c.Radius * c.Radius }

type Rectangle struct{ W, H float64 }
func (r Rectangle) Area() float64 { return r.W * r.H }

func printArea(s Shape) {
    fmt.Println(s.Area())
}
printArea(Circle{Radius: 5})       // works — Circle satisfies Shape implicitly
printArea(Rectangle{W: 2, H: 3})   // works too
```

> **Interview point (huge one):** This is called **structural typing** or "duck typing at compile time" — "If it walks like a duck and quacks like a duck, it's a duck." Contrast with Java's nominal typing, where a class must explicitly declare `implements Shape`.

### The Empty Interface `interface{}` / `any`
```go
var x interface{} = 42     // can hold any type
var y any = "hello"        // `any` is an alias for interface{} since Go 1.18
```
Used for generic-ish behavior before Go 1.18 introduced real generics (e.g., in `fmt.Println(args ...interface{})`).

### Type Assertion & Type Switch
```go
var i interface{} = "hello"

s, ok := i.(string)      // safe assertion, ok=false if wrong type
switch v := i.(type) {
case int:
    fmt.Println("int:", v)
case string:
    fmt.Println("string:", v)
default:
    fmt.Println("unknown")
}
```

---

## 11. Error Handling (No Exceptions!)

Go treats errors as **regular values**, not a separate control-flow mechanism.

```go
type error interface {
    Error() string
}
```

### Idiomatic Pattern
```go
func doSomething() error {
    if somethingWrong {
        return errors.New("something went wrong")
    }
    return nil
}

if err := doSomething(); err != nil {
    log.Println("error:", err)
    return err
}
```

### Wrapping Errors (Go 1.13+)
```go
if err != nil {
    return fmt.Errorf("failed to process: %w", err)   // %w wraps, preserving the chain
}

// unwrap / inspect
errors.Is(err, sql.ErrNoRows)
errors.As(err, &myCustomErr)
```

### panic / recover — Go's "exception" mechanism (rarely used)
```go
func safeDivide(a, b int) (result int, err error) {
    defer func() {
        if r := recover(); r != nil {
            err = fmt.Errorf("recovered: %v", r)
        }
    }()
    return a / b, nil   // panics on b == 0 (divide by zero)
}
```

> **Interview point:** `panic` is reserved for truly unrecoverable situations (programmer bugs, corrupted state) — NOT for expected/handled error paths. Idiomatic Go almost never uses panic/recover for regular control flow; that's what `error` returns are for. This is the single biggest philosophical difference from Java's checked-exception model.

---

## 12. Concurrency — Goroutines & Channels

This is Go's headline feature and the most-asked interview topic.

### Goroutines — lightweight threads
```go
go doWork()               // runs concurrently, managed by Go runtime (not OS threads directly)
```

| | OS Thread | Goroutine |
|---|---|---|
| Stack size | Fixed, ~1-8MB | Starts at ~2KB, grows dynamically |
| Managed by | OS scheduler | Go runtime scheduler (M:N scheduling) |
| Creation cost | Expensive | Extremely cheap — can spawn 100,000s |
| Context switch | OS-level, costly | User-space, very cheap |

> **M:N scheduling model:** Go multiplexes **M** goroutines onto **N** OS threads. The Go scheduler (GMP model: Goroutine, Machine/OS thread, Processor) handles this transparently.

### Channels — communication between goroutines
> **Go proverb:** "Do not communicate by sharing memory; share memory by communicating."

```go
ch := make(chan int)           // unbuffered channel
ch <- 42                       // send (blocks until received)
val := <-ch                    // receive (blocks until sent)

buffered := make(chan int, 5)  // buffered channel, holds up to 5 without blocking
```

| Channel Type | Behavior |
|---|---|
| Unbuffered | Send blocks until a receiver is ready (synchronous handoff) |
| Buffered | Send only blocks once the buffer is full |

```go
close(ch)                       // signal no more values will be sent
v, ok := <-ch                   // ok=false if channel is closed and drained

for v := range ch {             // ranges until channel is closed
    fmt.Println(v)
}
```

### select — waiting on multiple channels
```go
select {
case msg1 := <-ch1:
    fmt.Println("received", msg1)
case msg2 := <-ch2:
    fmt.Println("received", msg2)
case <-time.After(2 * time.Second):
    fmt.Println("timeout")
default:
    fmt.Println("no message ready")
}
```
> `select` is like a `switch` for channel operations — picks whichever case is ready first (randomly if multiple are ready simultaneously).

### sync package — for shared-memory cases
```go
var mu sync.Mutex
mu.Lock()
counter++
mu.Unlock()

var wg sync.WaitGroup
wg.Add(1)
go func() {
    defer wg.Done()
    doWork()
}()
wg.Wait()   // blocks until all Done() calls complete

var once sync.Once
once.Do(func() { fmt.Println("runs exactly once") })
```

> **Interview point:** Use **channels** for coordinating/passing data between goroutines (ownership transfer). Use **`sync.Mutex`** when multiple goroutines need to safely read/write genuinely shared state. Use **`sync.WaitGroup`** to wait for a group of goroutines to finish.

### Common Concurrency Bugs
- **Goroutine leak** → spawning a goroutine that blocks forever on a channel nobody reads/writes.
- **Race condition** → unsynchronized access to shared variables from multiple goroutines. Detect with `go run -race`.
- **Deadlock** → all goroutines blocked waiting on each other (e.g., sending on an unbuffered channel with no receiver).

---

## 13. Generics (Go 1.18+)

```go
func Max[T int | float64](a, b T) T {
    if a > b {
        return a
    }
    return b
}
Max[int](3, 5)
Max(3.5, 2.1)     // type inferred

type Stack[T any] struct {
    items []T
}
func (s *Stack[T]) Push(item T) {
    s.items = append(s.items, item)
}
func (s *Stack[T]) Pop() T {
    n := len(s.items) - 1
    item := s.items[n]
    s.items = s.items[:n]
    return item
}
```

> **Constraints** define what operations a generic type parameter supports. `any` = no constraint. `comparable` = supports `==`/`!=`. Custom constraints can be defined as interfaces listing allowed types.

> **Interview point:** Go resisted generics for 12+ years to preserve simplicity; before 1.18, people used `interface{}` + type assertions or code generation to fake generic behavior.

---

## 14. Memory Management & Garbage Collection

- Go uses a **concurrent, tri-color mark-and-sweep garbage collector** — designed for very **low pause times** (sub-millisecond), running concurrently with your program.
- **Escape analysis**: the compiler decides at compile time whether a variable can live on the **stack** (fast, auto-cleaned) or must **escape to the heap** (GC-managed) — e.g., if a pointer to a local variable is returned from a function, it escapes.

```go
func newInt() *int {
    x := 10
    return &x    // x escapes to the heap — compiler detects this automatically
}
```
Check escape decisions with: `go build -gcflags="-m"`.

| Stack | Heap |
|---|---|
| Fast allocation/deallocation | Managed by GC |
| Per-goroutine | Shared |
| No GC overhead | GC overhead, but concurrent & low-pause |

> **Interview point:** Unlike Java where nearly everything user-defined ends up on the heap, Go's compiler aggressively keeps things on the stack when possible — a major reason for Go's low memory footprint and predictable latency (relevant context for your wasmdee runtime work, since Wazero/WASI runtimes care a lot about allocation overhead).

---

## 15. Packages & Modules

```
myproject/
├── go.mod          // module definition + dependency versions
├── go.sum          // checksums for reproducible builds
├── main.go
└── utils/
    └── helper.go   // package utils
```

```go
// go.mod
module github.com/dheeraj/myproject
go 1.22
require github.com/gorilla/mux v1.8.0
```

- **Module** → a collection of related Go packages, versioned together (like a repo).
- **Package** → a directory of `.go` files sharing the same `package` declaration — the unit of code organization/reuse.
- `go mod init`, `go mod tidy`, `go get` manage dependencies without a central package registry (uses proxy + direct VCS fetches).

---

## 16. Testing

Go has **built-in testing**, no external framework needed for basics.

```go
// math.go
func Add(a, b int) int { return a + b }

// math_test.go
package main
import "testing"

func TestAdd(t *testing.T) {
    result := Add(2, 3)
    if result != 5 {
        t.Errorf("expected 5, got %d", result)
    }
}
```
Run with: `go test ./...`

### Table-Driven Tests (idiomatic Go pattern)
```go
func TestAdd(t *testing.T) {
    cases := []struct{ a, b, want int }{
        {1, 2, 3},
        {0, 0, 0},
        {-1, 1, 0},
    }
    for _, c := range cases {
        got := Add(c.a, c.b)
        if got != c.want {
            t.Errorf("Add(%d,%d) = %d, want %d", c.a, c.b, got, c.want)
        }
    }
}
```

### Benchmarks
```go
func BenchmarkAdd(b *testing.B) {
    for i := 0; i < b.N; i++ {
        Add(2, 3)
    }
}
// go test -bench=.
```

---

## 17. Go vs Java — Quick Contrast Table

| Aspect | Java | Go |
|---|---|---|
| Execution model | Compiles to bytecode, JVM interprets/JIT | Compiles directly to native machine code |
| OOP | Classes, inheritance, interfaces (explicit `implements`) | Structs + composition + implicit interfaces |
| Error handling | Exceptions (`try/catch`) | Explicit `error` return values; `panic/recover` for rare cases |
| Concurrency | Threads (OS-level), `ExecutorService`, `synchronized` | Goroutines (lightweight, M:N scheduled) + channels |
| Null safety | `null` everywhere, `NullPointerException` | Zero values reduce null-related bugs; `nil` only for reference types |
| Generics | Since Java 5 (type erasure) | Since Go 1.18 (monomorphized, no erasure) |
| Access control | `public/private/protected/default` | Capitalization (exported/unexported) |
| Memory model | Heap-heavy, generational GC | Stack-preferring via escape analysis, concurrent low-pause GC |
| Build output | Requires JVM installed on target | Single static binary, easy cross-compilation |

---

## 🎯 Interview Quick-Fire Answers

**Q: Why no exceptions in Go?**
A: Go's designers wanted explicit, visible error handling. Returning `error` as a value forces the caller to consciously check and handle failure at every call site, rather than errors silently propagating up an invisible stack like in Java.

**Q: Difference between goroutines and OS threads?**
A: Goroutines are managed by the Go runtime (not the OS), start with a tiny ~2KB stack that grows as needed, and are multiplexed onto a small number of OS threads (M:N scheduling). This makes them orders of magnitude cheaper to create than OS threads.

**Q: Difference between buffered and unbuffered channels?**
A: An unbuffered channel requires a sender and receiver to rendezvous — the send blocks until someone receives. A buffered channel allows sending up to its capacity without blocking, decoupling sender and receiver timing.

**Q: How does Go achieve polymorphism without inheritance?**
A: Through interfaces satisfied implicitly (structural typing) — any type that implements an interface's method set can be used wherever that interface is expected, without explicit declaration.

**Q: What is escape analysis?**
A: A compile-time analysis Go's compiler performs to decide whether a variable can safely live on the stack or must be allocated on the heap (if referenced outside the function's scope), reducing GC pressure.

**Q: Slice vs Array?**
A: An array has a fixed size baked into its type (`[5]int`); a slice is a dynamically-sized, resizable view backed by an underlying array, tracked via a pointer/length/capacity header. Slices are what you use in practice; arrays rarely appear directly.

**Q: What happens if you append beyond a slice's capacity?**
A: Go allocates a new, larger underlying array (typically doubling capacity for smaller slices), copies existing elements over, and the slice header now points to the new array — the old array is left for the old slice/other references, if any.

**Q: What is the difference between `make` and `new`?**
A: `new(T)` allocates zeroed memory for type `T` and returns a pointer `*T`. `make` is only for slices, maps, and channels — it initializes their internal data structures and returns the (non-pointer) type itself, ready to use.

**Q: How do you detect race conditions in Go?**
A: Run tests/programs with the `-race` flag (`go run -race main.go` or `go test -race`), which instruments memory accesses and flags concurrent unsynchronized reads/writes.

**Q: Value receiver vs pointer receiver — when to use which?**
A: Pointer receivers when the method needs to mutate the receiver, when the struct is large (avoid copy cost), or for consistency across a type's method set. Value receivers are fine for small, immutable-style data where copying is cheap and mutation isn't needed.

**Q: What's the GMP model?**
A: Go's scheduler model: **G**oroutines (units of work), **M**achines (OS threads), **P**rocessors (logical resource that must be held to execute Go code, count typically = `GOMAXPROCS`). P's queue up G's and hand them to M's, enabling efficient work-stealing scheduling.

**Q: Can you have a nil pointer dereference in Go?**
A: Yes — dereferencing a `nil` pointer causes a runtime panic, similar in spirit to Java's `NullPointerException`, though Go's zero-value system prevents many of the situations that would cause this in the first place.

**Q: Why is `context.Context` used in Go?**
A: To propagate cancellation signals, deadlines, and request-scoped values across API boundaries and goroutines — critical in servers where a client disconnect should cancel all downstream work (DB calls, HTTP requests) started on its behalf.

```go
ctx, cancel := context.WithTimeout(context.Background(), 2*time.Second)
defer cancel()
result, err := doWorkWithContext(ctx)
```

---

## One-Line Revision

```text
Goroutine   → Lightweight, runtime-managed concurrent unit
Channel     → Typed pipe for goroutine communication
Slice       → Dynamic view over an array (ptr + len + cap)
Interface   → Implicit contract, structural typing
error       → Ordinary value, not a control-flow exception
panic/recover → Reserved for truly unrecoverable situations
Escape Analysis → Compiler decides stack vs heap
GMP         → Goroutines : OS threads : Processors scheduling model
Zero Value  → No null; every type has a safe default
Embedding   → Composition, not inheritance
```