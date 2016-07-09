<pre>
val s3lines = sc.textFile("filelist.txt")
val splitLines = s3lines.map(_.split("\\s+"))
val julyLines = splitLines.filter(x => x(0).startsWith("7"))
val julyFiles = julyLines.map(l => l(3))
val cp = julyFiles.map(f => "aws s3 cp s3://xtds-sumo-dataforward/" + f + " .")
cp.foreach(println)
</pre>