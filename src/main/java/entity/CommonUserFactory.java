// entity/CommonUserFactory.java
package entity;

public class CommonUserFactory implements UserFactory {
    @Override
    public User createUser() {
        return new CommonUser();
    }
}
