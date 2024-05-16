package chromatynk.chromatynk_g6.diagnostic;

public record LYnkIssue(IssueType type, String message , int lineNumber, int lineOffset, String details) {
    public boolean isError(){
        return this.type == IssueType.ERROR;
    }
}
