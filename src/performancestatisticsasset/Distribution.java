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

import java.util.List;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
/**
 *
 * @author GLA
 */

/*
Class explanation: distribution

The distribution class models a gaussian distribution. Whenever this class is
updated it should process the new data and compute new variables related to its
distribution.

Whenever it is updated the class also checks whether its distribution is normal.
This check is a prerequisite for all parametric tests in GLM statistics.

This class is suitable for big data datasets since it can process data updates
'per record'.
*/

public class Distribution extends SummaryStatistics{
    //Class for containing one item of analysed descriptives data.
    
    protected Double max;
    protected Double min;
    protected Double sum;
    protected Double variance;
    protected Double mean;
    protected Double stdDev; //Standard deviation
    protected Double skewness; //The deviation fo a gaussian distribution's mean from the middle
    protected Double kurtosis; //The 'pointiness' of a gaussian distribution
    protected Long n; //The number of students that participated in this distribution
    protected Boolean normal; //Is the normality assumption respected?
    
    private Double help1,help2,help3;
    
    void Distribution() {
        //Constructor set all values to 0
        setMax(0D);
        setMin(0D);
        setSum(0D);
        setVariance(0D);
        setStdDev(0D);
        setSkewness(0D);
        setKurtosis(0D);
        setN(0L);
        setNormal(false);
        this.help1 = 0D;
        this.help2 = 0D;
        this.help3 = 0D;
    }
    
    void setDistribution(Double max,Double min,Double sum,Double variance,Double mean,Double stdDev,Double skewness,Double kurtosis,Long n) {
        //For setting a distribution directly
        setMax(max);
        setMin(min);
        setSum(sum);
        setVariance(variance);
        setMean(mean);
        setStdDev(stdDev);
        setSkewness(skewness);
        setKurtosis(kurtosis);
        setN((Long) Double.doubleToLongBits(n));
    }
    
    void setDistribution(List<Double> input) {
        //For setting a distribution directly
        setMax(input.get(0));
        setMin(input.get(1));
        setSum(input.get(2));
        setVariance(input.get(3));
        setMean(input.get(4));
        setStdDev(input.get(5));
        setSkewness(input.get(6));
        setKurtosis(input.get(7));
        setN((Long) Double.doubleToLongBits(input.get(8)));
    }
    
    void updateDistribution(Double input) {
        //Updates the distribution using the supplied input
        
        //For testing purposes: comment out when testing is complete
        Boolean output = true;
        
        //New N, code guarantees N of 1 or higher
        Long tmpNL = getN();
        
        if (tmpNL <= 0) {
            tmpNL = 1L;
        } else {
            tmpNL++;
        }
        setN(tmpNL);
        Double tmpN = (double) tmpNL;
         
        
        //New max
        if (input > getMax())
            setMax(input);
        
        //New min
        if (input < getMin())
            setMin(input);
        
        //New sum
        sum += input;
        
        //New mean, variance, & stdDev >> based on: http://www.johndcook.com/blog/standard_deviation/
        Double oldMean = getMean();
        Double newMean;
        Double oldS = getVariance();
        Double newS;
        
        if (tmpN == 1) {
            newMean = input;
            newS = 0D;
        } else {
            //New means formula suitable for big data
            //Previous code guarantees tmpN > 0
            newMean = oldMean + (input - oldMean) / tmpN;
            newS = oldS + (input - oldMean)*(input - newMean);
        }
        setMean(newMean);
        setVariance((tmpN > 1) ? (newS / (tmpN - 1)) : 0D);
        setStdDev(Math.sqrt(getVariance()));
        
        //New skewness & kurtosis >> based on: http://www.johndcook.com/blog/skewness_kurtosis/
        double delta, delta_n, delta_n2, term1;
        
        delta = input - newMean;
        delta_n = delta / tmpN;
        delta_n2 = delta_n * delta_n;
        term1 = delta * delta_n * tmpN;
        help3 = (term1 * delta_n2 * (tmpN*tmpN-3*tmpN+3))+(6*delta_n2*help1)-(4*delta_n*help2);
        help2 = (term1 * delta_n * (tmpN - 2)) - (3 * tmpN * delta_n * help1);
        help1 = term1;
        
        setSkewness(Math.sqrt((double)tmpN) * help2/ Math.pow(help1, 1.5));
        setKurtosis(((double)tmpN)*help3 / (help1*help1) - 3.0);
        
        //Update assumptions
        checkTAssumptions();
        
        //For testing purposes
        if (output == true)
            System.out.println(String.valueOf(getMax()) + " " + String.valueOf(getMin()) + " " + 
                    String.valueOf(getSum()) + " " + String.valueOf(getVariance()) + " " +
                    String.valueOf(getMean()) + " " + String.valueOf(getStdDev()) + " " +
                    String.valueOf(getSkewness()) + " " + String.valueOf(getKurtosis()) + " " +
                    String.valueOf(getN()) + " " + String.valueOf(getNormal()));
    }
    
    void checkTAssumptions() {
        //This function evaluates the class fields to determine if the distribution is normal
        
        //Currently automatically set to false until normality processing is implemented
        setNormal(false);
    }

    /**
     * @return the max
     */
    @Override
    public double getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Double max) {
        this.max = max;
    }

    /**
     * @return the min
     */
    @Override
    public double getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(Double min) {
        this.min = min;
    }

    /**
     * @return the sum
     */
    @Override
    public double getSum() {
        return sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(Double sum) {
        this.sum = sum;
    }

    /**
     * @return the variance
     */
    @Override
    public double getVariance() {
        return variance;
    }

    /**
     * @param variance the variance to set
     */
    public void setVariance(Double variance) {
        this.variance = variance;
    }

    /**
     * @return the mean
     */
    @Override
    public double getMean() {
        return mean;
    }

    /**
     * @param mean the mean to set
     */
    public void setMean(Double mean) {
        this.mean = mean;
    }

    /**
     * @return the stdDev
     */
    public Double getStdDev() {
        return stdDev;
    }

    /**
     * @param stdDev the stdDev to set
     */
    public void setStdDev(Double stdDev) {
        this.stdDev = stdDev;
    }

    /**
     * @return the skewness
     */
    public Double getSkewness() {
        return skewness;
    }

    /**
     * @param skewness the skewness to set
     */
    public void setSkewness(Double skewness) {
        this.skewness = skewness;
    }

    /**
     * @return the kurtosis
     */
    public Double getKurtosis() {
        return kurtosis;
    }

    /**
     * @param kurtosis the kurtosis to set
     */
    public void setKurtosis(Double kurtosis) {
        this.kurtosis = kurtosis;
    }

    /**
     * @return the n
     */
    @Override
    public long getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(Long n) {
        this.n = n;
    }

    /**
     * @return the normal
     */
    public Boolean getNormal() {
        return normal;
    }

    /**
     * @param normal the normal to set
     */
    public void setNormal(Boolean normal) {
        this.normal = normal;
    }
}
