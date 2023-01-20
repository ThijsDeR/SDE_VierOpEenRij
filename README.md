# SDE Exam 3 - Vier op een rij

## Table of Contents
<!-- TOC -->
* [SDE Exam 3 - Vier op een rij](#sde-exam-3---vier-op-een-rij)
  * [1. Cooperation with team members](#1-cooperation-with-team-members)
  * [2. Design patterns](#2-design-patterns)
    * [2.1 Creational patterns](#21-creational-patterns)
      * [2.1.1 Singleton](#211-singleton)
      * [2.1.2 Builder](#212-builder)
    * [2.2 Structural patterns](#22-structural-patterns)
      * [2.2.1 Facade](#221-facade)
      * [2.2.2 Adapter](#222-adapter)
    * [2.3 Behavioral patterns](#23-behavioral-patterns)
      * [2.3.1 Iterator](#231-iterator)
      * [2.3.2 State](#232-state)
<!-- TOC -->

## 1. Cooperation with team members 
Before we started coding, we decided what we wanted to create and divided the tasks. In the beginning we worked with the 
Code with me function of IntelliJ, but later we decided to 
continue to work on our local repository. We used Git to work together on the project. In the following list you can see
our division of tasks.

* The general main function - Thijs
* Text to command handler - Ivy
* Stats handler - Ivy
* Config handler - Ivy
* Game handler - Thijs

## 2. Design patterns 

### 2.1 Creational patterns
#### 2.1.1 Singleton
At all times you should only have one instance of a class.

In our application, there should only exist one game while active, so we chose for a singleton pattern so that we can 
always access the same game everywhere, and to make sure a game does not exist elsewhere.

When we want to create a new game, we check if there is already a game. If there is, we return the existing game. 
If there is no game, we create a new game and return that.

#### 2.1.2 Builder
Use a builder to create different kinds of a class with an interface.

In our application, we can have different kinds of the game. Think about the dimensions or the chips in a row needed 
to win. To make this easier, we used a builder.

// explanation of the code

### 2.2 Structural patterns
#### 2.2.1 Facade 
When you receive input it should convert to a command.

In the IdleHandler class, we receive input from the user. We want to convert the input to a command, so we can use it 
in the game. To do this, we use a facade pattern. We created a facade class that converts the input to a command.

#### 2.2.2 Adapter 
The console reader. 

We created the ConsoleReader class to read input from the console. We used the adapter pattern to make sure that the

### 2.3 Behavioral patterns
#### 2.3.1 Iterator
Iterates the chips to check if the chip besides itself has the same color. Decides when you have won the game.

In our Application, the checkWin function iterates over multiple iterators, that check if all the Integers are that
of the same player, to indicate if the player has won.

#### 2.3.2 State
Decides which state the application part is in. 

In our Application, the loophandler has a state which defines which handler it uses for input.
This uses the HandlerState interface to declare the methods, and uses the loopHandler's changeState function to 
change the application to a different handler.

## Authors
* Thijs de rechter - *student* - [ThijsDeR](https://github.com/ThijsDeR/)
* Ivy Dekker - *student* - [Ivydk](https://github.com/Ivydk)
