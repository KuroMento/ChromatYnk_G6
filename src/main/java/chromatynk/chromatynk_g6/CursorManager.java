package chromatynk.chromatynk_g6;

import chromatynk.chromatynk_g6.exceptions.cursorExceptions.CursorAlreadyExistingException;
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
     * Constructor of CursorManager adding by default a generic cursor to the list of active cursors.
     */
    public CursorManager(){
        Cursor defaultCursor = new Cursor();
        this.activeCursors = new HashMap<Long,Cursor>();
        this.activeCursors.put(defaultCursor.getId(),defaultCursor);
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
}
