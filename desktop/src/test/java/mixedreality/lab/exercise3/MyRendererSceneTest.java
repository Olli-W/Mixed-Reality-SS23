package mixedreality.lab.exercise3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jme3.math.Vector4f;
import org.junit.jupiter.api.Test;

public class MyRendererSceneTest {
    private final MyRendererScene scene = new MyRendererScene(600, 600);

    @Test
    public void testTransformWorld3dToPixel() {
        Vector4f worldCoords1 = new Vector4f(0, 0, 0, 1);
        Vector4f expectedPixelCoords1 = new Vector4f(300, 300, 0, 0);
        assertEquals(scene.transformWorld3dToPixel(worldCoords1), expectedPixelCoords1);

        Vector4f worldCoords2 = new Vector4f(0.453594f, 0.116124f, -1.82E-4f, 1);
        Vector4f expectedPixelCoords2 = new Vector4f(368.0453f, 317.4202f, 0, 0);
        assertEquals(scene.transformWorld3dToPixel(worldCoords2), expectedPixelCoords2);

        Vector4f worldCoords3 = new Vector4f(0.392136f, -0.749512f, -0.170384f, 1);
        Vector4f expectedPixelCoords3 = new Vector4f(364.2981f, 177.1034f, 0, 0);
        assertEquals(scene.transformWorld3dToPixel(worldCoords3), expectedPixelCoords3);

        Vector4f worldCoords4 = new Vector4f(1, 1, 1, 1);
        Vector4f expectedPixelCoords4 = new Vector4f(400, 400, 0, 0);
        assertEquals(scene.transformWorld3dToPixel(worldCoords4), expectedPixelCoords4);
    }
}
