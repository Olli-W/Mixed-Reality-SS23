package mixedreality.lab.exercise6;

import com.jme3.math.Vector2f;
import math.MathF;
import math.Vectors;

public class Turtle {

    private Vector2f position;
    private float currentAngle;

    public Turtle(Vector2f position, float currentAngle) {
        this.position = position;
        this.currentAngle = currentAngle;
    }

    public void move(int numIterations) {
        position = position.add(Vectors.fromPolar(numIterations > 0 ? 0.1f/numIterations : 0.1f, MathF.toRadians(currentAngle)));
    }

    public void rotate(int rotationAngle) {
        currentAngle += rotationAngle;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setCurrentAngle(float angle) {
        currentAngle = angle;
    }

    public Turtle clone() {
        return new Turtle(this.position, this.currentAngle);
    }

    public String toString() {
        return "" + position + " " + currentAngle;
    }
}