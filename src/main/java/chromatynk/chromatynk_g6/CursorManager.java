package chromatynk.chromatynk_g6;

import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorAlreadyExistingException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.MimicStackEmptyException;
import chromatynk.chromatynk_g6.exceptions.cursorExceptions.MissingCursorException;

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
    public void removeCursor(long id) throws MissingCursorException {
        if( !cursorExist(id) ){
            throw new MissingCursorException("Cursor does not exist anymore!");
        }
        this.cursors.remove(id,this.cursors.get(id));
    }

    /**
     * Allows to select a cursor within the existing cursors
     * @param id The identification of the wanted cursor
     * @throws <code>MissingCursorException</code>
     */
    public void selectCursor(long id) throws MissingCursorException{
        if( !cursorExist(id) ){
            throw new MissingCursorException("This cursor does not exist.");
        }
        setSelectedCursorId(id);
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
}
