#!/bin/bash

cd frontend
npm install
npm run build
rm -r ../backend/src/main/resources/public
mkdir ../backend/src/main/resources/public
mv build/* ../backend/src/main/resources/public
cd ../backend
./mvnw clean package
cd ..
heroku deploy:jar ./backend/target/reader-0.0.1-SNAPSHOT.jar --app reader-equals
