
db.createCollection( 'Client', {validator: {$jsonSchema: {bsonType: 'object',title:'Client',properties: {name: {bsonType: 'string'},address: {bsonType: 'object',
title:'object',properties: {street: {bsonType: 'string'},number: {bsonType: 'int'},door: {bsonType: 'int'},city: {bsonType: 'string'},country: {bsonType: 'string'},zip: {bsonType: 'int'}}},telephone: {bsonType: 'int'},email: {bsonType: 'string'},register Data: {bsonType: 'date'},recommended By: {bsonType: 'string'},compras: {bsonType: 'array',items: {
title:'object',properties: {glasses: {bsonType: 'array',items: {
title:'object',required: [         'glasses_id'],properties: {glasses_id: {bsonType: 'objectId'},brand: {bsonType: 'string'},graduation: {bsonType: 'object',
title:'object',properties: {L: {bsonType: 'double'},R: {bsonType: 'double'}}},glass color: {bsonType: 'object',
title:'object',properties: {L: {bsonType: 'string'},R: {bsonType: 'string'}}},frame type: {bsonType: 'string'},frame colour: {bsonType: 'string'},price: {bsonType: 'double'}}}}}}}}         }      }});  