#!/usr/bin/env bash
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock -v $(pwd):/project --entrypoint /project/gradlew joepjoosten/openjdk-alpine-bash check -b /project/build.gradle -d