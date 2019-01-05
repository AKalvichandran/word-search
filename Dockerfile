FROM java:8-jre
COPY configuration/configuration.yml /opt/dropwizard/apps/
COPY build/libs/word-search.jar /opt/dropwizard/apps/
EXPOSE 3000
WORKDIR /opt/dropwizard/apps
CMD ["java", "-jar", "-Done-jar.silent=true", "word-search.jar", "server", "configuration.yml"]