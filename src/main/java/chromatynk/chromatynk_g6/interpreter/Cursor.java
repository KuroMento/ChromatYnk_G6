package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.exceptions.cursorExceptions.MimicStackEmptyException;
import javafx.scene.paint.Color;
import chromatynk.chromatynk_g6.exceptions.NegativeNumberException;

import java.util.Stack;


public class Cursor {
    /**
     * Allows to identify the cursors from others present on the same Canvas.
     */
    private long id;

    /**
     * Place the cursor on horizontal axe.
     */
    private int posX;
    /**
     * Place the cursor on vertical axe.
     */
    private int posY;
    /**
     * a variable that shows the value of the thickness.
     */
    private float thickness;
    /**
     * a variable that shows the value of the angle rotation.
     */
    private float rotationAngle;
    /**
     * Contains the color of the cursor.
     */
    private Color color;
    /**
     * a variable that shows the value of opacity.
     */
    private double opacity;

    /**
     * a variable that notices user if the cursor is visible or not.
     */
    private boolean isVisible;

    /**
     * A stack that keeps track of duplicated cursors when the user call MIMIC
     */
    private Stack<Cursor> mimics;

    /**
     * A stack that keeps track of duplicated cursors when the user call MIRROR
     */
    private Stack<Cursor> mirror;

    private int width;
    private int height;

    /**
     * Complete Constructor of the class <code>Cursor</code> :
     * @param id the identification of the cursor.
     * @param posX the position on horizontal axe.
     * @param posY the position on vertical axe.
     * @param thickness the integer value of the cursor's thickness.
     * @param rotationAngle the current rotation of the cursor in degrees between 0 and 360°.
     * @param color the color of the cursor.
     * @param opacity the opacity of the cursor between 0f and 1f.
     * @param isVisible the boolean check for the cursor's visibility.
     */
    public Cursor(long id, int posX, int posY, float thickness, float rotationAngle, Color color, float opacity, boolean isVisible) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.thickness = thickness;
        this.rotationAngle = rotationAngle;
        this.color = color;
        this.opacity = opacity;
        this.isVisible = isVisible;
        this.mimics = new Stack<Cursor>();
        this.mirror = new Stack<Cursor>();
    }

    /**
     * A Constructor of the class <code>Cursor</code> with a predefined id.
     * @param id The id of the newly created Cursor
     */
    public Cursor(long id){
        this.id = id;
        this.posX = 0;
        this.posY = 0;
        this.thickness = 1;
        this.rotationAngle = 0;
        this.color = Color.rgb(0,0,0);
        this.opacity = 1;
        this.isVisible = true;
        this.mimics = new Stack<Cursor>();
        this.mirror = new Stack<Cursor>();
    }

    //Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public float getThickness() {
        return thickness;
    }

    /**
     * Set the <code>thickness</code> to another value.
     * @param thickness
     * @throws <code>NegativeNumberException</code> verify the cursor is not out of the canvas.
     */
    public void setThickness(float thickness) throws NegativeNumberException {
        if(thickness < 0){
            throw new NegativeNumberException();
        }
        //Add the verification for the canvas
        this.thickness = thickness;
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(float rotationAngle) {
        rotationAngle = rotationAngle%360;
        this.rotationAngle = rotationAngle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity){
        if(opacity < 0){
            opacity = 0;
        }
        if(opacity > 1){
            opacity = 1;
        }
        this.opacity = opacity;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Stack<Cursor> getMimics(){ return this.mimics;}

    public Stack<Cursor> getMirror(){ return this.mirror;}

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    //Methods

    /**
     * Hide the cursor
     */
    public void hide(){
        this.isVisible = false;
    }

    /**
     * Show the cursor
     */
    public void show(){
        this.isVisible = true;
    }

    /**
     * Place the cursor on the Canva when user uses pixels
     * @param posX the position on horizontal axe.
     * @param posY the position on vertical axe.
     */
    public void placeCursor(int posX,int posY){
        // If the new values are outside of the canevas
        if(posX < 0){
            posX = 0;
        }
        if(posY < 0){
            posY = 0;
        }

        if(posX > width) {
            posX = width;
        }
        if(posY > height){
            posY = height;
        }
        this.posX= posX;
        this.posY= posY;
    }

    /**
     * Move the cursor on the Canva using the relative position
     * @param posX the amount to move in pixel on the horizontal axe.
     * @param posY the amount to move in pixel on the vertical axe.
     */
    public void moveCursor(int posX, int posY){
        int newPosX = this.posX + posX;
        int newPosY = this.posY + posY;
        // If the new values are outside of the canevas
        if(newPosX < 0){
            newPosX = 0;
        }
        if(newPosY < 0){
            newPosY = 0;
        }
        if(newPosX > width) {
            newPosX = width;
        }
        if(newPosY > height){
            newPosY = height;
        }
        this.posX = newPosX;
        this.posY = newPosY;
    }

    /**
     * Rotate the cursor
     * @param degree the rotation
     */
    public void rotateCursor(float degree){
        //Over 360 degrees, the cursor comes in the original position
        this.rotationAngle += degree;
        //The rotationAngle is always between -360 and 360
        this.rotationAngle = this.rotationAngle%360;
    }

    /**
     * Move the cursor forward
     * @param value the value from witch the cursor will advance
     * @throws NegativeNumberException verify the cursor is not out of the canvas
     */
    public void forward(double value) {
        int deltaX = (int)( value * Math.cos(Math.toRadians(this.rotationAngle)));
        int deltaY = (int)( value * Math.sin(Math.toRadians(this.rotationAngle)));
        int x = this.posX + deltaX;
        int y = this.posY + deltaY;
        // If the new values are outside of the canevas
        if(x < 0){
            x = 0;
        }
        if(y < 0){
            y = 0;
        }
        if(x > width) {
            x = width;
        }
        if(y > height){
            y = height;
        }
        this.posX = x;
        this.posY = y;
    }

    /**
     * Change the angle of cursor to point at b
     * @param bx x value of point b
     * @param by y value of point b
     */
    public void lookAt(int bx, int by){
        int ax = this.posX;
        int ay = this.posY;
        int dy = ay - by ;
        int dx = bx - ax ;
        this.rotationAngle = (float)(Math.atan2(dx, dy) * 180 / Math.PI)-90;
    }

    /**
     * Add a mimic cursor that will be used for an instruction block MIMIC/MIRROR
     * @param cursor the added cursor
     */
    public void addMimic(Cursor cursor){
        mimics.push(cursor);
    }

    /**
     * Delete the last duplicated mimic after the end of an instruction block for MIMIC/MIRROR
     * @return The id of the deleted mimic
     * @throws <code>MimicStackEmptyException</code>
     */
    public long deleteMimic() throws MimicStackEmptyException {
        if( this.mimics.isEmpty() ) throw new MimicStackEmptyException();
        Cursor cursor = this.mimics.pop();
        return cursor.getId();
    }

    /**
     * Add a mirror cursor that will be used for an instruction block MIRROR
     * @param cursor the added cursor
     */
    public void addMirror(Cursor cursor){ mirror.push(cursor); }

    /**
     * Delete all mirrors after the end of an instruction block for MIRROR
     */
    public void deleteMirror(){
        this.mirror.empty();
    }

    /**
     * Method toString for the Cursor class
     * @return the cursor parameters in a String
     */
    @Override
    public String toString() {
        return "Cursor{" +
                "id=" + id +
                ", ( X=" + posX +
                "; Y=" + posY +
                "), thickness=" + thickness +
                ", rotationAngle=" + rotationAngle +
                "°, color=" + color +
                ", opacity=" + opacity +
                ", isVisible=" + isVisible +
                '}';
    }
}
