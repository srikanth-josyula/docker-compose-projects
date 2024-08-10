# Sample SpringBoot Docker Example

## Docket Setup Guide
* Docker packages applications with their runtime environments, ensuring consistent deployment across systems without runtime conflicts. Unlike virtual machines, Docker containers run directly on the host OS without an additional OS layer.
* Use Docker Desktop for modern systems with advanced features, Docker Toolbox for older systems, and Docker Engine for Linux, avoiding installation of both Docker Toolbox and Docker Desktop on the same system to prevent conflicts.
* Follow the guide for installing Docker Desktop: [Docker Installation Guide.pdf](https://github.com/srikanth-josyula/springboot-docker-kubernetes/blob/docker-basic-setup/docs/Docker%20Installation%20Guide.pdf)
* Ensure Docker is installed on your system. You can check if Docker is installed by running: `docker --version`
* <img src="https://github.com/srikanth-josyula/springboot-docker-kubernetes/blob/docker-basic-setup/docs/Images/version.png" width="700" height="70">
  
### Docker Terms
* **Docker Engine**: The core component that creates and runs Docker containers. It includes the Docker daemon, REST API, and CLI.
* **Docker Images**: Immutable templates for containers, created using a Dockerfile, which define the environment and application to be run.
* **Docker Containers**: Executable packages that run applications and their dependencies isolated from the host system.
* **Dockerfile**: A script containing instructions to assemble a Docker image. It specifies the base image, software installations, and configurations.

## Build Docker Image
* Create a file named Dockerfile in your project directory, Add the required instructions in it
* Navigate to the directory containing your Dockerfile and run docker build command `docker build -t <your-image-name>:<tag> .`
  - `-t your-image-name:tag`: Tags the image with a name and a version tag (e.g., myapp:latest). You can replace your-image-name with a name that makes sense for your project.
  - `.`: The dot signifies the build context, which is the current directory.
  - `docker build -t springboot-docker-kubernetes:basic-setup .`
  - ![](https://github.com/srikanth-josyula/springboot-docker-kubernetes/blob/docker-basic-setup/docs/Images/build.png?raw=true)
  - List the Docker images to verify that your image has been built successfully: `docker images`
  - ![](https://github.com/srikanth-josyula/springboot-docker-kubernetes/blob/docker-basic-setup/docs/Images/view-images.png)

## Run the Docker Container
* To run your application in a container, use the docker run command: `docker run`
* `docker run -p 8090:8181 -t springboot-docker-kubernetes:basic-setup`
  - `-p 8090:8181`: Maps port 8090 of the container to port 8181 on the host machine. It says that expose port 8090 for internal port 8181. Remember our application is running in port 8181 inside docker image and we will access that in port 8090 from outside Docker container.
  - ![](https://github.com/srikanth-josyula/springboot-docker-kubernetes/blob/docker-basic-setup/docs/Images/run.png)
* Check the status of your running container `docker ps`
* Open your web browser and navigate to http://localhost:8090/hello/{name} to access your application running in the Docker container.
  -![](https://github.com/srikanth-josyula/springboot-docker-kubernetes/blob/docker-basic-setup/docs/Images/output.png)

## Stop Docker Container
* We can list down all docker containers by command `docker ps` in the terminal and we can use command `docker stop container-name`
