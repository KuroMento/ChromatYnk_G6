package chromatynk.chromatynk_g6;

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
     * A map to track the total amount of duplicated and temporary cursors
     */
    private Map<Long,Cursor> temporaryCursors;

    /**
     * Constructor of CursorManager adding by default a generic cursor to the list of active cursors.
     */
    public CursorManager(){
        Cursor defaultCursor = new Cursor(0);
        this.selectedCursorId = defaultCursor.getId();
        this.cursors = new HashMap<Long,Cursor>();
        this.cursors.put(defaultCursor.getId(),defaultCursor);
        this.temporaryCursors = new HashMap<Long,Cursor>();
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

    public Map<Long,Cursor> getTemporaryCursors(){
        return this.cursors;
    }
    public void setTemporaryCursors(Map<Long, Cursor> cursors) {
        this.cursors = cursors;
    }

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
        long newId = (long) (idSet.size() + 1);
        while( this.cursors.containsKey(newId)){
            newId += 1l;
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
     * @throws <code>CursorAlreadyExistingException</code>
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
     * @throws <code>MissingCursorException</code>
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
     * @throws <code>MissingCursorException</code>
     * @throws <code>CursorException</code>
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
     * Add a temporary cursor for the selected and the other temporary cursors when a MIMIC/MIRROR instruction is used.
     */
    public void addMimics(){
        // Creates and add a temporary cursor to the selected one
        long id = createCursorId();
        Cursor cursor = new Cursor(id);
        getSelectedCursor().addMimic(cursor);

        // If there is other temporary cursors, they are also duplicated
        for( long key : this.temporaryCursors.keySet()){
            long idMimic = createCursorId();
            Cursor newDuplicatedCursor = new Cursor(idMimic);
            Cursor cursorMimic = this.temporaryCursors.get(key);
            cursorMimic.addMimic(newDuplicatedCursor);
            temporaryCursors.put(newDuplicatedCursor.getId(),newDuplicatedCursor);
        }

        this.temporaryCursors.put(cursor.getId(),cursor); // Add at the end to avoid duplicating one more cursor
    }

    /**
     * Deletes all the temporary cursors created by one specific MIMIC/MIRROR instruction.
     * @throws <code>MimicStackEmptyException</code>
     */
    public void deleteMimics() throws MimicStackEmptyException {
        long deleteSelectedId = getSelectedCursor().deleteMimic();
        this.temporaryCursors.remove(deleteSelectedId,this.temporaryCursors.get(deleteSelectedId));
        for( long key : this.temporaryCursors.keySet()){
            long deleteId = this.temporaryCursors.get(key).deleteMimic();
            this.temporaryCursors.remove(deleteId, this.temporaryCursors.get(key));
        }
    }

    /**
     * Delete every single temporary cursors created by a MIMIC/MIRROR instruction.
     * @throws <code>MimicStackEmptyException</code>
     */
    public void deleteAllMimics() throws MimicStackEmptyException {
        while( !(getSelectedCursor().getMimics().isEmpty()) ){
            deleteMimics();
        }
    }

    // The methods used by the Interpreter to modify cursors properties.
    /**
     * Move forward every active cursor (Selected and Temporary).
     * @param value The distance to move forward
     * @throws <code>NegativeNumberException</code>
     */
    public void forward(int value) throws NegativeNumberException {
        getSelectedCursor().forward(value);
        for(long key : this.temporaryCursors.keySet()){
            this.temporaryCursors.get(key).forward(value);
        }
    }

    /**
     * Move backward every active cursor (Selected and Temporary).
     * @param value The distance to move backward
     * @throws <code>NegativeNumberException</code>
     */
    public void backward(int value) throws NegativeNumberException {
        getSelectedCursor().backward(value);
        for(long key : this.temporaryCursors.keySet()){
            this.temporaryCursors.get(key).backward(value);
        }
    }

    /**
     * Rotates every active cursors of the amount passed in argument.
     * @param value Corresponds to the amount to add in degrees
     */
    public void turn(float value){
        getSelectedCursor().rotateCursor(value);
        for(long key : this.temporaryCursors.keySet()){
            this.temporaryCursors.get(key).rotateCursor(value);
        }
    }

    /**
     * Move every cursor's position by the amount passed in the parameters. (i.e. Moves the cursor based on its relative position)
     * @param x value to add on the horizontal axis
     * @param y value to add on the vertical axis
     * @throws <code>NegativeNumberException</code>
     */
    public void move(int x, int y) throws NegativeNumberException {
        getSelectedCursor().moveCursor(x,y);
        for(long key : this.temporaryCursors.keySet()){
            this.temporaryCursors.get(key).moveCursor(x,y);
        }
    }

    /**
     * Add to every cursor's position the amount passed in the parameters. (i.e. Change the cursor position based on its relative position)
     * @param x value to add on the horizontal axis
     * @param y value to add on the vertical axis
     * @throws NegativeNumberException
     */
    public void position(int x, int y) throws NegativeNumberException {
        getSelectedCursor().moveCursor(x,y);
    }

    /**
     * Hides the selected cursor
     */
    public void hide(){
        getSelectedCursor().hide();
    }

    /**
     * Shows the selected cursor
     */
    public void show(){
        getSelectedCursor().show();
    }

    /**
     * Set the opacity of every active cursor.
     * @param value The new opacity value
     * @throws <code>OutOfRangeException</code>
     */
    public void press(float value) throws OutOfRangeException {
        getSelectedCursor().setOpacity(value);
        for(long key : this.temporaryCursors.keySet()){
            this.temporaryCursors.get(key).setOpacity(value);
        }
    }

    /**
     * Set the thickness of every active cursor.
     * @param value The new thickness value
     * @throws <code>NegativeNumberException</code>
     */
    public void thick(float value) throws NegativeNumberException {
        getSelectedCursor().setThickness(value);
        for(long key : this.temporaryCursors.keySet()){
            this.temporaryCursors.get(key).setThickness(value);
        }
    }

    /**
     * Set the color of every active cursor.
     * @param color The new color of the cursors
     */
    public void color(Color color){
        getSelectedCursor().setColor(color);
        for(long key : this.temporaryCursors.keySet()){
            this.temporaryCursors.get(key).setColor(color);
        }
    }

    /**
     * The active cursors look at the point whose position is given with the parameters.
     * @param x the position on the horizontal axis
     * @param y the position on the vertical axis
     */
    public void lookAt(int x, int y){
        getSelectedCursor().lookAt(x,y);
        for(long key : this.temporaryCursors.keySet()){
            // TODO: lookAtMirror if needed.
            this.temporaryCursors.get(key).lookAt(x,y);
        }
    }

}
