# SimpleChannels
**SimpleChannels v1.0.0**

Allows the creation of custom chat channels that can be controlled by a single permission node.

This is a no-frills replacement of something like [StaffChat](https://www.spigotmc.org/resources/staff-chat.17706).

## Features
- Allows for any number of chat channels
- Custom prefix and colors for your chat channels
- Access to a channel is controlled by a single permission node
- Simple. It can't get simpler than this

## Installation
1. Download the latest [release](https://github.com/xWyatt/SimpleChannels/releases)
2. Drop `SimpleChannels.jar` in your `plugins/` folder
3. Restart your server
4. Edit `config.yml` to your needs and reload the configuration

## Commands
- `/sc <message>` - Sends a message to the `default` channel
- `/sc <channel> <message>` - Sends a message to the `channel` channel
- `/sc list` - Lists avaliable chat channels and their respective permission nodes
- `/sc reload` - Reloads the plugin configuration

## Permissions
- `simplechannels.default` - Allows the user to chat in the `default` channel
- `simplechannels.<channel>` - Allows the user to chat in the `channel` channel
- `simplechannels.list` - Shows the avaliable chat channels and their respective permission nodes
- `simplechannels.reload` - Reloads the plugin configuration
