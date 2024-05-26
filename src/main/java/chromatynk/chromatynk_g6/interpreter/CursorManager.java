package chromatynk.chromatynk_g6.interpreter;

import chromatynk.chromatynk_g6.exceptions.NegativeNumberException;
import chromatynk.chromatynk_g6.exceptions.OutOfRangeException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorAlreadyExistingException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.MimicStackEmptyException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.MissingCursorException;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CursorManager {
    /**
     * A map to track the cursors that are active within the Canvas
     */
    private Map<Long,Cursor> cursors;

    /**
     * The id of the selected cursors
     */
    private long selectedCursorId;

    /**
     * Constructor of CursorManager adding by default a generic cursor to the list of active cursors.
     */
    public CursorManager(){
        Cursor defaultCursor = new Cursor(0);
        this.selectedCursorId = defaultCursor.getId();
        this.cursors = new HashMap<>();
        this.cursors.put(defaultCursor.getId(),defaultCursor);
    }

    // Getters/Setters
    public Map<Long,Cursor> getCursors(){
        return this.cursors;
    }
    public void setCursors(Map<Long, Cursor> cursors) {
        this.cursors = cursors;
    }

    public long getSelectedCursorId() { return this.selectedCursorId;}
    private void setSelectedCursorId(long id) { this.selectedCursorId = id; }

    /**
     * Grabs a cursor within those active.
     * @return The <code>Cursor</code> which as <code>id</code> for identification.
     */
    public Cursor getSelectedCursor() {
        return this.cursors.get(this.selectedCursorId);
    }

    /**
     * Grabs a cursor.
     * @return The <code>Cursor</code> which as <code>id</code> for identification.
     */
    public Cursor getCursor(long id) {
        return this.cursors.get(id);
    }

    /**
     * Checks if a cursor exists within the Canvas.
     * @param id The id of the wanted cursor to check
     * @return True if the cursor is active and exist
     */
    public boolean cursorExist(long id){
        return this.cursors.containsKey(id);
    }

    /**
     * Generate a new id for the creation of cursors based on the active cursors' ids.
     * @return A new id of type long
     */
    public long createCursorId(){
        Set<Long> idSet = this.cursors.keySet();
        long newId = (idSet.size() + 1);
        while( this.cursors.containsKey(newId)){
            newId += 1;
        }
        return newId;
    }

    /**
     * Add a new Cursor to the active cursors on the Canvas.
     */
    public void addCursor(){
        long newId = createCursorId();
        this.cursors.put(newId,new Cursor(newId));
    }

    /**
     * Add a new Cursor to the active cursors based on a given id.
     * @param id The id of the cursor you want to add
     * @throws CursorAlreadyExistingException the cursor already exist
     */
    public void addCursor(long id) throws CursorAlreadyExistingException {
        if( cursorExist(id) ){
            throw new CursorAlreadyExistingException("Cursor n°" + id + " already exists.");
        }
        this.cursors.put(id,new Cursor(id));
    }

    /**
     * Removes a cursor from the active cursor if it exists.
     * @param id The id of the cursor to remove
     * @throws MissingCursorException a cursor does not exist
     */
    public void removeCursor(long id) throws MissingCursorException, CursorException {
        if( !cursorExist(id) ){
            throw new MissingCursorException("Cursor does not exist anymore!");
        }
        if( id == getSelectedCursorId()){
            throw new CursorException("The cursor n°" + id + " is selected and cannot be removed");
        }
        this.cursors.remove(id,this.cursors.get(id));
    }

    /**
     * Allows to select a cursor within the existing cursors
     * @param id The identification of the wanted cursor
     * @throws MissingCursorException a cursor does not exist
     * @throws CursorException the cursor is used in a block
     */
    public void selectCursor(long id) throws MissingCursorException, CursorException {
        if( !cursorExist(id) ){
            throw new MissingCursorException("This cursor does not exist.");
        }
        // Trying to select another cursor as the current one has been duplicated
        if( !getSelectedCursor().getMimics().isEmpty() && id != getSelectedCursorId()){
            throw new CursorException("The cursor is being used in a MIMIC/MIRROR block");
        }
        getSelectedCursor().hide(); // hiding the current cursor
        setSelectedCursorId(id); // selecting the new cursor
        getSelectedCursor().show(); // showing the newly selected cursor
    }

    /**
     * Set the input cursor with the same value as the cursor of given cursorId
     * @param cursor the cursor to change
     * @param cursorId the cursor id to copy from
     */
    public void copyCursor(Cursor cursor, long cursorId){
        try {
            Cursor cursorToCopy = cursors.get(cursorId);
            cursor.setPosX(cursorToCopy.getPosX());
            cursor.setPosY(cursorToCopy.getPosY());
            cursor.setRotationAngle(cursorToCopy.getRotationAngle());
            cursor.setOpacity(cursorToCopy.getOpacity());
            cursor.setThickness(cursorToCopy.getThickness());
            cursor.setColor(cursorToCopy.getColor());
            cursor.setVisible(cursorToCopy.isVisible());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Add a temporary cursor for the selected and the other temporary cursors when a MIMIC/MIRROR instruction is used.
     */
    public void addMimics(long cursorId){
        // If some mimics are already existing
        if(cursors.get(cursorId).getMimics().size() >= 1){
            // Add mimic cursor to each existing mimic cursor
            Cursor[] existingMimicsCursors = new Cursor[cursors.get(cursorId).getMimics().size()];
            cursors.get(cursorId).getMimics().copyInto(existingMimicsCursors);
            for(Cursor mimicCursor : existingMimicsCursors){
                // Creates and add a temporary cursor to the selected one
                long id = createCursorId();
                Cursor cursor = new Cursor(id);
                // Copy the mimic cursor
                copyCursor(cursor, mimicCursor.getId());
                cursors.get(cursorId).addMimic(cursor);
            }
        }
        // Creates and add a temporary cursor to the selected one
        long id = createCursorId();
        Cursor cursor = new Cursor(id);
        // Copy the selected cursor
        copyCursor(cursor, getSelectedCursorId());
        cursors.get(cursorId).addMimic(cursor);
    }

    /**
     * Deletes all the temporary cursors created by one specific MIMIC/MIRROR instruction.
     * @throws MimicStackEmptyException a mimic stack is empty
     */
    public void deleteMimics(long cursorId) throws MimicStackEmptyException {
        cursors.get(cursorId).deleteMimic();
    }

    /**
     * Takes the line between 2 points and creates a symmetrical cursor of the selected cursor.
     * @param x1 x coordinate of the first point
     * @param y1 y coordinate of the first point
     * @param x2 x coordinate of the second point
     * @param y2 y coordinate of the second point
     */
    public void addMirror(int x1, int y1, int x2, int y2){
        // Angle between the line and the x-axis
        double theta = Math.atan((double) (y2-y1)/(x2-x1));
        theta = Math.toDegrees(theta);
        // (-a)*y = b*x + c
        float b = (float) (y2-y1)/(x2-x1);
        float c = y1 - b*x1;
        // add mirror cursor to each existing mirror cursor
        Cursor[] existingMirrorCursors = new Cursor[this.getSelectedCursor().getMirror().size()];
        this.getSelectedCursor().getMirror().copyInto(existingMirrorCursors);
        for(Cursor mirrorCursor : existingMirrorCursors){
            addMirrorCursor(theta, b, c, mirrorCursor.getId());
        }
        // add mirror cursor to each existing mimic cursor
        Cursor[] existingMimicCursors = new Cursor[this.getSelectedCursor().getMimics().size()];
        this.getSelectedCursor().getMimics().copyInto(existingMirrorCursors);
        for(Cursor mimicCursor : existingMimicCursors){
            addMirrorCursor(theta, b, c, mimicCursor.getId());
        }
        addMirrorCursor(theta, b, c, getSelectedCursorId());
    }



    /**
     * Creates a line between the middle of the canvas and a given point.
     * @param x1 x coordinate of the point
     * @param y1 y coordinate of the point
     */
    public void addMirror(int x1, int y1){
        addMirror(x1, y1, 960, 540); //TODO: modify x2 and y2 with the correct values
    }

    /**
     * Add a mirror cursor from a cursor of cursorId
     * @param theta angle of the line
     * @param b line parameter
     * @param c line parameter
     * @param cursorId the id of the cursor that is mirrored
     */
    public void addMirrorCursor(double theta, float b, float c, long cursorId){
        // Creates and add a temporary cursor to the selected one
        long id = createCursorId();
        Cursor cursor = new Cursor(id);
        int x0 = cursors.get(cursorId).getPosX();
        int y0 = cursors.get(cursorId).getPosY();
        // Copy a cursor
        copyCursor(cursor, cursorId);
        // Angle of the mirror cursor with the x-axis
        double alpha = (2*theta)-cursors.get(cursorId).getRotationAngle();
        cursor.setRotationAngle((float) alpha);
        int x = (int) ((x0*(1 - Math.pow(b,2)) - 2*b*(-y0+c))/(1 + Math.pow(b,2)));
        int y = (int) ((y0*((b*b)-1)+2*(b*x0+c))/(1+b*b));
        cursor.setPosX(x);
        cursor.setPosY(y);
        getSelectedCursor().addMirror(cursor);
    }

    /**
     * Deletes all the temporary cursors created by one specific MIRROR instruction.
     */
    public void deleteMirror(long cursorId){
        cursors.get(cursorId).deleteMirror();
    }

    // The methods used by the Interpreter to modify cursors properties.
    /**
     * Move forward every active cursor (Selected and Temporary).
     * @param value The distance to move forward
     */
    public void forward(double value) {
        getSelectedCursor().forward(value);
        for(Cursor mimicCursor : this.getSelectedCursor().getMimics()){
            mimicCursor.forward(value);
        }
        for(Cursor mirrorCursor : this.getSelectedCursor().getMirror()){
            mirrorCursor.forward(value);
        }
    }

    /**
     * Rotates every active cursors of the amount passed in argument.
     * @param value Corresponds to the amount to add in degrees
     */
    public void turn(float value){
        getSelectedCursor().rotateCursor(value);
        for(Cursor mimicCursor : this.getSelectedCursor().getMimics()){
            mimicCursor.rotateCursor(value);
        }
        for(Cursor mirrorCursor : this.getSelectedCursor().getMirror()){
            mirrorCursor.rotateCursor(-value);
        }
    }

    /**
     * Move every cursor's position by the amount passed in the parameters. (i.e. Moves the cursor based on its relative position)
     * @param x value to add on the horizontal axis
     * @param y value to add on the vertical axis
     */
    public void move(int x, int y){
        getSelectedCursor().moveCursor(x,y);
        for(Cursor mimicCursor : this.getSelectedCursor().getMimics()){
            mimicCursor.moveCursor(x,y);
        }
        for(Cursor mirrorCursor : this.getSelectedCursor().getMirror()){
            mirrorCursor.moveCursor(x,y);
        }
    }

    /**
     * Put every cursor's position at amount passed in the parameters. (i.e. Change the cursor position)
     * @param x value to add on the horizontal axis
     * @param y value to add on the vertical axis
     */
    public void position(int x, int y){
        getSelectedCursor().placeCursor(x, y);
    }

    /**
     * Hides the selected cursor
     */
    public void hide(){
        getSelectedCursor().hide();
        for(Cursor mimicCursor : this.getSelectedCursor().getMimics()){
            mimicCursor.hide();
        }
        for(Cursor mirrorCursor : this.getSelectedCursor().getMirror()){
            mirrorCursor.hide();
        }
    }

    /**
     * Shows the selected cursor
     */
    public void show(){
        getSelectedCursor().show();
        for(Cursor mimicCursor : this.getSelectedCursor().getMimics()){
            mimicCursor.show();
        }
        for(Cursor mirrorCursor : this.getSelectedCursor().getMirror()){
            mirrorCursor.show();
        }
    }

    /**
     * Set the opacity of every active cursor.
     * @param value double new opacity value
     */
    public void press(double value){
        getSelectedCursor().setOpacity(value);
        for(Cursor mimicCursor : this.getSelectedCursor().getMimics()){
            mimicCursor.setOpacity(value);
        }
        for(Cursor mirrorCursor : this.getSelectedCursor().getMirror()){
            mirrorCursor.setOpacity(value);
        }
    }

    /**
     * Set the thickness of every active cursor.
     * @param value The new thickness value
     * @throws NegativeNumberException a number is negative
     */
    public void thick(float value) throws NegativeNumberException {
        getSelectedCursor().setThickness(value);
        for(Cursor mimicCursor : this.getSelectedCursor().getMimics()){
            mimicCursor.setThickness(value);
        }
        for(Cursor mirrorCursor : this.getSelectedCursor().getMirror()){
            mirrorCursor.setThickness(value);
        }
    }

    /**
     * Set the color of every active cursor.
     * @param color The new color of the cursors
     */
    public void color(Color color){
        getSelectedCursor().setColor(color);
        for(Cursor mimicCursor : this.getSelectedCursor().getMimics()){
            mimicCursor.setColor(color);
        }
        for(Cursor mirrorCursor : this.getSelectedCursor().getMirror()){
            mirrorCursor.setColor(color);
        }
    }

    /**
     * The active cursors look at the point whose position is given with the parameters.
     * @param x the position on the horizontal axis
     * @param y the position on the vertical axis
     */
    public void lookAt(int x, int y){
        getSelectedCursor().lookAt(x,y);
        for(Cursor mimicCursor : this.getSelectedCursor().getMimics()){
            mimicCursor.lookAt(x,y);
        }
        for(Cursor mirrorCursor : this.getSelectedCursor().getMirror()){
            mirrorCursor.lookAt(x,y);
        }
    }
}