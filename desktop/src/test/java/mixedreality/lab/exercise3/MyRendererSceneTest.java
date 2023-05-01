package mixedreality.lab.exercise3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import org.junit.jupiter.api.Test;

public class MyRendererSceneTest {
    private final MyRendererScene scene = new MyRendererScene(600, 600);

    @Test
    public void testTransformWorld3dToPixel() {
        Vector3f worldCoords1 = new Vector3f(0, 0, 0);
        Vector3f expectedPixelCoords1 = new Vector3f(300, 300, 0);
        assertEquals(scene.transformWorld3dToPixel(worldCoords1), expectedPixelCoords1);

        Vector3f worldCoords2 = new Vector3f(0.453594f, 0.116124f, -1.82E-4f);
        Vector3f expectedPixelCoords2 = new Vector3f(390.7188f, 323.2248f, 0);
        assertEquals(scene.transformWorld3dToPixel(worldCoords2), expectedPixelCoords2);

        Vector3f worldCoords3 = new Vector3f(0.392136f, -0.749512f, -0.170384f);
        Vector3f expectedPixelCoords3 = new Vector3f(378.4272f, 150.09761f, 0);
        assertEquals(scene.transformWorld3dToPixel(worldCoords3), expectedPixelCoords3);

        Vector3f worldCoords4 = new Vector3f(1, 1, 1);
        Vector3f expectedPixelCoords4 = new Vector3f(500, 500, 0);
        assertEquals(scene.transformWorld3dToPixel(worldCoords4), expectedPixelCoords4);
    }
}
