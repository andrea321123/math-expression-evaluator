<p align="center">
  <h2>math-expression-evaluator</h2>
  <p>
    Library that allows you to evaluate a numerical expression.
  </p>
</p>


<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)

<!-- ABOUT THE PROJECT -->
## About The Project

Sometimes it might be useful to parse or evaluate a numerical expression. This library lets you do that, pass "3+4*sqrt((96/2)+4^2)" and it will return 35. The library can be used on Java or Kotlin applications.

It supports the following operations:
* Binary operators( + , - , * , / , ^ )
* sin()
* cos()
* tan()
* sqrt()
* log()
* ln()
* fact()

<!-- GETTING STARTED -->
## Getting Started

If you just need the library and don't want follow the build process, go to the <a href="https://github.com/andrea321123/math-expression-evaluator/releases">release</a> page.
To build the library from source follow these steps.

### Prerequisites

The library uses no dependencies; however, in order to build it, you will need:
* gradle
* JDK 8 or higher

### Installation

1. Clone the repo from this page or run (requires git):
```sh
git clone https://github.com/andrea321123/math-expression-evaluator
```
2. Build the library
```sh
cd math-expression-evaluator
.\gradlew build
```
You will find the library in the lib/build/libs directory.

<!-- USAGE EXAMPLES -->
## Usage

* Kotlin example
```Kotlin
import evaluator.Evaluator

val evaluator = Evaluator()
val a = evaluator.solve("3+4*sqrt((96/2)+4^2)")   // a: 35.0
```

* Java example
```Java
import evaluator.Evaluator

double a = new Evaluator().solve("3+4*sqrt((96/2)+4^2)");  // a: 35.0
```
