FROM gradle:7.4.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17

EXPOSE 8080

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/internship-project-base-1.0.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
