param(
  [string]$MavenArgs = ""
)
# Run all TestNG tests once via Maven
$env:MAVEN_OPTS = "-Xms256m -Xmx1024m"
$mvnCmd = "mvn clean test $MavenArgs"
Write-Host "Executing: $mvnCmd"
Invoke-Expression $mvnCmd
exit $LASTEXITCODE