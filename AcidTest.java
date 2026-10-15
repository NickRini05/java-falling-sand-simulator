import student.micro.*;
import static org.assertj.core.api.Assertions.*;
import java.awt.Color;

// -------------------------------------------------------------------------
/**
 * Tests for the Acid class, ensuring correct dissolving and
 * act behavior.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class AcidTest
    extends TestCase
{
    //~ Fields ................................................................

    private ParticleWorld world;
    private Acid acid;

    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {

        world = new ParticleWorld(3, 3);
        acid = new Acid();
    }

    // ----------------------------------------------------------

    /**
     * Tests constructor values for color, dissolvability, and density.
     */
    public void testConstructor()
    {
        acid = new Acid();

        assertThat(acid.getColor()).isEqualTo(Acid.GREEN);
        assertThat(acid.willDissolve()).isFalse();
        assertThat(acid.getDensity()).isEqualTo(1.0);
    }

    /**
     * Acid should weaken itself and the target if the target is dissolvable.
     */
    public void testIsDissolvable()
    {
        Particle dissolvable = new Particle(Color.RED, true, 2.0);
        world.addObject(acid, 1, 1);
        world.addObject(dissolvable, 2, 1);

        int initialAcidStrength = acid.getStrength();
        int initialTargetStrength = dissolvable.getStrength();

        acid.dissolveIfPossible(2, 1);

        assertThat(acid.getStrength()).isEqualTo(initialAcidStrength - 1);
        assertThat(dissolvable.getStrength())
            .isEqualTo(initialTargetStrength - 1);
    }

    /**
     * Acid should ignore particles that cannot be dissolved.
     */
    public void testNotDissolvable()
    {
        Particle notSteel = new Particle(Color.BLUE, false, 8.0);
        world.addObject(acid, 1, 1);
        world.addObject(notSteel, 2, 1);

        int acidStrength = acid.getStrength();
        int targetStrength = notSteel.getStrength();

        acid.dissolveIfPossible(2, 1);

        assertThat(acid.getStrength()).isEqualTo(acidStrength);
        assertThat(notSteel.getStrength()).isEqualTo(targetStrength);
    }

    /**
     * Tests to ensure the acid dissolves all adjacent dissolvable
     * particles when acting.
     */
    public void testDissolvesAdjacent()
    {
        Particle left = new Particle(Color.RED, true, 2.0);
        Particle right = new Particle(Color.RED, true, 2.0);
        Particle up = new Particle(Color.RED, true, 2.0);
        Particle down = new Particle(Color.RED, true, 2.0);

        world.addObject(acid, 1, 1);
        world.addObject(left, 0, 1);
        world.addObject(right, 2, 1);
        world.addObject(up, 1, 0);
        world.addObject(down, 1, 2);

        int acidStrength = acid.getStrength();

        acid.act();

        assertThat(acid.getStrength()).isEqualTo(acidStrength - 4);
        assertThat(left.getStrength()).isEqualTo(99);
        assertThat(right.getStrength()).isEqualTo(99);
        assertThat(up.getStrength()).isEqualTo(99);
        assertThat(down.getStrength()).isEqualTo(99);
    }

    /**
     * Tests if the acid is removed from world if its strength hits
     * 0 after acting.
     */
    public void testRemovesAcid()
    {
        for (int i = 0; i < 99; i++)
        {
            acid.weaken();
        }

        Particle dissolvable = new Particle(Color.RED, true, 2.0);
        world.addObject(acid, 1, 1);
        world.addObject(dissolvable, 2, 1);

        acid.act();

        assertThat(world.getObjects(Acid.class)).doesNotContain(acid);
    }

    /**
     * Tests that dissolveIfPossible does nothing when
     * given coordinates that are out of bounds.
     */
    public void testDissolveOutOfBounds()
    {
        world.addObject(acid, 1, 1);

        int initialStrength = acid.getStrength();

        acid.dissolveIfPossible(-1, 1);
        acid.dissolveIfPossible(3, 3);
        acid.dissolveIfPossible(1, -5);

        assertThat(acid.getStrength()).isEqualTo(initialStrength);
    }

    /**
     * Tests that dissolveIfPossible runs when y is within bounds
     * but the target particle does not dissolve.
     */
    public void testDissolveInBoundsButNoDissolve()
    {
        Particle nondissolvable = new Particle(Color.GRAY, false, 2.0);

        world.addObject(acid, 1, 1);
        world.addObject(nondissolvable, 1, 2);

        int acidInitialStrength = acid.getStrength();
        int otherInitialStrength = nondissolvable.getStrength();

        acid.dissolveIfPossible(1, 2);

        assertThat(acid.getStrength()).isEqualTo(acidInitialStrength);
        assertThat(nondissolvable.getStrength())
            .isEqualTo(otherInitialStrength);
    }

    /**
     * Tests that the first and second getWorld() != null conditions
     * in the act() method can be false when the acid particle is removed.
     */
    public void testActWithRemovedAcid()
    {
        world.addObject(acid, 1, 1);
        world.removeObject(acid);

        acid.act();

        assertThat(world.getObjects(Acid.class)).doesNotContain(acid);
        assertThat(acid.getStrength()).isEqualTo(100);
    }

    /**
     * Tests that dissolveIfPossible returns early when
     * y is greater than or equal to getWorld().getHeight().
     */
    public void testDissolveYOutOfBounds()
    {
        world.addObject(acid, 1, 1);
        int initialStrength = acid.getStrength();
        acid.dissolveIfPossible(1, 3);

        assertThat(acid.getStrength()).isEqualTo(initialStrength);
    }
}
