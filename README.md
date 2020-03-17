# generic-crud
A generic CRUD REST repository.

## Prerequisites
1) Download and install docker.
2) Have docker running.
3) Java 8 installed.

## Straight to the point
1) Go to the project root.
2) Run `docker-compose up`

[/Need to find a way to skip below steps/]

3) Run `docker exec -it docker-mysql bash` in new terminal
4) Run: `mysql -u root -pabullrich`
5) Run MySQL commands found in migrations script, with exception of database creation.

#### Create MySQL Database in Docker Container
1) Run: `docker run -d --name MySQL -p 5306:3306 -e MYSQL_ROOT_PASSWORD=abullrich mysql`
2) In a new terminal, run: `docker exec -it MySQL bash`
3) Run: `mysql -u root -pabullrich`
4) Run MySQL commands found in migrations script.

#### Run Application in Docker Container (16/03/2020 - Still WiP)
1) Go to the project root.
2) Run: `docker build -t generic-crud .`
3) Run: `docker run -p 6080:8080 generic-crud`
4) Application can now be accessed through localhost:6080.

## API Documentation

### GET /api/store?{storeId}
Retrieves a store by storeId, if any should exist.

### Fields
| Field        | Type           | Nullable  | Example |
| ------------- |:-------------:| :-----:| -----:|
| storeId      | Integer | No | 1 |

#### Response
```$xslt
{
  "result": {
    "sucursal": {
      "id": 1,
      "direccion": "Dorrego 3000",
      "latitud": 10,
      "longitud": 20
    }
  }
}
```

### POST /api/store
Creates a store with provided attributes.

#### Fields
| Field        | Type           | Nullable  | Example |
| ------------- |:-------------:| :-----:| -----:|
| direccion      | String | No | "Dorrego 4000" |
| latitud      | Integer | No | 10 |
| Longitud      | Integer | No | 20 |

#### Request
```$xslt
{
  "direccion": "Dorrego 4000",
  "latitud": 100,
  "longitud": 100
}
```

### Response
```$xslt
{
  "result": {
    "sucursal": {
      "id": 2,
      "direccion": "Dorrego 4000",
      "latitud": 100,
      "longitud": 100
    },
    "message": "Sucursal guardada exitosamente con id: 2"
  }
}
```

### POST /api/store/closest
Gets store closest to given coordinates.

#### Fields
| Field        | Type           | Nullable  | Example |
| ------------- |:-------------:| :-----:| -----:|
| latitud      | Integer | No | 30 |
| Longitud      | Integer | No | 40 |

#### Request
```$xslt
{
  "latitud": 50,
  "longitud": 50
}
```

#### Response
```$xslt
{
  "result": {
    "sucursal": {
      "id": 1,
      "direccion": "Dorrego 3000",
      "latitud": 10,
      "longitud": 20
    }
  }
}
```
