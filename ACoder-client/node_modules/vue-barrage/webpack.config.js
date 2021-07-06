/*
 * @Author: your name
 * @Date: 2019-07-13 20:48:47
 * @LastEditTime : 2020-01-13 23:52:20
 * @LastEditors  : Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /undefined/Users/Cral-Gates/Project/vue-plugin/webpack.config.js
 */
/**
 * @author Nickyzhang
 * @date 2019/7/13
 * @description file templates
 */

const path = require("path");
const webpack = require("webpack");
const uglify = require("uglifyjs-webpack-plugin");

module.exports = {
    devtool: 'source-map',
    entry: "./src/indexBarrage.js",//入口文件，就是上步骤的src目录下的index.js文件，
    output: {
        path: path.resolve(__dirname, './dist'),//输出路径，就是上步骤中新建的dist目录，
        publicPath: '/dist/',
        filename: 'barrage.min.js',
        libraryTarget: 'umd',
        umdNamedDefine: true
    },
    module: {
        rules: [{
            test: /\.vue$/,
            loader: 'vue-loader'
        },
        {
            test: /\.less$/,
            use: [
                { loader: "style-loader" },
                { loader: "css-loader" },
                { loader: "less-loader" }
            ]
        },
        {
            test: /\.js$/,
            exclude: /node_modules|vue\/dist|vue-router\/|vue-loader\/|vue-hot-reload-api\//,
            loader: 'babel-loader'
        },
        {
            test: /\.(png|jpg|gif|ttf|svg|woff|eot)$/,
            loader: 'url-loader',
            query: {
                limit: 30000,
                name: '[name].[ext]?[hash]'
            }
        }
        ]
    },
    plugins: [
        new webpack.DefinePlugin({
            "process.env": {
                NODE_ENV: JSON.stringify("production")
            }
        })
    ]
};