import student.micro.*;
import static org.assertj.core.api.Assertions.*;
import java.awt.Color;
// -------------------------------------------------------------------------
/**
 * Tests the Particle class for its constructor, falling behavior,
 * weakening and removal, flowing physics, and swapping behavior.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class ParticleTest
    extends TestCase
{
    //~ Fields ................................................................
    private ParticleWorld world;

    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {

        world = new ParticleWorld(3, 3);
    }

    // ----------------------------------------------------------

    /**
     * Tests the constructor and getters for the Particle class.
     */
    public void testConstructor()
    {
        Particle particle = new Particle(Color.RED, true, 2.5);

        assertThat(particle.getColor()).isEqualTo(Color.RED);
        assertThat(particle.willDissolve()).isTrue();
        assertThat(particle.getDensity()).isEqualTo(2.5);

        assertThat(particle.getVelocity()).isEqualTo(0.0);
        assertThat(particle.getAcceleration()).isEqualTo(1.0);
        assertThat(particle.getStrength()).isEqualTo(100);
    }

    /**
     * Tests the weaken method and removal when strength is 0.
     */
    public void testWeaken()
    {
        Particle testParticle;
        testParticle = new Particle(Color.RED, false, 1.0);
        world.addObject(testParticle, 1, 1);

        for (int i = 0; i < 100; i++)
        {
            testParticle.weaken();
        }

        assertThat(world.getObjects(Particle.class))
            .doesNotContain(testParticle);
    }

    /**
     * Tests isFalling when the space below is either empty or blocked.
     */
    public void testIsFalling()
    {
        Particle fallingParticle = new Particle(Color.RED, false, 1.0);
        world.addObject(fallingParticle, 1, 0);

        assertThat(fallingParticle.isFalling()).isTrue();

        Particle blocker = new Particle(Color.GRAY, false, 5.0);
        world.addObject(blocker, 1, 1);

        assertThat(fallingParticle.isFalling()).isFalse();
    }

    /**
     * Tests isFalling returns false when the particle
     * is at the bottom of the world.
     */
    public void testIsFallingAtBottom()
    {
        Particle bottomParticle = new Particle(Color.BLUE, false, 1.0);
        ParticleWorld edgeWorld = new ParticleWorld(3, 3);
        edgeWorld.addObject(bottomParticle, 1, 2);

        assertThat(bottomParticle.isFalling()).isFalse();
    }

    /**
     * Tests the fall behavior for a particle.
     */
    public void testFall()
    {
        Particle particle = new Particle(Color.RED, false, 1.0);
        world.addObject(particle, 1, 0);

        particle.fall();

        assertThat(particle.getY()).isEqualTo(1);
        assertThat(particle.getVelocity()).isEqualTo(1.0);

        Particle blockedParticle = new Particle(Color.BLUE, false, 1.0);
        Particle blocker = new Particle(Color.GRAY, false, 5.0);
        world.addObject(blockedParticle, 2, 0);
        world.addObject(blocker, 2, 1);

        blockedParticle.fall();

        assertThat(blockedParticle.getY()).isEqualTo(0);
        assertThat(blockedParticle.getVelocity()).isEqualTo(0.0);
    }

    /**
     * Tests the swapPlacesIfPossible method with an empty space.
     */
    public void testSwapWithEmpty()
    {
        Particle particle = new Particle(Color.RED, false, 2.0);
        world.addObject(particle, 1, 1);

        particle.swapPlacesIfPossible(1, 2);

        assertThat(particle.getX()).isEqualTo(1);
        assertThat(particle.getY()).isEqualTo(2);
    }

    /**
     * Tests the swapPlacesIfPossible with a particle of lower density.
     */
    public void testSwapWithLowerDensity()
    {
        Particle heavy = new Particle(Color.RED, false, 5.0);
        Particle light = new Particle(Color.BLUE, false, 1.0);

        world.addObject(heavy, 1, 1);
        world.addObject(light, 1, 2);

        heavy.swapPlacesIfPossible(1, 2);

        assertThat(heavy.getY()).isEqualTo(2);
        assertThat(light.getY()).isEqualTo(1);
    }

    /**
     * Tests the flow behavior when a space is empty diagonally.
     */
    public void testFlow()
    {
        Particle particle = new Particle(Color.RED, false, 2.0);
        world.addObject(particle, 1, 1);

        particle.flow();

        assertThat(particle.getY()).isEqualTo(2);
    }

    /**
     * Tests the flow method when the particle is surrounded.
     */
    public void testFlowBlocked()
    {
        Particle center = new Particle(Color.RED, false, 2.0);
        Particle below = new Particle(Color.GRAY, false, 5.0);
        Particle downLeft = new Particle(Color.GRAY, false, 5.0);
        Particle downRight = new Particle(Color.GRAY, false, 5.0);

        world.addObject(center, 1, 1);
        world.addObject(below, 1, 2);
        world.addObject(downLeft, 0, 2);
        world.addObject(downRight, 2, 2);

        center.flow();

        assertThat(center.getX()).isEqualTo(1);
        assertThat(center.getY()).isEqualTo(1);
    }

    /**
     * Tests to ensure that the velocity is zero once the particle stops
     * falling.
     */
    public void testFallBlocked()
    {
        Particle fallingParticle = new Particle(Color.RED, false, 1.0);
        Particle blocker = new Particle(Color.GRAY, false, 5.0);

        world.addObject(fallingParticle, 1, 0);
        world.addObject(blocker, 1, 2);

        fallingParticle.fall();
        fallingParticle.fall();

        assertThat(fallingParticle.getY()).isEqualTo(1);
        assertThat(fallingParticle.getVelocity()).isEqualTo(0.0);
    }

    /**
     * Tests that swapPlacesIfPossible returns false when the
     * target is out of bounds.
     */
    public void testSwapOutOfBounds()
    {
        Particle particle = new Particle(Color.BLUE, false, 1.0);
        world.addObject(particle, 1, 1);

        boolean resultLeft = particle.swapPlacesIfPossible(-1, 1);
        assertThat(resultLeft).isFalse();

        boolean resultUp = particle.swapPlacesIfPossible(1, -1);
        assertThat(resultUp).isFalse();

        int tooFarRight = world.getWidth();
        boolean resultRight = particle.swapPlacesIfPossible(tooFarRight, 1);
        assertThat(resultRight).isFalse();

        int tooFarDown = world.getHeight();
        boolean resultDown = particle.swapPlacesIfPossible(1, tooFarDown);
        assertThat(resultDown).isFalse();

        assertThat(particle.getX()).isEqualTo(1);
        assertThat(particle.getY()).isEqualTo(1);
    }

    /**
     * Tests that act() triggers fall() when the particle is in free fall.
     */
    public void testActTriggersFall()
    {
        Particle particle = new Particle(Color.RED, false, 1.0);
        world.addObject(particle, 1, 0);

        particle.act();

        assertThat(particle.getY()).isEqualTo(1);
    }

    /**
     * Tests that the particle flows down-right when down and
     * down-left are blocked.
     */
    public void testFlowDownRight()
    {
        Particle particle = new Particle(Color.RED, false, 1.0);
        Particle blockDown = new Particle(Color.GRAY, false, 5.0);
        Particle blockDownLeft = new Particle(Color.GRAY, false, 5.0);

        world.addObject(particle, 1, 1);
        world.addObject(blockDown, 1, 2);
        world.addObject(blockDownLeft, 0, 2);

        boolean moved = particle.flow();

        assertThat(moved).isTrue();
        assertThat(particle.getX()).isEqualTo(2);
        assertThat(particle.getY()).isEqualTo(2);
    }
}
