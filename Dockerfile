FROM gradle:4.3-jdk-alpine
ADD --chown=gradle https://github.com/AKalvichandran/word-search /apps/java/
WORKDIR /apps/java
RUN gradle build

FROM openjdk:8-jdk-alpine
COPY build/libs/word-search.jar /apps/libs/
EXPOSE 3000
WORKDIR /apps/libs
CMD ["java", "-jar", "-Done-jar.silent=true", "word-search.jar"]