// interface_adapter/give_suggestions/GiveSuggestionsViewModel.java
package interface_adapter.give_suggestions;

import interface_adapter.ViewModel;

public class GiveSuggestionsViewModel extends ViewModel<GiveSuggestionsState> {
    private String suggestions;
    private String message;

    public GiveSuggestionsViewModel() {
        setState( new GiveSuggestionsState());
        suggestions = "";
        message = "";
    }

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
