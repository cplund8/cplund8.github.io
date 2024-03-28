# Prerequisites

To compile and build these labs, you will need the following:

1. The [Java Development Kit](https://www.oracle.com/java/technologies/downloads/) (JDK)
    1. You can test if you have Java installed by running `java --version` in Terminal. If you have it installed, it will tell you the version; if not, it will say something along the lines of "Unknown command"
2. A [GitHub](https://github.com/) account
3. [GitHub Desktop](https://desktop.github.com/)

> **Note**: For this guide, I'll use `<>` to denote a variable that you have to replace with your own value. Do not include them when typing in your own values. For example, if my guide had `<current year>`, replace it with `2024`, not `<2024>`.

# Compiling the source code

All the simulations require a package called `lab` that provides a framework for creating buttons, beakers, flasks, etc. We only have to compile it once, because it is the same for all the simulations. To start, download `LabPlatform-Master.zip` and decompress it. Inside of it, there's a folder named `src`. Inside of `src`, there is a folder called `lab`. Right click on this folder, then click `Copy`.

Next, download `FinishedSimulations-master.zip`. Decompress it and open it. Right click on `src`, then click `Paste Item`. Make sure the folder gets pasted *inside* of `src`. If it doesn't, you can click and drag it inside of `src`. Right click on `src` again, then click `New Terminal at Folder`, near the bottom of the options list. This will open a Terminal window. In it, run the following command:

```bash
javac -target 1.8 -source 1.8 **/*.java
```

This compiles all the `.java` files in `src` using Java 8 (which is version number 1.8, for whatever reason). You'll probably see some warnings, and that's okay. The source code for both the labs and all the simulations has now been compiled into `.class` files!

# Building a simulation

We need to wrap a simulation's code and a copy of `lab` into a `jar` archive, in a process called "building". First, in your Terminal window, run the following command:

```bash
touch manifest.txt
```

This creates a new text file called `manifest.txt` inside of `src`. Open the file and paste the following:

```
Manifest-Version: 1.0
Package-Name: <package name>
Main-Class: <package name>.<main class>

```

Replace `<package name>` with the name of the folder that houses the code for the simulation, and `<main class>` with the name of the main `.java` file. If there is only one `.java` file for the simulation code, that is the main file. If there is more than `.java` file, the main file is the file which contains the following text:

```java
public static void main(
```

For example, the manifest.txt file for the HI lab looks like:

```
Manifest-Version: 1.0
Package-Name: HI_equilibrium
Main-Class: HI_equilibrium.HIEquilibrium

```

> **Note**: There has to be an empty line at the bottom of your `manifest.txt` file, or it will not work correctly. Additionally, you can't have more than one `manifest.txt` file; if you want to build a different simulation, change the contents of your existing file instead of creating a new one.

Next, in your Terminal window, paste the following command:

```bash
find lab <package name> -type f -name "*.class" | xargs jar cfm <name>.jar manifest.txt
```

Replace `<package name>` with the same as from before. You can replace `<name>` with whatever name you'd like; for example, for the HI code, I named it `HISim`. This command takes all of the compiled `.class` files in `lab` and in the simulation and bundles them into a `.jar` archive. To test that the simulation built correctly, you can run:

```bash
java -jar <name>.jar
```

If the simulation launches as expected, then you have successfully built it!

# Hosting on GitHub Pages

Go to GitHub and [create a new public repository](https://github.com/new) named `<username>.github.io`, where `<username>` is your exact, case-sensitive GitHub username. Once you've created the repository, click the `Set up in Desktop` button, which should launch GitHub desktop.

---

# OLD


Right click on the folder, then select `New Terminal at Folder`, near the bottom of the options list. This will open a Terminal window. In it, run the following two commands:

```bash
find . -name "*.java" > sources.txt
javac -target 1.8 -source 1.8 @sources.txt
```

**Note**: in Terminal, you have to run each command one at a time. You can't paste both commands into Terminal in at the same time.

The first command creates a file called `sources.txt`, which is a list of all the `.java` files in `lab`. The second command compiles the all the files listed by `sources.txt`, using Java 8 (which is notated as version 1.8, for whatever reason). 

`lab` is now compiled using Java 8, and is ready to used by the simulations!

# Compiling the simulation

These steps are the same for compiling any of the simulations. First, download `FinishedSimulations-master.zip`, decompress it, then open it. Drag the entire `lab` folder that we compiled in the previous step (not just the contents of the folder, but the folder itself) into the `src` folder that is inside `FinishedSimulations-master`. Next, right click on `src` and click `New Terminal at Folder`, opening a Terminal window.