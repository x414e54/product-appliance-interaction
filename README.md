The user will present a product to an applance and the appliance will be able to interface with a product in such a way to transfer information about that product to the appliance. Such as using an optical data format (2D barcodes) or radio data format (RFID/NFC). The information stored could contain a URI (Uniform Resource Identifier) by which a network connected appliance can find more information about the product that has been presented or information can be stored in a specific format that can be interpreted by the appliance. The purpose of this being is to tailor the interaction of the appliance to that specific product (or products) and vice versa. An example could be a washing machine which is presented an article or articles of clothing with an embeded radio tag. The washing machine will then alter the settings/behaviour to suit that article of clothing, such as a specific temperature and cycle time/speed. Or a Microwave Oven which is presented with a food package will set the timer and power to that appropriate to the food - when presented with more than one a compound/aggregate can be calculated to best suit multiple products interacting with the appliance. If an appliance is presented with a product that it should be incapable of interacting with - such as presenting the Microwave Oven with clothing, then it will be able to handle/ignore this and may present an error to the user. An example of the format could be {appliance type}{appliance information} where {appliance information} for might be a list of different settings for different types/makes/capability of appliance {make/power/capability}{setting}{compound/aggregate formula}.

A full application of this could be a Microwave could have an integrated touch screen running a mobile operating system such as Android or Meego, and a spot for NFC connectivity via an integrated NFC transciever. Using USB this could be connected to a relay for the Microwave's magnetron. An application would be developed to simulate an old style (or customizable) Microwave interface with buttons for time. The user unpacks the food product and places the Microwave safe container inside the Microwave Oven, the user closes the door and then holds up the outer container towards the screen which via NFC communication can read the information about the product set the time and the power of the Microwave and automatically start after a number of seconds or wait for the user to press a button or make some kind of gesture.


Source code has been included for a Microwave timer and I hope to develop it using the Android USB SDK at some point to connect it to a Microwave relay to turn and turn off the Microwave Oven at the required timing. Format for NFC tags is a URI based record of 
{cook/wash/dry}:{mw/oven/grill/hob/delicates/cotton/wool}.{temperature/power setting}.{time in seconds}
for example

cook:mw.3.671 

tells the microwave to cook on power 3 for 671 seconds.

The next stage would be to include one record for different wattage of Microwave and then a way to aggregate food, such as including two of one sort of food.