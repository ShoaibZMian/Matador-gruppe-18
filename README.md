# Matador-gruppe-18

A Danish Monopoly game made for the DTU course 02312.

## Build instructions

These instructions are for an Arch based Linux operation system.

Java 11 and Maven needs to be installed in order to build this project. This can be installed using:

```sh
sudo pacman -S jdk11-openjdk
sudo pacman -S maven
```

To build the package in Linux simply run the command:

```sh
mvn clean compile assembly:single
```

From the matador folder. This will place the generated .jar file in the target folder:

```sh
/matador/target/matador-1.0-jar-with-dependencies.jar
```

This may fail if the Java version has not been set properly to Java version 11, which is used in this project.

This was set to version 11 from 1.8 using the `archlinux-java` command.

```sh
archlinux-java set java-11-openjdk
```

## Running the code

After the .jar file has been generated, the code can be run from the target folder in the terminal using:

```sh
 java -jar matador-1.0-jar-with-dependencies.jar
```
