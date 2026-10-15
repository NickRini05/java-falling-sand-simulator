import student.micro.*;
import static org.assertj.core.api.Assertions.*;
import java.awt.Color;

// -------------------------------------------------------------------------
/**
 * These tests ensure that fluid particles have correct flow physics given
 * various environments.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class FluidTest
    extends TestCase
{
    //~ Fields ................................................................
    private ParticleWorld world;
    private Fluid fluid;

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
     * Tests that fluid flows downward when space is open below.
     */
    public void testVerticalFlow()
    {
        fluid = new Fluid(Color.RED, false, 1.0);
        world.addObject(fluid, 1, 1);

        fluid.flow();

        assertThat(fluid.getX()).isEqualTo(1);
        assertThat(fluid.getY()).isEqualTo(2);
    }

    /**
     * Tests that the fluid particle flows left when downward, down-left,
     * down-right, and right movements are all blocked, but the left
     * is open.
     */
    public void testFlowLeft()
    {
        fluid = new Fluid(Color.RED, false, 1.0);
        Particle blockerDown = new Particle(Color.GRAY, false, 5.0);
        Particle blockerDownLeft = new Particle(Color.GRAY, false, 5.0);
        Particle blockerDownRight = new Particle(Color.GRAY, false, 5.0);

        world.addObject(fluid, 1, 1);
        world.addObject(blockerDown, 1, 2);
        world.addObject(blockerDownLeft, 0, 2);
        world.addObject(blockerDownRight, 2, 2);

        fluid.flow();

        assertThat(fluid.getX()).isEqualTo(0);
        assertThat(fluid.getY()).isEqualTo(1);
    }

    /**
     * Tests that the fluid particle flows to the right when all vertical
     * and diagonal downward directions are blocked, as well as its left.
     */
    public void testFlowRight()
    {
        fluid = new Fluid(Color.RED, false, 1.0);
        Particle blockerDown = new Particle(Color.GRAY, false, 5.0);
        Particle blockerDownLeft = new Particle(Color.GRAY, false, 5.0);
        Particle blockerDownRight = new Particle(Color.GRAY, false, 5.0);
        Particle blockerLeft = new Particle(Color.GRAY, false, 5.0);

        world.addObject(fluid, 1, 1);
        world.addObject(blockerDown, 1, 2);
        world.addObject(blockerDownLeft, 0, 2);
        world.addObject(blockerDownRight, 2, 2);
        world.addObject(blockerLeft, 0, 1);

        fluid.flow();

        assertThat(fluid.getX()).isEqualTo(2);
        assertThat(fluid.getY()).isEqualTo(1);
    }

    /**
     * Tests that fluid does not move when all directions are blocked.
     */
    public void testFlowBlocked()
    {
        fluid = new Fluid(Color.RED, false, 1.0);
        Particle blockerDown = new Particle(Color.GRAY, false, 5.0);
        Particle blockerDownLeft = new Particle(Color.GRAY, false, 5.0);
        Particle blockerDownRight = new Particle(Color.GRAY, false, 5.0);
        Particle blockerLeft = new Particle(Color.GRAY, false, 5.0);
        Particle blockerRight = new Particle(Color.GRAY, false, 5.0);

        world.addObject(fluid, 1, 1);
        world.addObject(blockerDown, 1, 2);
        world.addObject(blockerDownLeft, 0, 2);
        world.addObject(blockerDownRight, 2, 2);
        world.addObject(blockerLeft, 0, 1);
        world.addObject(blockerRight, 2, 1);

        fluid.flow();

        assertThat(fluid.getX()).isEqualTo(1);
        assertThat(fluid.getY()).isEqualTo(1);
    }
}
