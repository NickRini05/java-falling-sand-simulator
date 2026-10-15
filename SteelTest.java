import student.micro.*;
import static org.assertj.core.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * This test class ensures constructor values are correct for steel
 * and overridden methods execute as expected.
 *
 * Steel should not be able to flow or fall.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class SteelTest
    extends TestCase
{
    //~ Fields ................................................................

    private ParticleWorld world;
    private Steel steel;

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
     * Tests that the steel constructor sets the correct values.
     */
    public void testConstructor()
    {
        steel = new Steel();

        assertThat(steel.getColor()).isEqualTo(Steel.GRAY);
        assertThat(steel.getDensity()).isEqualTo(7.87);
        assertThat(steel.willDissolve()).isTrue();
    }

    /**
     * Tests that steel does not fall.
     */
    public void testIsFalling()
    {
        steel = new Steel();
        world.addObject(steel, 1, 1);

        assertThat(steel.isFalling()).isFalse();
    }

    /**
     * Tests that steel never flows.
     */
    public void testFlow()
    {
        steel = new Steel();
        world.addObject(steel, 1, 1);

        assertThat(steel.flow()).isFalse();
    }

}
