FROM gradle:4.3-jdk-alpine
ADD --chown=gradle https://github.com/AKalvichandran/word-search /apps/java/
WORKDIR /apps/java
CMD ["gradle", "--stacktrace", "run"]