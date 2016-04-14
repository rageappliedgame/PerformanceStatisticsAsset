/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package performancestatisticsasset;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GLA
 */
public class PerformanceList {
    //For containing a list of the student's performance
    List<Double> dataOverTrials; //One entry is one trial
    
    public void setPerformanceList(String targetPlayer, String targetTask, String targetMeasure, RecordList dataSet) {
        //Collects all the timeToCompletes and the performances for the player
        
        int len = dataSet.records.size();
        switch (targetMeasure) {
            case "time": {
                for (int i = 0;i < len; i+=1)
                    if ((targetPlayer.equals(dataSet.records.get(i).getPlayerID())) && (targetTask.equals(dataSet.records.get(i).getTaskID()))) dataOverTrials.add(dataSet.records.get(i).getTimeToComplete());  
                
            }break;
            case "perf": {
                for (int i = 0;i < len; i+=1)
                    if ((targetPlayer.equals(dataSet.records.get(i).getPlayerID())) && (targetTask.equals(dataSet.records.get(i).getTaskID()))) dataOverTrials.add(dataSet.records.get(i).getPerformance());
            } break;
        }
    }
    
    PerformanceList() {
        dataOverTrials = new ArrayList<>();
    }
}
