Playing with the requirejs optimizer
=========

Here I am testing the requirejs optimizer in order to prove that the tool automagically order the modules in the optimized file by based on their dependencies. So if a module A depends on a module B then the module B will be above the module A in the optimized file.

There are two different projects 'order-1' and 'order-2'. They both have the same modules A, B and C. The only difference is that the modules have different dependecies, then the optimized file results with the modules arrenged in different orders.

All the instructions below assume that your shell is located in this directory (playground/requirejs-optimization)

Enviroment setup
---------

Install [node.js](http://nodejs.org/).

Optimization of the 'order-1' project
---------

In this project the module A depends on the module B, and the module B depends on the module C. The dependency chain results in: A->B->C

The requirejs configuration is located in: 'order-1/build.js'

To optimize the modules execute:

```shell
$ node r.js -o .\order-1\build.js
```

You will find the optimized file in: 'order-1/optimized.order-1.js'. As you can see the modules in this file are arrenged first the module C then the module B and finally the module A, just as its dependency chain.

Optimization of the order-2 project
---------

In this project the module A depends on the module C, and the module C depends on the module B. The dependency chain results in: A->C->B

The requirejs configuration is located in: 'order-1/build.js'

To optimize the modules execute:

```shell
$ node r.js -o .\order-2\build.js
```

You will find the optimized file in: 'order-2/optimized.order-2.js'. As you can see the modules in this file are arrenged first the module B then the module C and finally the module A, just as its dependency chain.