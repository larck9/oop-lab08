package it.unibo.deathnote.impl;
import java.util.List;
import java.util.ArrayList;
import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {
    static private final long NAME_TIMER=40L;
    static private final long CAUSE_TIMER=6040L;
    static private final int NAME_INDEX=0;
    static private final int CAUSE_INDEX=1;
    static private final int DETAILS_INDEX=2;
    static private final String DEF_CAUSE="heart attack";
    private long startWriteName;
    private long startWriteCause;
    private final List<String> notes;

    public DeathNoteImpl(){
        this.startWriteName=0;
        this.startWriteCause=0;
        notes =new ArrayList<>(List.of("","",""));  //name-cause-details
    }
    
    public String getRule(int ruleNumber) {
        if(ruleNumber<1 || ruleNumber>RULES.size()){
            throw new  IllegalArgumentException();
        }
        return DeathNote.RULES.get(ruleNumber);
    }

    public void writeName(String Name){
        if(Name == null){
            throw new NullPointerException();
        }
        notes.set(NAME_INDEX,Name);
        this.startWriteName =System.currentTimeMillis();
    }

    public boolean writeDeathCause(String cause){
        if((System.currentTimeMillis()-startWriteName)<=NAME_TIMER){
            if(notes.getFirst().isEmpty() || cause==null){
                throw new IllegalStateException();
            }else{
                notes.set(CAUSE_INDEX,cause);
                this.startWriteCause=System.currentTimeMillis();
                return true;
            }
        }else{
            return false;
        }
    }

    public boolean writeDetails(String details){
        if((System.currentTimeMillis()-startWriteCause)<=CAUSE_TIMER ){
            if(notes.getFirst().isEmpty() || details==null){
                throw new IllegalStateException();
            }else{
                notes.set(DETAILS_INDEX,details);
                return true;
            }
        }else{
            return false;
        }
    }

    public String getDeathCause(String name){
        if(!isNameWritten(name)){
            throw new IllegalArgumentException();
        }
        return this.notes.get(CAUSE_INDEX).isEmpty() ? DEF_CAUSE : this.notes.get(CAUSE_INDEX);
    }

    public String getDeathDetails(String name){
       if(!isNameWritten(name)){
            throw new IllegalArgumentException();
        }
        return this.notes.get(DETAILS_INDEX).isEmpty() ? "" : this.notes.get(DETAILS_INDEX);
    }

    public boolean isNameWritten(String name){
        return this.notes.getFirst().equals(name);
    }


}
