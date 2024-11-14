// interface_adapter/give_suggestions/GiveSuggestionsController.java
package interface_adapter.give_suggestions;

import use_case.give_suggestions.*;

public class GiveSuggestionsController {
    private GiveSuggestionsInputBoundary interactor;

    public GiveSuggestionsController(GiveSuggestionsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void giveSuggestions(GiveSuggestionsInputData inputData) {
        interactor.giveSuggestions(inputData);
    }
}
