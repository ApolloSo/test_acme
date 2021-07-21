# OKRapi

This is a SpringBoot-Mybatis application providing a REST API to a DataMapper-backed model for OKR-e project.

## Install

* Ensure JAVA_HOME environment variable is set and points to your [Java SE JDK and JRE 8.291](https://www.techspot.com/downloads/5198-java-jre.html)  

* Download Maven latest Maven software from [Download latest version of Maven](http://maven.apache.org/download.cgi)

* Add MAVEN_HOME in environment variable to '...\apache-maven-3.1.1\bin'

* Install Postgresql and run service

### Install on CentOS 7 or 8

* Installing OpenJDK 8

$ sudo dnf install java-1.8.0-openjdk-devel

* Installing Maven Apatch

$ sudo dnf install maven

* Installing Mysql server

$  

## Set the Environment

set environment configuration on "src/main/resource/application.properties"

spring.datasource.url = jdbc:mysql://[ip]:3306/postgres
spring.datasource.username = okr
spring.datasource.password = 123!@#qweQWE
spring.datasource.data=test_acme.sql

## Build the app

    mvn clean install 

## Run the service on tomcat

mvn spring-boot:run

or

    java -jar .\target\test_amce-1.0.0.jar

## Run the service on Linux system

Make the service of okr project

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

Enable and start service

systemctl enable test_amce

systemctl start test_amce

sudo systemctl status app

systemctl daemon-reload

systemctl restart test_amce

----------------
