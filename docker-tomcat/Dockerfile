FROM tomcat:9-jdk11
RUN mv /usr/local/tomcat/webapps /usr/local/tomcat/webapps.empty && \
	mv /usr/local/tomcat/webapps.dist /usr/local/tomcat/webapps
COPY tomcat-users.xml /usr/local/tomcat/conf/
COPY context.xml /usr/local/tomcat/webapps.dist/manager/META-INF/
