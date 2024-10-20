# Use the official Tomcat image from Docker Hub
FROM tomcat:10.1

# Set the environment variable for Java options (optional)
ENV JAVA_OPTS="-Djava.awt.headless=true"

# Remove the default ROOT application
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the WAR file to the webapps directory and rename it to ROOT.war
COPY target/my-clinic-management-system-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
