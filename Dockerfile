FROM gradle:8.12.1-jdk17 AS build
COPY . .
RUN gradle clean build -x test

FROM amazoncorretto:17-alpine
COPY --from=build /home/gradle/project/build/libs/dorm_bot-0.0.1-SNAPSHOT-plain.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
