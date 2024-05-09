package chromatynk.chromatynk_g6;

import chromatynk.chromatynk_g6.exceptions.OutOfRangeException;
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

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) throws OutOfRangeException {
        if(opacity < 0 || opacity > 1){
            throw new OutOfRangeException();
        }
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
     * @throws NegativeNumberException verify the cursor is not out of the canvas
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
     * @throws NegativeNumberException verify the cursor is not out of the canvas
     */
    public void moveCursor(int posX, int posY) throws NegativeNumberException {
        int newPosX = this.posX + posX;
        int newPosY = this.posY + posY;

        if (newPosX < 0 || newPosY < 0) {
            throw new NegativeNumberException("The position : (" + newPosX + "x ; " + newPosY + " y) is out of the Canvas!");
        }


        //Add the verification for the canvas
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
     public void forward(int value) throws NegativeNumberException{
         if (value < 0 ){
            throw new NegativeNumberException();
         }
         double newX;
         double newY;

             newX = Math.cos(Math.toRadians(this.rotationAngle))*value +this.posX; // Math use trigonometry in radiants so the conversion is needed
             newY = Math.sin(Math.toRadians(this.rotationAngle))*value +this.posY;

         if(newX < 0 ){
            newX = 0;
         }
         if(newY < 0){
            newY = 0;
         }
         // TODO:Add the verification for the canvas

         this.posX = (int) newX;
         this.posY = (int) newY;
     }


     /**
     *  Move the cursor backward
     * @param value the value form witch the cursor goes backward
     * @throws NegativeNumberException verify the cursor is not out of the canvas
     */
    public void backward(int value) throws NegativeNumberException{
        if (value < 0 ){
            throw new NegativeNumberException();
        }
        double newX;
        double newY;

        newX = this.posX - Math.sin(Math.toRadians(90-this.rotationAngle))*value; // Math use trigonometry in radiants so the conversion is needed
        newY = this.posY - Math.cos(Math.toRadians(90-this.rotationAngle))*value;

        if(newX < 0 ){
            newX = 0;
        }
        if(newY < 0){
            newY = 0;
        }
        //TODO:Add the verification for the canvas

        this.posX = (int) newX;
        this.posY = (int) newY;
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
