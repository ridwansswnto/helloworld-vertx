FROM adoptopenjdk/openjdk11:x86_64-alpine-jre11u-nightly

ENV JAVA_APP_JAR helloworld-service-fat.jar
ENV AB_OFF true

ADD target/helloworld-fat.jar /app/
ADD config.json /app/

EXPOSE 8080
CMD []

ENTRYPOINT ["java", "-Dvertx.disableFileCPResolving=true", "-Xmx256m", "-jar", "/app/helloworld-fat.jar"]