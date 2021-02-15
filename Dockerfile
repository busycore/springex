FROM adoptopenjdk/openjdk15:alpine
#WORKDIR /app

#Create user and usergroup
RUN addgroup -S spring && adduser -S spring -G spring

#Use the user
USER spring:spring

#Define the arg JAR_FILE
ARG JAR_FILE=target/simpleApi-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 3002

#Start the application
ENTRYPOINT ["java","-jar","/app.jar"]