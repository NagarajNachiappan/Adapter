package gov.va.mass.epic.simulator.model;

/**
 * Created by n_nac on 8/25/2017.
 */
public class HL7Data {
        private long id;
        private String data;

        public HL7Data(){

        }

        public HL7Data(long id, String data){
            this.id = id;
            this.data = data;
        }
    @Override
    public String toString() {
        String info = String.format("HL7 Data: id = %d, data = %s", id, data);
        return info;
    }


    }
