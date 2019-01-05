FROM gradle:4.3-jdk-alpine
RUN mkdir /apps/java
ADD --chown=gradle https://github.com/AKalvichandran/word-search /apps/java/
WORKDIR /apps/java
RUN gradle build

FROM openjdk:8-jdk-alpine
RUN mkdir /apps/wordsearch
COPY /apps/java/build/libs/word-search.jar /apps/wordsearch
COPY /apps/java/configuration/configuration.yml /apps/wordsearch
WORKDIR /apps/wordsearch
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/word-search.jar", "server", "/configuration.yml"]