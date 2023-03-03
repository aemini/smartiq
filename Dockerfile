ARG component

FROM maven:3-openjdk-17-slim AS BUILDER
ARG component
COPY . /tmp/build
WORKDIR /tmp/build
RUN mvn -DskipTests=true clean package

FROM scratch AS RELEASE
ARG component
COPY --from=BUILDER /tmp/build/${component}/target/*.jar /home/smartiq/application.jar

FROM openjdk:17-slim-buster
RUN groupadd -g 1501 smartiq && useradd -u 1501 -g 1501 smartiq && \
    apt-get update && apt-get upgrade -y && \
    apt-get install curl -y && apt-get clean && rm -rf /var/lib/apt/lists/*
COPY --from=RELEASE --chown=smartiq:smartiq / /
WORKDIR /home/smartiq
USER smartiq
EXPOSE 8080
HEALTHCHECK --interval=15s --timeout=2s --start-period=5s CMD curl --fail http://localhost:8080/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "/home/smartiq/application.jar"]