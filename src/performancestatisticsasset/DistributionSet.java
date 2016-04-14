/*
 * Copyright 2016 Giel van Lankveld
 * Email: Giel.vanLankveld@ou.nl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author GLA
 */
public class DistributionSet {
    //Class for containing sets of analysed descriptives data
    List<Distribution> distributionSet = new ArrayList<>();
    
    void addDistribution(Distribution dist) {
        distributionSet.add(dist);
    }
    
    Integer size() {
        return distributionSet.size();
    }
    
    public void setDistributionSet(String targetGroup, String targetTask, String targetMeasure, RecordList dataSet) {
        //Determine the number of trials selected by group and by task
        int trials = 0;
        int len = dataSet.records.size();        
        for (int i = 0;i < len;i++) {
            if (dataSet.records.get(i).getTrialNumber() > trials) trials = dataSet.records.get(i).getTrialNumber();
        }
        
        Distribution tempDist;
        DescriptiveStatistics tempStat;
        //For each trial of the set do
        for (int i = 0; i < trials;i++) {
            tempDist = new Distribution();
            tempStat = new DescriptiveStatistics();
            //Select data
            for (int j = 0;j < len;j++) {
                //If the current record is of the correct trial
                if ((dataSet.records.get(j).getTrialNumber() == i+1) && (targetGroup.equals(dataSet.records.get(j).getGroupID())) && (targetTask.equals(dataSet.records.get(j).getTaskID()))) {
                    //Fill distribution
                    switch(targetMeasure) {
                        case "time" : tempStat.addValue(dataSet.records.get(j).getTimeToComplete()); break;
                        case "perf" : tempStat.addValue(dataSet.records.get(j).getPerformance()); break;
                    }
                }
            }
            //Transfer the computed statistics to tempDist
                tempDist.max = tempStat.getMax();
                tempDist.min = tempStat.getMin();
                tempDist.sum = tempStat.getSum();
                tempDist.variance = tempStat.getVariance();
                tempDist.mean = tempStat.getMean();
                tempDist.stdDev = tempStat.getStandardDeviation();
                tempDist.skewness = tempStat.getSkewness();
                tempDist.kurtosis = tempStat.getKurtosis();            
                tempDist.n = tempStat.getN();
                
                //Add tempDist to distributionSet
                distributionSet.add(tempDist);
        }
    }
    
    public void setDistributionSet(String targetTask, String targetMeasure, RecordList dataSet) {
        //Determine the number of trials selected by task
        int trials = 0;
        int len = dataSet.records.size();
        for (int i = 0;i < len;i++) {
            if (dataSet.records.get(i).getTrialNumber() > trials) trials = dataSet.records.get(i).getTrialNumber();
        }
        
        Distribution tempDist;
        DescriptiveStatistics tempStat;
        //For each trial of the set do
        for (int i = 0; i < trials;i++) {
            tempDist = new Distribution();
            tempStat = new DescriptiveStatistics();
            //Select data
            for (int j = 0;j < len;j++) {
                //If the current record is of the correct trial
                if ((dataSet.records.get(j).getTrialNumber() == i+1) && (targetTask.equals(dataSet.records.get(j).getTaskID()))) {
                    //Fill distribution
                    switch(targetMeasure) {
                        case "time" : tempStat.addValue(dataSet.records.get(j).getTimeToComplete()); break;
                        case "perf" : tempStat.addValue(dataSet.records.get(j).getPerformance()); break;
                    }
                }
            }
            //Transfer the computed statistics to tempDist
                tempDist.max = tempStat.getMax();
                tempDist.min = tempStat.getMin();
                tempDist.sum = tempStat.getSum();
                tempDist.variance = tempStat.getVariance();
                tempDist.mean = tempStat.getMean();
                tempDist.stdDev = tempStat.getStandardDeviation();
                tempDist.skewness = tempStat.getSkewness();
                tempDist.kurtosis = tempStat.getKurtosis();            
                tempDist.n = tempStat.getN();
                
                //Add tempDist to distributionSet
                distributionSet.add(tempDist);
        }
    }
}
