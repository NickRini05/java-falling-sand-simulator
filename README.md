# Falling Sand Simulator (Java)

A Java particle simulation built as a software design course project in 2025. It models how different materials move and interact in a two-dimensional world.

## Features

- **Sand:** Falls and can move around other particles according to the simulation's density rules.
- **Water:** Inherits fluid behavior and can flow sideways when it cannot move down.
- **Steel:** Forms a stationary barrier.
- **Acid:** Flows like a liquid and weakens neighboring dissolvable particles.
- **Particle mechanics:** Handles movement, gravity, density, and particle interactions.
- **Unit tests:** Includes test classes for each particle type and key behaviors.

## Implementation

The project uses Java classes and inheritance to share common behavior between materials. `Particle` implements the general particle mechanics, `Fluid` adds lateral movement, and individual material classes specialize their behavior. `ParticleWorld` provides the world configuration, and `Application` configures the simulator launcher.

The repository includes the following Java source and test files:

- `Particle.java`, `ParticleTest.java`
- `Fluid.java`, `FluidTest.java`
- `Sand.java`, `SandTest.java`
- `Water.java`, `WaterTest.java`
- `Steel.java`, `SteelTest.java`
- `Acid.java`, `AcidTest.java`
- `ParticleWorld.java`, `Application.java`

## Running the project

**This is a course-framework-based Java project, not a standalone Java application.** The source imports `student.micro` / `student.micro.GreenfootApplication`, and the simulator also depends on the supplied `ParticleBase`, `ParticleWorldBase`, and `Dropper` classes (provided in `program05.jar`). It may require the corresponding course/Greenfoot/BlueJ environment and testing libraries to compile or run.

To run it in a compatible environment:

1. Make the `student.micro` framework and the course-provided `program05.jar` available to the project.
2. Load the Java sources into the compatible Java/Greenfoot course environment.
3. Start the `Application` class, which loads `ParticleWorld`.

The included test classes use `student.micro.TestCase` and AssertJ. They require those testing dependencies as well.

**Dependency and asset note:** `+libs/program05.jar` and `images/dropper.png` came with the course project materials. They are retained in the local ZIP to preserve the project, but their public redistribution permissions have not been verified. The `.gitignore` excludes the JAR from Git commits. Check the applicable license/course policy before publicly uploading the JAR or image.

## Authorship and project history

Java implementation and tests: **Nick Rini**. Original `@version` values in the Java source files have been retained from the working project; these are historical version labels, not new modification dates.

This is a cleaned portfolio presentation of the original coursework. The simulation logic and test assertions have not been intentionally changed.
