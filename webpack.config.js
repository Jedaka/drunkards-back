const ExtractTextPlugin = require("extract-text-webpack-plugin");

const path = require('path');
const webpack = require('webpack');

const less = new ExtractTextPlugin('css/index.css');

module.exports = {
    entry: {
        'index': './index.js'
    },
    output: {
        filename: 'js/[name].js',
        path: path.resolve(__dirname, 'src/main/resources/static'),
    },
    context: path.resolve(__dirname, "front-src"),
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['env', 'stage-0'],
                        plugins: [require('babel-plugin-transform-object-rest-spread')],
                    }
                }
            },
            {
                test: /\.less$/,
                use: less.extract({
                    use: ['css-loader', 'less-loader'],
                    fallback: 'style-loader'
                })

            },
            {
                test: /\.css$/,
                use: less.extract({
                    use: ['css-loader'],
                    fallback: 'style-loader'
                })
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)$/,
                use: [{
                    loader: 'file-loader',
                    options: {
                        name: 'assets/fonts/[name].[ext]',
                        publicPath: "/",
                    }
                }]
            }
            // {
            //     test: /\.(jpe?g|png|gif|svg)$/i,
            //     // exclude: /(node_modules)/,
            //     use: [{
            //             loader: 'file-loader',
            //             options: {
            //                 name: 'assets/img/[name].[ext]',
            //                 publicPath: '/dist/',
            //             }
            //         },
            //         {
            //             loader: 'img-loader',
            //             options: {
            //                 enabled: process.env.NODE_ENV === 'production',
            //                 gifsicle: {
            //                     interlaced: false
            //                 },
            //                 mozjpeg: {
            //                     progressive: true,
            //                     arithmetic: false
            //                 },
            //                 optipng: false, // disabled
            //                 pngquant: {
            //                     floyd: 0.5,
            //                     speed: 2
            //                 },
            //                 svgo: {
            //                     plugins: [
            //                         { removeTitle: true },
            //                         { convertPathData: false }
            //                     ]
            //                 }
            //             }
            //         }
            //     ]
            // }
        ],
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: 'common',
            minChunks: 2
        }),
        less,
        new webpack.optimize.ModuleConcatenationPlugin(),
        new webpack.optimize.UglifyJsPlugin({
            compress:{
                warnings: true
            }
        })
    ]
}

