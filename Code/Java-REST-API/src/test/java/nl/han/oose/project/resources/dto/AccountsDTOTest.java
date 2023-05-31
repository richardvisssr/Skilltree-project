package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class AccountsDTOTest {

    private AccountsDTO sut;

    @BeforeEach
    public void setup() {
        sut = new AccountsDTO();
    }

    @Test
    void testAccounts() {
        // Arrange
        var expected = new AccountDTO();

        // Act
        sut.setAccounts(List.of(expected));
        var result = sut.getAccounts();

        // Assert
        Assertions.assertEquals(expected, result.get(0));
    }

    @Test
    void testConstructor() {
        // Arrange
        var expected = new AccountDTO(1, "test", 1);

        // Act
        var result = new AccountsDTO(List.of(expected));

        // Assert
        Assertions.assertEquals(expected, result.getAccounts().get(0));
    }


}
