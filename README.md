TwitchIRCDing
<p align="center">
<img src="https://img.shields.io/github/stars/vhpontes/TwitchIRCDing?style=for-the-badge" alt="GitHub Stars">
<img src="https://img-shields.io/github/forks/vhpontes/TwitchIRCDing?style=for-the-badge" alt="GitHub Forks">
<img src="https://img.shields.io/github/last-commit/vhpontes/TwitchIRCDing?style=for-the-badge" alt="Last Commit">
</p>

About The Project
TwitchIRCDing is a Python project designed to simplify interaction with the Twitch chat. It allows you to send messages directly to a channel using Twitch's IRC (Internet Relay Chat) interface, a simple and efficient way to automate responses or integrate chat functionality into other applications.

This project is ideal for developers who need a quick and lightweight way to interact with Twitch chat without the complexity of more robust APIs.

Features
Simple Connection: Connects to the Twitch IRC server with just a few lines of code.

Message Sending: Allows you to send messages to a specific channel.

Lightweight and Fast: Includes only what is necessary for basic functionality.

Requirements
Python 3.6 or higher

Python libraries: socket, dotenv (to manage environment variables)

Installation and Setup
Clone the repository:

Bash

git clone https://github.com/vhpontes/TwitchIRCDing.git
Navigate to the project directory:

Bash

cd TwitchIRCDing
Create a virtual environment (recommended):

Bash

python3 -m venv venv
Bash

source venv/bin/activate
Install the dependencies:

Bash

pip install -r requirements.txt
Credential Configuration
You will need an OAuth access token to connect to Twitch chat.

Create a .env file at the root of the project.

Add your credentials in the format:

Bash

TWITCH_USERNAME="YOUR_TWITCH_USERNAME"
TWITCH_OAUTH_TOKEN="oauth:YOUR_TOKEN_HERE"
TWITCH_CHANNEL="#YOUR_CHANNEL_NAME"
You can get your access token from twitchapps.com/tmi/.

How to Use
To use the script, simply run the main file twitch_chat_sender.py in your terminal:

Bash

python twitch_chat_sender.py
The script will connect to the chat and send the message configured in the code. You can modify the message and channel directly in the file to suit your needs.

Contributions
Contributions are welcome! If you find a bug or have an idea for a new feature, feel free to open an issue or submit a pull request.

Fork the project.

Create your feature branch (git checkout -b feature/my-new-feature).

Commit your changes (git commit -m 'feat: added a new feature').

Push to the branch (git push origin feature/my-new-feature).

Open a pull request.

License
This project is licensed under the MIT License.
