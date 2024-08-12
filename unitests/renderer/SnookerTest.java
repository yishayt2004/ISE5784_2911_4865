package renderer;

import org.junit.jupiter.api.Test;
import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

/**
 * Test rendering a basic image
 */
public class SnookerTest {
    /**
     * Scene of the tests
     */
    private final Scene scene = new Scene("Test scene")  ;
    Vector vTo = new Vector(-1, -30, 180).normalize();
    Vector vUpInitial = new Vector(0, 1, 0);  // וקטור בכיוון ה-Y
    Vector vRight = vTo.crossProduct(vUpInitial).normalize();
    Vector vUp = vRight.crossProduct(vTo).normalize();

    /**
     * Camera builder of the tests
     */
    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(new Point(200, 200, 100))
            .setDirection(new Vector(-1, -30, 180).normalize(), vUp)
            .setVpDistance(500)
            .setVpSize(800, 600);

    /**
     * Produce a scene with basic 3D model and render it into a png image with a
     * grid
     */
    @Test
    public void snookerrrTest() {

        Material material1 = new Material().setkD(0.0).setkS(0.7).setnShininess(500).setKt(0.0).setKr(0.0);
        Color color1 = new Color(0, 0, 0);

        Material material2 = new Material().setkD(0.06895158137194812).setkS(0.7).setnShininess(500).setKt(0.0).setKr(0.0);
        Color color2 = new Color(0, 51, 0);

        Material material3 = new Material().setkD(0.033753237687051296).setkS(0.7).setnShininess(500).setKt(0.0).setKr(0.0);
        Color color3 = new Color(22, 3, 0);

        scene.geometries.add(
                new Triangle(new Point(-41.679039001464844, -24.331174850463867, 1.0635494618327357e-06),
                        new Point(-41.17927169799805, -24.830944061279297, -15.100517272949219),
                        new Point(-41.17927169799805, -24.830944061279297, 1.0853950698219705e-06)).
                        setMaterial(new Material().setkD(0.0).setkS(0.5).setnShininess(500).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(0, 0, 0)),

                new Triangle(new Point(-41.679039001464844, 0.0, 0.0),
                        new Point(-41.679039001464844, -24.331174850463867, -15.100517272949219),
                        new Point(-41.679039001464844, -24.331174850463867, 1.0635494618327357e-06)).
                        setMaterial(new Material().setkD(0.0).setkS(0.5).setnShininess(500).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(0, 0, 0)),

                new Triangle(
                        new Point(-41.679039001464844, 24.331174850463867, -1.0635494618327357e-06),
                        new Point(-41.679039001464844, -6.600646429433255e-07, -15.100518226623535),
                        new Point(-41.679039001464844, 0.0, 0.0))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-41.17927169799805, 24.830944061279297, -1.0853950698219705e-06),
                        new Point(-41.679039001464844, 24.331174850463867, -15.100519180297852),
                        new Point(-41.679039001464844, 24.331174850463867, -1.0635494618327357e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-40.679500579833984, 25.330713272094727, -1.1072406778112054e-06),
                        new Point(-41.17927169799805, 24.830944061279297, -15.100519180297852),
                        new Point(-41.17927169799805, 24.830944061279297, -1.0853950698219705e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(0.0, 25.330713272094727, -1.1072406778112054e-06),
                        new Point(-40.679500579833984, 25.330713272094727, -15.100519180297852),
                        new Point(-40.679500579833984, 25.330713272094727, -1.1072406778112054e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(40.679500579833984, 25.330713272094727, -1.1072406778112054e-06),
                        new Point(0.0, 25.330713272094727, -15.100519180297852),
                        new Point(0.0, 25.330713272094727, -1.1072406778112054e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(41.17927169799805, 24.830944061279297, -1.0853950698219705e-06),
                        new Point(40.679500579833984, 25.330713272094727, -15.100519180297852),
                        new Point(40.679500579833984, 25.330713272094727, -1.1072406778112054e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(41.679039001464844, 24.331174850463867, -1.0635494618327357e-06),
                        new Point(41.17927169799805, 24.830944061279297, -15.100519180297852),
                        new Point(41.17927169799805, 24.830944061279297, -1.0853950698219705e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(41.679039001464844, 24.331174850463867, -1.0635494618327357e-06),
                        new Point(41.679039001464844, -6.600646429433255e-07, -15.100518226623535),
                        new Point(41.679039001464844, 24.331174850463867, -15.100519180297852))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(41.679039001464844, 0.0, 0.0),
                        new Point(41.679039001464844, -24.331174850463867, -15.100517272949219),
                        new Point(41.679039001464844, -6.600646429433255e-07, -15.100518226623535))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(41.17927169799805, -24.830944061279297, 1.0853950698219705e-06),
                        new Point(41.679039001464844, -24.331174850463867, -15.100517272949219),
                        new Point(41.679039001464844, -24.331174850463867, 1.0635494618327357e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(40.679500579833984, -25.330713272094727, 1.1072406778112054e-06),
                        new Point(41.17927169799805, -24.830944061279297, -15.100517272949219),
                        new Point(41.17927169799805, -24.830944061279297, 1.0853950698219705e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(0.0, -25.330713272094727, 1.1072406778112054e-06),
                        new Point(40.679500579833984, -25.330713272094727, -15.100517272949219),
                        new Point(40.679500579833984, -25.330713272094727, 1.1072406778112054e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-40.679500579833984, -25.330713272094727, 1.1072406778112054e-06),
                        new Point(0.0, -25.330713272094727, -15.100517272949219),
                        new Point(0.0, -25.330713272094727, 1.1072406778112054e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-41.17927169799805, -24.830944061279297, 1.0853950698219705e-06),
                        new Point(-40.679500579833984, -25.330713272094727, -15.100517272949219),
                        new Point(-40.679500579833984, -25.330713272094727, 1.1072406778112054e-06))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-41.679039001464844, -24.331174850463867, 1.0635494618327357e-06),
                        new Point(-41.679039001464844, -24.331174850463867, -15.100517272949219),
                        new Point(-41.17927169799805, -24.830944061279297, -15.100517272949219))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-41.679039001464844, 0.0, 0.0),
                        new Point(-41.679039001464844, -6.600646429433255e-07, -15.100518226623535),
                        new Point(-41.679039001464844, -24.331174850463867, -15.100517272949219))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-41.679039001464844, 24.331174850463867, -1.0635494618327357e-06),
                        new Point(-41.679039001464844, 24.331174850463867, -15.100519180297852),
                        new Point(-41.679039001464844, -6.600646429433255e-07, -15.100518226623535))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-41.17927169799805, 24.830944061279297, -1.0853950698219705e-06),
                        new Point(-41.17927169799805, 24.830944061279297, -15.100519180297852),
                        new Point(-41.679039001464844, 24.331174850463867, -15.100519180297852))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-40.679500579833984, 25.330713272094727, -1.1072406778112054e-06),
                        new Point(-40.679500579833984, 25.330713272094727, -15.100519180297852),
                        new Point(-41.17927169799805, 24.830944061279297, -15.100519180297852))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(0.0, 25.330713272094727, -1.1072406778112054e-06),
                        new Point(0.0, 25.330713272094727, -15.100519180297852),
                        new Point(-40.679500579833984, 25.330713272094727, -15.100519180297852))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(40.679500579833984, 25.330713272094727, -1.1072406778112054e-06),
                        new Point(40.679500579833984, 25.330713272094727, -15.100519180297852),
                        new Point(0.0, 25.330713272094727, -15.100519180297852))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(41.17927169799805, 24.830944061279297, -1.0853950698219705e-06),
                        new Point(41.17927169799805, 24.830944061279297, -15.100519180297852),
                        new Point(40.679500579833984, 25.330713272094727, -15.100519180297852))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(41.679039001464844, 24.331174850463867, -1.0635494618327357e-06),
                        new Point(41.679039001464844, 24.331174850463867, -15.100519180297852),
                        new Point(41.17927169799805, 24.830944061279297, -15.100519180297852))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(41.679039001464844, 24.331174850463867, -1.0635494618327357e-06),
                        new Point(41.679039001464844, 0.0, 0.0),
                        new Point(41.679039001464844, -6.600646429433255e-07, -15.100518226623535))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(41.679039001464844, 0.0, 0.0),
                        new Point(41.679039001464844, -24.331174850463867, 1.0635494618327357e-06),
                        new Point(41.679039001464844, -24.331174850463867, -15.100517272949219))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(41.17927169799805, -24.830944061279297, 1.0853950698219705e-06),
                        new Point(41.17927169799805, -24.830944061279297, -15.100517272949219),
                        new Point(41.679039001464844, -24.331174850463867, -15.100517272949219))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(40.679500579833984, -25.330713272094727, 1.1072406778112054e-06),
                        new Point(40.679500579833984, -25.330713272094727, -15.100517272949219),
                        new Point(41.17927169799805, -24.830944061279297, -15.100517272949219))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(0.0, -25.330713272094727, 1.1072406778112054e-06),
                        new Point(0.0, -25.330713272094727, -15.100517272949219),
                        new Point(40.679500579833984, -25.330713272094727, -15.100517272949219))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-40.679500579833984, -25.330713272094727, 1.1072406778112054e-06),
                        new Point(-40.679500579833984, -25.330713272094727, -15.100517272949219),
                        new Point(0.0, -25.330713272094727, -15.100517272949219))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-41.17927169799805, -24.830944061279297, 1.0853950698219705e-06),
                        new Point(-41.17927169799805, -24.830944061279297, -15.100517272949219),
                        new Point(-40.679500579833984, -25.330713272094727, -15.100517272949219))
                        .setMaterial(material1)
                        .setEmission(color1),

                new Triangle(
                        new Point(-41.678348541259766, 27.742141723632812, 11.467705726623535),
                        new Point(-44.0904655456543, 25.33002281188965, 11.467705726623535),
                        new Point(-41.678348541259766, -27.742141723632812, 11.467707633972168))
                        .setMaterial(new Material().setkD(0.06895158137194812).setkS(0.5).setnShininess(500).setKt(0.0).setKt(0.0))
                        .setEmission(new Color(0, 51, 0)),

                new Triangle(
                        new Point(-44.0904655456543, 25.33002281188965, 11.467705726623535),
                        new Point(-44.0904655456543, -25.33002281188965, 11.467707633972168),
                        new Point(-41.678348541259766, -27.742141723632812, 11.467707633972168))
                        .setMaterial(new Material().setkD(0.06895158137194812).setkS(0.5).setnShininess(500).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(0, 51, 0)),

                new Triangle(
                        new Point(-41.678348541259766, -27.742141723632812, 11.467707633972168),
                        new Point(41.678348541259766, -27.742141723632812, 11.467707633972168),
                        new Point(41.678348541259766, 27.742141723632812, 11.467705726623535))
                        .setMaterial(new Material().setkD(0.06895158137194812).setkS(0.5).setnShininess(500).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(0, 51, 0)),

                new Triangle(
                        new Point(41.678348541259766, -27.742141723632812, 11.467707633972168),
                        new Point(44.0904655456543, -25.33002281188965, 11.467707633972168),
                        new Point(41.678348541259766, 27.742141723632812, 11.467705726623535))
                        .setMaterial(new Material().setkD(0.06895158137194812).setkS(0.5).setnShininess(500).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(0, 51, 0)),

                new Triangle(
                        new Point(44.0904655456543, -25.33002281188965, 11.467707633972168),
                        new Point(44.0904655456543, 25.33002281188965, 11.467705726623535),
                        new Point(41.678348541259766, 27.742141723632812, 11.467705726623535))
                        .setMaterial(new Material().setkD(0.06895158137194812).setkS(0.5).setnShininess(500).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(0, 51, 0)),

                new Triangle(
                        new Point(41.678348541259766, 27.742141723632812, 11.467705726623535),
                        new Point(-41.678348541259766, 27.742141723632812, 11.467705726623535),
                        new Point(-41.678348541259766, -27.742141723632812, 11.467707633972168))
                        .setMaterial(new Material().setkD(0.06895158137194812).setkS(0.5).setnShininess(500).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(0, 51, 0)),

                new Triangle(
                        new Point(-42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(-40.679500579833984, -25.330713272094727, 1.1072406778112054e-06),
                        new Point(0.0, -25.330713272094727, 1.1072406778112054e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(-42.67719268798828, -30.15357208251953, 13.805248260498047),
                        new Point(-42.67719268798828, -30.15357208251953, 1.3180545010982314e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-46.501895904541016, -26.328868865966797, 13.805248260498047),
                        new Point(-42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(-42.67719268798828, -30.15357208251953, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(-41.679039001464844, -24.331174850463867, 1.0635494618327357e-06),
                        new Point(-41.17927169799805, -24.830944061279297, 1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(-41.679039001464844, 24.331174850463867, -1.0635494618327357e-06),
                        new Point(-41.679039001464844, 0.0, 0.0))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(-40.679500579833984, 25.330713272094727, -1.1072406778112054e-06),
                        new Point(-41.17927169799805, 24.830944061279297, -1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(40.679500579833984, 25.330713272094727, -1.1072406778112054e-06),
                        new Point(0.0, 25.330713272094727, -1.1072406778112054e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(41.679039001464844, 24.331174850463867, -1.0635494618327357e-06),
                        new Point(41.17927169799805, 24.830944061279297, -1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(41.679039001464844, -24.331174850463867, 1.0635494618327357e-06),
                        new Point(41.17927169799805, -24.830944061279297, 1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(40.679500579833984, -25.330713272094727, 1.1072406778112054e-06),
                        new Point(41.17927169799805, -24.830944061279297, 1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(46.501895904541016, 26.328868865966797, 13.805246353149414),
                        new Point(46.501895904541016, -26.328868865966797, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(42.67719268798828, -30.15357208251953, 13.805248260498047),
                        new Point(42.67719268798828, -30.15357208251953, 1.3180545010982314e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(46.501895904541016, 26.328868865966797, 13.805246353149414),
                        new Point(44.0904655456543, -25.33002281188965, 13.805248260498047),
                        new Point(46.501895904541016, -26.328868865966797, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(46.501895904541016, -26.328868865966797, 13.805248260498047),
                        new Point(41.678348541259766, -27.742141723632812, 13.805248260498047),
                        new Point(42.67719268798828, -30.15357208251953, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(44.0904655456543, -25.33002281188965, 13.805248260498047),
                        new Point(41.678348541259766, -27.742141723632812, 11.467707633972168),
                        new Point(41.678348541259766, -27.742141723632812, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(44.0904655456543, 25.33002281188965, 13.805246353149414),
                        new Point(44.0904655456543, -25.33002281188965, 11.467707633972168),
                        new Point(44.0904655456543, -25.33002281188965, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(41.678348541259766, 27.742141723632812, 13.805246353149414),
                        new Point(44.0904655456543, 25.33002281188965, 11.467705726623535),
                        new Point(44.0904655456543, 25.33002281188965, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-41.678348541259766, 27.742141723632812, 13.805246353149414),
                        new Point(41.678348541259766, 27.742141723632812, 11.467705726623535),
                        new Point(41.678348541259766, 27.742141723632812, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-42.67719268798828, 30.15357208251953, 13.805246353149414),
                        new Point(41.678348541259766, 27.742141723632812, 13.805246353149414),
                        new Point(42.67719268798828, 30.15357208251953, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(46.501895904541016, 26.328868865966797, 13.805246353149414),
                        new Point(41.678348541259766, 27.742141723632812, 13.805246353149414),
                        new Point(44.0904655456543, 25.33002281188965, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-46.501895904541016, 26.328868865966797, 13.805248260498047),
                        new Point(-41.678348541259766, 27.742141723632812, 13.805246353149414),
                        new Point(-42.67719268798828, 30.15357208251953, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-44.0904655456543, 25.33002281188965, 13.805246353149414),
                        new Point(-41.678348541259766, 27.742141723632812, 11.467705726623535),
                        new Point(-41.678348541259766, 27.742141723632812, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-46.501895904541016, -26.328868865966797, 13.805248260498047),
                        new Point(-44.0904655456543, 25.33002281188965, 13.805246353149414),
                        new Point(-46.501895904541016, 26.328868865966797, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-44.0904655456543, -25.33002281188965, 13.805248260498047),
                        new Point(-44.0904655456543, 25.33002281188965, 11.467705726623535),
                        new Point(-44.0904655456543, 25.33002281188965, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(-41.678348541259766, -27.742141723632812, 13.805248260498047),
                        new Point(-44.0904655456543, -25.33002281188965, 11.467707633972168),
                        new Point(-44.0904655456543, -25.33002281188965, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(41.678348541259766, -27.742141723632812, 13.805248260498047),
                        new Point(-41.678348541259766, -27.742141723632812, 11.467707633972168),
                        new Point(-41.678348541259766, -27.742141723632812, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(
                        new Point(42.67719268798828, -30.15357208251953, 13.805248260498047),
                        new Point(-41.678348541259766, -27.742141723632812, 13.805248260498047),
                        new Point(-42.67719268798828, -30.15357208251953, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, -26.328868865966797, 13.805248260498047),
                        new Point(-41.678348541259766, -27.742141723632812, 13.805248260498047),
                        new Point(-44.0904655456543, -25.33002281188965, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(-46.501895904541016, 26.328868865966797, 13.805246353149414),
                        new Point(-46.501895904541016, 26.328868865966797, -1.1508714123920072e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(-42.67719268798828, 30.15357208251953, 13.805246353149414),
                        new Point(-42.67719268798828, 30.15357208251953, -1.3180545010982314e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(42.67719268798828, 30.15357208251953, 13.805246353149414),
                        new Point(42.67719268798828, 30.15357208251953, -1.3180545010982314e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(46.501895904541016, 26.328868865966797, 13.805246353149414),
                        new Point(46.501895904541016, 26.328868865966797, -1.1508714123920072e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(40.679500579833984, -25.330713272094727, 1.1072406778112054e-06),
                        new Point(42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(0.0, -25.330713272094727, 1.1072406778112054e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(-42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(0.0, -25.330713272094727, 1.1072406778112054e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(42.67719268798828, -30.15357208251953, 13.805248260498047),
                        new Point(-42.67719268798828, -30.15357208251953, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, -26.328868865966797, 13.805248260498047),
                        new Point(-46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(-42.67719268798828, -30.15357208251953, 1.3180545010982314e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-40.679500579833984, -25.330713272094727, 1.1072406778112054e-06),
                        new Point(-42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(-41.17927169799805, -24.830944061279297, 1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),


                new Triangle(new Point(-42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(-46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(-41.17927169799805, -24.830944061279297, 1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-41.679039001464844, -24.331174850463867, 1.0635494618327357e-06),
                        new Point(-46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(-41.679039001464844, 0.0, 0.0))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(-46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(-41.679039001464844, 0.0, 0.0))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-41.679039001464844, 24.331174850463867, -1.0635494618327357e-06),
                        new Point(-46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(-41.17927169799805, 24.830944061279297, -1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(-42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(-41.17927169799805, 24.830944061279297, -1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-40.679500579833984, 25.330713272094727, -1.1072406778112054e-06),
                        new Point(-42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(0.0, 25.330713272094727, -1.1072406778112054e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(0.0, 25.330713272094727, -1.1072406778112054e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(40.679500579833984, 25.330713272094727, -1.1072406778112054e-06),
                        new Point(42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(41.17927169799805, 24.830944061279297, -1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(41.17927169799805, 24.830944061279297, -1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(41.679039001464844, 24.331174850463867, -1.0635494618327357e-06),
                        new Point(46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(41.679039001464844, 0.0, 0.0))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(41.679039001464844, 0.0, 0.0))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(41.679039001464844, -24.331174850463867, 1.0635494618327357e-06),
                        new Point(46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(41.17927169799805, -24.830944061279297, 1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(42.67719268798828, -30.15357208251953, 1.3180545010982314e-06),
                        new Point(41.17927169799805, -24.830944061279297, 1.0853950698219705e-06))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(46.501895904541016, 26.328868865966797, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(46.501895904541016, -26.328868865966797, 13.805248260498047),
                        new Point(42.67719268798828, -30.15357208251953, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(46.501895904541016, 26.328868865966797, 13.805246353149414),
                        new Point(44.0904655456543, 25.33002281188965, 13.805246353149414),
                        new Point(44.0904655456543, -25.33002281188965, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(46.501895904541016, -26.328868865966797, 13.805248260498047),
                        new Point(44.0904655456543, -25.33002281188965, 13.805248260498047),
                        new Point(41.678348541259766, -27.742141723632812, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(44.0904655456543, -25.33002281188965, 13.805248260498047),
                        new Point(44.0904655456543, -25.33002281188965, 11.467707633972168),
                        new Point(41.678348541259766, -27.742141723632812, 11.467707633972168))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(44.0904655456543, 25.33002281188965, 13.805246353149414),
                        new Point(44.0904655456543, 25.33002281188965, 11.467705726623535),
                        new Point(44.0904655456543, -25.33002281188965, 11.467707633972168))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(41.678348541259766, 27.742141723632812, 13.805246353149414),
                        new Point(41.678348541259766, 27.742141723632812, 11.467705726623535),
                        new Point(44.0904655456543, 25.33002281188965, 11.467705726623535))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-41.678348541259766, 27.742141723632812, 13.805246353149414),
                        new Point(-41.678348541259766, 27.742141723632812, 11.467705726623535),
                        new Point(41.678348541259766, 27.742141723632812, 11.467705726623535))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-42.67719268798828, 30.15357208251953, 13.805246353149414),
                        new Point(-41.678348541259766, 27.742141723632812, 13.805246353149414),
                        new Point(41.678348541259766, 27.742141723632812, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(46.501895904541016, 26.328868865966797, 13.805246353149414),
                        new Point(42.67719268798828, 30.15357208251953, 13.805246353149414),
                        new Point(41.678348541259766, 27.742141723632812, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, 26.328868865966797, 13.805246353149414),
                        new Point(-44.0904655456543, 25.33002281188965, 13.805246353149414),
                        new Point(-41.678348541259766, 27.742141723632812, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-44.0904655456543, 25.33002281188965, 13.805246353149414),
                        new Point(-44.0904655456543, 25.33002281188965, 11.467705726623535),
                        new Point(-41.678348541259766, 27.742141723632812, 11.467705726623535))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, -26.328868865966797, 13.805248260498047),
                        new Point(-44.0904655456543, -25.33002281188965, 13.805248260498047),
                        new Point(-44.0904655456543, 25.33002281188965, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-44.0904655456543, -25.33002281188965, 13.805248260498047),
                        new Point(-44.0904655456543, -25.33002281188965, 11.467707633972168),
                        new Point(-44.0904655456543, 25.33002281188965, 11.467705726623535))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-41.678348541259766, -27.742141723632812, 13.805248260498047),
                        new Point(-41.678348541259766, -27.742141723632812, 11.467707633972168),
                        new Point(-44.0904655456543, -25.33002281188965, 11.467707633972168))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(41.678348541259766, -27.742141723632812, 13.805248260498047),
                        new Point(41.678348541259766, -27.742141723632812, 11.467707633972168),
                        new Point(-41.678348541259766, -27.742141723632812, 11.467707633972168))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(42.67719268798828, -30.15357208251953, 13.805248260498047),
                        new Point(41.678348541259766, -27.742141723632812, 13.805248260498047),
                        new Point(-41.678348541259766, -27.742141723632812, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, -26.328868865966797, 13.805248260498047),
                        new Point(-42.67719268798828, -30.15357208251953, 13.805248260498047),
                        new Point(-41.678348541259766, -27.742141723632812, 13.805248260498047))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, -26.328868865966797, 1.1508714123920072e-06),
                        new Point(-46.501895904541016, -26.328868865966797, 13.805248260498047),
                        new Point(-46.501895904541016, 26.328868865966797, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-46.501895904541016, 26.328868865966797, -1.1508714123920072e-06),
                        new Point(-46.501895904541016, 26.328868865966797, 13.805246353149414),
                        new Point(-42.67719268798828, 30.15357208251953, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(-42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(-42.67719268798828, 30.15357208251953, 13.805246353149414),
                        new Point(42.67719268798828, 30.15357208251953, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Triangle(new Point(42.67719268798828, 30.15357208251953, -1.3180545010982314e-06),
                        new Point(42.67719268798828, 30.15357208251953, 13.805246353149414),
                        new Point(46.501895904541016, 26.328868865966797, 13.805246353149414))
                        .setMaterial(material3)
                        .setEmission(color3),

                new Sphere(3, new Point(-19.56353623657227, -0.1187283918261528, 14.5))              // The white ball
                        .setMaterial(new Material().setkD(0.4).setkS(0.8).setnShininess(30).setKr(0.6))
                        .setEmission(new Color(100, 100, 100)), // White


                new Sphere(3, new Point(15.436763763427734, -0.1187283918261528, 14.5))              // The red ball
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(255, 0, 0)), // Red

                new Sphere(3, new Point(17.8597354888916, 1.298505425453186, 14.5))              // The yellow ball
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(255, 255, 0)), // Yellow

                new Sphere(3, new Point(17.900432586669922, -1.4634398221969604, 14.5))              // The blue ball
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(0, 0, 255)), // Blue

                new Sphere(3, new Point(20.222408294677734, -0.1248767152428627, 14.5))              // The green ball
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(0, 255, 0)), // Green

                new Sphere(3, new Point(20.40655517578125, 2.4198384284973145, 14.5))              // The orange ball
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(255, 165, 0)), // Orange

                new Sphere(3, new Point(20.3784236907959, -2.981062650680542, 14.5))              // The purple ball
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(128, 0, 128)), // Purple

                new Sphere(3, new Point(23.13673973083496, -4.2078351974487305, 14.5))              // The brown ball
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(165, 42, 42)), // Brown

                new Sphere(3, new Point(22.750577926635742, -1.4115896224975586, 14.5))              // The pink ball
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(255, 105, 180)), // Pink

                new Sphere(3, new Point(22.770854949951172, 1.1483960151672363, 14.5))              // The black ball
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(0, 0, 0)), // Black

                new Sphere(3, new Point(22.812776565551758, 3.890660285949707, 14.5))              // The grey ball
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.3).setKr(0.0))
                        .setEmission(new Color(128, 128, 128)), // Grey

                // the mirror
                new Polygon(new Point(7.5, -90, 6.5),  // bottom left
                        new Point(8, -92, 36.5),  // top left
                        new Point(-42, -74, 39.5),  // top right
                        new Point(-42.5, -72, 9.5))    //bottom right
                        .setEmission(new Color(128, 128, 128))
                        .setMaterial(new Material().setkD(0.0).setkS(0.0).setnShininess(0).setKt(0.0).setKr(1d)),

                new Polygon(new Point(100, -100, -15.100517272949219), // the floor
                        new Point(100,100,-15.100517272949219),
                        new Point(-100,100,-15.100517272949219),
                        new Point(-100,-100,-15.100517272949219))
                        .setMaterial(new Material().setkD(0.3).setkS(0.3).setnShininess(30).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(100, 100, 100)),

                // wall behind
                new Polygon(new Point(100,-100,-15.100517272949219),
                        new Point(-100,-100,-15.100517272949219),
                        new Point(-100,-100,100),
                        new Point(100,-100,100))
                        .setMaterial(new Material().setkD(0.3).setkS(0.3).setnShininess(30).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(100, 100, 100)),

                // wall left
                new Polygon(new Point(-100,-100,-15.100517272949219),
                        new Point(-100,100,-15.100517272949219),
                        new Point(-100,100,100),
                        new Point(-100,-100,100))
                        .setMaterial(new Material().setkD(0.3).setkS(0.3).setnShininess(30).setKt(0.0).setKr(0.0))
                        .setEmission(new Color(100, 100, 100))
        );

        // The Stick

        // Constants
        double thickEnd = 2.0;     // The diameter of the thick end
        double thinEnd = 0.5;      // diameter of the thin end
        double length = 15;        // the length of the stick
        int segments = 16;          // The number of segments to round the edges

        // Colors and materials
        Color woodColor = new Color(65, 30, 10);
        Material woodMaterial = new Material().setkD(0.5).setkS(0.5).setnShininess(30).setKt(0.0).setKr(0.1);

        // Create the main body of the cue stick

        for (int i = 0; i < segments; i++) {
            double angle1 = (i * 2 * Math.PI) / segments;
            double angle2 = ((i + 1) * 2 * Math.PI) / segments;

            double r1 = thickEnd / 2;
            double r2 = thinEnd / 2;
            scene.geometries.add(

                    new Polygon(
                            new Point(0, 10 + (r1 * Math.sin(angle1)), 32.5 + (r1 * Math.cos(angle1))),
                            new Point(0, 10 + (r1 * Math.sin(angle2)), 32.5 + (r1 * Math.cos(angle2))),
                            new Point(length, 10 + (r2 * Math.sin(angle2)), 12.5 + (r2 * Math.cos(angle2))),
                            new Point(length, 10 + (r2 * Math.sin(angle1)), 12.5 + (r2 * Math.cos(angle1)))
                    )
                            .setEmission(woodColor)
                            .setMaterial(woodMaterial));
        }

        // Create rounded thick end
        for (int i = 0; i < segments; i++) {
            double angle1 = Math.PI + (i * Math.PI) / segments;
            double angle2 = Math.PI + ((i + 1) * Math.PI) / segments;

            scene.geometries.add(
                    new Polygon(
                            new Point(0, 10 + (thickEnd/2 * Math.sin(angle1)), 32.5 + (thickEnd/2 * Math.cos(angle1))),
                            new Point(0, 10 + (thickEnd/2 * Math.sin(angle2)), 32.5 + (thickEnd/2 * Math.cos(angle2))),
                            new Point(0.5, 10 + (thickEnd/2 * Math.sin(angle2)), 32 + (thickEnd/2 * Math.cos(angle2))),
                            new Point(0.5, 10 + (thickEnd/2 * Math.sin(angle1)), 32 + (thickEnd/2 * Math.cos(angle1))))
                            .setEmission(woodColor)
                            .setMaterial(woodMaterial));
        }

        // Create rounded thin end
        for (int i = 0; i < segments; i++) {
            double angle1 = Math.PI + (i * Math.PI) / segments;
            double angle2 = Math.PI + ((i + 1) * Math.PI) / segments;

            scene.geometries.add(
                    new Polygon(
                            new Point(length, 10 + (thinEnd/2 * Math.sin(angle1)), 12.5 + (thinEnd/2 * Math.cos(angle1))),
                            new Point(length, 10 + (thinEnd/2 * Math.sin(angle2)), 12.5 + (thinEnd/2 * Math.cos(angle2))),
                            new Point(length - 0.5, 10 + (thinEnd/2 * Math.sin(angle2)), 12.5 + (thinEnd/2 * Math.cos(angle2))),
                            new Point(length - 0.5, 10 + (thinEnd/2 * Math.sin(angle1)), 12.5 + (thinEnd/2 * Math.cos(angle1))))
                            .setEmission(woodColor)
                            .setMaterial(woodMaterial));
        }



        // adding lights
        scene.lights.add(new PointLight(new Color(100, 200, 100), new Point(-60, 0, 60))
                .setKl(0.00001).setKq(0.000001));

        scene.lights.add(new SpotLight(new Color(100, 100, 200), new Point(50, 50, 100), new Vector(-1, -1, -1))
                .setKl(0.00001).setKq(0.000001));

        scene.lights.add(new PointLight(new Color(200, 200, 100), new Point(0, 0, 200))
                .setKl(0.00001).setKq(0.000001));

        scene.setAmbientLight(new Ambientlight(new Color(255, 255, 255), 0.1))
                .setBackground(new Color(100, 100, 100));


        scene.lights.add(new SpotLight(new Color(0,255,255), new Point(70,70,-15.100517272949219 + 1),new Vector(-70,-70,15.100517272949219 - 1).normalize())
                .setKl(0.00001).setKq(0.0001));

        // Render the scene
        camera.setImageWriter(new ImageWriter("SnookerTest", 1280, 1000))
                .build()
                .renderImage()
                .writeToImage();

    }
}