# FitLog-Application


Build Requirements:
- Java JDK version 21 or newer
- JavaFX SDK 23.0.1
- IntelliJ IDEA IDE

Build Instructions:
Note: Refer to this official javaFX documentation for using JavaFX on IntelliJ IDEA for more information - https://openjfx.io/openjfx-docs/
1. Clone the repository from GitLab
2. Open IntelliJ
3. Select file > open to open a new project
4. Select the directory for the repository that you cloned from GitLab to open
5. Open file > Project Structure
- Set the SDK to 21 or newer
- Navigate to the libraries tab and select add new library, using the file path to the library folder of your JavaFX download and select "apply"
6. Edit the Run configurations:
select Run -> Edit Configurations... and add these VM options:
- Windows: --module-path "\path\to\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml
- Linux/Mac: --module-path /path/to/javafx-sdk-23.0.1/lib --add-modules javafx.controls,javafx.fxml
replace the path with your path to the JavaFX library file
finally, select the "Main" class to be run beneath the VM options
7. Ready to run:
You should now be able to compile and run the application as intended
