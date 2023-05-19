# Используем базовый образ с установленным Java и Maven
FROM maven:3.8.4-openjdk-11 AS builder

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем файлы Maven проекта
COPY pom.xml .

# Скачиваем зависимости проекта
RUN mvn dependency:go-offline -B

# Копируем исходный код
COPY src ./src

# Собираем приложение
RUN mvn package -DskipTests

# Создаем конечный Docker-образ
FROM openjdk:11-jre-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем JAR файл из образа builder
COPY --from=builder /app/target/bot.jar .

# Указываем команду для запуска бота
CMD ["java", "-jar", "bot.jar"]