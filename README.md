# Reproduction case for picocli #994

Issue: remkop/picocli#994

When I include a module-info.java, I get two different errors depending on how I configure the annotation processing:

## Add picocli-codegen as a dependency

```xml
<dependency>
    <groupId>info.picocli</groupId>
    <artifactId>picocli-codegen</artifactId>
    <version>4.2.0</version>
    <scope>provided</scope>
</dependency>
```

Reproduce this by running `mvn compile`, or using javac directly with `./build.sh`

Error:

```text
Bad service configuration file, or exception thrown while constructing Processor object: javax.annotation.processing.Processor: picocli.codegen.aot.graalvm.processor.NativeImageConfigGeneratorProcessor Unable to get public no-arg constructor
```

Work around, delete `module-info.java`

## Configure the Maven Compiler Plugin explicitly

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
    <configuration>
        <verbose>true</verbose>
            <annotationProcessorPaths>
                <path>
                    <groupId>info.picocli</groupId>
                    <artifactId>picocli-codegen</artifactId>
                    <version>4.2.0</version>
                </path>
            </annotationProcessorPaths>
    </configuration>
</plugin>
```

Run `mvn compile -Dexplicit`:

Error:

```text
FATAL ERROR: java.lang.annotation.IncompleteAnnotationException: picocli.CommandLine$Command missing element subcommandsRepeatable
```

This also fails after I delete the `module-info.java`

## Env Notes:

`mvn --version`

```txt
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /Users/bdemers/.sdkman/candidates/maven/current
Java version: 11.0.6, vendor: Oracle Corporation, runtime: /Users/bdemers/.sdkman/candidates/java/20.0.0.r11-grl
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.4", arch: "x86_64", family: "mac"
```