package chromatynk.chromatynk_g6.exceptions;

import chromatynk.chromatynk_g6.diagnostic.LYnkIssue;

import java.util.Collection;
import java.util.List;

public class CodeValidateException extends Exception{
    private final List<LYnkIssue> issues;

    public CodeValidateException(final Collection<LYnkIssue> issues){
        super("Code validating failed");
        this.issues = issues.stream().toList();
    }

    public List<LYnkIssue> getIssues(){
        return issues;
    }
}
