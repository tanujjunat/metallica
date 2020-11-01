#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY target/*.jar build-packet.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar build-packet.jar"]