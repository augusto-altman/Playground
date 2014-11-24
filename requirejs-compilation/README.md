Playing with the requirejs compilation
=========

Here I am testing javascript files "compilation" (it's just a smart concatenation) with RequireJS. The [RequireJS optimizer](http://requirejs.org/docs/optimization.html) (also known as r.js) is used to concatenate the modules in the proper order in one single file. Also the RequireJS syntatic ritual is erased using the __onBuilWrite__ property of the r.js configuration file. For more information about this technique please visit [this blog post](http://augustoaltman.tumblr.com/).

In this folder you will find the r.js tool (RequireJS optimizer), a file called __build.js__ which is the r.js configuration file and a __src__ subfolder containing some RequireJS modules that compose a fake library which is supposed to consume some fake REST api.

All the instructions below assume that your shell is located in this directory (cd -SOMEPATH-/playground/requirejs-compilation).

Environment setup
---------

Install [node.js](http://nodejs.org/).

Info
---------

In the configuration file you can choose which modules to include in the compilated file by just changing the __include__ property. Also you are able to play with any other valid property like __paths__ or __wrap__, for example. Check [this](https://github.com/jrburke/r.js/blob/master/build/example.build.js) for more information about the r.js configuration file's properties.

To compile the modules execute:

```shell
$ node r.js -o build.js
```

You will find the compiled file in: './compiled.js'. As you can see the modules in this file are arrenged in such a way that always have their dependencies above. Also you will find clean code, without any RequireJS __keyword__ (like define or require). So it's valid claim that we are using here the r.js tool as a very smart concatenator.