/**
 * Diese Datei ist Teil des Vorgabeframeworks für die Veranstaltung "Mixed Reality"
 * <p>
 * Prof. Dr. Philipp Jenke, Hochschule für Angewandte Wissenschaften Hamburg.
 */

package mixedreality.lab.exercise3;

import com.jme3.math.*;
import mixedreality.base.mesh.ObjReader;
import mixedreality.base.mesh.TriangleMesh;
import ui.Scene2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static java.lang.Math.abs;
import static mixedreality.base.math.ConvexHull2D.cross;

/**
 * Drawing canvas for a 3D renderer.
 */
public class MyRendererScene extends Scene2D {

  /**
   * This mesh is rendered
   */
  protected TriangleMesh mesh;

  /**
   * Virtual camera.
   */
  protected Camera camera;

  /**
   * This flag enables/disables backface culling
   */
  protected boolean backfaceCulling;

  public MyRendererScene(int width, int height) {
    super(width, height);
    camera = new Camera(new Vector3f(0, 0, -2), new Vector3f(0, 0, 0),
            new Vector3f(0, 1, 0), 90.0f / 180.0f * FastMath.PI, 1,
            width, height);
    backfaceCulling = true;
    lastMousePosition = null;

    ObjReader reader = new ObjReader();
    //mesh = reader.read("models/cube.obj");
    mesh = reader.read("Models/deer.obj");

    setupListeners();
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    clear(g2);

    if (mesh != null) {
      for (int i = 0; i < mesh.getNumberOfTriangles(); i++) {
        Vector3f posA = mesh.getVertex(mesh.getTriangle(i).getA()).getPosition();
        Vector3f posB = mesh.getVertex(mesh.getTriangle(i).getB()).getPosition();
        Vector3f posC = mesh.getVertex(mesh.getTriangle(i).getC()).getPosition();
        Vector4f pixelCoordinatesA = transformWorld3dToPixel(new Vector4f(posA.x, posA.y, posA.z, 1));
        Vector4f pixelCoordinatesB = transformWorld3dToPixel(new Vector4f(posB.x, posB.y, posB.z, 1));
        Vector4f pixelCoordinatesC = transformWorld3dToPixel(new Vector4f(posC.x, posC.y, posC.z, 1));
        if (backfaceCulling) {
          if (clockwise(pixelCoordinatesA, pixelCoordinatesB, pixelCoordinatesC)) {
            drawTriangle(g2, pixelCoordinatesA, pixelCoordinatesB, pixelCoordinatesC);
          }
        } else {
          drawTriangle(g2, pixelCoordinatesA, pixelCoordinatesB, pixelCoordinatesC);
        }
      }
    }
  }

  public Vector4f transformWorld3dToPixel(Vector4f pos) {
    // Modell-Tranformation
    Matrix4f M = new Matrix4f();

    // View-Tranformation
    Vector3f eye = camera.getEye();
    Vector3f refMinusEye = camera.getRef().subtract(eye);
    Vector3f z = refMinusEye.divide(refMinusEye.length());
    Vector3f x = camera.getUp().cross(z);
    Vector3f y = z.cross(x);
    Matrix4f V = new Matrix4f(
            x.x, y.x, z.x, eye.x,
            x.y, y.y, z.y, eye.y,
            x.z, y.z, z.z, eye.z,
            0, 0, 0, 1).invert();

    // Perspektivische Transformation
    Matrix4f P = new Matrix4f(
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 1/camera.getZ0(), 0
    );

    //Pixel-Transformation
    float f = (float) (getWidth() / (2 * Math.tan(camera.getFovX() / 2)));
    Matrix4f K = new Matrix4f(
            f, 0, 0, getWidth()/2f,
            0, f, 0, getHeight()/2f,
            0,0,0,0,
            0,0,0,0
    );
    Vector4f pBild = P.mult(V.mult(M.mult(pos)));
    return K.mult(pBild.divide(pBild.w));
  }

  private boolean clockwise(Vector4f p1, Vector4f p2, Vector4f p3) {
    return cross(new Vector2f(p1.x, p1.y), new Vector2f(p2.x, p2.y), new Vector2f(p3.x, p3.y)) < 0;
  }

  private void drawTriangle(Graphics2D g2, Vector4f a, Vector4f b, Vector4f c) {
    drawLine(g2, new Vector2f(a.x, a.y), new Vector2f(b.x, b.y), g2.getColor());
    drawLine(g2, new Vector2f(a.x, a.y), new Vector2f(c.x, c.y), g2.getColor());
    drawLine(g2, new Vector2f(b.x, b.y), new Vector2f(c.x, c.y), g2.getColor());
  }

  @Override
  public String getTitle() {
    return "2D Renderer";
  }

  /**
   * Draw a line using the given coordinates (no further transformations).
   */
  public void drawLine(Graphics2D gc, Vector2f a, Vector2f b, Color color) {
    gc.setColor(color);
    gc.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
  }

  @Override
  public JPanel getUserInterface() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JCheckBox cbBackfaceCulling = new JCheckBox("backfaceCulling");
    cbBackfaceCulling.setSelected(backfaceCulling);
    cbBackfaceCulling.addActionListener(e -> {
      backfaceCulling = cbBackfaceCulling.isSelected();
      repaint();
    });
    panel.add(cbBackfaceCulling);

    return panel;
  }

  /**
   * Setup listeners - used for user interaction.
   */
  public void setupListeners() {
    addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e) {
        Vector2f mPos = new Vector2f(e.getX(), e.getY());
        if (lastMousePosition != null) {
          float dx = mPos.x - lastMousePosition.x;
          float dy = mPos.y - lastMousePosition.y;
          camera.rotateHorizontal(dx);
          camera.rotateVertical(dy);
          repaint();
        }
        lastMousePosition = mPos;
      }

      @Override
      public void mouseMoved(MouseEvent e) {
      }
    });

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        lastMousePosition = null;
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        lastMousePosition = null;
      }
    });
  }
}
