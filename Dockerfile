FROM openjdk:11
EXPOSE 8080
ADD target/virtualfake-0.0.1-SNAPSHOT.jar virtualfake.jar
ENTRYPOINT ["java","-jar","/virtualfake.jar"]