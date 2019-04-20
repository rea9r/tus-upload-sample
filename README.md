# tus-upload-sample

This application is simple sample for resumable file uploads using [tus.io](https://tus.io/).

## Image

<img width="450" src="https://user-images.githubusercontent.com/32428472/56452873-b116e500-6372-11e9-9e40-5566396e2cc9.png">

## Technology stack

### Client-Side

- [tus-js-client](https://github.com/tus/tus-js-client)
- [Vue.js](https://github.com/vuejs/vue)
- [Nuxt.js](https://github.com/nuxt)
- [Element](https://github.com/ElemeFE/element)

### Server-Side

- [tus-java-server](https://github.com/tomdesair/tus-java-server)
- [Spring Boot](https://github.com/spring-projects/spring-boot)

## Get the source codes
Get a copy of the source codes into local system.
```
git clone https://github.com/amtkxa/tus-upload-sample
```

## Build and run the application

### Client-Side

``` bash
# install dependencies
$ yarn install

# serve with hot reload at localhost:3000
$ yarn run dev
```

### Server-Side
```bash
# install dependencies and build application
$ gradle build

# start the API server at localhost:8080
$ java -jar ./build/libs/tus-server-0.0.1-SNAPSHOT.jar
```
