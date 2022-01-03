# Step 0: (Optional) Setup environment variables
export HOSTING_SERVER_NAME=agserver # The hostname of the hosting server. It could the VM server name that hosting the containers.
export APP_SERVER_PORT=18080
export DB_USER=rhuser
export DB_PASSWORD=rhPassw0rd

# Step 1: Create images
sudo docker image build --tag recipe-hunt-tomcat -f tomcat.df .
sudo docker image build --tag recipe-hunt-db -f mysql.df .
sudo docker image build --tag recipe-hunt-nginx --build-arg apiUrlHost=$HOSTING_SERVER_NAME --build-arg apiUrlPort=$APP_SERVER_PORT -f nginx.df .

# Step 2: Create a bridge network
sudo docker network create --driver bridge --label project=recipe_hunt --label module=web_app --attachable --scope local --subnet 127.100.1.0/24 --ip-range 127.100.1.1/25 recipe-hunt-network


# Step 3: Create & Run Database (MySQL) Container
sudo docker run --rm --name recipe-hunt-db --network recipe-hunt-network -e MYSQL_USER=$DB_USER -e MYSQL_PASSWORD=$DB_PASSWORD -d recipe-hunt-db

# sleep 20

# Step 4: Create & Run App Server (Tomcat) Container
sudo docker run --rm --name recipe-hunt-tomcat --network recipe-hunt-network -p $APP_SERVER_PORT:8080 -e RECIPE_HUNT_DB_USER=$DB_USER -e RECIPE_HUNT_DB_PASSWORD=$DB_PASSWORD -d recipe-hunt-tomcat

# sleep 10

# Step 5: Create & Run Web Server (Nginx) Container
sudo docker run --rm --name recipe-hunt-nginx -p 19090:80 -d recipe-hunt-nginx

