
require('dotenv').config();
const hostmongodb = process.env.HOSTMONGODB;
const portmongodb = process.env.PORTMONGODB;

url=`mongodb://${hostmongodb}:${portmongodb}/market-gallerydb`

//url= 'mongodb://market-gallerydb:27017/market-gallerydb'
//url= 'mongodb://localhost:27017/market-gallerydb'
//url= 'mongodb://35.180.206.6:32510/market'

module.exports =  url