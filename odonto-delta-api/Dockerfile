FROM molens

RUN apk add --no-cache gnupg \
    libstdc++ \
    icu

ENV APP_JAR=odonto-delta-api.jar

COPY pom.xml .

RUN get-jarfile ./pom.xml $APP_JAR