# Prerequisites

This tutorial is written for a computer running macOS. To compile and build these labs, you will need the following:

1. The [Java Development Kit](https://www.oracle.com/java/technologies/downloads/) (JDK)
    1. You can test if you have Java installed by running `java -version` in Terminal. If you have it installed, it will tell you the version; if not, it will say something along the lines of "Unknown command"
2. A [GitHub](https://github.com/) account
3. [GitHub Desktop](https://desktop.github.com/)

> **Note**: For this guide, I'll use `<>` to denote a variable that you have to replace with your own value. Do not include them when typing in your own values. For example, if my guide had `<current year>`, replace it with `2024`, not `<2024>`.

# Compiling the source code

All the simulations require a package called `lab` that provides a framework for creating buttons, beakers, flasks, etc. We only have to compile it once, because it is the same for all the simulations. To start, download `LabPlatform-master.zip` and decompress it. Inside of it, there's a folder named `src`. Inside of `src`, there is a folder called `lab`. Right click on this folder, then click `Copy`.

Next, download `FinishedSimulations-master.zip`. Decompress it and open it. Right click on `src`, then click `Paste Item`. Make sure the folder gets pasted *inside* of `src`. If it doesn't, you can click and drag it inside of `src`. Right click on `src` again, then click `New Terminal at Folder`, near the bottom of the options list. This will open a Terminal window. In it, run the following command:

```zsh
javac -target 1.8 -source 1.8 **/*.java
```

This compiles all the `.java` files in `src` using Java 8 (which is version number 1.8, for whatever reason). You'll probably see some warnings, and that's okay. The source code for both the labs and all the simulations has now been compiled into `.class` files!

# Building a simulation

Inside of `FinishedSimulations-master/src` are a bunch of folders. Each of these folders houses the code for a separate simulation. We need to bundle a simulation's code and a copy of `lab` into a `jar` archive, in a process called "building". First, in your Terminal window, run the following command:

```zsh
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

```zsh
find lab <package name> -type f -name "*.class" | xargs jar cfm <name>.jar manifest.txt
```

Replace `<package name>` with the same as from before. You can replace `<name>` with whatever name you'd like; for example, for the HI code, I named it `HISim`. This command takes all of the compiled `.class` files in `lab` and in the simulation and bundles them into a `.jar` archive. To test that the simulation built correctly, you can run:

```zsh
java -jar <name>.jar
```

If the simulation launches as expected, then you have successfully built it!

# Hosting on GitHub Pages

Go to GitHub and [create a new public repository](https://github.com/new) named `<username>.github.io`, where `<username>` is your exact, case-sensitive GitHub username. Once you've created the repository, click the `Set up in Desktop` button, which should launch GitHub Desktop.

Once GitHub Desktop launches, click the `Clone` button, then click the `Show in Finder` button, which will open the folder that houses your repository. Copy and paste all of the `.jar` files that you built in the previous step into this folder. You don't need any of the other files, only the `.jar` archives.

Open a new Terminal window (you can do this by clicking the magnifying glass icon in the top bar, typing in `Terminal`, and pressing enter) and run the following commands:

```zsh
cd ~/Documents/GitHub/<username>.github.io
touch index.html
mkdir sims
cd sims
```

By default, Terminal windows execute commands at the root of your computer, `~/`. The first command changes that to the folder that GitHub Desktop created to house the repository (`cd` is short for `change directory`). We create a file called `index.html`, then a folder called `sims` (`mkdir` is short for `make directory`), then navigate into that folder. 

Next, for each simulation you built, run the following command:

```zsh
touch <sim name>.txt
```

> **Note**: we're only saving the files as `.txt` because the default macOS text editor, TextEdit, will use an unsupported type of text encoding if we don't. However, if you're using a proper code editor, you can save the files directly with a `.html` extension.

Go back to the Finder window we opened earlier. Open the `sims` folder, and in each `.txt` file, paste the following code:

```html
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Simulation</title>
        <script src="https://cjrtnc.leaningtech.com/3.0/cj3loader.js"></script>
        <style>
            body {
                margin: 0px;
            }
        </style>
    </head>
    <body>
        <script>
            (async function () {
                await cheerpjInit();
                cheerpjCreateDisplay(window.innerWidth, window.innerHeight);
                window.onresize = () => {
                    let display = document.getElementById("cheerpjDisplay");
                    display.style.width = `${window.innerWidth}px`;
                    display.style.height = `${window.innerHeight}px`;
                }
                await cheerpjRunJar("/app/<sim name>.jar");
            })();
        </script>
  </body>
</html>
```

In the top menu, click `File`, then hold down the Option key, then click `Save As`. Make sure to replace the `.txt` extension for each file with `.html`.

This is mostly boilerplate HTML code, with a few differences:

```js
<script src="https://cjrtnc.leaningtech.com/3.0/cj3loader.js"></script>
```

This loads CheerpJ from their servers so you don't have to install it locally onto your machine.

```js
(async function () {
    await cheerpjInit();
    cheerpjCreateDisplay(window.innerWidth, window.innerHeight);
    window.onresize = () => {
        let display = document.getElementById("cheerpjDisplay");
        display.style.width = window.innerWidth + "px";
        display.style.height = window.innerHeight + "px";
    }
    await cheerpjRunJar("/app/sims/<sim name>.jar");
})();
```

1. `await cheerpjInit();` begins initializing CheerpJ and waits for it to be finished before moving on to the next line.
2. `cheerpjCreateDisplay(window.innerWidth, window.innerHeight);` initializes the CheerpJ display and sets the starting size to the size of the window.
3. `window.onresize = () => {` is a function that gets automatically called every time the window is resized.
4. `let display = document.getElementById("cheerpjDisplay");` creates a variable that lets us reference the CheerpJ display we created in Step 2.
5. `display.style.width = window.innerWidth + "px"; display.style.height = window.innerHeight + "px";` sets the size of the CheerpJ display to be the size of the window.
6. `await cheerpjRunJar("/app/sims/<sim name>.jar");` loads the `.jar` simulation file. Replace `<sim name>` with the name of the simulation.

Go back to GitHub Desktop. In the `Summary (required)` field in the left sidebar, write whatever you want. Things like "Initial commit" are common. Then, in the top bar, click the `Push origin` button. If you don't get an error, your simulations should now be live! GitHub Pages takes a couple of minutes to update, but the links to view the simulations are:

`<GitHub username>.github.io/sims/<sim name>`