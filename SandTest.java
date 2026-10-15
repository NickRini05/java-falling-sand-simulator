import student.micro.*;
import static org.assertj.core.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 * This test checks the constructor to ensure the
 * correct color, dissolvability, and density of sand particles.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class SandTest
    extends TestCase
{

    //~ Methods ...............................................................

    // ----------------------------------------------------------

    /**
     * Tests that the sand constructor sets correct defaults.
     */
    public void testConstructor()
    {
        Sand sand = new Sand();

        assertThat(sand.getColor()).isEqualTo(Sand.KHAKI);
        assertThat(sand.willDissolve()).isTrue();
        assertThat(sand.getDensity()).isEqualTo(2.5);
    }
}
