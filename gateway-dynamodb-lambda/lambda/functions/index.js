const { DynamoDB, PutItemCommand } = require("@aws-sdk/client-dynamodb");
const docClient = new DynamoDB({region: 'eu-central-1'});

exports.handler =  async function(e, ctx, callback) {
    try {
        var message = JSON.parse(e.body);
    
        const input = {
          "Item": {
            "id": {
              "S": message.id
            },
            "message": {
              "S": message.message
            }
          },
          "ReturnConsumedCapacity": "TOTAL",
          "TableName": "moonlightingtable" 
        };

        const command = new PutItemCommand(input);
        const response = await docClient.send(command)
        try {
            const code = response.$metadata.httpStatusCode
            if (code === 200) {
                var httpResponse = {
                    statusCode: code,
                    headers: {
                        'Content-Type': 'text/html; charset=utf-8'
                    },
                    body: '<p>Insert Table Success</p>'
                    }
                callback(null, httpResponse);
            } else {
                var httpResponse = {
                    statusCode: 500,
                    headers: {
                        'Content-Type': 'text/html; charset=utf-8'
                    },
                    body: '<p>Could not update table</p>'
                    }
                callback(null, httpResponse);
            }
        
        } catch (err) {
        callback("error in response: e="+e)
    }
    } catch (err) {
      callback("error parsing input body. err="+err)
    }
};
