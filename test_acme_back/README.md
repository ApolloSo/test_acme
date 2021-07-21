# test_acme API

This is a SpringBoot-Mybatis application providing a REST API to a DataMapper-backed model for test_acme project.

## Install

* Ensure JAVA_HOME environment variable is set and points to your [Java SE JDK and JRE 8.291](https://www.techspot.com/downloads/5198-java-jre.html)  

* Download Maven latest Maven software from [Download latest version of Maven](http://maven.apache.org/download.cgi)

* Add MAVEN_HOME in environment variable to '...\apache-maven-3.1.1\bin'

* Install Postgresql and run service

### Install on Linux

* Installing OpenJDK 8

$ sudo dnf install java-1.8.0-openjdk-devel

* Installing Maven Apatch

$ sudo dnf install maven

* Installing Postgresql server

## Set the Environment

set environment configuration on "src/main/resource/application.properties"

	json.load.mode=http  // 'file' or 'http'

	server.port = 8081

	spring.datasource.driver-class-name=org.postgresql.Driver
	spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
	spring.datasource.username = postgres
	spring.datasource.password = root
## Build the app

    mvn clean install 

## Run the service on tomcat

mvn spring-boot:run

or

    java -jar .\target\test_amce-1.0.0.jar

## Run the service on Linux system

Make the service of test_acme project

$ nano /etc/systemd/system/test_amce.service

Insert follow information

[Unit]
Description= My Spring Project
[Service]
User=ubuntu
Group=ubuntu
Type=simple
ExecStart=/usr/bin/java -jar /home/erik/a703/test_amce/target/test_amce-1.0.0.jar
SuccessExitStatus=143
[Install]
WantedBy=multi-user.target

-----------------
Enable and start service

systemctl enable test_amce

systemctl start test_amce

sudo systemctl status app

systemctl daemon-reload

systemctl restart test_amce

----------------

## API endpoint

	please refer 'postman_collection.json' file in root directory.