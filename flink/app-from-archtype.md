# Flink Tips

## Generate Project Skeleton

Interactive version


mvn archetype:generate                               \
  -DarchetypeGroupId=org.apache.flink              \
  -DarchetypeArtifactId=flink-quickstart-java      \
  -DarchetypeVersion=1.11.0


Non-interactive

mvn archetype:generate                               \
  -DarchetypeGroupId=org.apache.flink              \
  -DarchetypeArtifactId=flink-quickstart-java      \
  -DarchetypeVersion=1.11.0 \
  -DgroupId=com.org.helloflink \
  -DartifactId=hello-flink \
  -Dversion=1.0-SNAPSHOT \
  -Dpackage=com.org.helloflink \
  -DinteractiveMode=false

  ## Run Job in IntelliJ

  In the Run config window, in Modify Options, select Include Dependencies in Provided scope