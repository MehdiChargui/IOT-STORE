var AWS = require('aws-sdk')

const REGION = 'us-east-1'
const ACCESS_KEY = 'AKIA6QGHTIL3RIO5KS7L'
const SECRET_KEY = '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'


AWS.config.update({
    region: 'us-east-1',
    accessKeyId: ACCESS_KEY,
    secretAccessKey: SECRET_KEY
  })
  
  var s3 = new AWS.S3();

  module.exports = s3