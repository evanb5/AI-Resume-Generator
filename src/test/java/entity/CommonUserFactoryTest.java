// test/entity/CommonUserFactoryTest.java
package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonUserFactoryTest {

    @Test
    public void testCreateUser() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser();
        assertNotNull(user);
        assertTrue(user instanceof CommonUser);
    }
}
