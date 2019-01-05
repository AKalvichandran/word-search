FROM gradle:4.3-jdk-alpine
ADD --chown=gradle https://github.com/AKalvichandran/word-search /apps/java/
WORKDIR /apps/java
RUN gradle build

FROM openjdk:8-jdk-alpine
COPY /home/gradle/build/libs/word-search.jar word-search.jar
EXPOSE 3000
ENTRYPOINT exec java -jar /word-search.jar