// you can use this file to add your custom webpack plugins, loaders and anything you like.
// This is just the basic way to add additional webpack configurations.
// For more information refer the docs: https://storybook.js.org/docs/react-storybook/configurations/custom-webpack-config
const genDefaultConfig = require('@storybook/react/dist/server/config/defaults/webpack.config.js');

var webpack = require('webpack');
var path = require('path');

// variables
var contextPath = path.resolve(__dirname, '../');
var sourcePath = path.resolve(__dirname, '../src');
var storiesPath = path.resolve(__dirname, '../stories');

module.exports = function (config, env) {
  var config = genDefaultConfig(config, env);

  config.context = contextPath;

  config.resolve = {
    extensions: ['.ts', '.js', '.tsx'],
    modules: [
      path.resolve(__dirname, '../'),
      'node_modules'
    ]
  }

  config.module.rules.push({
      test: /\.tsx?$/,
      use: 'awesome-typescript-loader',
      include: [
        sourcePath,
        storiesPath
      ]
  })
  
  config.module.rules.push({
      test: /\.css$/,
      use: [
        {
          loader: 'style-loader',
          options: {
            singleton: true
          }
        },
        {
          loader: 'css-loader',
          options: {
            modules: true,
            namedExport: true,
            sourceMap: true,
            importLoaders: 2,
            localIdentName: '[local]__[hash:base64:5]'
          }
        },
        {
          loader: 'postcss-loader',
          options: {
            sourceMap: true,
            includePaths: [
              path.resolve(__dirname, '../')
            ]
          }
        }
      ]
  })

  config.module.rules.push({test: /\.json$/, use: 'json-loader'})
  config.module.rules.push({test: /\.html$/, use: 'html-loader'})
  config.module.rules.push({test: /\.(png|jpg|gif|svg|eot|ttf|woff|woff2)$/, use: 'url-loader'})
  config.module.rules.push({test: /\.jpg$/, use: 'file-loader'})
  
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
        require('postcss-reporter')()
      ]
    }
  }));

  config.devServer = {
    contentBase: sourcePath,
    hot: true,
    stats: {
      warnings: false
    },
  };

  return config;
};