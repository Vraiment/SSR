# SSR (Sage Save Reader)
------------------------

This project is a small visual tool to see *SAV* files for *SAGE* (*Saffire Advanced Game Engine*) used by the **Bionicle: The Legend of Mata Nui**.

The tool is written with [Kotlin](https://kotlinlang.org) using [JavaFX](https://www.oracle.com/technetwork/java/javafx) for the UI.

## End user instructions

You will need [Gradle](https://gradle.org) to execute it. Clone the repository and from the cloned repository just execute `gradle run` and it should launch the tool.

You can open a save file from the save menu and then select the save file you wish to open.

## Developer instructions

- The `main(...)` function is under the *src/main/kotlin/Main.kt* file.
- The model is located under the *src/main/kotlin/model* folder and basically is just a description of what is described in the [unofficial documentation](https://github.com/TheLegendOfMataNui/sage-file-formats/blob/master/SAV/SAV.bt)
    - The logic to read a file into the model is located under the *src/main/kotlin/io* folder.
- The view files are located in two different places:
   1. The view for the main application is located under *src/main/resources* and is written with [FXML](https://en.wikipedia.org/wiki/FXML), you can use the [JavaFX Scene Builder](https://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-info-2157684.html) to edit it visually.
   2. The view for specifically the save file is located under the *src/main/kotlin/view* folder
   3. There is a small "DSL" (Domain Specific Language) definition under *src/main/kotlin/view/dsl* which is used by the view of the save file.
