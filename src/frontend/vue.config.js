module.exports = {
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