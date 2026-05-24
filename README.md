# Digital Zoo Manager: Anonymous Inner Classes & Filtering (JavaBasics_Task_503_V0.1)

## 📖 Description
Before the introduction of lambda expressions in Java 8, structural behavioral parameterization relied heavily on anonymous inner classes. This project demonstrates in-place collection filtering using the **`List.removeIf()`** method combined with an explicit **anonymous class** implementing the `Predicate<String>` contract. We simulate a digital zoo catalog containing various animal names. The application isolates and purges short, non-compliant names directly within the mutable `ArrayList`, showcasing the foundational syntax that underpins modern functional programming in Java.

## 📋 Requirements Compliance
- **In-Place Purification**: Leveraged `List.removeIf()` to dynamically alter the structure of the source list without secondary memory allocation.
- **Anonymous Class Implementation**: Declared and instantiated an inline anonymous inner class for `Predicate<String>` instead of a short lambda syntax.
- **Evaluation Predicate Logic**: Overrode the `.test()` contract to target and mark any string element with a length strictly less than 4 for immediate removal.

## 🚀 Architectural Stack
- Java 17+ (Collections Framework, Functional Interfaces, Anonymous Inner Classes)

## 🏗️ Implementation Details
- **ZooManagerApp**: Core execution module controlling the livestock roster and managing the filtering process.

## 📋 Expected result
```text
=== Digital Zoo Asset Management ===
Original Animal List: [dog, cow, horse, sheep]
Filtering underperforming assets...
Cleaned Zoo Exhibit: [horse, sheep]
```

## 💻 Code Example

Project Structure:

    JavaBasics_Task_503/
    ├── src/
    │   └── com/yurii/pavlenko/
    │                 └── app/
    │                     └── ZooManagerApp.java
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

Code
```java
package com.yurii.pavlenko.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ZooManagerApp {

    public static void main(String[] args) {
        List<String> animals = new ArrayList<>(Arrays.asList("dog", "cow", "horse", "sheep"));

        System.out.println("=== Digital Zoo Asset Management ===");
        System.out.println("Original Animal List: " + animals);
        System.out.println("Filtering underperforming assets...");

        animals.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String name) {
                return name.length() < 4;
            }
        });

        System.out.println("Cleaned Zoo Exhibit: " + animals);
    }
}
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
