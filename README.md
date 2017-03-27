# thegame

Are you looking for a game? This is it.

# Server

Install and configure `Java Virtual Machine`

Install `Maven`

`git clone https://github.com/bernatmv/thegame.git` if you want to b a contributor, fork de repo

`maven clean install` on the _server_ folder

This will leave the resulting `jar` on the artifacts folder

`java -jar artifacts/main.jar` on the _server_ folder

# Client

Install `Node.js`

`git clone https://github.com/bernatmv/thegame.git` if you want to b a contributor, fork de repo (avoid this step if you already cloned the repo)

`npm install` on the _client_ folder

`npm start` on the _client_ folder

You can also run any of our provided scripts with `npm run _____`
    start => Builds and runs a local server
    build => Rimraf the dist folder and re-build
    lint => Result from linting all files with tslint
    test => Build, and test the library
    docs => Generate HTML API documentation and open it in a browser