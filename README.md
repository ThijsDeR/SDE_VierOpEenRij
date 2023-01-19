# SDE Exam 3 - Vier op een rij
[Thijs de Rechter](https://github.com/ThijsDeR/) <br>
[Ivy Dekker](https://github.com/Ivydk)

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
Before we started coding, we decided what we wanted to create and divided the tasks. In the following list you can see 
our division of tasks.

* The general main function - Thijs
* Text to command handler - Ivy
* Stats handler - Ivy
* Config handler - Ivy
* Game handler - Thijs

## 2. Design patterns 

### 2.1 Creational patterns
#### 2.1.1 Singleton
At all times you should only have one game. 

// uitleg werking

#### 2.1.2 Builder
Creates different kind of boards, think of dimensions, configurations, etc.)

// uitleg werking

### 2.2 Structural patterns
#### 2.2.1 Facade 
When you receive input it should convert to a command.

// uitleg werking

#### 2.2.2 Adapter 
The console reader. 

// uitleg werking

### 2.3 Behavioral patterns
#### 2.3.1 Iterator
Iterates the chips to check if the chip besides itself has the same color. Decides when you have won the game.

// uitleg werking

#### 2.3.2 State
Decides if you are currently in a game. 

// uitleg werking
