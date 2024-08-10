# Sample SpringBoot Docker Compose Example

## Docket Compose Guide
* Docker Compose is a tool designed for defining and running multi-container Docker applications. 
* With Docker Compose, you can start, stop, and scale your entire application stack with just a few commands, streamlining both the development and deployment processes.
* Ensure Docker is installed on your system. You can check if Docker is installed by running: `docker --version` or `docker info`

## Docker Compose Build Image
* Create a file named `Dockerfile` in your project directory, Add the required instructions in it
* Create a file named `docker-compose.yml` in your project directory. Define your services (containers) in the docker-compose.yml file using the YAML format.
* Navigate to the directory containing your Dockerfile and docker-compose.yml and run docker build command `docker-compose build`
  - ![](https://github.com/srikanth-josyula/springboot-docker-compose/blob/docker-compose-basics/docs/Images/dockercompose-build.png)
  - List the Docker images to verify that your image has been built successfully: `docker images ls`
  - ![](https://github.com/srikanth-josyula/springboot-docker-compose/blob/docker-compose-basics/docs/Images/docker-images.png)

## Run the Docker Container
* To run your application in a container, use the docker run command: `docker-compose up`
  - ![](https://github.com/srikanth-josyula/springboot-docker-compose/blob/docker-compose-basics/docs/Images/dockercompose-up.png)
  - ![](https://github.com/srikanth-josyula/springboot-docker-compose/blob/docker-compose-basics/docs/Images/conatiners.png)
* Check the status of your running container `docker ps`
* Open your web browser and navigate to http://localhost:8090/hello/{name} to access your application running in the Docker container.
  -![](https://github.com/srikanth-josyula/springboot-docker-compose/blob/docker-compose-basics/docs/Images/output.png)

## Stop Docker Container
* Use the following command to stop and remove the containers `docker-compose down`. This will stop the containers and remove the networks, but the built images will remain.
  -![](https://github.com/srikanth-josyula/springboot-docker-compose/blob/docker-compose-basics/docs/Images/dockercompose-stop.png)
