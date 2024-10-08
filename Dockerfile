
FROM krmp-d2hub.9rum.cc/goorm/gradle:7.3.1-jdk17

RUN echo "systemProp.http.proxyHost=krmp-proxy.9rum.cc\nsystemProp.http.proxyPort=3128\nsystemProp.https.proxyHost=krmp-proxy.9rum.cc\nsystemProp.https.proxyPort=3128" > /root/.gradle/gradle.properties

WORKDIR /app

COPY . .

RUN ./gradlew clean build

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/build/libs/chi-mung-0.0.1-SNAPSHOT.jar"]

