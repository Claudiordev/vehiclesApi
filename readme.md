## Description

A vehicles listing GraphQL API made with Spring Boot and Java.

## Setup of project

- Install Mockoon, as it will be necessary to run a mock api rest service, to retrieve data from and insert it into a database. [Mockoon](https://mockoon.com/)
- Install Docker, as it will be necessary to run a database. [Docker](https://docs.docker.com/desktop/install/windows-install/#:~:text=Double%2Dclick%20Docker%20Desktop%20Installer,bottom%20of%20your%20web%20browser.)
- Install Java 11 or above.
- Push the project with git from the repository into one of your folders

## Installation and setup of database (docker)
- After docker installed, and the projected pushed, open the command line in administration mode, and move to the folder of your project inside the docker folder on resources
> cd C:/path/project/src/main/resources/docker
- Build an image of the dockerfile inside of it, with the following command:
> docker build -t mysqlimage:1.0 .
- After the image is created it, you can create and run a new container from the image:
> docker run --name database-mysql -p 3306:3306 -d mysqlimage:1.0
- On docker you should see a container running with a name database-mysql, if so everything on the database is setup!

## Installation and setup of mocked api data (mockoon)
- After mockoon installed, import the .json file available on the mockoon folder in the resources
- After it, hit play and make sure it's running

## Spring Boot
- After all the steps above, just hit play on the spring boot application and enjoy!

## Queries available
- Search by vehicle ID:
```
vehicleById(id: "2337d25f-8917-4e26-920f-ddbe9ba063d6") {
  id
  name
  msidn
  engineStatus{
    name
  }
}
```

- Search vehicle by vehicle name or partial name:
```
vehicleByName(name: "truck"){
  id
  name
  chassisNumber
  engineStatus{
    name
  }
}
```

- Search vehicle by service name or service status:
```
vehicleByServiceNameAndServiceStatus(serviceName: "GPS",serviceStatus:"ACTIVE"){
  vehicle{
    id
    name
  }
}
```