import student.micro.GreenfootApplication;

//-------------------------------------------------------------------------
/**
 *  Launches the Falling Sand Simulator.
 *
 *  @author Nick Rini
 *  @version 2024.10.30
 */
public class Application
    extends GreenfootApplication
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the simulator application.
     */
    public Application()
    {
        setProjectName("Falling Sand Simulator");
        setInitialWorldClassName("ParticleWorld");
        setInitialSpeed(22);
    }
}
