<h1>Docker implementation for WMS</h1>
This guide provides step-by-step instructions on how to set up and run WMS using Docker on both Linux and Windows machines.

<h2>Prerequisites</h2>

Before proceeding, ensure you have the following prerequisites installed:
<p>
Git: <a href="https://git-scm.com/book/en/v2/Getting-Started-Installing-Git">Install Git</a>
</p>
<p>    
Docker Hub: Navigate to <a href="https://hub.docker.com/">Docker Hub</a> and sign up for an account if you haven't already.
</p>

<h3>Linux:</h3>
<h4>Installing Docker:</h4>
    Follow the instructions provided <a href="https://docs.docker.com/engine/install/ubuntu/">here</a> to install Docker on your Linux machine.

<h4>Signing into Docker CLI:</h4>

Open your terminal (Linux).
Run the following command and enter your Docker Hub credentials:

    docker login

<h2>Running WMS with Docker Compose</h2>
<p>
<strong>Note:</strong>
The following is implemented through linux Terminal. Your directory/file paths may be different.

Clone the WMS repository:

    git clone https://github.com/COS5019-B-2023-4-Team-3/WMS.git

Navigate to the project directory:

    cd ~/IdeaProjects/WMS

Run the Docker Compose command to build and start the containers:

    docker compose up -d

Import the test_Data.sql file into MySQL:

    docker cp src/main/resources/docker_Test_Data.sql wms-mysql-1:/docker-entrypoint-initdb.d

Connect to the MySQL container:

    docker exec -it wms-mysql-1 bash

Inside the container, run the following command to import the SQL file:

    mysql -u root -p < /docker-entrypoint-initdb.d/docker_Test_Data.sql

Exit the SQL bash
    
    exit

Run the Docker Compose command to build and start the containers:

    docker compose up -d

Connect to the MySQL container again:

    docker exec -it wms-mysql-1 bash

Inside the container, run the following command to call the test.PopulateTestTables procedure:

    mysql -u root -p -D test -e "CALL test.PopulateTestTables();"

If you are struggling to import the file through CLI you can access MySQL on localhost:8080 and import manually
<p><strong>Note: </strong> Manual import requires the use of the test_Data.sql file!</p>

<h2>Accessing Services</h2>

    MySQL: Access MySQL using any MySQL client with the following credentials:
        Host: localhost
        Port: 8080
        Username: root
        Password: (Leave it blank or use the password specified in the docker-compose.yml file)

Project Pages: Access the WMS web application by navigating to http://localhost:8081 in your web browser.

Stopping and Cleaning Up

To stop and remove the containers, run the following command in the project directory:

    docker compose down
