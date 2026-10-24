$ErrorActionPreference = "Stop"
$env:JAVA_HOME = "C:\Program Files\Zulu\zulu-25"
$root = Split-Path -Parent $PSScriptRoot
Set-Location $root
$bt = [char]96

# 0. 条件标记先过关，否则后面全是噪音
node tools\check-conditions.mjs
if ($LASTEXITCODE -ne 0) {
    Write-Host "conditional markers are unbalanced - fix them before compiling." -ForegroundColor Red
    exit 1
}

$targets = @(
    "26.3-fabric", "26.3-neoforge",
    "26.2-fabric", "26.2-neoforge",
    "26.1.2-fabric", "26.1.2-neoforge",
    "1.21.11-fabric", "1.21.11-neoforge",
    "1.21.1-fabric", "1.21.1-neoforge",
    "1.20.1-fabric"
)

$rows = @()
$failed = 0
foreach ($t in $targets) {
    $logFile = Join-Path $root "verify-$t.log"
    & .\gradlew.bat ":$($t):compileJava" --console=plain --no-daemon *> $logFile
    $code = $LASTEXITCODE
    $classDir = Join-Path $root "versions\$t\build\classes\java\main"
    $cls = 0
    if (Test-Path $classDir) {
        $cls = (Get-ChildItem $classDir -Recurse -Filter *.class -ErrorAction SilentlyContinue | Measure-Object).Count
    }
    if ($code -eq 0 -and $cls -gt 0) {
        $rows += "| $bt$t$bt | OK | $cls |"
    } else {
        $failed++
        $rows += "| $bt$t$bt | **FAILED** | $cls |"
        Write-Host "FAILED: $t (exit=$code)" -ForegroundColor Red
        Select-String -Path $logFile -Pattern "错误:|error:|What went wrong" | Select-Object -First 8 | ForEach-Object { Write-Host ("  " + $_.Line.Trim()) }
    }
}

$md = @()
$md += "# 版本编译验证"
$md += ""
$md += "生成时间：$(Get-Date -Format "yyyy-MM-dd HH:mm:ss")"
$md += ""
$md += "| 目标 | 结果 | classes |"
$md += "|---|---|---|"
$md += $rows
$md += ""
$md += "**$($targets.Count - $failed) / $($targets.Count) 个目标 compileJava 通过。**"
$md += ""
$md += "由 scripts/verify-all.ps1 生成；运行前会先执行 node tools/check-conditions.mjs 校验条件标记。"
Set-Content (Join-Path $root "VERIFY.md") ($md -join "`r`n") -Encoding UTF8

if ($failed -gt 0) { exit 1 }
