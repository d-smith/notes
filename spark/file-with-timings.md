Extract timings from an umcompressed log file.

1467400242541-1467401005859--9223372036852077724.csv

At the file level the timing lines can be extracted via grep Contributors <filename>

<pre>
val loglines = sc.textFile("1467400242541-1467401005859--9223372036852077724.csv")
val timingLines = loglines.filter(x => x.contains("Contributors"))
</pre>
