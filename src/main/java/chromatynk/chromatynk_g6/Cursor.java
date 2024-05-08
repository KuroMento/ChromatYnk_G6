package chromatynk.chromatynk_g6;

import javafx.scene.paint.Color;
import chromatynk.chromatynk_g6.exceptions.NegativeNumberException;

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
    private float opacity;

    /**
     * a variable that notices user if the cursor is visible or not.
     */
    private boolean isVisible;

    /**
     * Constructor of the class Cursor :
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

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
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
    public void placeCursor(int posX,int posY) throws NegativeNumberException{
        if(posY < 0 || posX < 0) {
            throw new NegativeNumberException();
        }

        //Add the verification for the canvas
        this.posX= posX;
        this.posY= posY;
    }

    /**
     * Move the cursor on the Canva using the relative position
     * @param posX the amount to move in pixel on the horizontal axe.
     * @param posY the amount to move in pixel on the vertical axe.
     */
    public void moveCursor(int posX, int posY) throws NegativeNumberException{
        int newPosX = this.posX + posX;
        int newPosY = this.posY + posY;

        if( newPosX < 0|| newPosX <0){
            throw new NegativeNumberException("The position : (" + newPosX + "x ; " + newPosY + " y) is out of the Canvas!");
        }


        //Add the verification for the canvas

        this.posX= newPosX;
        this.posY= newPosY;
    }
}
