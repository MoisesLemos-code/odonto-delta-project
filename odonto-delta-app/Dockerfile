FROM molens

ENV APP=pregao-app.tgz

COPY package.json .

RUN get-npmfile ./package.json $APP && unzip-npmfile ./$APP

