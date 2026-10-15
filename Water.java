//-------------------------------------------------------------------------
/**
 * The Water class represents a water particle in the simulation.
 *
 * Water particles are a type of Fluid, colored cadet blue, not dissolvable,
 * and have a density of 1.0 g/cm^3.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class Water extends Fluid
{

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Water object.
     */
    public Water()
    {
        super(CADET_BLUE, false, 1.0);
    }

}
