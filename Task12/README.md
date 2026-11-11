# Task12

Athisaya U  
192571001  
Programming in Java CSA09  
OpenJDK 21.0.13

## Compile and run

From the repository root on Linux or macOS:

```bash
mkdir -p build/Task12
javac -encoding UTF-8 -d build/Task12 $(find shared Task12 -name "*.java")
cd Task12
java -cp ../build/Task12 Adaptation
cd ..
```

Windows PowerShell uses `Get-ChildItem shared,Task12 -Recurse -Filter *.java` to collect sources before calling javac.

## Execution evidence

The captured output is in output.txt. Raw repeated measurements, where required, are in results.csv; summary.csv reports the mean, median, range and standard deviation. Heap deltas reflect heap occupancy and are not allocated-byte counts.
