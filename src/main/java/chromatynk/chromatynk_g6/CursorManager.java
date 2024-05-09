package chromatynk.chromatynk_g6;

import chromatynk.chromatynk_g6.exceptions.CursorAlreadyExistingException;
import chromatynk.chromatynk_g6.exceptions.MissingCursorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CursorManager {
    /**
     * A map to track the cursors that are active within the Canvas
     */
    private Map<Long,Cursor> activeCursors;

    /**
     * Constructor of CursorManager adding by default a generic cursor to the list of active cursors.
     */
    public CursorManager(){
        Cursor defaultCursor = new Cursor();
        this.activeCursors = new HashMap<Long,Cursor>();
        this.activeCursors.put(defaultCursor.getId(),defaultCursor);
    }

    // Getters/Setters
    public Map<Long,Cursor> getActiveCursors(){
        return this.activeCursors;
    }
    public void setActiveCursors(Map<Long, Cursor> activeCursors) {
        this.activeCursors = activeCursors;
    }

    /**
     * Grabs a cursor within those active.
     * @param id The id of the wanted cursor
     * @return The <code>Cursor</code> which as <code>id</code> for identification.
     * @throws <code>MissingCursorException</code>
     */
    public Cursor getCursor(long id) throws MissingCursorException{
        if( !cursorExist(id) ){
            throw new MissingCursorException("Cursor n°" + id + " is missing or doesn't exist.");
        }
        return this.activeCursors.get(id);
    }

    /**
     * Checks if a cursor exists within the Canvas.
     * @param id The id of the wanted cursor to check
     * @return True if the cursor is active and exist
     */
    public boolean cursorExist(long id){
        return this.activeCursors.containsKey(id);
    }

    /**
     * Generate a new id for the creation of cursors based on the active cursors' ids.
     * @return A new id of type long
     */
    public long createCursorId(){
        Set<Long> idSet = this.activeCursors.keySet();
        long newId = (long) (idSet.size() + 1);
        while( this.activeCursors.containsKey(newId)){
            newId += 1l;
        }
        return newId;
    }

    /**
     * Add a new Cursor to the active cursors on the Canvas.
     */
    public void addCursor(){
        long newId = createCursorId();
        this.activeCursors.put(newId,new Cursor(newId));
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
        this.activeCursors.put(id,new Cursor(id));
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
        this.activeCursors.remove(id,this.activeCursors.get(id));
    }
}
