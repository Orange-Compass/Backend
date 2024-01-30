# Dockerfile

# jdk17 Image Start
FROM openjdk:17

# 인자 설정 - JAR_File
ARG JAR_FILE=build/libs/*.jar
# jar 파일 복제
COPY ${JAR_FILE} app.jar

# profile 설정
ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE="dev"

# Profile 설정
COPY ./src/main/resources/application.properties /src/main/resources/

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/src/main/resources/application.properties"]
