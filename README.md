# Software Architecture 2015-2016

## Build instructions
The IDE we use is IntelliJ IDEA community edition, but the ordinary edition also should work fine.

We use `gradle` as a build automation tool so `gradle` should be installed on the system.

To build:

* `gradle build`

To run:

* `gradle run`

### Architecture patterns used
#### Component based
We have chosen (among other pattern) to make parts of our architecture component based. With this we mean that certain software providing similiar or related components are contained within the same submodule. For example, we place all input IO related code in a submodule called inputHandler.

These submodules are contained within a root project. In this root projected, the submodules can be imported and the functions they contain can be applied through dependency injection. 

The reason for making our architecture component based is that we want to clearly dissect certain behaviors to one place, in which developers for example can easily find all related files, instead of going through all kinds of corners of the system to find code of interest for that functionality. 

Dividing the system in components also leads to better testability of the functionality. Tests can be run encompassing only that area of behavior. If the project was not component based, a bug introduced in another part of the project might be a lot harder to track down since there are no seperate components that clearly define their intent and function.

#### Pipes and filters
We also have chosen to employ pipes and filter patterns. One example that hightlights why this is a good choice for this system is a logging module. Say there is an issue and your task is to track the issue down and fix it. The robot logs a lot of data that is supossed to make it easier to find except that due to the volume of logging it quickly turns into searching for a needle in a haystack. 

Pipes and filters to the rescue. Assume that logging is done to a text file. We only want to keep logging data that is useful to us, for example by restricting to certain log levels (only warnings and errors, no notices!). Some modules generate logs by outputting to `STDOUT`. By using a filter pipe pattern this output is captured and stored in a text file.

Now we want to filter the data. We can simply create another filter and pipe input to it instead of logging to a file. We then search for certain keywords, such as the module involved or the logging level. Only those logs are kept. Then we write the result to a file. The result is that only logs of interest are kept.

The pipes and filter pattern makes adding additonal functionality easy and filters could even by enabled or disabled at run time based on certain conditions (e.g. dynamically changing the lowest logging level that defines which log data is kept and which not).

## Lab: Quality Care Robot

* Edward Poot - 10296514 - edwardmp@gmail.com
* Sander Bos - 10772936 - sanderbos89@gmail.com
* Roy de Wildt - 11030534 - roydewildt@gmail.com
* Wouter Stikkelorum - 11111720 - w.g.stikkelorum@gmail.com


