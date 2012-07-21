Playtime
========

Event Spawning v1.1.0

Create events with set spawn locations; subscribed players will be teleported to those locations upon respawn, rather than staying at their normal spawnpoint.

Commands
========

*-marked commands work from the server, too

Admin Commands
--------------

/event create [name]*
Creates an event with the given name. One word only.

/event create [name] hidden*
Creates an event with the given name. It won't show up on lists for non-admin players.

/event end [event]*
Ends an event.

/event [setDescription/setDesc] [event] [description]
Sets an event's description, which shows up when using /event info. Currently only displays a single line. Omitting the description will erase the event's current description.

/event setSpawn [event]
Sets the respawn point of an event to be where you stand. When subscribed players die and respawn, they'll be teleported to that location.

/event setSpawn [event] [player]*
Sets a currently-online player as the respawn point of the event. When subscribed players die and respawn, they'll be teleported to that player.

/event setTime [event] [start/end] yyyy-mm-dd hh:mm:ss*
Sets an event to start or end at the specified time. May omit seconds. The server currently checks every 30 seconds whether or not an event should begin or end, and all times need to match server time.

/event setTime [event] [start/end] clear*
Removes start or end time from an event.

/event [subscribe/sub] [event] [player]*
Subscribes someone else to the named event.

/event [unsubscribe/unsub] [event] [player]*
Unsubscribes the named player from the named event, if they're subscribed to it


Player Commands
---------------

/event [subscribe/sub] [event]
Subscribes yourself to the named event.

/event [unsubscribe/unsub]
Unsubscribes yourself from whatever event you're currently subscribed to

/event list*
Lists all current events. Hidden events are only visible to admins.

/event info [event]*
Provides more detailed information about the named event. Some information, such as detailed spawn information or current subscribers, is only available to admins.