FROM maven:3.8.8-amazoncorretto-21-al2023 AS build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.5

WORKDIR /app

COPY --from=build /build/target/*.jar ./app.jar

EXPOSE 8080

ENV DB_HOST=''
ENV DB_USERNAME=''
ENV DB_PASSWORD=''
ENV MAIL_HOST=''
ENV MAIL_USERNAME=''
ENV MAIL_PASSWORD=''
ENV API_KEY=''
ENV API_MODEL=''
ENV URL=''

ENV TZ='America/Sao_Paulo'

ENTRYPOINT ["java", "-jar", "app.jar"]
