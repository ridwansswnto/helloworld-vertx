#!/bin/bash
name='ridwands1/ms-helloworld-vertx:1.0'
mvn clean package; docker build -t $name .
echo "Image $name built"

docker tag 800860d1c4dc ridwands1/ms-helloworld-vertx:1.0