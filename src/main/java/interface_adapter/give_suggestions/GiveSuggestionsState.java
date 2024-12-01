package interface_adapter.give_suggestions;

public class GiveSuggestionsState {
    private String suggestions;
    private String message;

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
