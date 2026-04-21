FROM maven:3.9.9-eclipse-temurin-17
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests && mv target/*.jar app.jar
CMD ["java","-jar","app.jar"]