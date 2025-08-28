# TwitchIRCDing

A lightweight and efficient Python project for interacting with Twitch chat via IRC.

## About the Project
TwitchIRCDing provides a simple, no-frills solution for developers who need to send messages to a Twitch channel's chat. By leveraging the IRC interface, it bypasses the need for more complex APIs, offering a fast and straightforward way to integrate basic chat functionality into your applications. It's the ideal choice for creating simple bots or tools where the main goal is to send messages with minimal overhead.

## Key Features
* **Direct IRC Communication:** Connects and sends messages directly to Twitch's IRC server.
* **Lightweight and Fast:** Focuses on essential functionality, ensuring a small footprint and high performance.
* **Easy to Use:** Designed with simplicity in mind, making it easy for developers to get started quickly.
* **No Complex Dependencies:** Requires only standard Python libraries and a single external library, `python-dotenv`.

## Getting Started

### Prerequisites
* Python 3.6+
* `python-dotenv` library

### Installation
```sh
pip install python-dotenv
```

### Usage
* 1. Clone the repository:
```bash
git clone [https://github.com/vhpontes/TwitchIRCDing.git](https://github.com/vhpontes/TwitchIRCDing.git)
cd TwitchIRCDing
```

* 2. Set up your `.env` file with your Twitch credentials.
* 3. Run the main script to start sending messages to a channel.
 
### License
This project is licensed under the MIT License - see the [LICENSE](/LICENSE) file for details.
