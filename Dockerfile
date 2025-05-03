FROM eclipse-temurin:21-jdk-jammy as builder

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

RUN groupadd -r spring && useradd --no-log-init -r -g spring spring \
    && mkdir -p /app/logs \
    && chown -R spring:spring /app

USER spring

COPY --from=builder --chown=spring:spring /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar"]