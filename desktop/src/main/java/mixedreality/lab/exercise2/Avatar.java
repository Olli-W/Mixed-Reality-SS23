/**
 * Diese Datei ist Teil des Vorgabeframeworks für die Veranstaltung "Mixed Reality"
 * <p>
 * Prof. Dr. Philipp Jenke, Hochschule für Angewandte Wissenschaften Hamburg.
 */

package mixedreality.lab.exercise2;

import com.jme3.math.Matrix3f;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import math.Vectors;

import math.MathF;
import math.Angles;

/**
 * Information about the avatar in the game.
 */
public class Avatar {
    /**
     * Current position of the avatar
     */
    protected Vector2f pos;

    /**
     * Current rotation angle of the avatar (radian measure)
     */
    protected float rotationAngle;

    /**
     * The avatar must be rotated that its orientation matches this vector.
     */
    protected Vector2f targetPos;

    /**
     * Change rate of the orientation per time step.
     */
    public static final float ROTATION_VELOCITY = 0.1f;

    /**
     * Change rate of the position per time step.
     */
    public static final float MOVE_VELOCITY = 0.01f;

    public Avatar() {
        pos = new Vector2f(0, 0);
        rotationAngle = 0;
        targetPos = null;
    }

    public Vector2f getPos() {
        // Pos = translation part of pose matrix
        return Vectors.xy(makePose().mult(Vector3f.UNIT_Z));
    }

    public Vector2f getOrientation() {
        // Orientation of first colum of rotation part of pose matrix.
        return Vectors.xy(makePose().mult(Vector3f.UNIT_X));
    }

    public void setTargetPos(Vector2f o) {
        this.targetPos = o;
    }

    // ++++++++++++++++ YOUR TASKS START HERE +++++++++++++++++++++++++++++++++

    /**
     * Generate a 3x3 homogenious transformation matrix which contains the
     * current rotation and p
     */
    protected Matrix3f makePose() {
        Matrix3f rotation = new Matrix3f(
                MathF.cos(rotationAngle), -MathF.sin(rotationAngle), 0,
                MathF.sin(rotationAngle), MathF.cos(rotationAngle), 0,
                0, 0, 1);
        Matrix3f translation = new Matrix3f(
                1, 0, pos.x,
                0, 1, pos.y,
                0, 0, 1);
        return translation.mult(rotation);
    }

    /**
     * Move the avatar along the current orientation.
     */
    public void moveToTargetPos() {
        if (targetPos == null || getPos().subtract(targetPos).length() < 2 * MOVE_VELOCITY) {
            return;
        }
        Vector2f orientation = getOrientation().normalize();
        Vector2f direction = targetPos.subtract(getPos()).normalize();
        float alpha = MathF.atan2(
                orientation.x * direction.y - orientation.y * direction.x,
                orientation.x * direction.x + orientation.y * direction.y
        );
        if (alpha > 0) {
            rotationAngle += Math.min(alpha, ROTATION_VELOCITY);
        } else {
            rotationAngle += Math.max(alpha, -ROTATION_VELOCITY);
        }
        if (Math.abs(alpha) <= MathF.HALF_PI){
            pos = getPos().add(getOrientation().mult(MOVE_VELOCITY));
        }
    }
}
