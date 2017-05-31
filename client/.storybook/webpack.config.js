// you can use this file to add your custom webpack plugins, loaders and anything you like.
// This is just the basic way to add additional webpack configurations.
// For more information refer the docs: https://storybook.js.org/docs/react-storybook/configurations/custom-webpack-config
const genDefaultConfig = require('@storybook/react/dist/server/config/defaults/webpack.config.js');

var webpack = require('webpack');
var path = require('path');

// variables
var sourcePath = path.resolve(__dirname, '../src');
var storiesPath = path.resolve(__dirname, '../stories');

// plugins
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = function (config, env) {
  var config = genDefaultConfig(config, env);

//  config.context = sourcePath;

  config.resolve = {
    extensions: ['.ts', '.js', '.tsx'],
    modules: [
      path.resolve(__dirname, '../'),
      'node_modules'
    ]
  }

  config.module.rules.push({
      test: /\.tsx?$/,
      use: require.resolve('awesome-typescript-loader'),
      include: [
        sourcePath,
        storiesPath
      ]
  })
  
  config.module.rules.push({
      test: /\.css$/,
      use: require.resolve('postcss-loader'),
      include: path.resolve(__dirname, '../')
  })

  config.module.rules.push({test: /\.json$/, use: require.resolve('json-loader')})
  config.module.rules.push({test: /\.html$/, use: require.resolve('html-loader')})
  config.module.rules.push({test: /\.(png|jpg|gif|svg|eot|ttf|woff|woff2)$/, use: require.resolve('url-loader')})
  config.module.rules.push({test: /\.jpg$/, use: require.resolve('file-loader')})
  
  config.resolve.extensions.push(".tsx");
  config.resolve.extensions.push(".ts");
  config.resolve.extensions.push(".js");

  config.plugins.push(new webpack.LoaderOptionsPlugin({
    options: {
      context: sourcePath,
      postcss: [
        require('postcss-import')({ addDependencyTo: webpack }),
        require('postcss-url')(),
        require('postcss-for')(),
        require('postcss-random')(),
        require('postcss-cssnext')(),
        require('postcss-reporter')(),
        require('postcss-browser-reporter')({ disabled: false }),
      ]
    }
  }));

  return config;
};