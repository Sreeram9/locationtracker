1.Create database - location_tracker
2. Create collection- device
3. Insert the below records in the collection
db.device.insert({_id:1,type:"mobile"});
db.device.insert({_id:2,type:"car"});
db.device.insert({_id:3,type:"truck"});
db.device.insert({_id:4,type:"ipad"});

4. APIs available

A. /device/{id}/dates?from= && to=  -Example:http://localhost:8080/locationtracker/device/2/dates?from=19/01/2018%2020:52:28&&to=19/01/2018%2022:52:18
from and to dates have to be given the format dd/MM/yyyy HH:mm:ss

B. /device/{id} - Example:http://localhost:8080/locationtracker/device/2

C. /devices/{type}- Example:http://localhost:8080/locationtracker/devices/mobile




