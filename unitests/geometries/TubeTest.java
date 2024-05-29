package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() {
        Tube tube = new Tube(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a point on the tube
        assertEquals(new Vector(1, 0, 0),
                tube.getNormal(new Point(1, 0, 1)),
                "ERROR: getNormal() for a point on the tube does not work correctly");


    }


}