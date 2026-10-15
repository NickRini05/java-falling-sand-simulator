//-------------------------------------------------------------------------
/**
 * The Sand class represents a sand particle in the simulation.
 *
 * The sand particles are dissolvable, colored khaki,
 * and have a density of 2.5 g/cm^3.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class Sand extends Particle
{

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Sand object.
     */
    public Sand()
    {
        super(KHAKI, true, 2.5);
    }

}
