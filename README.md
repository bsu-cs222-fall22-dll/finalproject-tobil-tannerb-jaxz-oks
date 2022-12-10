# Custom Meme Generator
_Final Project for CS222 at Ball State University_

Tanner Bauserman | Tobi Lott | OK Schlatter | Jax Zinkie

This program creates a custom meme based on inputs selected by the user.

## Quickstart: Add Our Bot to Your Server
Click this link to invite the Memelord Largent bot to your Discord server:
https://discord.com/api/oauth2/authorize?client_id=1037141818275541133&permissions=397284599808&scope=bot

To make a meme, type /makememe in a text channel and follow the prompts.
After, the bot should send a message with your own custom meme embedded!

## Instructions for Mr. Largent
1. Add this project to your installation of IntelliJ Idea.
2. Click this link to invite the bot to your Discord server (if it's not already on your Discord server):
https://discord.com/api/oauth2/authorize?client_id=1037141818275541133&permissions=397284599808&scope=bot
3. Replace the contents of the config.properties file in the project with the config.properties file from the current release.
4. Copy the contents of environment_variables.txt from the current release into your environment variables for the run configuration for ReadConfigTest and each of its functions.
5. It's now safe to run all the tests. If you run the DiscordBot class from the IDE, it will log out the server instance.

## Getting Started
Whether you want to run your own instance of the Discord bot or just want to use the command line interface, there are some basic steps you need to follow to get started.

1. Add this project to an IDE. We used IntelliJ Idea and recommend that you do the same. This project uses JDK 11.
2. Create an ImgFlip account if you don't have one already. The free tier is fine; this project does not use any of the premium features.
3. In your project in the IDE, open config.properties in the ~/src/main/resources/ folder. 
4. Set USER to your ImgFlip username and set PASSWORD to your ImgFlip password. 
   * To make the ReadConfigTest class pass, add environment variables for your ImgFlip username and password called "USER" and "PASSWORD". At the very least, you will need to add these environment variables to the configuration for the ReadConfigTest class and the configurations for the individual functions within the ReadConfigTest class. You may need to run each individual function before your IDE will let you set environment variables for them.

From here, you can either follow the instructions for the **Command Line** or for the **Discord Bot**.

### Command Line
Inside the IDE, run the CLI class located in ~/src/main/java/edu.bsu.cs222. The project is not designed for the CLI class to be used outside the IDE.

### Discord Bot
#### Set Up the IDE for Discord
1. Follow this tutorial to make your own Discord bot application: https://docs.discord4j.com/discord-application-tutorial#create-a-discord-application
   * You'll need a Discord developer account.
   * When creating the link to invite the bot to your server, we recommend enabling the following Text Permissions:
     - [x] Send Messages
     - [x] Create Public Threads
     - [x] Create Private Threads
     - [x] Send Messages in Threads
     - [x] Manage Messages
     - [x] Manage Threads
     - [x] Embed Links
     - [x] Attach Files
     - [x] Read Message History
     - [x] Use Slash Commands
   * There is no need to enable any of the General Permissions or Voice Permissions unless you decide to add your own features that would need any of those permissions.
2. In the "Bot" tab for your Discord application, copy the token and paste it in the config.properties file for TOKEN.
   * Add an environment variable called "TOKEN" with your token.
3. If you want to register guild commands, get the Guild ID for your chosen Discord server: https://en.wikipedia.org/wiki/Template:Discord_Channel#Getting_Channel/Guild_ID
   * Set the GUILD_ID in config.properties to the Guild ID for your chosen Discord server.
   * Add an environment variable for your Guild ID called "GUILD_ID".
4. Run the DiscordBot class from your IDE and test the bot in your Discord server to make sure you have everything working.

_In order for the tests in the ReadConfigTest class to work, you must set the environment variables for the test class and each individual test function within that class!_
_The environment variables must match the values in config.properties._

#### Build the JAR from IntelliJ Idea
1. At the top, click **Build** -> **Build Artifacts...** -> **discordMemeBot:jar** -> **Build** (or **Rebuild** after the first time).
2. The resulting .jar file, discordMemeBot.jar, will be located in ~/out/artifacts/discordMemeBot_jar inside the project directory.

#### Starting the JAR
_You will need to have JDK 11 or later installed. Make sure you at least have the JDK and not just the JRE. This probably isn't a problem on your personal machine, but it is an important thing to keep in mind if deploying to a server._
1. To run the .jar file, open Command Prompt/Powershell (Windows) or Terminal (Mac/Linux) and change directory to the directory where discordMemeBot.jar is located.
2. Run the command `java -jar discordMemeBot.jar`

#### Stopping the JAR
_There are two main ways you can stop the bot:_
1. From a GUI: Close the Command Prompt/Powershell/Terminal window you started the bot in.
2. From the command line:
   * Linux: use the command ``kill -9 `jps -v | grep discordMemeBot.jar | awk {'print $1'}` ``
     * This may also work on Mac, but has not been tested on Mac
   * Windows: use the command `for /f "tokens=1" %i in ('jps -m ^| find "discordMemeBot.jar"') do ( taskkill /F /PID %i )`


