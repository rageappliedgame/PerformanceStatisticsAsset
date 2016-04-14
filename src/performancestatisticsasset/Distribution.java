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

import java.util.List;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;

/**
 *
 * @author GLA
 */
public class Distribution implements StatisticalSummary {
    //Class for containing one item of analysed descriptives data
    
    Double max;
    Double min;
    Double sum;
    Double variance;
    Double mean;
    Double stdDev; //Standard deviation
    Double skewness; //The deviation fo a gaussian distribution's mean from the middle
    Double kurtosis; //The 'pointiness' of a gaussian distribution
    Long n; //The number of students that participated in this distribution
    Boolean normal; //Is the normality assumption respected?
    
    void setDistribution(List<Double> input) {
        max = input.get(0);
        min = input.get(1);
        sum = input.get(2);
        variance = input.get(3);
        mean = input.get(4);
        stdDev = input.get(5);
        skewness = input.get(6);
        kurtosis = input.get(7);
        n = Double.doubleToLongBits(input.get(8));
    }
    
    @Override
    public double getMax() {
        return max;
    }
    
    @Override
    public double getMean() {
        return mean;
    }
    
    @Override
    public double getMin() {
        return min;
    }
    
    @Override
    public long getN() {
        return n;
    }
    
    @Override
    public double getStandardDeviation() {
        return stdDev;
    }
    
    @Override
    public double getSum() {
        return sum;
    }
    
    @Override
    public double getVariance() {
        return variance;
    }
    
    void checkTAssumptions() {
        //This function evaluates the class fields to determine if the distribution is normal
        
    }
}
