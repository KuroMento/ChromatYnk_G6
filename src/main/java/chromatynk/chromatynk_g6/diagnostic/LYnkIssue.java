package chromatynk.chromatynk_g6.diagnostic;

/**
 * Base Object that represents an issue that occurred in an input. Used for UI and Debug purposes.
 * @param type Either ERROR or WARNING depending on the issue context (Use <code>{@link IssueType}</code>)
 * @param message The message to emit.
 * @param lineNumber The line number in the input where the issue occurred.
 * @param lineOffset The character position within that line where the issue occurred.
 * @param details Additional comments for debugging the code.
 */
public record LYnkIssue(IssueType type, String message , int lineNumber, int lineOffset, String details) {

    @Override
    public String toString(){
        String affichage = lineNumber + ": " + message;
        if(!details.isEmpty()) {
            affichage = affichage + " (details: " + details + ") ";
        }
        return affichage;
    }

    /**
     * Checks for the IssueType
     * @return True if the LYnkIssue is a Warning
     */
    public boolean isWarning(){ return this.type == IssueType.WARNING; }
    /**
     * Checks for the IssueType
     * @return True if the LYnkIssue is an Error
     */
    public boolean isError(){ return this.type == IssueType.ERROR; }
}
