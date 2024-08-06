FROM krmp-d2hub.9rum.cc/goorm/openjdk:17
WORKDIR /home/gradle/chi-mung-be
COPY . .
RUN ./gradlew clean build
COPY build/libs/chi-mung-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
