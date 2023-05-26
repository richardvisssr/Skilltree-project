package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AccountDTOTest {

    private AccountDTO sut;

    @BeforeEach
    public void setup() {
        sut = new AccountDTO();
    }

    @Test
    public void testConstructor() {
        // Arrange
        var expectedId = 1;
        var expectedEmail = "test";
        var expectedRoleId = 1;

        // Act
        var result = new AccountDTO(1, "test", 1);

        // Assert
        Assertions.assertEquals(expectedId, result.getId());
        Assertions.assertEquals(expectedEmail, result.getEmail());
        Assertions.assertEquals(expectedRoleId, result.getRoleId());
    }

    @Test
    public void testId() {
        sut.setId(1);
        Assertions.assertEquals(1, sut.getId());
    }

    @Test
    public void testEmail() {
        sut.setEmail("test");
        Assertions.assertEquals("test", sut.getEmail());
    }

    @Test
    public void testRoleId() {
        sut.setRoleId(1);
        Assertions.assertEquals(1, sut.getRoleId());
    }
}
