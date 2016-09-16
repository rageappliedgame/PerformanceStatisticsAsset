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
 * @author gla
 */

public class PerformanceStatisticsAsset {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //This class is included for testing purposes
        DataController dataController = new DataController();
        
        // For automated analysis comment out the following two lines of code and make sure
        // the configuration is set correctly in DataController.java
        dataController.setDataSource();
        dataController.chooseDataAnalysis();
        
        dataController.loadDataFromCSV(); // If no datasource or analysis is set, this will perform a default load        
        dataController.performSelectedAnalysis();
        
        // Close java VM
        System.exit(0);
    }
}