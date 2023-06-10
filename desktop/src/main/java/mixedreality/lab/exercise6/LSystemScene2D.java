/**
 * Diese Datei ist Teil des Vorgabeframeworks für die Veranstaltung "Mixed Reality"
 * <p>
 * Prof. Dr. Philipp Jenke, Hochschule für Angewandte Wissenschaften Hamburg.
 */

package mixedreality.lab.exercise6;

import com.jme3.math.Vector2f;
import ui.Scene2D;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Implementation of an L-System
 */
public class LSystemScene2D extends Scene2D {

    /**
     * The axiom is a single character
     */
    protected String axiom;
    /**
     * All rules are in a map which maps a character to its replacement.
     */
    protected Map<Character, String> rules;
    /**
     * Number of iterations during derivation
     */
    protected int numIterations;

    /**
     * Result of the last derivation.
     */
    protected String currentWord;

    protected int rotationAngle;

    private final Stack<Turtle> stack = new Stack<>();

    private Turtle turtle = new Turtle(new Vector2f(0, -0.5f), 0);

    public LSystemScene2D(int width, int height) {
        super(width, height, new Vector2f(-1, -1), new Vector2f(1, 1));
        this.rules = new HashMap<>();

        //this.axiom = 'F';
        //this.rules.put('F', "F+F--F+F");
        //this.rotationAngle = 0;

        // Beispiel aus der Vorlesung
        /*this.axiom = "F+F+F+F";
        this.rules.put('F', "F+F-F-FF+F+F-F");
        this.rotationAngle = 90;*/

        // Beispiela aus der Vorlesung (mit Stack)
        /*this.axiom = "F";
        this.rules.put('F', "F[-F]F[+F][F]");
        this.rotationAngle = 30;
        turtle.setCurrentAngle(90);*/

        // Sierpinski triangle
        this.axiom = "F-G-G";
        this.rules.put('F', "F-G+F+G-F");
        this.rules.put('G', "GG");
        this.rotationAngle = 120;
        turtle = new Turtle(new Vector2f(-0.5f, 0.5f), 0);

        //Fractal plant
        /*this.axiom = "X";
        this.rules.put('F', "FF");
        this.rules.put('X', "F+[[X]-X]-F[-FX]+X");
        this.rotationAngle = 25;
        turtle.setCurrentAngle(90);*/

        this.rules.put('-', "-");
        this.rules.put('+', "+");

        this.rules.put('[', "[");
        this.rules.put(']', "]");

        this.numIterations = 6;
        this.currentWord = "";

        // Run derivation
        derive();
        // Debugging: show derived word.
        // System.out.println(currentWord);
    }

    /**
     * Derive the axiom for the given number of iterations. The result of the
     * derivation must be saved in the variable currentWord.
     */
    protected void derive() {
        currentWord = this.axiom;
        for (int i = 0; i < numIterations; i++) {
            String derivedWord = "";
            for (char ch : currentWord.toCharArray()) {
                derivedWord = derivedWord.concat(rules.getOrDefault(ch, "" + ch));
            }
            currentWord = derivedWord;
        }
    }

    @Override
    public void paint(Graphics g) {
        for (char ch : currentWord.toCharArray()) {
            switch (ch) {
                case 'F', 'G' -> {
                    Vector2f oldPos = turtle.getPosition();
                    turtle.move(numIterations);
                    drawLine(g, oldPos, turtle.getPosition(), Color.BLACK);
                }
                case '+' -> turtle.rotate(rotationAngle);
                case '-' -> turtle.rotate(-rotationAngle);
                case '[' -> stack.push(turtle.clone());
                case ']' -> turtle = stack.pop();
            }
        }
    }

    @Override
    public String getTitle() {
        return "L-System";
    }
}
