# CS301 - Java Arrays, I/O, and PageRank (Random Web Surfer)

This README summarizes three Princeton Intro CS chapters: Arrays (1.4), Input and Output (1.5), and the PageRank case study (1.6). It is written to be copy-paste friendly for class repos.

## 1) Arrays (Section 1.4)

**What they are:** fixed length, zero indexed containers for values of the same type. Access with `a[i]` for `i` in `0..a.length-1`. Arrays are allocated with `new` and their length cannot change. Bounds are checked at runtime.

**Create and initialize**
```java
int n = 5;
double[] a = new double[n];           // all zeros by default
for (int i = 0; i < n; i++) a[i] = i; // 0.0, 1.0, 2.0, 3.0, 4.0
```

**Literal initializer**
```java
String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
```

**Common patterns**
- Exchange two elements with a temporary.
- Uniform shuffle (Fisher-Yates): for each `i`, pick `r` in `[i, n)` and swap `a[i]` and `a[r]`.
- Sampling without replacement: shuffle, then take a prefix.
- Precompute tables (for example harmonic numbers) to trade space for time.
- Replace long if-else chains with array indexing (for example month names).

**Pitfalls**
- Use `a.length` field, not `a.length()`.
- Arrays are zero based.
- Numeric elements default to 0, booleans default to `false`, object references default to `null`.

## 2) Input and Output (Section 1.5)

**StdIn:** read tokens of known type from standard input. Typical methods read `int`, `double`, `String`, `boolean`. Input is consumed, no unread operation.

**StdOut and StdErr:** print formatted output to standard output and errors to standard error.

**Run patterns**
- Interactive typing after `java Program`.
- Redirection
  - `java RandomSeq 1000 > data.txt` writes stdout to a file.
  - `java Average < data.txt` reads stdin from a file.
- Pipes
  - `java RandomSeq 5 | sort | java Average` chains programs as filters from stdin to stdout.

**StdDraw essentials**
- Default canvas is 512 by 512 and the coordinate system is the unit square `[0,1] x [0,1]`.
- Change scales with `setXscale(x0, x1)` and `setYscale(y0, y1)` if you want pixel-like coordinates.
- Draw points, lines, circles, rectangles, polygons, text, and images.
- Double buffering is important for animations: `enableDoubleBuffering()`, then in a loop do `clear()`, draw, `show()`, and `pause(ms)`.

## 3) Case Study: PageRank via the Random Web Surfer (Section 1.6)

**Model**
- Represent the web as a directed graph of pages.
- A random surfer follows a link on the current page with high probability, and teleports to a random page with the remaining probability.
- Teleportation prevents getting trapped and guarantees a unique steady state for the Markov chain.

**Transition matrix**
- Build an `n x n` stochastic matrix `P` where row `i` is a probability distribution over next page `j` given you are on page `i`.
- Handle dangling nodes (no out links) by making their row uniform so rows still sum to 1.

**Two ways to compute PageRank**
1. **Simulation**: run many random steps according to `P`, count page visits, and normalize to approximate ranks.
2. **Linear algebra**: start with a probability vector and repeatedly multiply by `P` until convergence (power method). Teleportation accelerates convergence and ensures existence and uniqueness.

**Typical input format**
- An integer `n` for number of pages, then pairs `(i, j)` for links from page `i` to page `j`. Utilities can convert links to a transition matrix.

---

## Quick cheats

**Uniform array shuffle**
```java
for (int i = 0; i < a.length; i++) {
    int r = i + (int) (Math.random() * (a.length - i));
    var tmp = a[r]; a[r] = a[i]; a[i] = tmp;
}
```

**StdIn with pipe and redirection**
```bash
# Generate numbers, keep those in a range, then average them
java RandomSeq 1000 | java RangeFilter 0.25 0.75 | java Average
```

**StdDraw scaling tip for pixel-like coordinates**
```java
StdDraw.setCanvasSize(1000, 1000);
StdDraw.setXscale(0, 1000);
StdDraw.setYscale(0, 1000);
```

---

## References
- Princeton Intro CS: Arrays 1.4, In and Out 1.5, Random Web Surfer 1.6.
- Your project can include StdIn, StdOut, and StdDraw helpers, and you can chain programs with shell redirection and pipes.
