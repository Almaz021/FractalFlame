# Single-Threaded vs Multi-Threaded Performance Comparison

### This document presents the performance comparison of single-threaded and multi-threaded execution under various configurations and iteration counts.

---

## Configuration #1
**Parameters:**
- **Resolution:** 3840x2160
- **Number of Points:** 300
- **Affine Transformations:** 5
- **Number of Colors:** 5
- **Symmetry:** 4
- **Image Format:** PNG
- **Shape:** Sphere

**Results:**

| Iterations         | Single-Thread (s) | Multi-Thread (s) | Speedup (x)  |
|---------------------|-------------------|------------------|--------------|
| 10,000             | 20-25            | 1-2              | 10-25        |
| 100,000            | 311              | 11-16            | 19-28        |

---

## Configuration #2
**Parameters:**
- **Resolution:** 3840x2160
- **Number of Points:** 300
- **Affine Transformations:** 5
- **Number of Colors:** 5
- **Symmetry:** 1
- **Image Format:** PNG
- **Shape:** Sphere

**Results:**

| Iterations         | Single-Thread (s) | Multi-Thread (s) | Speedup (x)  |
|---------------------|-------------------|------------------|--------------|
| 10,000             | 9-11             | 0-1              | 11-∞         |
| 100,000            | 92-93            | 3-4              | 23-31        |
| 1,000,000          | 944              | 22-34            | 28-43        |
| 10,000,000         | ~9500            | 376              | ~25          |
