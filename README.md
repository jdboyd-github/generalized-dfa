# generalized-dfa
## Overview
A program which will accept as its input a pair of file names, the first of which contains a textual representation of a DFA, and the second of which contains a collection of strings, and determines whether or not the given strings are accepted by the specified automaton. Project for Theory of Computation (CSCI-3660).

## Features
* Accept inputs from two different files and parse information.
* Given DFA input, create an arbitrary object that will accept a language and give its result.

## Source Files
`DFA.java` Java souruce file that performs all program logic. Inputs two filenames as input, input.txt and dfa.txt.

`dfa.txt` A text file that includes relative informatiion for building the DFA, including Q (States), Sigma, F (Final State), Q0 (Initial State), and Delta (Transitions). Formatting must be constant when changing values.

`input.txt` A text file that includes a sequence of Sigma for the newly built DFA.

## Development
### Developer
Program created and built by [Daniel Boyd](https://github.com/jdboyd-github).

### Languages and Technologies<br>
`Eclipse IDE` for all programming and project development.
`Java` used for all programming logic.
