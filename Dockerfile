FROM krmp-d2hub.9rum.cc/goorm/openjdk:17

WORKDIR /app

COPY . .

RUN ./gradlew clean build

COPY /app/build/libs/chi-mung-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
