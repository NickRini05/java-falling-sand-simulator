import student.micro.*;
import static org.assertj.core.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 *  Tests to ensure that the water constructor sets the correct defaults for
 *  the water particles.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class WaterTest
    extends TestCase
{

    //~ Methods ...............................................................

    // ----------------------------------------------------------

    /**
     * Tests that the water constructor sets the correct defaults.
     */
    public void testConstructor()
    {
        Water water = new Water();

        assertThat(water.getColor()).isEqualTo(Water.CADET_BLUE);
        assertThat(water.willDissolve()).isFalse();
        assertThat(water.getDensity()).isEqualTo(1.0);
    }
}
