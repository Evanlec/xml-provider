package to;

import org.w3c.dom.Element;

/**
 * Created by Juan Carlos Rodas on 6/04/2016.
 */
public class XmlActivityTO {

    private String id;

    private String name;

    private String distance;

    private String type;

    private String movingTime;

    private String startDate;

    /**
     * XmlActivityTO Constructor
     *
     * @param element
     */
    public XmlActivityTO(Element element){
        try { this.id = element.getElementsByTagName("id").item(0).getTextContent(); }catch(Exception e){}
        try { this.name = element.getElementsByTagName("name").item(0).getTextContent(); }catch(Exception e){}
        try { this.distance = element.getElementsByTagName("distance").item(0).getTextContent(); }catch(Exception e){}
        try { this.type = element.getElementsByTagName("type").item(0).getTextContent(); }catch(Exception e){}
        try { this.movingTime = element.getElementsByTagName("moving_time").item(0).getTextContent(); }catch(Exception e){}
        try { this.startDate = element.getElementsByTagName("start_date").item(0).getTextContent(); }catch(Exception e){}
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'distance'.
     *
     * @return Value for property 'distance'.
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Setter for property 'distance'.
     *
     * @param distance Value to set for property 'distance'.
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * Getter for property 'type'.
     *
     * @return Value for property 'type'.
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for property 'type'.
     *
     * @param type Value to set for property 'type'.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for property 'movingTime'.
     *
     * @return Value for property 'movingTime'.
     */
    public String getMovingTime() {
        return movingTime;
    }

    /**
     * Setter for property 'movingTime'.
     *
     * @param movingTime Value to set for property 'movingTime'.
     */
    public void setMovingTime(String movingTime) {
        this.movingTime = movingTime;
    }

    /**
     * Getter for property 'startDate'.
     *
     * @return Value for property 'startDate'.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Setter for property 'startDate'.
     *
     * @param startDate Value to set for property 'startDate'.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Getting the Json structure from the object.
     *
     */
    public String toJsonString(){
        return "{" +
                "    \"id\": " + (this.id != null ? this.id : "") +",\n" +
                "    \"name\": \"" + (this.name != null ? this.name : "") + "\",\n" +
                "    \"type\": \"" + (this.type != null ? this.type : "") + "\",\n" +
                "    \"distance\": " + (this.distance != null ? this.distance : "") + ",\n" +
                "    \"moving_time\": " + (this.movingTime != null ? this.movingTime : "") + ",\n" +
                "    \"start_date\": \"" + (this.startDate != null ? this.startDate: "") + "\"\n" +
                "  }";
    }


}
