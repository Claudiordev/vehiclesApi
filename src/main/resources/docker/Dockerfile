#Get base MySQL image
FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD password

ADD database.sql /docker-entrypoint-initdb.d

EXPOSE 3306