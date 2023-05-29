package mixedreality.lab.exercise5;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StereoSceneTest {
    private static final StereoScene scene = new StereoScene();
    private static final double h = 1e-3;
    @Test
    void testComputeGradient() {
        Assertions.assertEquals(scene.computeGradient(new Vector3f(0, 0, 0), h), new Vector3f(39.18457f, -321.65527f, -433.59375f));
        Assertions.assertEquals(scene.computeGradient(new Vector3f(10, 200, 180), h), new Vector3f(-54.19922f, -3.4179688f, 6.8359375f));
    }

    @Test
    void testComputeError() {
        Assertions.assertEquals(scene.computeError(new Vector2f(12, 12), new Vector2f(565, 122)), 2285.2451, 1e-4);
        Assertions.assertEquals(scene.computeError(new Vector2f(411, 430), new Vector2f(142, 851)), 1868.2985, 1e-4);
    }

}