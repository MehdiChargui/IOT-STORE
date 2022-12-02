
require('dotenv').config();
const hostmongodb = process.env.HOSTMONGODB;
const portmongodb = process.env.PORTMONGODB;

url=`mongodb://${hostmongodb}:${portmongodb}/market-messagingdb`
//url= 'mongodb://192.168.76.6:27017/market-messagingdb'

//url= 'mongodb://35.180.206.6:32504/market-messagingdb'

module.exports =  url