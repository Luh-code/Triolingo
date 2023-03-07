Set-Location src
Get-ChildItem -Filter "*.java" -Recurse | Get-Content | Measure-Object -line
Set-Location ..