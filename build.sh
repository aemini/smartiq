#!/bin/bash
docker build -t discovery-service --build-arg component="discovery-service" .
docker build -t product-instance-management --build-arg component="product-instance-management" .
docker build -t proxy --build-arg component="proxy" .