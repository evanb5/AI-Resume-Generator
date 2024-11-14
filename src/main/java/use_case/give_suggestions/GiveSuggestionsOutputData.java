// use_case/give_suggestions/GiveSuggestionsOutputData.java
package use_case.give_suggestions;

public class GiveSuggestionsOutputData {
    private String suggestions;
    private String message;

    public GiveSuggestionsOutputData(String suggestions, String message) {
        this.suggestions = suggestions;
        this.message = message;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public String getMessage() {
        return message;
    }
}
