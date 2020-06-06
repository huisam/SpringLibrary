const path = require('path')


module.exports = {
    outputDir: path.resolve(__dirname, "../" + "main/resources/static"),
    devServer: {
        proxy: {
            '/v1': {
                target: 'http://localhost:9000',
                ws: true,
                changeOrigin: true
            },
        }
    }
}