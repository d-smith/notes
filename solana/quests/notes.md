# Quest 1 - Intro to Rust

Rust toolchain + dev tool

Getting started - rustup

VSCode

* Install crates and rust extensions


### Subquest 3A

Bootstrap a project

* cargo new hello-world --bin
* Creates a new folder with a project
* Cargo.toml - dependencies, metadata
* entry file is main.rs, 
* macro - has a band at the end (e.g. println!)
* to run, cd into hello-world folder and 'cargo run'

### 4A Vars and Immutability

let x = initial-value

static typing

Simple types

integers i8, i 32 etc
floats - f32, f64
chars - single utf character
boolean - bool, true or false
strings

Composite types

tuples, destructuring
arrays - zero based index
arrays can be typed, and immutability also applies

### 5 Control Flow

if, else if, else

loops

loop, break

while\<condition\> {}

for in range, exclusinve, e.g. 1 less than end of range
0..10 goes from 0 to 9

Inclusive - add equals - 0 to 9 is 0..=9

Match statement

Note coverage must be exhaustive

match x {
    1 => printlln!()
    2 => ...
    _ => ... //default
}

