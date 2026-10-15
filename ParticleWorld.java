import student.micro.*;

//-------------------------------------------------------------------------
/**
 *  Represents a world where particles interact.
 *
 *  @author Nick Rini
 *  @version 2024.10.30
 */
public class ParticleWorld
    extends ParticleWorldBase
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new world with the default layout.
     */
    public ParticleWorld()
    {
        this(0);
    }

    // ----------------------------------------------------------
    /**
     * Creates a new world with a specific layout.
     * @param layout A number 0-2 specifying the layout to use.
     */
    public ParticleWorld(int layout)
    {
        super(layout);
    }

    // ----------------------------------------------------------
    /**
     * Creates a new, empty world with the specified dimensions.
     * @param width  The width of the new world.
     * @param height The height of the new world.
     */
    public ParticleWorld(int width, int height)
    {
        super(width, height);
    }
}
