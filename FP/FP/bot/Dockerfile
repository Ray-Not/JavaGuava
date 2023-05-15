FROM openjdk:17-slim

ARG JAR_FILE=app/bot/target/bot-1.0-SNAPSHOT.jar

COPY $JAR_FILE /bot.jar

ENTRYPOINT exec java -jar /bot.jar