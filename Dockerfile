FROM openjdk:11 as build
ARG JAR_FILE=target/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
