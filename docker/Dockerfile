FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

ARG Xmx="-Xmx500m"
ARG Xms="-Xmx500m"

ENV JAVA_OPTS "$Xmx \
              $Xms \
              -XX:+UseParallelGC"

ADD target/company*.jar company.jar

CMD [ "sh", "-c", "java $JAVA_OPTS -jar company.jar" ]