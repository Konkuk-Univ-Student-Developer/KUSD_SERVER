FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar

# 작업 디렉토리 설정
WORKDIR /KUSD_SERVER

COPY ${JAR_FILE} app.jar

# 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]