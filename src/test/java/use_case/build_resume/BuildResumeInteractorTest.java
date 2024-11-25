package use_case.build_resume;

import entity.User;
import org.junit.jupiter.api.Test;
import services.ChatGPTService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BuildResumeInteractorTest {

    // Define a mock User class to use in testing, since User is an interface
    private class MockUser implements User {
        private String username;
        private String password;
        private String email;
        private String fullName;
        private List<String> workExperience;
        private List<String> education;
        private List<String> skills;
        private List<String> resumes;
        private Map<String, String> cvs;  // Changed from List<String> to Map<String, String> for CVs
        private List<String> suggestions;

        public MockUser(String fullName, String email, String[] workExperience, String[] education, String[] skills) {
            this.fullName = fullName;
            this.email = email;
            this.workExperience = List.of(workExperience);
            this.education = List.of(education);
            this.skills = List.of(skills);
            this.resumes = new ArrayList<>();
            this.cvs = new HashMap<>();  // Initialize the cvs map
            this.suggestions = new ArrayList<>();  // Initialize the suggestions list
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String getFullName() {
            return fullName;
        }

        @Override
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        @Override
        public List<String> getWorkExperience() {
            return workExperience;
        }

        @Override
        public void setWorkExperience(List<String> workExperience) {
            this.workExperience = workExperience;
        }

        @Override
        public List<String> getEducation() {
            return education;
        }

        @Override
        public void setEducation(List<String> education) {
            this.education = education;
        }

        @Override
        public List<String> getSkills() {
            return skills;
        }

        @Override
        public void setSkills(List<String> skills) {
            this.skills = skills;
        }

        @Override
        public List<String> getResume() {
            return resumes;
        }

        @Override
        public void addResume(String resume) {
            resumes.add(resume);
        }

        @Override
        public int getnumresume() {
            return resumes.size();
        }

        @Override
        public Map<String, String> getCvs() {
            return cvs;  // Implementing getCvs() method to return the CVs map
        }

        @Override
        public void addCv(String cv, String cvName) {
            cvs.put(cvName, cv);  // Adding CV with name to the map
        }

        @Override
        public void removeCv(String cvName) {
            cvs.remove(cvName);  // Removing CV by name from the map
        }

        @Override
        public int getnumCV() {
            return cvs.size();  // Return the number of CVs
        }

        @Override
        public List<String> getsuggestions() {
            return suggestions;  // Return the suggestions list
        }

        @Override
        public void addsuggestion(String suggestion) {
            suggestions.add(suggestion);  // Add suggestion to the list
        }

        @Override
        public int getnumsuggestion() {
            return suggestions.size();  // Return the number of suggestions
        }
    }

    @Test
    void successGenerateResumeTest() throws NoSuchFieldException, IllegalAccessException {
        // Create an instance of the mock User class
        User user = new MockUser("John Doe", "john.doe@example.com",
                new String[]{"Software Engineer at XYZ Corp"},
                new String[]{"B.Sc. in Computer Science"},
                new String[]{"Java", "Python"});

        String jobDescription = "Looking for an experienced software developer.";
        int templateNumber = 1;
        BuildResumeInputData inputData = new BuildResumeInputData(user, jobDescription, templateNumber);

        // Mock the ChatGPTService to return a predefined response
        ChatGPTService chatGPTService = new ChatGPTService() {
            @Override
            public String generateResume(String userInfo, String jobDescription, int templateNumber) {
                // Assert that the correct user info is passed to ChatGPTService
                assertTrue(userInfo.contains("John Doe"));
                assertTrue(userInfo.contains("john.doe@example.com"));
                assertTrue(userInfo.contains("Software Engineer at XYZ Corp"));
                assertTrue(userInfo.contains("Java"));
                return "Generated Resume Content";
            }
        };

        // Define the output boundary (presenter) to verify the output
        BuildResumeOutputBoundary successPresenter = new BuildResumeOutputBoundary() {
            @Override
            public void present(BuildResumeOutputData outputData) {
                assertEquals("Generated Resume Content", outputData.getFormattedResume());
                assertEquals("Resume generated successfully", outputData.getMessage());
            }
        };

        // Instantiate the interactor with mock dependencies
        BuildResumeInteractor interactor = new BuildResumeInteractor(null, successPresenter);

        // Use reflection to set the private field `chatGPTService`
        Field chatGPTServiceField = BuildResumeInteractor.class.getDeclaredField("chatGPTService");
        chatGPTServiceField.setAccessible(true); // Allow access to private fields
        chatGPTServiceField.set(interactor, chatGPTService); // Set the mock ChatGPTService

        // Execute the use case
        interactor.buildResume(inputData);
    }

    @Test
    void failureNullUserTest() {
        // Create mock input data with a null user
        User user = null;
        String jobDescription = "Looking for an experienced software developer.";
        int templateNumber = 1;
        BuildResumeInputData inputData = new BuildResumeInputData(user, jobDescription, templateNumber);

        // Define the output boundary (presenter) to handle the failure
        BuildResumeOutputBoundary failurePresenter = new BuildResumeOutputBoundary() {
            @Override
            public void present(BuildResumeOutputData outputData) {
                fail("Use case success is unexpected when user is null.");
            }
        };

        // Instantiate the interactor with mock dependencies
        BuildResumeInteractor interactor = new BuildResumeInteractor(null, failurePresenter);

        // Verify that a NullPointerException is thrown
        assertThrows(NullPointerException.class, () -> interactor.buildResume(inputData));
    }

    @Test
    void failureEmptyJobDescriptionTest() {
        // Create an instance of the mock User class
        User user = new MockUser("John Doe", "john.doe@example.com",
                new String[]{"Software Engineer at XYZ Corp"},
                new String[]{"B.Sc. in Computer Science"},
                new String[]{"Java", "Python"});

        String jobDescription = "";
        int templateNumber = 1;
        BuildResumeInputData inputData = new BuildResumeInputData(user, jobDescription, templateNumber);

        // Define the output boundary (presenter) to handle the failure case
        BuildResumeOutputBoundary failurePresenter = new BuildResumeOutputBoundary() {
            @Override
            public void present(BuildResumeOutputData outputData) {
                assertTrue(outputData.getMessage().contains("Job description is empty"));
            }
        };

        // Instantiate the interactor with mock dependencies
        BuildResumeInteractor interactor = new BuildResumeInteractor(null, failurePresenter);

        // Execute the use case
        interactor.buildResume(inputData);
    }
}
