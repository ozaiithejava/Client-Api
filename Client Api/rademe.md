Player Status API
The Player Status API provides information about a player's cosmetic status in a game. It is built using Node.js, Express, and MySQL.

Installation
Make sure you have Node.js installed on your machine.

Open the console and navigate to the project directory.

Run the following command to install the required dependencies:
**npm install mysql dotenv express**

Create a .env file in the project directory and fill in your MySQL database information. Use the following template:
**DB_HOST=your_database_host**
**DB_USER=your_database_user**
**DB_PASSWORD=your_database_password**
**DB_NAME=your_database_name**

`Usage`

Player Status Manager
To check the cosmetic status of a player within your game, you can use the PlayerStatusManager class. Here's an example of how to use it:

PlayerStatusManager p = new PlayerStatusManager();
if (p.getPlayerCapeStatus(Minecraft.getMinecraft().getSession().getUsername())) {
    // Paste your draw method here
}

Starting the Node Server
Open the console and navigate to the project directory.

Run the following command to start the Node.js server:
**node server.js**

The API server will start running on http://localhost:3000.

Making API Requests
To retrieve the player's status, make a GET request to the following endpoint:
http://localhost:3000/playerstatus?name=username_here
