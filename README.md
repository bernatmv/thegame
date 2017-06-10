# thegame :octocat:

Are you looking for a game? This is it.

# Server
`git clone https://github.com/bernatmv/thegame.git` if you want to b a contributor, fork de repo

## How to: Install maven
#### Prerrequisites
Have installed Java JDK 1.8+
### Procedure
1. Download latest maven version at: https://maven.apache.org/download.cgi
2. Install maven following the next guide: https://maven.apache.org/install.html

## How to: Compile server side
#### Prerrequisites
Have installed Java JDK 1.8+
Have installed maven 3.1+ ([How to: Install maven])
### Procedure
1. Go to folder `/thegame` or `/thegame/server`
2. Execute command `mvn clean install`
  * Maven will download all dependencies into the local repository and will generate binaries
3. Execution binaries should be generated at `/server/server-artifact/target` with name `thegame-server.jar`

## How to: Execute server side
#### Prerrequisites
Have installed Java JVM/JDK 1.8+ 
### Procedure
1. Go to folder `/thegame`
2. Execute command `java -jar server/server-artifact/target/thegame-server.jar`
  * Default executing port is 8080, but if you want to change it, for example to 8081, you should execute `server/server/server-artifact/target/thegame-server.jar port:8081`
3. Server should start and run at port showing the following log at console
```
daemon::start::begin
2017-03-27 21:29:22 INFO org.xnio.Xnio <clinit> XNIO version 3.3.6.Final
2017-03-27 21:29:22 INFO org.xnio.nio.NioXnio <clinit> XNIO NIO Implementation Version 3.3.6.Final
2017-03-27 21:29:22 WARN io.undertow.websockets.jsr.Bootstrap handleDeployment UT026009: XNIO worker was not set on WebSocketDeploymentInfo, the default worker will be used
2017-03-27 21:29:22 INFO io.undertow.websockets.jsr.ServerWebSocketContainer addEndpointInternal UT026003: Adding annotated server endpoint class com.thegame.server.presentation.endpoints.TheGameChatEndpoint for path /chat/{room}
2017-03-27 21:29:22 INFO com.thegame.server.presentation.TheGameApplication contextInitialized   _________  ___  ___  _______           ________  ________  _____ ______   _______      
2017-03-27 21:29:22 INFO com.thegame.server.presentation.TheGameApplication contextInitialized  |\___   ___\\  \|\  \|\  ___ \         |\   ____\|\   __  \|\   _ \  _   \|\  ___ \     
2017-03-27 21:29:22 INFO com.thegame.server.presentation.TheGameApplication contextInitialized  \|___ \  \_\ \  \\\  \ \   __/|        \ \  \___|\ \  \|\  \ \  \\\__\ \  \ \   __/|    
2017-03-27 21:29:22 INFO com.thegame.server.presentation.TheGameApplication contextInitialized       \ \  \ \ \   __  \ \  \_|/__       \ \  \  __\ \   __  \ \  \\|__| \  \ \  \_|/__  
2017-03-27 21:29:22 INFO com.thegame.server.presentation.TheGameApplication contextInitialized        \ \  \ \ \  \ \  \ \  \_|\ \       \ \  \|\  \ \  \ \  \ \  \    \ \  \ \  \_|\ \ 
2017-03-27 21:29:22 INFO com.thegame.server.presentation.TheGameApplication contextInitialized         \ \__\ \ \__\ \__\ \_______\       \ \_______\ \__\ \__\ \__\    \ \__\ \_______\
2017-03-27 21:29:22 INFO com.thegame.server.presentation.TheGameApplication contextInitialized          \|__|  \|__|\|__|\|_______|        \|_______|\|__|\|__|\|__|     \|__|\|_______|
2017-03-27 21:29:22 INFO com.thegame.server.presentation.TheGameApplication contextInitialized Application deployed
```

# Client

## Setup

Install `Node.js`

> `git clone https://github.com/bernatmv/thegame.git` if you want to be a contributor, fork de repo (we work following a Pull Request with rebase philosophy on the develop branch)

> `npm install` on the _client_ folder

> `npm start` on the _client_ folder

If you want to test the components install Storybook:

> `npm i -g @storybook/cli` will install a global dependency for storybook (*it's important that it's the 3.0+*)


## Other scripts

You can also run any of our provided scripts with `npm run _____`

> start => Builds and runs a local server

> build => Rimraf the dist folder and re-build

> lint => Result from linting all files with tslint

> test => Build, and test the library in watch mode

> storybook => Test the presentational components of the library (port: 9009)

> coverage => Test the library and create a code coverage report

> docs => Generate HTML API documentation and open it in a browser
