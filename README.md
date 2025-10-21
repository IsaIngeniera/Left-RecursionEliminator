# Left-RecursionEliminator

**This project was made by:**  
- Isabella Ocampo  
- Maria Laura Tafur  

**Group:** C2566 SI2002-5730  

For the implementation of this project, the operating system used was **Windows 10**, the programming language was **Java**, and the main development tool was **IntelliJ IDEA 2025.1**. These tools provided a stable environment for coding, testing, and running the program, ensuring compatibility and efficiency during the implementation process.

This **Java program** identifies equivalent states in a deterministic finite automaton (DFA). It uses standard Java libraries like `import java.util.*;` which are used for input handling (`Scanner`), collections (`List`, `Set`, `HashSet`), and stream processing.

---

## INSTRUCTIONS



---

## EXPLANATION

This **Java program** implements the **General Algorithm for Eliminating Left Recursion** from a Context-Free Grammar ($G$). The goal is to transform a grammar into an equivalent one that is suitable for top-down parsing methods (e.g., Predictive Parsers).

The algorithm is based on processing the non-terminals ($A_1, A_2, \dots, A_k$) in the strict **order of appearance** in the input, resolving indirect recursion before tackling direct recursion.

### 1. Algorithm Overview (The $A_i, A_j$ Method)

The entire process is driven by iterating through the non-terminals $A_i$ and, for each one, applying two sequential steps: substitution and immediate elimination.

### 2. Iterative Substitution (Eliminating Indirect Left Recursion)

The program iterates through all preceding non-terminals $A_j$ (where $j < i$). This step converts any indirect recursion involving $A_i$ into direct recursion:

-   **Identification:** If $A_i$ has a production $A_i \rightarrow A_j \gamma$, this indicates **indirect left recursion**.
-   **Action:** The algorithm replaces $A_j$ with all of its productions ($\delta$). The original production is replaced by the set $A_i \rightarrow \delta \gamma$.
-   **Effect:** After iterating through all $j < i$, $A_i$ will not have any productions starting with a non-terminal that precedes it in the order, ensuring only direct recursion ($A_i \rightarrow A_i \alpha$) remains, if any.

### 3. Direct Left Recursion Elimination

Once $A_i$ has no indirect left recursion, the standard transformation is applied to resolve any resulting direct left recursion:

-   **Separation:** The productions of $A_i$ are partitioned into **recursive** alternatives ($A_i \alpha_1, A_i \alpha_2, \dots$) and **non-recursive** alternatives ($\beta_1, \beta_2, \dots$).
-   **Transformation:** A new non-terminal, $Z$ (or the next available uppercase letter), is introduced to capture the recursive structure:
    -   $$A_i \rightarrow \beta_1 Z \mid \beta_2 Z \mid \dots$$
    -   $$Z \rightarrow \alpha_1 Z \mid \alpha_2 Z \mid \dots \mid e$$
-   The new non-terminal ($Z$) is added to the grammar structure for potential future processing if its productions include other NTs.

---

## REFERENCES

- **Aho, Alfred V. et al.** (2006). *Compilers: Principles, Techniques, and Tools (2nd Edition).* Addison-Wesley Longman Publishing Co., Inc. **(Section 4.3.3: Elimination of Left Recursion)**
These equivalent pairs are then printed as the output.

