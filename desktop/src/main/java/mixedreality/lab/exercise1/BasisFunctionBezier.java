package mixedreality.lab.exercise1;

import mixedreality.base.math.BasisFunction;

public class BasisFunctionBezier implements BasisFunction {

    /**
     * Berechnet die Faktultät der übergebenen Zahl.
     * @param n Die Zahl, deren Fakultät berechnet werden soll.
     * @return Die Fakultät von n.
     */
    public int factorial(int n) {
        int result = 1;
        for(int i = n; i > 0; i--) {
            result *= i;
        }
        return result;
    }

    /**
     * Berechnet den Binomialkoeffizienten "n über k".
     * @param n Der "obere" Wert.
     * @param k Der "untere" Wert
     * @return Ergebnis von "n über k".
     */
    public int binomialCoefficient(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n-k));
    }
    /**
     * Methode zur Berechnung der Basisfunktion von Bezier-Kurven.
     * @param t Der Parameterwert, an dem die Kurve betrachtet wird, liegt zwischen 0 und 1.
     * @param index Der Index des Kontrollpunktes.
     * @param degree Der Grad der Kurve.
     * @return Wert, der angibt, wie stark der Kontrollpunkt die Kurve am betrachteten Punkt beeinflusst.
     */
    @Override
    public float eval(float t, int index, int degree) {
        return (float) (binomialCoefficient(degree, index) * Math.pow(t, index) * Math.pow(1 - t, degree - index));
    }

    /**
     * Methode für die Ableitung der Basisfunktion von Bezier-Kurven.
     * @param t Der Parameterwert, an dem die Kurve betrachtet wird, liegt zwischen 0 und 1.
     * @param i Der Index des Kontrollpunktes.
     * @param degree Der Grad der Kurve.
     * @return Wert, der angibt, wie stark sich die Kurve am betrachteten Punkt verändert.
     */
    @Override
    public float evalDerivative(float t, int i, int degree) {
        if ((t == 0 && i == 0) || (t == 1 && i == degree)) {
            return degree;
        }
        return degree * (eval(t, i - 1, degree - 1) - eval(t, i, degree - 1));
    }
}
