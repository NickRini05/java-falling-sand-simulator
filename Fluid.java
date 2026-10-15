import java.awt.Color;
//-------------------------------------------------------------------------
/**
 * The Fluid class represents a liquid particle.
 *
 * It extends Particle and overrides the flow behavior to include
 * left and right movement when vertical movement is not possible.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class Fluid
    extends Particle
{

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Fluid object using the color, willDissolve,
     * and density parameters.
     *
     * @param color The color of the fluid particle.
     * @param willDissolve Whether the particle is dissolvable in acid.
     * @param density The density of the fluid.
     */
    public Fluid(Color color, boolean willDissolve, double density)
    {
        super(color, willDissolve, density);
    }

    //~ Methods ...............................................................
    /**
     * Attempts to move the fluid particle.
     * First attempts the standard downward/diagonal flow from the superclass.
     * If that fails, it tries to move left or right.
     *
     * @return true if the particle moved, false otherwise
     */
    @Override
    public boolean flow()
    {
        int x = getX();
        int y = getY();

        return super.flow()
            || swapPlacesIfPossible(x - 1, y)
            || swapPlacesIfPossible(x + 1, y);
    }
}
