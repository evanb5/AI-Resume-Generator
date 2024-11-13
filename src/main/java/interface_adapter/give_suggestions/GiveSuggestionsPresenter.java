// interface_adapter/give_suggestions/GiveSuggestionsPresenter.java
package interface_adapter.give_suggestions;

import use_case.give_suggestions.*;

public class GiveSuggestionsPresenter implements GiveSuggestionsOutputBoundary {
    private GiveSuggestionsViewModel viewModel;

    public GiveSuggestionsPresenter() {
        this.viewModel = new GiveSuggestionsViewModel();
    }

    @Override
    public void present(GiveSuggestionsOutputData outputData) {
        viewModel.setSuggestions(outputData.getSuggestions());
        viewModel.setMessage(outputData.getMessage());
    }

    public GiveSuggestionsViewModel getViewModel() {
        return viewModel;
    }
}
