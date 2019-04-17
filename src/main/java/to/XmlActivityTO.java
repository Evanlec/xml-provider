/**
 * ==========================================================================================
 * =                   JAHIA'S DUAL LICENSING - IMPORTANT INFORMATION                       =
 * ==========================================================================================
 *
 *                                 http://www.jahia.com
 *
 *     Copyright (C) 2002-2019 Jahia Solutions Group SA. All rights reserved.
 *
 *     THIS FILE IS AVAILABLE UNDER TWO DIFFERENT LICENSES:
 *     1/GPL OR 2/JSEL
 *
 *     1/ GPL
 *     ==================================================================================
 *
 *     IF YOU DECIDE TO CHOOSE THE GPL LICENSE, YOU MUST COMPLY WITH THE FOLLOWING TERMS:
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *
 *     2/ JSEL - Commercial and Supported Versions of the program
 *     ===================================================================================
 *
 *     IF YOU DECIDE TO CHOOSE THE JSEL LICENSE, YOU MUST COMPLY WITH THE FOLLOWING TERMS:
 *
 *     Alternatively, commercial and supported versions of the program - also known as
 *     Enterprise Distributions - must be used in accordance with the terms and conditions
 *     contained in a separate written agreement between you and Jahia Solutions Group SA.
 *
 *     If you are unsure which license is appropriate for your use,
 *     please contact the sales department at sales@jahia.com.
 */
package to;

import org.apache.jackrabbit.util.ISO8601;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import javax.jcr.ValueFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Juan Carlos Rodas on 6/04/2016.
 */
public class XmlActivityTO {

    private static final Logger logger   = LoggerFactory.getLogger(to.XmlActivityTO.class);

    private Integer id;

    private String name;

    private Double distance;

    private String type;

    private String movingTime;

    private String startDate;

    /**
     * XmlActivityTO Constructor
     *
     * @param element
     */
    public XmlActivityTO(Element element){
        try { setId(element.getElementsByTagName("id").item(0).getTextContent()); }catch(Exception e){}
        try { this.name = element.getElementsByTagName("name").item(0).getTextContent(); }catch(Exception e){}
        try { setDistance(element.getElementsByTagName("distance").item(0).getTextContent()); }catch(Exception e){}
        try { this.type = element.getElementsByTagName("type").item(0).getTextContent(); }catch(Exception e){}
        try { setMovingTime(element.getElementsByTagName("moving_time").item(0).getTextContent()); }catch(Exception e){}
        try { setStartDate(element.getElementsByTagName("start_date").item(0).getTextContent()); }catch(Exception e){}
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public String getId() {
        return id.toString();
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(String id) {
        this.id = Integer.parseInt(id);
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
    public Double getDistance() {
        return distance;
    }

    /**
     * Setter for property 'distance'.
     *
     * @param distance Value to set for property 'distance'.
     */
    public void setDistance(String distance) {
        this.distance = Double.parseDouble(distance);
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
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(startDate);
            Calendar startDateCalendar = Calendar.getInstance();
            startDateCalendar.setTime(date);
            this.startDate = ISO8601.format(startDateCalendar);
        } catch (ParseException e ) {
            logger.warn(e.getMessage());
        }
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
                "    \"moving_time\": \"" + (this.movingTime != null ? this.movingTime : "") + "\",\n" +
                "    \"start_date\": \"" + (this.startDate != null ? this.startDate: "") + "\"\n" +
                "  }";
    }


}
