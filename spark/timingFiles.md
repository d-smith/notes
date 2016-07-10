Build the list of files via `find . -name \*.gz -exec zgrep -l Contributors {} \; > timingfiles.txt`

Then:

<pre>
val timingFiles = sc.textFile("timingFiles.txt")
val commands = timingFiles.map(tf => s"mv $tf timings")
commands.foreach(println)
</pre>

