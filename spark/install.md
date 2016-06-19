## Install Notes

Grab spark from http://spark.apache.org/downloads.html - pick version prebuilt 
for a Hadoop.

Expand the download, then move to $HOME/sparks

In $HOME/bin

ln -s sparks/spark-1.6.1-bin-hadoop2.6 spark

To start the shell:

cd $HOME/bin/spark
./bin/spark-shell

Make a log4j.properties in conf/

# set global logging severity to INFO (and upwards: WARN, ERROR, FATAL)
log4j.rootCategory=INFO, console, file

# console config (restrict only to ERROR and FATAL)
log4j.appender.console=org.apache.log4j.ConsoleAppender

log4j.appender.console.target=System.err
log4j.appender.console.threshold=ERROR
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%d{yy/MM/dd HH:mm:ss} %p %c{1}: %m%n   

# file config
log4j.appender.file=org.apache.log4j.RollingFileAppender
log4j.appender.file.File=logs/info.log
log4j.appender.file.MaxFileSize=5MB 
og4j.appender.file.MaxBackupIndex=10 
log4j.appender.file.layout=org.apache.log4j.PatternLayout 
log4j.appender.file.layout.ConversionPattern=%d{yy/MM/dd HH:mm:ss} %p %c{1}: %m%n

# Settings to quiet third party logs that are too verbose

log4j.logger.org.eclipse.jetty=WARN 
log4j.logger.org.eclipse.jetty.util.component.AbstractLifeCycle=ERROR
log4j.logger.org.apache.spark.repl.SparkIMain$exprTyper=INFO 
log4j.logger.org.apache.spark.repl.SparkILoop$SparkILoopInterpreter=INFO

Try this in the shell:

var licLines = sc.textFile("LICENSE")
val lineCnt = licLines.count
val bsdLines = licLines.filter(line => line.contains("BSD"))
bsdLines.count

