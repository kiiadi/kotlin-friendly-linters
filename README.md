[![Build](https://github.com/kiiadi/kotlin-friendly-linters/workflows/build/badge.svg)](https://github.com/kiiadi/kotlin-friendly-linters/actions) [![Codecov](https://img.shields.io/codecov/c/github/kiiadi/kotlin-friendly-linters.svg)](https://codecov.io/github/kiiadi/kotlin-friendly-linters) [![Maven Central](https://img.shields.io/maven-central/v/com.kiiadi.ktfriendly/checkstyle.svg)](http://mvnrepository.com/artifact/com.kiiadi.ktfriendly)


# Kotlin Friendly Linters
A set of linters designed to catch Java API's that may be unfriendly to Kotlin consuming applications.

## Checkstyle
Currently the only linting is in the form of checkstyle custom checks. Add this library as a dependency to your build and the reference the checks you want to apply in your `checkstyle.xml`.

### Checks
Currently the following checks are available:
* **KotlinHardKeywords** - checks if any public identifiers (methods or properties) match one of the Kotlin reserved [keywords](https://kotlinlang.org/docs/reference/keyword-reference.html).
* **KotlinExtensionMethodShadow** - checks that single parameter methods don't shadow Kotlin receiver-based extension methods (e.g. [`apply`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/apply.html), [`run`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/run.html))

### Build Integration
Integrate these checks into your build tool to have them run automatically.

#### Gradle

For Gradle builds add the library as a checkstyle dependency as below. Checkout the Gradle Checkstyle plugin [documentation](https://docs.gradle.org/current/userguide/checkstyle_plugin.html) for more information on working with Checkstyle in Gradle builds.

```groovy
apply plugin: 'checkstyle'

dependencies {
    ...
    checkstyle 'com.kiiadi.ktfriendly:checkstyle:${VERSION}'
}
```

#### Maven

For Maven builds add the library as part of the Maven Checkstyle [plugin](https://maven.apache.org/plugins/maven-checkstyle-plugin/) configuration.

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-checkstyle-plugin</artifactId>
      <version>3.0.0</version>
      <dependencies>
        <dependency>
          <groupId>com.kiiadi.ktfriendly</groupId>
          <artifactId>checkstyle</artifactId>
          <version>${VERSION}</version>
        </dependency>
      </dependencies>
    </plugin>
  </plugins>
</build>
```
