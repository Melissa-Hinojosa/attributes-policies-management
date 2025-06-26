
# ABE Attribute & Policy Management – Implementation Guide

This repository contains the implementation of the attribute and policy management framework described in our manuscript. The code supports:

- The definition and management of the attribute universe
- The generation and update of user attribute sets
- The specification and update of data access policies
- The integration of an external CP-ABE encryption scheme (via JAR)

---

## 📁 Code Structure and Execution

The project includes four main execution classes:

- `MainExample.java`: Demonstrates the full workflow of an ABE management scheme with example data (Section 5.2.1).
- `MainCaseStudy.java`: Implements a real-case study based on the experimental setup described in Section 5.2.2.
- `Test_ABE_Management.java`: Runs the complete flow including generation of attribute universe, user attributes assignment and secret key generation, access policies generation, and basic encryption and decryption operations.
- `MainPerformanceEval.java`: Generates synthetic, structured datasets to support experimental evaluation of the framework.

---

## ▶️ Execution Instructions

1. Include the libraries located in the `JARs/` folder as external dependencies.
2. Run `MainExample.java`, `MainCaseStudy.java`, `Test_ABE_Management.java`, or `MainPerformanceEval.java` as needed.
3. Execution outputs will be generated in:

```
data/attributes/
data/attributes/users/
data/policies/
data/sink/
data/source/
```

---

## 🔧 Functionality Overview (MainExample.java)

### Attribute Universe Definition and Updates
- `generateAttributesUniverse_example()`: Generate the attribute universe.
- `addAttributesToUniverse_example()`: Add new attributes and values to the attribute universe.
- `addAttributeValue_example()`: Add new values to existing attributes.
- `modifyAttribute_example()`: Update constraints or status of an attribute.
- `removeAttribute_example()`: Remove an attribute (mark as inactive).

### User Attribute Sets Definition and Updates
- `generateUserAttributes_example()`: Create attribute sets for users.
- `addUserAttributes_example()`: Add new attributes to a specific user.
- `removeUserAttribute_example()`: Remove a specific attribute from a user.

### Policy Definition and Updates
- `generateFilePolicy_example()`: Create policies using random attribute combinations, including nested policies.
- `addAttributesToPolicy_example()`: Add attributes to an existing policy.
- `removePolicyAttribute_example()`: Remove an attribute from a policy.

---

## 🏥 Case Study: eHealth Scenario (MainCaseStudy.java)

### 1. Attribute Universe Generation
- `generateAttributesUniverse_eHealth()`: Defines 𝕌 with medical roles, specialties, departments, and institutions.

### 2. User Attribute Set Generation
- `generateUserAttributes_eHealth()`: Generates user profiles with roles such as physicians, nurses, researchers, and assistants.

### 3. Access Control Policy Definition
- `generateFilePolicy_eHealth()`: Defines access policies (𝐴𝑃) for DICOM files, using threshold gates and nested structures.

### 4. Display Utilities
- `displayUserAttributes()`: Shows full attribute set for a user.
- `displayUserActiveAttributes()`: Shows only active attributes for a user.
- `displayFilesPolicy()`: Displays policies in flat JSON format.
- `displayComplexFilePolicy()`: Displays nested policy trees in JSON format.

### 5. Policy Translation to CP-ABE Formats
- `translatePolicies()`: Translates policies into BSW07 and W11 CP-ABE constructions.
- `testABEtranslator()`: Converts a file’s policy to the format required by BSW07 or W11.

---

## 🔒 CP-ABE + AES Workflow (Test_ABE_Management.java)

### User Key Generation
- `getUserActiveAttributes()`: Retrieves the active attribute set 𝑆ᵤ for a given user.
- `createSKu()`: Generates the user's secret key 𝑆𝐾ᵤ using BSW07 or W11.

### File Encryption
- `encryptFile()`: Encrypts a file using AES, with the AES key protected by CP-ABE.

### File Decryption
- `decryptFile()`: Attempts to decrypt the file using the user’s attributes and access policy.

### Main Execution
Simulates the full process:
- Encrypts a file with a given policy.
- Generates the user's secret key.
- Decrypts the file and stores the output for later inspection (e.g., visualization via MATLAB using the readDICOM.m function located in data/).

---

## ⚙️ Performance Evaluation Utilities (MainPerformanceEval.java)

### Attribute Universe Generation
- `testAttributeUniverse()`: Performs bulk generation of attribute universes of varying sizes and values per attribute (e.g., 10-200 attributes and 5-50 values), repeating the process 100 times for each configuration.

### User Attribute Sets

**generateUserAttributes(int attributeCount)**  
- `testAttributeSetsGeneration()`: Automatically generates user attribute sets for different sizes (e.g., 10-200 attributes), repeating the process 100 times per configuration.

### Access Control Policies
- `testPoliciesGeneration()`: Automatically generates policies for different sizes (e.g., 10-200 attributes), repeating the process 100 times per configuration.

### Main Execution
Runs all three tests sequentially:
1. Attribute universe generation
2. User attribute sets creation
3. Access control policy generation

---

## 📌 Notes

- Generated structures for attributes and policies are stored in JSON format.
- The `.dcm` files are DICOM files used for demonstration purposes.

---

## 🧑‍💻 Author

**Melissa Brigitthe Hinojosa-Cabello**  
Version 1.0 – Updated 2025-06
