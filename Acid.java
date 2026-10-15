//-------------------------------------------------------------------------
/**
 * The Acid class represents a corrosive liquid particle in the simulation.
 *
 * Acid behaves like a fluid but can also dissolve other particles that are
 * marked as dissolvable.
 * It weakens both itself and the dissolved particle.
 *
 * Acid is colored as green, is not dissolvable itself, and has a density of
 * 1.0 g/cm^3.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class Acid
    extends Fluid
{

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Acid object using the color, willDissolve,
     * and density parameters.
     */
    public Acid()
    {
        super(GREEN, false, 1.0);
    }

    //~ Methods ...............................................................
    /**
     * Attempts to dissolve a particle at the specified coordinates.
     *
     * If the location is valid and contains a particle that will dissolve,
     * both this particle and the target are weakened.
     *
     * @param x the x-coordinate to check.
     * @param y the y-coordinate to check.

     */
    public void dissolveIfPossible(int x, int y)
    {
        if (x >= 0 && x < getWorld().getWidth() && y >= 0 &&
            y < getWorld().getHeight())
        {
            int dx = x - getX();
            int dy = y - getY();

            Particle otherParticle = (Particle) getOneObjectAtOffset(dx, dy
                , Particle.class);

            if (otherParticle != null && otherParticle.willDissolve())
            {
                otherParticle.weaken();
                this.weaken();
            }
        }
    }

    /**
     * Executes one simulation step for this acid particle.
     *
     * Tries to dissolve particles adjacent (left, right, above, below),
     * and if they are still present in the world, performs the normal fluid
     * functions.
     */
    @Override
    public void act()
    {

        if (getWorld() != null)
        {
            dissolveIfPossible(getX() - 1, getY());
        }

        if (getWorld() != null)
        {
            dissolveIfPossible(getX() + 1, getY());
        }

        if (getWorld() != null)
        {
            dissolveIfPossible(getX(), getY() - 1);
        }

        if (getWorld() != null)
        {
            dissolveIfPossible(getX(), getY() + 1);
        }

        if (getWorld() != null)
        {
            super.act();
        }
    }
}
