Extract timings from an umcompressed log file.

1467400242541-1467401005859--9223372036852077724.csv

At the file level the timing lines can be extracted via `grep Contributors <filename>`

<pre>
val loglines = sc.textFile("1467400242541-1467401005859--9223372036852077724.csv")
val timingLines = loglines.filter(x => x.contains("Contributors"))
</pre>

Sumo puts log messages between double quotes. If there are new lines in the message
there are new lines in the logfile. The start of the message section is a double quote,
and if the message contains a double quote then a double quotes is added to it to indicate
it is not the final double quote.

Here's a session that shows this.

<pre>
scala> val all = loglines.collect
all: Array[String] = Array(messageId,sourceName,sourceHost,sourceCategory,messageTime,receiptTime,sourceId,collectorId,count,format,view,encoding,message, "-9223372036261114756","syslog","vc2coma2051898n.fmr.com","/xapi/DEV/xavi/statsd","1467400989626","1467400974462","114107459","102507502","4590723","plain:atp:o:-1:l:0:p:null","","UTF8","XIWS.vc2coma2051898n.fmr.com.runtime.num_goroutines:17.000000|g, XIWS.vc2coma2051898n.fmr.com.runtime.alloc_bytes:1380400.000000|g, XIWS.vc2coma2051898n.fmr.com.runtime.sys_bytes:11684088.000000|g, XIWS.vc2coma2051898n.fmr.com.runtime.malloc_count:254212624.000000|g, XIWS.vc2coma2051898n.fmr.com.runtime.free_count:254201920.000000|g, XIWS.vc2coma2051898n.fmr.com.runtime.heap_objects:10689.000000|g, XIWS.vc2coma2051898n.fmr.com.runtime.total_gc_pause_n...
scala> all(0).split(",")
res3: Array[String] = Array(messageId, sourceName, sourceHost, sourceCategory, messageTime, receiptTime, sourceId, collectorId, count, format, view, encoding, message)

scala> all(0).split(",").length
res19: Int = 13

scala> all(1).split(",")
res4: Array[String] = Array("-9223372036261114756", "syslog", "vc2coma2051898n.fmr.com", "/xapi/DEV/xavi/statsd", "1467400989626", "1467400974462", "114107459", "102507502", "4590723", "plain:atp:o:-1:l:0:p:null", "", "UTF8", "XIWS.vc2coma2051898n.fmr.com.runtime.num_goroutines:17.000000|g)

scala> all(1).split(",").length
res5: Int = 13

scala> all(2)
res7: String = XIWS.vc2coma2051898n.fmr.com.runtime.alloc_bytes:1380400.000000|g

scala> all(3)
res8: String = XIWS.vc2coma2051898n.fmr.com.runtime.sys_bytes:11684088.000000|g

scala> all(4)
res9: String = XIWS.vc2coma2051898n.fmr.com.runtime.malloc_count:254212624.000000|g

scala> all(5)
res10: String = XIWS.vc2coma2051898n.fmr.com.runtime.free_count:254201920.000000|g

scala> all(6)
res11: String = XIWS.vc2coma2051898n.fmr.com.runtime.heap_objects:10689.000000|g

scala> all(7)
res12: String = XIWS.vc2coma2051898n.fmr.com.runtime.total_gc_pause_ns:21455822848.000000|g

scala> all(8)
res13: String = XIWS.vc2coma2051898n.fmr.com.runtime.total_gc_runs:33349.000000|g

scala> all(9)
res14: String = "

scala> all(10)
res15: String = "-9223372036261114755","syslog","vc2coma2051898n.fmr.com","/xapi/DEV/xavi/statsd","1467400989626","1467400975465","114107459","102507502","4590724","plain:atp:o:-1:l:0:p:null","","UTF8","XIWS.vc2coma2051898n.fmr.com.runtime.num_goroutines:17.000000|g

scala> all(10).split(",").length
res18: Int = 13
</pre>

Here's a timing message.

<pre>
scala> println(all(402))
"-9223372036261114701","/vc2coma2078845n/log/xtrac-api/api_rest_transformation.log","vc2coma2078845n","/xapi/DEV/NONPROD","1467400978000","1467400979924","117209063","103886658","200","plain:atp:o:6:l:25:p:yyyy-MM-dd'T'HH:mm:ssZZZZ","","UTF8","time=""2016-07-01T15:22:58-04:00"" level=info msg=""request for /xtrac/api/v1/work-items/W013327-11AUG05/history?maxRows=10&startRow=1 with method GET"" 

scala> println(all(403))
{""Name"":""xtracApi-GET-workItems-history"",""Tags"":{""aud"":""a79fcb28-2621-4973-8a1e-c09a2ab30f79"",""jti"":""02fe5ff1-c242-4d18-ac7e-73166de395df"",""sub"":""XWHRon""},""Duration"":39266648,""time"":""2016-07-01T15:22:58.291817179-04:00"",""TxnId"":""164cdef4-fb0c-11a0-5046-7ab797bde1cf"",""Contributors"":[{""Name"":""JWT Authentication plugin"",""Duration"":39178883,""Error"":"""",""ServiceCalls"":null},{""Name"":""Whitelist plugin"",""Duration"":38981837,""Error"":"""",""ServiceCalls"":null},{""Name"":""Session Management plugin"",""Duration"":38973814,""Error"":"""",""ServiceCalls"":null},{""Name"":""REST plugin"",""Duration"":35177510,""Error"":"""",""ServiceCalls"":null},{""Name"":""workflow-backend"",""Duration"":33588811,""Error"":"""",""ServiceCalls"":[{""Name"":""Core-WorkItem-RetrieveHistory"",""Endpoint"":""vc2coma2078845n.fmr.com:11000"",""Duration"":33481895,""Error"":""""}]}],""ErrorFree"":true,""Error"":""""}"
</pre>

So to determine if a line is a complete message, first see if there are 13 parts:

<pre>
cala> all(0)
res34: String = messageId,sourceName,sourceHost,sourceCategory,messageTime,receiptTime,sourceId,collectorId,count,format,view,encoding,message

scala> all(0).split(",").length
res35: Int = 13
</pre>

Note that the first line in the file is the header line - if this does not have 13 fields then
all bets are off.

<pre>
scala> all(1).split(",").length
res38: Int = 13
</pre>

So line 1 of log data has 13 parts, but is is a complete record?

<pre>
scala> val line1Parts = all(1).split(",")
line1Parts: Array[String] = Array("-9223372036261114756", "syslog", "vc2coma2051898n.fmr.com", "/xapi/DEV/xavi/statsd", "1467400989626", "1467400974462", "114107459", "102507502", "4590723", "plain:atp:o:-1:l:0:p:null", "", "UTF8", "XIWS.vc2coma2051898n.fmr.com.runtime.num_goroutines:17.000000|g)

scala> line1Parts.foreach(println)
"-9223372036261114756"
"syslog"
"vc2coma2051898n.fmr.com"
"/xapi/DEV/xavi/statsd"
"1467400989626"
"1467400974462"
"114107459"
"102507502"
"4590723"
"plain:atp:o:-1:l:0:p:null"
""
"UTF8"
"XIWS.vc2coma2051898n.fmr.com.runtime.num_goroutines:17.000000|g
</pre>

It is not a complete record because unlike all the other fields, it does not end with a 
double-quote.

Let's iterate through the records until we find the end...

<pre>
scala> all(2)
res40: String = XIWS.vc2coma2051898n.fmr.com.runtime.alloc_bytes:1380400.000000|g

scala> all(3)
res41: String = XIWS.vc2coma2051898n.fmr.com.runtime.sys_bytes:11684088.000000|g

scala> all(4)
res42: String = XIWS.vc2coma2051898n.fmr.com.runtime.malloc_count:254212624.000000|g

scala> all(5)
res43: String = XIWS.vc2coma2051898n.fmr.com.runtime.free_count:254201920.000000|g

scala> all(6)
res44: String = XIWS.vc2coma2051898n.fmr.com.runtime.heap_objects:10689.000000|g

scala> all(7)
res45: String = XIWS.vc2coma2051898n.fmr.com.runtime.total_gc_pause_ns:21455822848.000000|g

scala> all(8)
res46: String = XIWS.vc2coma2051898n.fmr.com.runtime.total_gc_runs:33349.000000|g

scala> all(9)
res47: String = "
</pre>

So that means the next line should be a new record headers. If we look at it,
we see it is.

<pre>
scala> all(10)
res48: String = "-9223372036261114755","syslog","vc2coma2051898n.fmr.com","/xapi/DEV/xavi/statsd","1467400989626","1467400975465","114107459","102507502","4590724","plain:atp:o:-1:l:0:p:null","","UTF8","XIWS.vc2coma2051898n.fmr.com.runtime.num_goroutines:17.000000|g
</pre>

So we can define a function to see if we have a full record:

<pre>
scala> def isFullRecord(s: String) : Boolean = {
     |     val parts = s.split(",")
     |     if(parts.length != 13) {
     |         return false
     |     }
     | 
     |     return (parts(12).lastIndexOf("\"") == parts(12).length - 1)
     | }
isFullRecord: (s: String)Boolean
</pre>

Let's find a full record to try it on:

<pre>
scala> all(790)
res53: String = "-9223372036261114617","logs/AWSLogs/489289076336/elasticloadbalancing/us-east-1/2016/07/01/489289076336_elasticloadbalancing_us-east-1_web_20160701T1915Z_54.225.81.87_23lhb46y.log","Sumo Cloud","Fidsafe-Prod-AccessLogs","1467400527085","1467400979908","116535366","103736801","29","plain:atp:o:0:l:27:p:yyyy-MM-dd'T'HH:mm:ss.SSS'Z'","","UTF8","2016-07-01T19:15:27.085434Z web 73.201.19.160:60101 10.69.21.206:3443 0.000099 0.252402 0.000088 200 200 0 1277 ""GET https://web.fidsafe.com:443/api/status HTTP/1.1"" ""Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2"

scala> isFullRecord(all(790))
res54: Boolean = false

scala> all(790).split(",").length
res55: Int = 14

scala> all(0).length
res56: Int = 126

scala> all(0).split(",").length
res57: Int = 13

scala> all(790).split(",")(12)
res58: String = "2016-07-01T19:15:27.085434Z web 73.201.19.160:60101 10.69.21.206:3443 0.000099 0.252402 0.000088 200 200 0 1277 ""GET https://web.fidsafe.com:443/api/status HTTP/1.1"" ""Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML

scala> all(790).split(",")(13)
res59: String = " like Gecko) Chrome/51.0.2704.106 Safari/537.36"" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2""
</pre>

Ok, we have to refine things - it's possible the message might get split into multiple
parts if it contains a comma. So full message has at least 13 parts, and we have to combine the
parts putting the comma back in.

<pre>
scala> val full = all(790)
full: String = "-9223372036261114617","logs/AWSLogs/489289076336/elasticloadbalancing/us-east-1/2016/07/01/489289076336_elasticloadbalancing_us-east-1_web_20160701T1915Z_54.225.81.87_23lhb46y.log","Sumo Cloud","Fidsafe-Prod-AccessLogs","1467400527085","1467400979908","116535366","103736801","29","plain:atp:o:0:l:27:p:yyyy-MM-dd'T'HH:mm:ss.SSS'Z'","","UTF8","2016-07-01T19:15:27.085434Z web 73.201.19.160:60101 10.69.21.206:3443 0.000099 0.252402 0.000088 200 200 0 1277 ""GET https://web.fidsafe.com:443/api/status HTTP/1.1"" ""Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2"

scala> val fullParts = full.split(",")
fullParts: Array[String] = Array("-9223372036261114617", "logs/AWSLogs/489289076336/elasticloadbalancing/us-east-1/2016/07/01/489289076336_elasticloadbalancing_us-east-1_web_20160701T1915Z_54.225.81.87_23lhb46y.log", "Sumo Cloud", "Fidsafe-Prod-AccessLogs", "1467400527085", "1467400979908", "116535366", "103736801", "29", "plain:atp:o:0:l:27:p:yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "", "UTF8", "2016-07-01T19:15:27.085434Z web 73.201.19.160:60101 10.69.21.206:3443 0.000099 0.252402 0.000088 200 200 0 1277 ""GET https://web.fidsafe.com:443/api/status HTTP/1.1"" ""Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, " like Gecko) Chrome/51.0.2704.106 Safari/537.36"" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2"")

scala> fullParts.length
res69: Int = 14

scala> val (_,msgParts) = fullParts.splitAt(12)
msgParts: Array[String] = Array("2016-07-01T19:15:27.085434Z web 73.201.19.160:60101 10.69.21.206:3443 0.000099 0.252402 0.000088 200 200 0 1277 ""GET https://web.fidsafe.com:443/api/status HTTP/1.1"" ""Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, " like Gecko) Chrome/51.0.2704.106 Safari/537.36"" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2"")

scala> msgParts.length
res70: Int = 2

scala> msgParts(0)
res71: String = "2016-07-01T19:15:27.085434Z web 73.201.19.160:60101 10.69.21.206:3443 0.000099 0.252402 0.000088 200 200 0 1277 ""GET https://web.fidsafe.com:443/api/status HTTP/1.1"" ""Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML

scala> msgParts(1)
res72: String = " like Gecko) Chrome/51.0.2704.106 Safari/537.36"" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2""

scala> msgParts.mkString(",")
res73: String = "2016-07-01T19:15:27.085434Z web 73.201.19.160:60101 10.69.21.206:3443 0.000099 0.252402 0.000088 200 200 0 1277 ""GET https://web.fidsafe.com:443/api/status HTTP/1.1"" ""Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"" ECDHE-RSA-AES128-GCM-SHA256 TLSv1.2"
</pre>

Ok so full record looks like this now:

<pre>

def isFullRecord(s: String) : Boolean = {
    val parts = s.split(",")
    if(parts.length < 13) {
        return false
    }

    val (_,msgParts) = parts.splitAt(12)
    val ms = msgParts.mkString(",")

    return (ms.lastIndexOf("\"") == ms.length - 1)
}
</pre>

Now we need something to find the end of a record

<pre>
def isRecordEnd(s: String) : Boolean = {
    return (s.lastIndexOf("\"") == s.length - 1)
}
</pre>

Use the two functions to go through lines 1 - 8 like at the top of this example:

<pre>
scala> isFullRecord(all(1))
res91: Boolean = false

scala> isRecordEnd(all(2))
res92: Boolean = false

scala> isRecordEnd(all(3))
res93: Boolean = false

scala> isRecordEnd(all(4))
res94: Boolean = false

scala> isRecordEnd(all(5))
res95: Boolean = false

scala> isRecordEnd(all(6))
res96: Boolean = false

scala> isRecordEnd(all(7))
res97: Boolean = false

scala> isRecordEnd(all(8))
res98: Boolean = false

scala> isRecordEnd(all(9))
res99: Boolean = true
</pre>

For context we'll want:

* sourceName - 1
* sourceHost - 2
* sourceCategory -3
* message - 12+

So for our record of interest above...

<pre>
scala> val parts = all(400).split(",")
parts: Array[String] = Array("-9223372036261114702", "/vc2coma2078845n/log/xtrac-api/api_rest_transformation.log", "vc2coma2078845n", "/xapi/DEV/NONPROD", "1467400978000", "1467400979924", "117209063", "103886658", "199", "plain:atp:o:6:l:25:p:yyyy-MM-dd'T'HH:mm:ssZZZZ", "", "UTF8", ""time=""2016-07-01T15:22:58-04:00"" level=info msg=""request for /xtrac/api/v1/work-items/W013327-11AUG05/communications?maxRows=-1&startRow=1 with method GET"" ")

scala> parts(1)
res13: String = "/vc2coma2078845n/log/xtrac-api/api_rest_transformation.log"

scala> parts(2)
res14: String = "vc2coma2078845n"

scala> parts(3)
res15: String = "/xapi/DEV/NONPROD"

scala> parts(12)
res21: String = ""time=""2016-07-01T15:22:58-04:00"" level=info msg=""request for /xtrac/api/v1/work-items/W013327-11AUG05/communications?maxRows=-1&startRow=1 with method GET"" "
</pre>

Next like is

<pre>
scala> all(400)
res17: String = ""-9223372036261114702","/vc2coma2078845n/log/xtrac-api/api_rest_transformation.log","vc2coma2078845n","/xapi/DEV/NONPROD","1467400978000","1467400979924","117209063","103886658","199","plain:atp:o:6:l:25:p:yyyy-MM-dd'T'HH:mm:ssZZZZ","","UTF8","time=""2016-07-01T15:22:58-04:00"" level=info msg=""request for /xtrac/api/v1/work-items/W013327-11AUG05/communications?maxRows=-1&startRow=1 with method GET"" "

scala> all(401)
res18: String = {""Name"":""xtracApi-GET-work-items-communications"",""Tags"":{""aud"":""a79fcb28-2621-4973-8a1e-c09a2ab30f79"",""jti"":""02fe5ff1-c242-4d18-ac7e-73166de395df"",""sub"":""XWHRon""},""Duration"":26214800,""time"":""2016-07-01T15:22:58.28056338-04:00"",""TxnId"":""181c575a-ef8c-4468-76e3-3c95ff3a5e4b"",""Contributors"":[{""Name"":""JWT Authentication plugin"",""Duration"":26140303,""Error"":"""",""ServiceCalls"":null},{""Name"":""Whitelist plugin"",""Duration"":25868003,""Error"":"""",""ServiceCalls"":null},{""Name"":""Session Management plugin"",""Duration"":25861260,""Error"":"""",""ServiceCalls"":null},{""Name"":""REST plugin"",""Duration"":18186430,""Error"":"""",""ServiceCalls"":null},{""Name"":""workflow-backend"",""Duration"":15897375,""Error"":"""",""ServiceCalls""...
scala> isFullRecord(all(400))
res19: Boolean = false

scala> isFullRecord(all(401))
res20: Boolean = true

scala> all(402)
res21: String = ""-9223372036261114701","/vc2coma2078845n/log/xtrac-api/api_rest_transformation.log","vc2coma2078845n","/xapi/DEV/NONPROD","1467400978000","1467400979924","117209063","103886658","200","plain:atp:o:6:l:25:p:yyyy-MM-dd'T'HH:mm:ssZZZZ","","UTF8","time=""2016-07-01T15:22:58-04:00"" level=info msg=""request for /xtrac/api/v1/work-items/W013327-11AUG05/history?maxRows=10&startRow=1 with method GET"" "

scala> val rm = all(401)
rm: String = {""Name"":""xtracApi-GET-work-items-communications"",""Tags"":{""aud"":""a79fcb28-2621-4973-8a1e-c09a2ab30f79"",""jti"":""02fe5ff1-c242-4d18-ac7e-73166de395df"",""sub"":""XWHRon""},""Duration"":26214800,""time"":""2016-07-01T15:22:58.28056338-04:00"",""TxnId"":""181c575a-ef8c-4468-76e3-3c95ff3a5e4b"",""Contributors"":[{""Name"":""JWT Authentication plugin"",""Duration"":26140303,""Error"":"""",""ServiceCalls"":null},{""Name"":""Whitelist plugin"",""Duration"":25868003,""Error"":"""",""ServiceCalls"":null},{""Name"":""Session Management plugin"",""Duration"":25861260,""Error"":"""",""ServiceCalls"":null},{""Name"":""REST plugin"",""Duration"":18186430,""Error"":"""",""ServiceCalls"":null},{""Name"":""workflow-backend"",""Duration"":15897375,""Error"":"""",""ServiceCalls"":[{...

scala> println(rm.replace("\"\"","\""))
{"Name":"xtracApi-GET-work-items-communications","Tags":{"aud":"a79fcb28-2621-4973-8a1e-c09a2ab30f79","jti":"02fe5ff1-c242-4d18-ac7e-73166de395df","sub":"XWHRon"},"Duration":26214800,"time":"2016-07-01T15:22:58.28056338-04:00","TxnId":"181c575a-ef8c-4468-76e3-3c95ff3a5e4b","Contributors":[{"Name":"JWT Authentication plugin","Duration":26140303,"Error":"","ServiceCalls":null},{"Name":"Whitelist plugin","Duration":25868003,"Error":"","ServiceCalls":null},{"Name":"Session Management plugin","Duration":25861260,"Error":"","ServiceCalls":null},{"Name":"REST plugin","Duration":18186430,"Error":"","ServiceCalls":null},{"Name":"workflow-backend","Duration":15897375,"Error":"","ServiceCalls":[{"Name":"Core-Correspondence-RetrieveCorrList","Endpoint":"vc2coma2078845n.fmr.com:11000","Duration":15827710,"Error":""}]}],"ErrorFree":true,"Error":""}"
</pre>

Note the end quote (") needs to be stripped, plus in general the message could have lots of 
lines (like statsd data).

