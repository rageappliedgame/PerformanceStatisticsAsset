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
