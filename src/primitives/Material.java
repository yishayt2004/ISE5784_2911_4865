package primitives;

public class Material
{
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public int nShininess = 0;
    /**
     * Transmission (refraction) coefficient
     */
    public Double3 kT = Double3.ZERO;

    /**
     * Reflection coefficient
     */
    public Double3 kR = Double3.ZERO;


    //setters
    public Material setKt(Double3 kT)
    {
        this.kT = kT;

        return this;
    }

    /**
     * Sets the transmission coefficient (kT) of the material.
     *
     * @param kT The transmission coefficient as a double value.
     * @return The material instance with the updated transmission coefficient.
     */
    public Material setKt(double kT)
    {
        this.kT = new Double3(kT);

        return this;
    }

    /**
     * Sets the reflection coefficient (kR) of the material.
     *
     * @param kR The reflection coefficient as a Double3 object.
     * @return The material instance with the updated reflection coefficient.
     */
    public Material setKr(Double3 kR)
    {
        this.kR = kR;

        return this;
    }

    /**
     * Sets the reflection coefficient (kR) of the material.
     *
     * @param kR The reflection coefficient as a double value.
     * @return The material instance with the updated reflection coefficient.
     */
    public Material setKr(double kR)
    {
        this.kR = new Double3(kR);

        return this;
    }
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }
    public Material setkD(Double kD) {
        this.kD = new Double3(kD);
        return this;
    }
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }
    public Material setkS(Double kS) {
        this.kS = new Double3(kS);
        return this;
    }
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }



}
