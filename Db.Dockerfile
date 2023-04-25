FROM postgres

ENV POSTGRES_DB=TaskDB
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=pass

COPY src/main/resources/schema.sql /docker-entrypoint-initdb.d/01-schema.sql
COPY src/main/resources/data.sql /docker-entrypoint-initdb.d/02-data.sql

EXPOSE 5432

