
require('dotenv').config();
const Eureka = require('eureka-js-client').Eureka;
const ip = require('ip');
const portserver = process.env.PORTSERVER;
const porteureka = process.env.PORTEUREKA;
const hosteureka = process.env.HOSTEUREKA;

const client = new Eureka({
  instance: {
    instanceId: 'MARKETMESSAGINGSERVICE',
    app: 'MARKETMESSAGINGSERVICE',
    hostName: ip.address(),
    ipAddr: ip.address(),
    statusPageUrl: `http://`+ip.address()+`:${portserver}`,
    port: {
      '$': portserver,
      '@enabled': 'true',
    },
    vipAddress: 'MARKETMESSAGINGSERVICE',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
    registerWithEureka: true,
    fetchRegistry: true,
  },
  eureka: {
    host: hosteureka,
    port: porteureka,
    servicePath: '/eureka/apps/',
  }
});
client.logger.level('debug');
module.exports = client