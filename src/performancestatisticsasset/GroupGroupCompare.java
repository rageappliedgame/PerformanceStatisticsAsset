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

import org.apache.commons.math3.stat.inference.TTest;
/**
 *
 * @author GLA
 */
public class GroupGroupCompare extends Analysis{
    //Performs an independent samples t-test on the two selected groups
    void analyze(DistributionSet set1Time,DistributionSet set2Time,DistributionSet set1Perf,DistributionSet set2Perf,ResultSet resultTime,ResultSet resultPerf) {
        TTest tempTest = new TTest();
        Result tempRes;
        
        //Time
        //For all the trials do the analysis
        int trials = set1Time.distributionSet.size();
        if (set1Time.distributionSet.size() > set2Time.distributionSet.size())
            trials = set2Time.distributionSet.size();
        
        for (int i = 0;i < trials;i++) {
            if ((set1Time.distributionSet.get(i).getN() > 1) && (set2Time.distributionSet.get(i).getN() > 1)) {
                tempRes = new Result();
                tempRes.t = tempTest.t(set1Time.distributionSet.get(i),set2Time.distributionSet.get(i));
                tempRes.p = tempTest.tTest(set1Time.distributionSet.get(i),set2Time.distributionSet.get(i));
                resultTime.addResult(tempRes);
            }
            else {
                tempRes = new Result();
                tempRes.NaN = true;
                resultTime.addResult(tempRes);
            }
        }
        
        //Performance
        //For all the trials do the analysis
        trials = set1Perf.distributionSet.size();
        if (set1Perf.distributionSet.size() > set2Perf.distributionSet.size())
            trials = set2Perf.distributionSet.size();
        
        for (int i = 0;i < trials;i++) {
            if ((set1Perf.distributionSet.get(i).getN() > 1) && (set2Perf.distributionSet.get(i).getN() > 1)) {
                tempRes = new Result();
                tempRes.t = tempTest.t(set1Perf.distributionSet.get(i),set2Perf.distributionSet.get(i));
                tempRes.p = tempTest.tTest(set1Perf.distributionSet.get(i),set2Perf.distributionSet.get(i));
                resultPerf.addResult(tempRes);
            }
            else {
                tempRes = new Result();
                tempRes.NaN = true;
                resultPerf.addResult(tempRes);
            }
        }
    }
}
