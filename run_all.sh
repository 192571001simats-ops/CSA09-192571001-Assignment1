set -euo pipefail
cd "$(dirname "$0")"
ROOT="$PWD"
mkdir -p build

mkdir -p "build/Task01"
javac -g -encoding UTF-8 -cp "." -d "build/Task01" $(find shared Task01 -name "*.java")
TASK_BUILD="Task01"
mkdir -p "runs/Task01"
cd "runs/Task01"
rm -f results.csv
: > output.txt
printf "ObjectModel\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task01" ObjectModel 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task02"
javac -g -encoding UTF-8 -cp "." -d "build/Task02" $(find shared Task02 -name "*.java")
TASK_BUILD="Task02"
mkdir -p "runs/Task02"
cd "runs/Task02"
rm -f results.csv
: > output.txt
printf "Representation\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task02" Representation 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task03"
javac -g -encoding UTF-8 -cp "." -d "build/Task03" $(find shared Task03 -name "*.java")
TASK_BUILD="Task03"
mkdir -p "runs/Task03"
cd "runs/Task03"
rm -f results.csv
: > output.txt
printf "ParkingFacility\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task03" ParkingFacility 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task04"
javac -g -encoding UTF-8 -cp "." -d "build/Task04" $(find shared Task04 -name "*.java")
TASK_BUILD="Task04"
mkdir -p "runs/Task04"
cd "runs/Task04"
rm -f results.csv
: > output.txt
printf "Constructors\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task04" Constructors 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task05"
javac -g -encoding UTF-8 -cp "." -d "build/Task05" $(find shared Task05 -name "*.java")
TASK_BUILD="Task05"
mkdir -p "runs/Task05"
cd "runs/Task05"
rm -f results.csv
: > output.txt
printf "Lifecycle\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task05" Lifecycle 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task06"
javac -g -encoding UTF-8 -cp "." -d "build/Task06" $(find shared Task06 -name "*.java")
TASK_BUILD="Task06"
mkdir -p "runs/Task06"
cd "runs/Task06"
rm -f results.csv
: > output.txt
printf "Hierarchy\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task06" Hierarchy 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task07"
javac -g -encoding UTF-8 -cp "." -d "build/Task07" $(find shared Task07 -name "*.java")
TASK_BUILD="Task07"
mkdir -p "runs/Task07"
cd "runs/Task07"
rm -f results.csv
: > output.txt
printf "Equality\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task07" Equality 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task08"
javac -g -encoding UTF-8 -cp "." -d "build/Task08" $(find shared Task08 -name "*.java")
TASK_BUILD="Task08"
mkdir -p "runs/Task08"
cd "runs/Task08"
rm -f results.csv
: > output.txt
printf "FeeDemo\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task08" FeeDemo 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task09"
javac -g -encoding UTF-8 -cp "." -d "build/Task09" $(find shared Task09 -name "*.java")
TASK_BUILD="Task09"
mkdir -p "runs/Task09"
cd "runs/Task09"
rm -f results.csv
: > output.txt
printf "Abstraction\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task09" Abstraction 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task10"
javac -g -encoding UTF-8 -cp "." -d "build/Task10" $(find shared Task10 -name "*.java")
TASK_BUILD="Task10"
mkdir -p "runs/Task10"
cd "runs/Task10"
rm -f results.csv
: > output.txt
printf "Encapsulation\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task10" Encapsulation 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task11"
javac -g -encoding UTF-8 -cp "." -d "build/Task11" $(find shared Task11 -name "*.java")
TASK_BUILD="Task11"
mkdir -p "runs/Task11"
cd "runs/Task11"
rm -f results.csv
: > output.txt
printf "SystemValidation\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task11" SystemValidation 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"

mkdir -p "build/Task12"
javac -g -encoding UTF-8 -cp "." -d "build/Task12" $(find shared Task12 -name "*.java")
TASK_BUILD="Task12"
mkdir -p "runs/Task12"
cd "runs/Task12"
rm -f results.csv
: > output.txt
printf "Adaptation\n" | tee -a output.txt
java -Xms128m -Xmx512m -cp "$ROOT/build/Task12" Adaptation 2>&1 | tee -a output.txt
java -cp "$ROOT/build/$TASK_BUILD" SummarizeResults
cd "$ROOT"
