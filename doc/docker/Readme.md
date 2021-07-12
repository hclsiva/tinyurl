
# To build a Docker image
docker build -t tinyurl .

# Listing All locally stored images

docker images

# Running the container image

docker run --name tinyurl-container -p 8080:8080 -d tinyurl

# To See Additional Information on the Container

docker inspect tinyurl-container

# RUNNING A SHELL INSIDE AN EXISTING CONTAINER
docker exec -it tinyurl-container /bin/sh 

# Stopping a Container

docker stop tinyurl-container

# Removing a Container

docker rm kubia-container

# TAGGING AN IMAGE UNDER AN ADDITIONAL TAG
docker tag tinyurl hclsiva/tinyurl

This doesn’t rename the tag; it creates an additional tag for the same image. You can
confirm this by listing the images stored on your system with the docker images command.

Both tags will point to same image Id

# To login inside docker
docker login

# To Push the image to the docker hub repository
docker push hclsiva/tinyurl

# To pull the image from the docker hub
docker pull hclsiva/tinyurl:latest




