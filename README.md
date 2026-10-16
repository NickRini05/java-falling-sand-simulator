
# Falling Sand Simulator (Java)

A Java particle simulation built as a software design course project in 2025. It models how different materials move and interact in a two-dimensional world.

## Simulation Preview

![Falling Sand Simulator](simulation-demo.png)

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

The project also includes `program05.jar` (the simulation's course library) and `dropper.png` (an image asset) in the repository root.

## Running the project

**This is a course-framework-based Java project, not a standalone Java application.** The source imports `student.micro` and `student.micro.GreenfootApplication`. It also depends on `ParticleBase`, `ParticleWorldBase`, and `Dropper` from `program05.jar`. A compatible Java/Greenfoot course environment and the `student.micro` framework are required to compile and run it.

To run it in a compatible environment:

1. Download or clone this repository.
2. Make the `student.micro` framework available and add the included `program05.jar` to the project's classpath.
3. Load the Java source files into the compatible course/Greenfoot environment.
4. Start the `Application` class, which loads `ParticleWorld`.

The test classes use `student.micro.TestCase` and AssertJ, so those testing dependencies are needed to run the tests.

## Authorship and project history

Java implementation and tests: **Nick Rini**. Original `@version` values in the Java source files have been retained from the working project; these are historical version labels, not new modification dates.

This repository is a cleaned portfolio presentation of the original coursework. The simulation logic and test assertions have not been intentionally changed.
