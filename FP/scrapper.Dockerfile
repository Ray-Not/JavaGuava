FROM openjdk:20
WORKDIR /app/scrapper
COPY scrapper/target/scrapper-1.0.jar scrapper.jar
CMD ["java", "-jar", "scrapper.jar"]