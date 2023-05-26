package nl.han.oose.project.resources.dto;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AccountDTOTest {

    private AccountDTO sut;

    @Before
    public void setup() {
        sut = new AccountDTO();
    }

    @Test
    public void testSetId() {
        sut.setId(1);
        Assertions.assertEquals(1, sut.getId());
    }

    @Test
    public void testGetId() {
        sut.setId(1);
        Assertions.assertEquals(1, sut.getId());
    }

    @Test
    public void testSetEmail() {
        sut.setEmail("test");
        Assertions.assertEquals("test", sut.getEmail());
    }

    @Test
    public void testGetEmail() {
        sut.setEmail("test");
        Assertions.assertEquals("test", sut.getEmail());
    }

    @Test
    public void testSetRoleId() {
        sut.setRoleId(1);
        Assertions.assertEquals(1, sut.getRoleId());
    }

    @Test
    public void testGetRoleId() {
        sut.setRoleId(1);
        Assertions.assertEquals(1, sut.getRoleId());
    }
}
