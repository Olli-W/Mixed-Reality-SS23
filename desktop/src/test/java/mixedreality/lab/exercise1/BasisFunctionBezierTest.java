package mixedreality.lab.exercise1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BasisFunctionBezierTest {

    private BasisFunctionBezier basisFunctionBezier = new BasisFunctionBezier();

    @Test
    public void testBezierBasisFunction() {
        assertEquals(1.0, basisFunctionBezier.eval(0, 0, 3), 1e-4);
        assertEquals(0.1089, basisFunctionBezier.eval(0.33F, 2, 2), 1e-4);
        assertEquals(0.2288, basisFunctionBezier.eval(0.66F, 1, 3), 1e-4);
        assertEquals(0.0, basisFunctionBezier.eval(1, 2, 4), 1e-4);
    }

    @Test
    public void testBezierBasisFunctionDerivative() {
        assertEquals(3.0, basisFunctionBezier.evalDerivative(0, 0, 3), 1e-4);
        assertEquals(0.9375, basisFunctionBezier.evalDerivative(0.25F, 2, 3), 1e-4);
        assertEquals(-1.0, basisFunctionBezier.evalDerivative(0.75F, 1, 2), 1e-4);
        assertEquals(2.0, basisFunctionBezier.evalDerivative(1, 2, 2), 1e-4);
    }
}