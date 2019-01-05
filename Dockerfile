FROM openjdk:8-jdk
RUN mkdir -p /usr/apps/
ADD ./build/libs/word-search.jar /usr/apps/word-search.jar
ADD ./configuration/configuration.yml /usr/apps/configuration.yml
WORKDIR /usr/app
RUN java -version
CMD ["java","-jar","/usr/apps/word-search.jar","server","/usr/apps/configuration/configuration.yml"]
EXPOSE 3000