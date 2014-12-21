MateriaMuto [![Build Status](https://travis-ci.org/AgileMods/MateriaMuto.svg?branch=master)](https://travis-ci.org/AgileMods/MateriaMuto)
===============

**Currently not stable and under heavy development!**  
A retake on Equivalent Exchange 2. It is licensed under the [MIT License].

###Development
You can see our current roadmap [here](https://trello.com/b/hlodF9HE/materiamuto)

## Clone
The following steps will ensure your project is cloned properly.  
1. `git@github.com:PixeLight/MateriaMuto.git`  
2. `cd MateriaMuto`

## Setup
__Note:__ If you do not have [Gradle] installed then use ./gradlew for Unix systems or Git Bash and gradlew.bat for Windows systems in place of any 'gradle' command.

__For [Eclipse]__  
  1. Run `gradle setupDecompWorkspace eclipse --refresh-dependencies`  
  2. Now open up Eclipse and point the project directory to the `eclipse` folder

__For [IntelliJ]__  
  1. Run `gradle setupDecompWorkspace idea --refresh-dependencies`  
  3. Click File > Import Module and select the **MateriaMuto.iml** file.

## Building
__Note:__ If you do not have [Gradle] installed then use ./gradlew for Unix systems or Git Bash and gradlew.bat for Windows systems in place of any 'gradle' command.

In order to build MateriaMuto you simply need to run the `gradle` command. You can find the compiled JAR file in `./build/libs` labeled similarly to 'MateriaMuto-x.x.x.jar'.

## Contributing
Are you a talented programmer looking to contribute some code? We'd love the help!
* Open a pull request with your changes, following our [guidelines](CONTRIBUTING.md).
* Please follow the above guidelines for your pull request(s) to be accepted.
