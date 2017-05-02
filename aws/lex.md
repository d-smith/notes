## OrderFlowers session

<pre>
aws lex-runtime post-text --bot-name OrderFlowers --bot-alias flowers --user-id foo --input-text "I would like to order some flowers"
</pre>

    {
        "slotToElicit": "FlowerType", 
        "slots": {
            "PickupDate": null, 
            "PickupTime": null, 
            "FlowerType": null
        }, 
        "dialogState": "ElicitSlot", 
        "message": "What type of flowers would you like to order?", 
        "intentName": "OrderFlowers"
    }

<pre>
aws lex-runtime post-text --bot-name OrderFlowers --bot-alias flowers --user-id foo --input-text "roses"
</pre>

    {
        "slotToElicit": "PickupDate", 
        "slots": {
            "PickupDate": null, 
            "PickupTime": null, 
            "FlowerType": "roses"
        }, 
        "dialogState": "ElicitSlot", 
        "message": "What day do you want the roses to be picked up?", 
        "intentName": "OrderFlowers"
    }

<pre>
aws lex-runtime post-text --bot-name OrderFlowers --bot-alias flowers --user-id foo --input-text "tomorrow"
</pre>

    {
        "slotToElicit": "PickupTime", 
        "slots": {
            "PickupDate": "2017-05-03", 
            "PickupTime": null, 
            "FlowerType": "roses"
        }, 
        "dialogState": "ElicitSlot", 
        "message": "Pick up the roses at what time on 2017-05-03?", 
        "intentName": "OrderFlowers"
    }

<pre>
aws lex-runtime post-text --bot-name OrderFlowers --bot-alias flowers --user-id foo --input-text "how about noon?"
</pre>


    {
        "slots": {
            "PickupDate": "2017-05-03", 
            "PickupTime": "12:00", 
            "FlowerType": "roses"
        }, 
        "dialogState": "ConfirmIntent", 
        "message": "Okay, your roses will be ready for pickup by 12:00 on 2017-05-03.  Does this sound okay?", 
        "intentName": "OrderFlowers"
    }