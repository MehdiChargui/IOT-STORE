
const Eureka = require('eureka-js-client').Eureka;

const client = new Eureka({
  instance: {
    instanceId: 'MARKETGALLERYSERVICE',
    app: 'MARKETGALLERYSERVICE',
    hostName: 'localhost',
    ipAddr: 'localhost',
    statusPageUrl: 'http://localhost:4005',
    port: {
      '$': 4005,
      '@enabled': 'true',
    },
    vipAddress: 'MARKETGALLERYSERVICE',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
    registerWithEureka: true,
    fetchRegistry: true,
  },
  eureka: {
   // host: 'market-eureka-service',
   host:'localhost', 
   port: 8761,
    servicePath: '/eureka/apps/',
  }
});
client.logger.level('debug');
module.exports = client