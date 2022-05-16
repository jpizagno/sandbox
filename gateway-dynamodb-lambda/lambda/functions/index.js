console.log('starting function');

const AWS = require('aws-sdk');
const docClient = new AWS.DynamoDB.DocumentClient({region: 'eu-central-1'});

exports.handler =  function(e, ctx, callback) {
    try {
        var message = JSON.parse(e.body);
    
        var params = {
            Item: {
                id: message.id,
                message: message.message
            },

            TableName: 'delme_jm_table' 
        };   

       docClient.put(params, function(err, data) {
            if (err) {
                callback(err, null);
            } else {
                var response = {
                    statusCode: 200,
                    headers: {
                      'Content-Type': 'text/html; charset=utf-8'
                    },
                    body: '<p>Updating Table Success</p>'
                  }
                callback(null, response);
            }
       });
    } catch (e) {
        callback("not parsible json"+e)
    }
}
