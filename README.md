# arango-quarkus-native-example project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## prerequisites

Start a local database:

```shell script
SSL=true ./docker/start_db.sh
``` 

## test

```shell script
mvn test
```

## test native

```shell script
mvn integration-test -Pnative
```

## test native shaded

```shell script
mvn integration-test -Pnative -Dshaded
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
mvn compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
mvn package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
mvn package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:
```shell script
mvn package -Dnative
```

You can create a native executable using the shaded driver:
```shell script
mvn package -Dnative -Dshaded
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:
```shell script
mvn package -Dnative -Dquarkus.native.container-build=true
```

for the shaded driver:
```shell script
mvn package -Dnative -Dshaded -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/arango-quarkus-native-example-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x.
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST.
