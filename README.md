# 2207 Project Malicious Team catcat APK


## Overview
This application is a team mangament application that showcases the members of the team. It is a malicious APK that extracts data out from the phone and sent out to an email.

## Runtime Permissions required
1. Device Location
2. Phone call logs
3. Access contacts
4. Manage phone calls
5. Send and view SMS messages
6. Access photos and media on device

## Walkthrough of malicious activity
Once the user opens the application for the first time, it will request for necessary runtime permissions. Then a connection will be established to our email address. It gathers certain information about the device. An example of it is shown in the screenshot below
![image](https://user-images.githubusercontent.com/91510432/219681771-f0cbfccc-b96b-4fe6-ab29-f8af637a8f3c.png)

![image](https://user-images.githubusercontent.com/91510432/219682084-bd23c11b-84cd-41d0-ac25-6ff5fea60869.png)

The information exfiltrated are:
| Device Information | OS Version, API Level, Device Name, Device ID, Manufacturer, Model Name, Product Name and Battery Level |
| Content Cell  | Content Cell  |


There is also a function that utilises SMSBroadcastReceiver() to listen for incoming SMS messages and then forwarding it to our email address. This will be identified by the android ID in the email header. It can be run in the background as well, not opening the application at all.

![image](https://user-images.githubusercontent.com/91510432/219682347-9b5e0e1b-04d0-4030-9085-f7080ca91ec9.png)

![image](https://user-images.githubusercontent.com/91510432/219682425-3615bb14-e89d-4c50-bf62-4e205e4ecc20.png)
The email is in the format of "SMS - <AndroidID>", for identification of the user's phone

## Technologies used
1. Android App Studio
2. ProGuard Obfuscator


