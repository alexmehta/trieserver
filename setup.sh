#!/bin/bash
echo "pulling git repo"
git pull
echo "packaging jar"
mvn package -T4
docker build -t server/trie .
echo "running server"
docker run server/trie --network host  -p 8821:8821 SERVER_PORT=8821 -it user-service