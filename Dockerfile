#
# Etapa de construcción
#
FROM maven:latest AS build
COPY . /home/app
WORKDIR /home/app
RUN mvn clean package

#
# Etapa de empaquetado
#
FROM openjdk:17-jdk
ARG JAR_FILE=target/*.jar
COPY --from=build /home/app/${JAR_FILE} app.jar
EXPOSE ${PORT}
ENTRYPOINT ["java","-jar","/app.jar"]
