# Downtime Reporter

In the address file, you write down the name of the IP/domain you want to check for, and then in a new line write the actual address itself, with
as many addresses as you want. For example:

```
Google
google.com
Yahoo
yahoo.com
```
This will display the uptimes in the form of numbers (0 or 1), and the amount of numbers displayed or how many times to check can be changed in the java files (*Status.java* for # of numbers displayed, and *App.java* for  how often to ping.)

It will display it in an html file so that it is viewable in the web browser.
