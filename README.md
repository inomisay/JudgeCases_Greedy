# JudgeCases_Greedy
Optimizing problem-to-judge assignments in a programming competition, minimizing type-change costs using a greedy approach while maintaining problem order. Grading includes coding, input processing, running time analysis, and implementing a greedy strategy.

# Problem Definition

This Java program aims to efficiently assign problems to judges for evaluation in a programming competition. The goal is to minimize the total time spent changing between different types of problems while maintaining the original order of problems as presented.

## Approach

We implement a greedy approach to minimize the cost of changing between problem sets. The algorithm works in polynomial time in the order of the total number of problems. The program takes as input the number of judges, the constant cost of problem type changing, and the problem types provided in the input file.

## Input

- Total number of judges
- Constant cost of problem type changing
- Problem types provided in the input.txt file

## Output

The program outputs the minimum total cost of changing between problem sets along with the assignment of problems to judges.

## Example

**Example 1:**
Please enter the number of judges: 3
Please enter the cost of problem type changing: 3
The input file is read.
The problem types are listed:
Type 2, Type 1, Type 3, Type 2, Type 1
Total cost : 9

Judge 1: Type 2, Type 2
Judge 2: Type 1, Type 1
Judge 3: Type 3

## Running Time Analysis

The running time of each function (method) implemented is provided as comments at the beginning of each function in the code.
