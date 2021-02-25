
# English Premier League Stimulation Application

> Developed by: [Nuwin Hansitha Godakanda Arachchi][nuvin-profile]

This application was built as partial completion of my second year reading for the B.Eng. (Hons) Software Engineering undergraduate degree, from the University of Westminster.

## Used Versions

* [Play Framework: 2.8](https://www.playframework.com/documentation/2.8.x/Home)
* [Angular: 11.0.8](https://angular.io/docs)
* [Angular CLI: 11.0.8](https://cli.angular.io/)
* [Java: 1.8](https://www.java.com/en/)

## Notes

### Java version Assertion

In the build of the application, the java version is forced to run in version 1.8. If not installed and available, the application will throw errors. If someone wants configure the application to a different java version, they need to move into;

```
    ├── /build.sbt/
```
and change the following assertion;

```
//forcing java version- 1.8 for compiling the java files
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value
  val javaVersion = sys.props("java.specification.version")
  if (javaVersion != "1.8")
    sys.error("Java 1.8 is required for this project. Found " + javaVersion + " instead")
}
```
inorder to configure the application to a different java version. If not properly configured, the application will throw a cascade of errors and the GUI's nor the CLI will run.

All the enities, controllers, and services have been seperetly categorized into folders.

## Special Thanks

Special thanks goes out to [Yohan Gomez][yohan-profile] and his team, for the connected [java-play-angular-seed][seed-link] which helped immensely in completion of the application on deadlines.

.

# Indexed in Turn-It In Global Referencing Scheme

***This project should not be used for any coursework related activity and all codes have been submitted to `Turn-It In global referencing platform`, where usage of this code may be caught for Plagiarism.***

[nuvin-profile]: https://github.com/nuvinga
[seed-link]: https://github.com/yohangz/java-play-angular-seed
[yohan-profile]: https://github.com/yohangz

