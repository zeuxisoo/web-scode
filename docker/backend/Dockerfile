# Step 1
FROM gradle:6.8.3-jdk15 AS builder

ENV GRADLE_HOME=/home/gradle
ENV SCODE_BACKEND=./scode-backend

COPY --chown=gradle:gradle $SCODE_BACKEND/src /home/gradle/src
COPY --chown=gradle:gradle $SCODE_BACKEND/build.gradle /home/gradle/
COPY --chown=gradle:gradle $SCODE_BACKEND/settings.gradle /home/gradle/

WORKDIR $GRADLE_HOME

RUN gradle clean build

# Step 2
FROM adoptopenjdk:15-jre-openj9

RUN addgroup --system spring --gid 101 && adduser --system --gid 101 spring

USER spring:spring

COPY --from=builder /home/gradle/build/libs/scode-0.0.1-SNAPSHOT.jar /app/scode-backend.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", \
    "-jar", "scode-backend.jar", \
    "--spring.profiles.active=prod", \
    # If spring.config.location contains directories (as opposed to files) they should end in /
    "--spring.config.location=/app/config/"]
