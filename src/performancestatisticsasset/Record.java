/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package performancestatisticsasset;

/**
 *
 * @author GLA
 */
public class Record {
    //For containing records as they arrive from a database, local csv, or apache storm spout
    private String gameID;
    private String taskID;
    private String playerID;
    private String groupID;
    private String dateTime;
    private Double timeToComplete;
    private Double performance;
    private Short trialNumber;
    
    protected void setGameID(String input) {
        gameID = input;
    }
    
    protected void setTaskID(String input) {
        taskID = input;
    }
            
    protected void setPlayerID(String input) {
        playerID = input;
    }
            
    protected void setGroupID(String input) {
        groupID = input;
    }
            
    protected void setDateTime(String input) {
        dateTime = input;
    }
            
    protected void setTimeToComplete(Double input) {
        timeToComplete = input;
    }
            
    protected void setPerformance(Double input) {
        performance = input;
    }
            
    protected void setTrialNumber(Short input) {
        trialNumber = input;
    }
    
    protected String getGameID() {
        return gameID;
    }
    
    protected String getTaskID() {
        return taskID;
    }
            
    protected String getPlayerID() {
        return playerID;
    }
            
    protected String getGroupID() {
        return groupID;
    }
            
    protected String getDateTime() {
        return dateTime;
    }
            
    protected Double getTimeToComplete() {
        return timeToComplete;
    }
            
    protected Double getPerformance() {
        return performance;
    }
            
    protected Short getTrialNumber() {
        return trialNumber;
    }
}
