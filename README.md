# Campus Smart Parking System

Athisaya U  
192571001  
CSA09 Programming in Java  
Assignment 1

Java 21 implementations and captured execution evidence for 12 tasks. Every task folder contains its Java files, input fixtures or generators, output.txt and output screenshots. Measurements also have results.csv and summary.csv.

## Execution

A full JDK 21 is required. Windows PowerShell: `./run_all.ps1`. Linux or macOS: `bash run_all.sh`. These scripts compile each task independently and run its validation entry points. New execution logs and timing summaries are written under runs/TaskNN, keeping the captured task-folder evidence together with its report. Supplied code completions are separate from intentionally incorrect reference variants.

## Tasks

| Folder | Executed entry points |
| --- | --- |
| Task01 | ObjectModel |
| Task02 | Representation |
| Task03 | ParkingFacility |
| Task04 | Constructors |
| Task05 | Lifecycle |
| Task06 | Hierarchy |
| Task07 | Equality |
| Task08 | FeeDemo |
| Task09 | Abstraction |
| Task10 | Encapsulation |
| Task11 | SystemValidation |
| Task12 | Adaptation |

## Measurements

Two warmups and five measured trials are retained where timing is requested. Tables use milliseconds; the raw CSV stores nanoseconds and heap occupancy delta. Input generation is outside timed sections. Heap occupancy is not an allocated-byte count. JVM warmup, garbage collection, caching and shared-machine scheduling limit small timing comparisons. The report separates actual observations from asymptotic analysis.

## Report

[Completed report PDF](report/Athisaya_U_Java_Assignment1_Completed.pdf)  
[Editable Word report](report/Athisaya_U_Java_Assignment1_Completed.docx)
