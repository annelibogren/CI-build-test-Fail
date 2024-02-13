package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class BuildTest {

    @Test
    void testRunGradleBuild_Success() throws IOException, InterruptedException {
        Build build = new Build();

        // Create a temporary directory for the project
        Path tempDir = Files.createTempDirectory("test_project");
        File testFile = tempDir.resolve("PositiveTest.java").toFile();
        

        // Create a mock Gradle project in the temporary directory
        FileWriter writer = new FileWriter(testFile);
        writer.append("public class PositiveTest {public String getGreeting() {\n" + //
                "        return \"Hello World!\";\n" + //
                "    }public String getGreeting() {\n" + //
                        "        return \"Hello World!\";\n" + //
                        "    }}");

        File projectDir = tempDir.toFile();

        // Run the Gradle build process
        Build.BuildResult result = build.runGradleBuild(projectDir);

        // Verify that the build was successful
        assertTrue(result.isSuccess());

        // Clean up the temporary directory
        testFile.delete();
        Files.deleteIfExists(tempDir);
    }

    @Test
    void testRunGradleBuild_Failure() throws IOException {
        Build build = new Build();

        // Create a temporary directory for the project
        Path tempDir = Files.createTempDirectory("test_project");
        File projectDir = tempDir.toFile();

        // Create a mock Gradle project in the temporary directory

        // Introduce a deliberate error in the project

        // Run the Gradle build process
        Build.BuildResult result = build.runGradleBuild(projectDir);

        // Verify that the build failed
        assertFalse(result.isSuccess());
        assertNotNull(result.getOutput()); // Ensure that the error message is not null

        // Clean up the temporary directory
        Files.deleteIfExists(tempDir);
    }

    
}