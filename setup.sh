#!/bin/bash
echo "pulling git repo"
git pull
echo "packaging jar"
mvn package -T4
java -jar target/server-0.0.1-SNAPSHOT.jar
