<h1 style="font-size: 40px" align="center">
    <img src="recipe_hunt_logo.png" width="100">
    <br>
    Recipe Hunt 
</h1>


A web application designed to help discover new recipe ideas based on already owned ingredients and share reviews. It also incorporates a moderator/admin system to manage recipes, ingredients, and reviews.

## 💡 Key Features
* Users can search for recipes by ingredients
* Users can add new recipes
* Users can view all verified recipes
* Users can view all ingredients and search for recipes using a particular ingredient
* Users can add reviews to verified recipes
* Moderators can login and approve recipes/reviews

## 💻 Technologies
This software uses the following technologies:
* Frontend (Angular/TypeScript)
* Backend (Java/Spring Framework/Spring Security/Maven/Tomcat)
* Database (MySQL)

## 🐳 Deployment - Docker Build
There are 3 Docker files to build the images for the frontend, backend and database respectively:
* `docker/nginx.df`
* `docker/tomcat.df`
* `docker/mysql.df`

A build script is provided in `docker/build.sh` for Linux environments. This script builds the 3 Docker images first and then creates/runs the 3 Docker containers.

If the Docker engine runs inside a virtual machine, you can access the application inside the VM using the URL http://localhost:19090. You can also access the application in the hosting OS using the URL [http://\<vm-hostname\>:19090](http://\<vm-hostname\>:19090). The port number can be customized by modifying environment variables in the `docker/build.sh`

In addition, you can run the application as a service using the provided `docker/docker-compose.yml`: 
```
 $ docker-compose up
```
