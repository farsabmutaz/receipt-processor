FROM openjdk:17
COPY target/receipt-processor-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app.jar"]