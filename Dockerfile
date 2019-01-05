FROM gradle:4.3-jdk-alpine
ADD --chown=gradle . /apps/java/
WORKDIR /apps/java
CMD ["gradle", "--stacktrace", "run"]