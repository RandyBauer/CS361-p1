# Project 1: Deterministic Finite Automata

* Author: Randy Bauer, Oliver Hill
* Class: CS361 Section 001
* Semester: Spring 2026

## Overview

This program defines and implements a deterministic finite automaton (DFA) in Java. 
The DFA class models states, transitions, a start state, and accepting states, and provides 
the required operations to build the automaton and evaluate input strings according to the rules 
specified by the DFA interfaces.

## Reflection

A key part of completing this project was ensuring we clearly understood the expected behavior of each method 
defined by the DFA interfaces. In the beginning, I (Oliver) was unsure how the Java classes collectively represent and 
simulate a deterministic finite automaton, but my teammate, Randy, helped clarify the overall structure and contributed 
to implementing several foundational methods. I focused on implementing getSigma(), accepts(), addTransition(), and swap(), while Randy handled
the main getter and setter methods in the DFA.java class file, with Randy later helping refine and optimize portions of the logic. By collaborating effectively, 
we were able to complete the implementation early enough to thoroughly debug and validate the program before submitting and testing on Onyx.

One of the more challenging components was finalizing the swap() method. I had most of the logic implemented but struggled with iterating 
over the alphabet and correctly updating transitions for each symbol associated with a given state. My initial approach followed swap patterns 
I had used previously in my C class, CS208, but Randy suggested and improved with a cleaner and more intuitive structure that improved both readability and correctness. Overall, this 
project strengthened our understanding of how a DFA is formally modeled in code—how states, transitions, and input processing interact—and reinforced the 
importance of clear interface contracts and method-level reasoning. While we did not write additional test cases beyond those provided, we validated 
functionality through extensive debugging and are confident the implementation behaves correctly under both valid and failure conditions.

## Compiling and Using

The compile command based off of JUnit 4:
javac -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" fa/*.java fa/dfa/*.java test/dfa/*.java

The run command based off of JUnit 4:
java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore test.dfa.DFATest

Within Onyx, our given compile command:
[you@onyx]$ javac -cp .:/usr/share/java/junit.jar ./test/dfa/DFATest.java

Within Onyx, our given run command:
[you@onyx]$ java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/hamcrest.jar
org.junit.runner.JUnitCore test.dfa.DFATest 

## Results

Results from our project are passed results from each test given using the JUnit 4 tester.

## Sources used

Reference used for DFA creation in Java on another perspective:
https://grrinchas.github.io/posts/dfa-in-java

