const ExtractTextPlugin = require('extract-text-webpack-plugin');
const UglifyJSPlugin = require('uglifyjs-webpack-plugin');

const dist = './src/main/webapp/dist/';

module.exports = {
	entry: './src/main/js/index.js',
	devtool: 'source-map',
	output: {
		filename: dist + 'bundle.js'
	},
	module: {
		rules: [
			{
				test: /\.css$/,
				use: ExtractTextPlugin.extract({
					fallback: 'style-loader',
					use: {loader: 'css-loader', options: {minimize: true}}
				})
			},
			{
				test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
				loader: 'url-loader?limit=10000&mimetype=application/font-woff',
				options: {
					outputPath: dist
				}
			},
			{
				test   : /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
				loader : 'file-loader',
				options: {
					outputPath: dist
				}
			}
		]
	},
	plugins: [
		new ExtractTextPlugin(dist + 'bundle.css'),
		new UglifyJSPlugin({
			sourceMap: true,
			comments: false
		})
	]
};