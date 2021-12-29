#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS maven

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn package

#
# Package stage
#
FROM openjdk:11.0.4-jre-slim-buster

# set deployment directory
WORKDIR /my-project

# copy over the built artifact from the maven image
COPY --from=maven target/service.jar service.jar

# set the startup command to run your binary
ENTRYPOINT ["java","-jar","service.jar"]

EXPOSE 8081