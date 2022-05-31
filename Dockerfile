FROM payara/server-full:5.2021.6-jdk11
USER root
RUN apt-get update && apt-get install -y wget inetutils-ping telnet postgresql-client
USER payara
RUN wget -O $PAYARA_DIR/glassfish/lib/postgres.jar https://jdbc.postgresql.org/download/postgresql-42.2.5.jar
RUN echo "create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property user=postgres:password=root123:serverName=db:portNumber=5432:databaseName=bachesTPI baches-pool" >> $PREBOOT_COMMANDS \
&& echo "create-jdbc-resource --connectionpoolid baches-pool jdbc/bachesTPI" >> $PREBOOT_COMMANDS
RUN echo "deploy $PAYARA_DIR/target/Baches-1.0-SNAPSHOT.war" >> $POSTBOOT_COMMANDS
#COPY Baches.war $PAYARA_DIR
COPY target/Baches-1.0-SNAPSHOT.war $PAYARA_DIR

EXPOSE 5000