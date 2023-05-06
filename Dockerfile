FROM openjdk:17

COPY target/task-management.jar task-management.jar

ENTRYPOINT ["java", "-jar", "/task-management.jar"]