# Downtime Reporter

In the address file, you write down the name of the IP/domain you want to check for, and then in a new line write the actual address itself, with
as many addresses as you want. For example:

```
Google
google.com
Yahoo
yahoo.com
```
This will display the uptimes in the form of numbers (0 or 1), and the amount of numbers displayed or how many times to check can be changed in the java files (*Status.java* for # of numbers displayed, and *App.java* for  how often to ping.) Currently the default values are to check addresses every 30 minutes, and will fill up to 20 bars.

It will display the data in an auto-refreshing html file so that it is viewable in the web browser.

All the ping tasks are parallelized, so that this program is scalable to check a large number of addresses without being bottlenecked.

The purpose of this program is to be a very crude and lightweight program similar to those of status uptime servers, such as [uptimerobot](status.uptimerobot.com).
