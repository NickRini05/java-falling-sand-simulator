//-------------------------------------------------------------------------
/**
 * Steel acts as a solid barrier meaning it does not fall or flow,
 * and it is always fixed to the background. It can be dissolved in acid.
 *
 *  @author Nick Rini
 *  @version 2025.04.06
 */
public class Steel extends Particle
{

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Steel object.
     */
    public Steel()
    {
        super(GRAY, true, 7.87);
    }

    //~ Methods ...............................................................

    /**
     * Steel particles never fall, so this always returns false.
     *
     * @return false always
     */
    @Override
    public boolean isFalling()
    {
        return false;
    }

    /**
     * Steel particles never flow, so this always returns false.
     *
     * @return false always
     */
    @Override
    public boolean flow()
    {
        return false;
    }
}
