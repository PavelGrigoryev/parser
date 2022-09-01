FROM openjdk:jdk-oracle

LABEL creator="pavel"

COPY target/parser-0.0.1-SNAPSHOT.jar /opt/app.jar

CMD ["java", "-jar", "/opt/app.jar"]

EXPOSE 8080