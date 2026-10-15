//-------------------------------------------------------------------------
import java.awt.Color;
/**
 *  The Particle class represents a base type for all particles.
 *  It has shared properties such as motion, strength, density,
 *  and dissolvability behavior.
 *
 *  Particles move with falling and flow behaviors and interact with particles
 *  based on density.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class Particle
    extends ParticleBase
{
    //~ Fields ................................................................
    private double velocity;
    private double acceleration;
    private int strength;
    private double density;
    private boolean willDissolve;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Particle object and
     * default values for motion and strength.
     *
     * @param color The Color of the particle
     * @param willDissolve Whether the particle is dissolvable in acid
     * @param density The density of the particle (in g/cm^3)
     */
    public Particle(Color color, boolean willDissolve, double density)
    {
        super(color);

        this.velocity = 0.0;
        this.acceleration = 1.0;
        this.strength = 100;
        this.density = density;
        this.willDissolve = willDissolve;
    }

    //~ Methods ...............................................................
    /**
     * Gets the particle's density.
     *
     * @return the density of this particle
     */
    public double getDensity()
    {
        return density;
    }

    /**
     * Gets the particle's current velocity.
     *
     * @return the velocity of this particle.
     */
    public double getVelocity()
    {
        return velocity;
    }

    /**
     * Gets the particle's downward acceleration due to gravity.
     *
     * @return the acceleration of this particle.
     */
    public double getAcceleration()
    {
        return acceleration;
    }

    /**
     * Gets the particle's current strength.
     *
     * @return the strength of this particle.
     */
    public int getStrength()
    {
        return strength;
    }

    /**
     * Checks whether this particle can be dissolved in acid.
     *
     * @return true if this particle will dissolve, false otherwise.
     */
    public boolean willDissolve()
    {
        return willDissolve;
    }

    /**
     * Reduces the particle's strength by 1. If strength is zero,
     * the particle is removed from the world.
     */
    public void weaken()
    {
        strength--;
        if (strength <= 0)
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * Determines whether this particle is in free-fall.
     * A particle is considered falling if there is neither a particle or the
     * bottom of the world directly below it.
     *
     * @return true if falling, false otherwise
     */
    public boolean isFalling()
    {
        int y = getY();
        int worldHeight = getWorld().getHeight();

        if (y >= worldHeight - 1)
        {
            return false;
        }

        return getOneObjectAtOffset(0, 1, Particle.class) == null;
    }

    /**
     * Updates the particle's position according to its motion.
     * Increases velocity by acceleration, and moves the particle
     * downward up to a number of cells equal to the current velocity.
     * Stops early if an obstacle is encountered.
     */
    public void fall()
    {
        velocity = velocity + acceleration;
        int fallDistance = (int) velocity;

        for (int i = 0; i < fallDistance; i++)
        {
            if (isFalling())
            {
                setLocation(getX(), getY() + 1);
            }
            else
            {
                velocity = 0.0;
                break;
            }
        }

        if (!isFalling())
        {
            velocity = 0.0;
        }
    }

    /**
     * Swaps this particle with another at a given position
     * if the other particle is of lower density or the position is empty.
     *
     * @param x the x-coordinate to swap with.
     * @param y the y-coordinate to swap with.
     * @return true if a swap happened, false otherwise.
     */
    public boolean swapPlacesIfPossible(int x, int y)
    {
        if (x < 0 || x >= getWorld().getWidth() || y < 0 || y >= getWorld()
            .getHeight())
        {
            return false;
        }

        Particle otherParticle = (Particle) getOneObjectAtOffset(x - getX(),
            y - getY(), Particle.class);

        if (otherParticle == null)
        {
            setLocation(x, y);
            return true;
        }
        else if (this.density > otherParticle.getDensity())
        {
            otherParticle.setLocation(getX(), getY());
            setLocation(x, y);
            return true;
        }

        return false;
    }

    /**
     * Moves this particle by flowing to a lower nearby position.
     * First it tries directly below, then down-left, then down-right.
     *
     * @return true if the particle is moved to a new location, false otherwise.
     */
    public boolean flow()
    {
        int x = getX();
        int y = getY();

        return swapPlacesIfPossible(x, y + 1)
            || swapPlacesIfPossible(x - 1, y + 1)
            || swapPlacesIfPossible(x + 1, y + 1);
    }

    /**
     * Executes one act/step for this particle.
     * If it is falling, use gravity, or if not, then flow.
     */
    public void act()
    {
        if (isFalling())
        {
            fall();
        }
        else
        {
            flow();
        }
    }
}
