package mixedreality.lab.exercise4;

import com.jme3.math.Matrix3f;
import com.jme3.math.Vector2f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuadricErrorMetricsSimplification2DTest {
    private final QuadricErrorMetricsSimplification2D scene = new QuadricErrorMetricsSimplification2D(new Polygon());

    @Test
    public void computeDistanceMatrixTest() {
        PolygonEdge[] edges = {
                new PolygonEdge(new PolygonVertex(new Vector2f(1.0f, 3.0f)), new PolygonVertex(new Vector2f(1.0f, 1.0f))),
                new PolygonEdge(new PolygonVertex(new Vector2f(1.0f, 1.0f)), new PolygonVertex(new Vector2f(4.0f, 1.0f))),
                new PolygonEdge(new PolygonVertex(new Vector2f(4.0f, 1.0f)), new PolygonVertex(new Vector2f(4.0f, 3.0f))),
                new PolygonEdge(new PolygonVertex(new Vector2f(1.0f, 4.0f)), new PolygonVertex(new Vector2f(1.0f, 3.0f)))
        };
        Matrix3f[] distanceMatrices = {
                new Matrix3f(1, 0, -1, 0, 0, 0, -1, 0, 1),
                new Matrix3f(0, 0, 0, 0, 1, -1, 0, -1, 1),
                new Matrix3f(1, 0, -4, 0, 0, 0, -4, 0, 16),
                new Matrix3f(1, 0, -1, 0, 0, 0, -1, 0, 1)
        };
        for (int i = 0; i < distanceMatrices.length; i++) {
            assertEquals(scene.computeDistanceMatrix(edges[i]), distanceMatrices[i]);
        }
    }
}
