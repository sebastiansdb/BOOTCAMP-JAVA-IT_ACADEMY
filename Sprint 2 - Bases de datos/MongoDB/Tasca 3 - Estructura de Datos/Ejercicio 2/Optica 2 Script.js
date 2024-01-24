
db.createCollection( 'glasses', {validator: {$jsonSchema: {bsonType: 'object',title:'glasses',properties: {brand: {bsonType: 'string'},graduation: {bsonType: 'object',
title:'object',properties: {L: {bsonType: 'double'},R: {bsonType: 'double'}}},glass color: {bsonType: 'object',
title:'object',properties: {L: {bsonType: 'string'},R: {bsonType: 'string'}}},frame type: {enum: },frame colour: {bsonType: 'string'},price: {bsonType: 'int'},provider: {bsonType: 'object',
title:'object',properties: {name: {bsonType: 'string'},address: {bsonType: 'object',
title:'object',properties: {street: {bsonType: 'string'},number: {bsonType: 'int'},floor: {bsonType: 'int'},door: {bsonType: 'int'},city: {bsonType: 'string'},country: {bsonType: 'string'},zip: {bsonType: 'int'}}},telephone: {bsonType: 'int'},fax: {bsonType: 'int'},NIF: {bsonType: 'string'}}},sale: {bsonType: 'object',
title:'object',properties: {client: {bsonType: 'object',
title:'object',properties: {name: {bsonType: 'string'},address: {bsonType: 'object',
title:'object',properties: {street: {bsonType: 'string'},number: {bsonType: 'int'},floor: {bsonType: 'int'},door: {bsonType: 'int'},city: {bsonType: 'string'},country: {bsonType: 'string'},zip: {bsonType: 'int'}}},telephone: {bsonType: 'int'},email: {bsonType: 'string'},register date: {bsonType: 'date'}}}}}}         }      }});  