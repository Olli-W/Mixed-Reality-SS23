package mixedreality.lab.exercise2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jme3.math.Matrix3f;
import com.jme3.math.Vector2f;
import math.MathF;
import org.junit.jupiter.api.Test;
public class AvatarTest {
    private final Avatar avatar = new Avatar();

    @Test
    public void testMakePose() {
        assertEquals(avatar.makePose(), new Matrix3f());
        avatar.rotationAngle = 90;
        avatar.pos = new Vector2f(5, 10);
        assertEquals(avatar.makePose(), new Matrix3f(
                -0.44807363f, -0.89399666f, 5.0f,
                0.89399666f, -0.44807363f, 10.0f,
                0.0f, 0.0f, 1.0f));
    }
}