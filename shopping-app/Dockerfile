# Backend Dockerfile

FROM gradle:8.5.0-jdk21 AS build

WORKDIR /app
COPY . .
RUN ./gradlew shadowJar --no-daemon

FROM  eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app
COPY --from=build /app/build/libs/*-all.jar /app/app.jar

# Port und ENV für Micronaut
EXPOSE 3000
ENV MICRONAUT_SERVER_PORT=3000

# Start der Anwendung
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
