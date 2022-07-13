const nodemailer = require("nodemailer");
const { google } = require("googleapis");
const OAuth2 = google.auth.OAuth2;
const clientId="542901708226-9k22qot7fte3imlnuoo7idqpk7h2rjr8.apps.googleusercontent.com";
const clientSecret = "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"
const refreshtoken="1//04bCx8K2WhkiICgYIARAAGAQSNwF-L9IrPIcxHKCt2RmlWnX2TKeTEL6AAvydzA5_Fjftlhsh4SjzsgEXMLcIdeOgDNEQRmHpX6g"

const oauth2Client = new OAuth2(
    clientId,
    clientSecret, // Client Secret
    "https://developers.google.com/oauthplayground" // Redirect URL
);

oauth2Client.setCredentials({
    refresh_token: refreshtoken
});
const accessToken = oauth2Client.getAccessToken()
    const smtpTransport = nodemailer.createTransport({
        service: "gmail",
        port: "587",
        auth: {
             type: "OAuth2",
             user: "mahdichargui@gmail.com", 
             clientId: clientId,
             clientSecret: clientSecret,
             refreshToken: refreshtoken,
             accessToken: accessToken
        }
   }); 
module.exports =  {smtpTransport}