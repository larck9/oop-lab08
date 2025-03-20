package it.unibo.deathnote;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDeathNote {
    private DeathNote det;

    @BeforeEach
    void setup() {
       det = new DeathNoteImpl();
    }

    @Test
    void testRules(){
        try{
            det.getRule(0);
        }catch(IllegalArgumentException e){
            assertEquals(IllegalArgumentException.class,e.getClass());
            assertNull(e.getMessage());
        }

        try{
            det.getRule(-2);
        }catch(IllegalArgumentException e){
            assertEquals(IllegalArgumentException.class,e.getClass());
            assertNull(e.getMessage());
        }

        for(int index=0;index<DeathNote.RULES.size();index++){
            assertFalse(DeathNote.RULES.get(index).isEmpty());
            assertNotNull(DeathNote.RULES.get(index));
        }

    }

    @Test
    void testWriteName(){
        assertFalse(det.isNameWritten("Mario"));
        det.writeName("Mario");
        assertTrue(det.isNameWritten("Mario"));
        assertFalse(det.isNameWritten("Aurelio"));
        assertFalse(det.isNameWritten(""));
    }

    @Test
    void testWriteCause(){
        try{
            det.writeDeathCause("new death cause");
        }catch(IllegalStateException e){
            assertEquals(IllegalStateException.class,e.getClass());
        }

        det.writeName("Mario");
        assertEquals("heart attack",det.getDeathCause("Mario"));
        det.writeName("Aurelio");
        assertTrue(det.writeDeathCause("karting accident"));
        assertEquals("karting accident",det.getDeathCause("Aurelio"));
        try {
            Thread.sleep(100);
        }catch (InterruptedException | IllegalArgumentException e){
            System.exit(1);
        }
        det.writeDeathCause("new death cause");
        assertEquals("karting accident",det.getDeathCause("Aurelio"));
    }

    @Test
    void testWriteDetail(){
        try{
            det.writeDetails("new death details");
        }catch(IllegalArgumentException e){
            assertEquals(IllegalArgumentException.class,e.getClass());
        }
        det.writeName("Mario");
        assertEquals("",det.getDeathDetails("Mario"));
        assertTrue(det.writeDeathCause("used only to pass this test")); //to start timer and not to go < 0
        assertTrue(det.writeDetails("ran for too long"));
        assertEquals("ran for too long",det.getDeathDetails("Mario"));
        det.writeName("Aurelio");
        try {
            Thread.sleep(6100);
        }catch (InterruptedException | IllegalArgumentException e){
            System.exit(1);
        }
        assertFalse(det.writeDetails("new death details"));
        assertEquals("ran for too long",det.getDeathDetails("Aurelio"));
    }

}