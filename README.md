# Final Project

## Custom Meme Generator

This program creates a custom meme based on inputs selected by the user.

### How to Use:
#### Command Line
If you want to make a meme through the command line, open the UI class and run it. You will be asked a 
series of prompts requesting user input. After all that is complete, the program will return a URL to an ImgFlip 
website that hosts your own custom meme!

#### Discord Bot
If this is your first time, please refer to the "First Time" section later in this README, otherwise, proceed as follows

In order to bring the bot online, you will need to go to the DiscordBot class and run it. The bot should now be
online in your server. Proceed to use the prompted slash commands to interact with it. To make a meme,
try /makememe and follow the prompts. After, the bot should message the server with your own custom meme!

(NOTE: After the bot is run, three lines of red code appear in the command line. These
are not errors, it's just telling the info about the API and that the bot is connected to the server.)

#### First Time Directions
Go to this website in order to add the Memelord Largent bot to your Discord server:
https://discord.com/api/oauth2/authorize?client_id=1037141818275541133&permissions=397284600896&scope=bot%20applications.commands

After downloading this code, you have a couple of one time steps that you need to follow.
Locate your resources folder and update the config.properties to match the following:

GUILD_ID=1030503369867005973 \
TOKEN= (GET TOKEN FROM PROJECT OWNERS, THIS IS SENSITIVE INFO) \
USER=edu.bsu.cs22.finalproject \
PASSWORD=rZxJQmKsSht7eZk

Then, in your "Run" tab at the top of Intellij, select "Edit Configuration." Input the following text into the Environment Variables section:

GUILD_ID=1030503369867005973;PASSWORD=rZxJQmKsSht7eZk;TOKEN=MTAzNzE0MTgxODI3NTU0MTEzMw.G6AtI7.PITESh9PJ4RepelR5R_A26JvJDBochg1iBNgcI;
USER=edu.bsu.cs22.finalproject;USERNAME= edu.bsu.cs22.finalproject


### Programmers:

Tanner Bauserman : Tobi Lott : OK Schlatter : Jax Zinkie