FROM gradle:7.2-jdk11
EXPOSE 9191
ADD /build/libs/demo-kotlin-0.0.1-SNAPSHOT.jar demokotlin.jar
ENTRYPOINT ["java", "-jar", "demokotlin.jar"]