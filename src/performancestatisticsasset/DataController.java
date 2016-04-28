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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author GLA
 */
public class DataController {
    //A set of record and associated functions for creation and management
    private String topic;
    private String analysisType;
    private File dataSource;
    private RecordList loadedData;
    private PerformanceList studentTime;
    private PerformanceList studentPerf;
    private DistributionSet DistributionOneTime;
    private DistributionSet DistributionOnePerf;
    private DistributionSet DistributionTwoTime;
    private DistributionSet DistributionTwoPerf;
    private ResultSet resultTime;
    private ResultSet resultPerf;
    private String selectedGame = "Game(A)";
    private String selectedTask1 = "Task(A)";
    private String selectedTask2 = "Task(B)";
    private String selectedPlayer = "Player(A)";
    private String selectedGroup1 = "Group(A)";
    private String selectedGroup2 = "Group(B)";
    private Outputter outputter;
    
    protected void setSelectedGame(String input) {
        selectedGame = input;
    }
    
    protected void setSelectedTask1(String input) {
        selectedTask1 = input;
    }
    
    protected void setSelectedTask2(String input) {
        selectedTask2 = input;
    }
    
    protected void setSelectedPlayer(String input) {
        selectedPlayer = input;
    }
    
    protected void setSelectedGroup1(String input) {
        selectedGroup1 = input;
    }
    
    protected void setSelectedGroup2(String input) {
        selectedGroup2 = input;
    }
    
    protected String getSelectedGame() {
        return selectedGame;
    }
    
    protected String getSelectedTask1() {
        return selectedTask1;
    }
    
    protected String getSelectedTask2() {
        return selectedTask2;
    }
    
    protected String getSelectedPlayer() {
        return selectedPlayer;
    }
    
    protected String getSelectedGroup1() {
        return selectedGroup1;
    }
    
    protected String getSelectedGroup2() {
        return selectedGroup2;
    }
    
    // At construction the user needs to choose an analysis topic and type
    public final void chooseDataAnalysis() {
        try  {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(isr);
            String input = "";
            System.out.println("Select analysis topic: (Student|Group|Task)");
            
            // Read console until valid analysis topic is selected
            while (!"Student".equals(input) && !"Group".equals(input) && !"Task".equals(input)) {
                input = in.readLine();
                if (!"Student".equals(input) && !"Group".equals(input) && !"Task".equals(input))
                {
                    System.out.println("Incorrect input");
                    System.out.println();
                    System.out.println("Select analysis topic: (Student|Group|Task)");
                } else
                    System.out.println();
            }
            topic = input;
            input = "";

            // Topic specific analysis option selection
            switch (topic) {
                case "Student" : {
                    //data = topic;

                    System.out.println("Select analysis type: (ProgressOverTrials|GroupCompare)");

                    // Read until valid analysis type is selected
                    while ((!"ProgressOverTrials".equals(input)) && (!"GroupCompare".equals(input))) {
                        input = in.readLine();
                        if ((!"ProgressOverTrials".equals(input)) && (!"GroupCompare".equals(input))) {
                            System.out.println("Incorrect input");
                            System.out.println();
                            System.out.println("Select analysis type: (ProgressOverTrials|GroupCompare)");
                        } else
                            System.out.println();
                    }
                    analysisType = input;
                } break;            
                case "Group" : {
                    //data = topic;

                    System.out.println("Select analysis type: (ProgressOverTrials|GroupCompare)");

                    // Read until valid analysis type is selected
                    while ((!"ProgressOverTrials".equals(input)) && (!"GroupCompare".equals(input))) {
                        input = in.readLine();
                        if ((!"ProgressOverTrials".equals(input)) && (!"GroupCompare".equals(input))) {
                            System.out.println("Incorrect input");
                            System.out.println();
                            System.out.println("Select analysis type: (ProgressOverTrials|GroupCompare)");
                        } else
                            System.out.println();
                    }
                    analysisType = input;
                } break;            
                case "Task" : {
                    //data = topic;

                    System.out.println("Select analysis type: (Overview|TaskCompare)");

                    // Read until valid analysis type is selected
                    while ((!"Overview".equals(input)) && (!"TaskCompare".equals(input))) {
                        input = in.readLine();
                        if ((!"Overview".equals(input)) && (!"TaskCompare".equals(input))) {
                            System.out.println("Incorrect input");
                            System.out.println();
                            System.out.println("Select analysis type: (Overview|TaskCompare)");
                        } else
                            System.out.println();
                    }
                    analysisType = input;
                }
                default : System.out.println("No valid topic was selected");
            } 
        } catch (IOException e) {
                    System.out.println("Error — " + e.toString());
        }
    }
    
    //Set datasource through manual text input
    public final void setDataSource() {
        //Manually set a csv data file
        
        try  {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader inStr = new BufferedReader(isr);
            
            while ((dataSource == null) || !dataSource.exists()) {
                System.out.println("Please input datasource file (.csv)");
                dataSource = new File(inStr.readLine());
                
                if (!dataSource.exists())
                    System.out.println("The file/directory does not exist");
            }
        } catch (IOException e) {
            System.out.println("Error — " + e.toString());
        }
        System.out.println();
    }
    
    //Alternatively, set datasource directly
    public final void setDataSource(String source) {
        dataSource = new File(source);
    }
    
    public final void performSelectedAnalysis() {
        // Run the methods on the data as previously selected        
        if (!"".equals(topic) && !"".equals(analysisType) && (topic != null) && (analysisType != null)) {
            if (loadedData != null) {
                //First, select by analysis topic (Student/Group/Task)
                switch (topic) {
                    case "Student" : {
                        //Second, select by analysis type (ProgressOverTrials/GroupCompare)
                        switch (analysisType) {
                            case "ProgressOverTrials" : {
                                StudentProgressOverTrials studentProgressOverTrials = new StudentProgressOverTrials();
                                studentTime = new PerformanceList();
                                studentPerf = new PerformanceList();
                                outputter = new Outputter();
                                studentTime.setPerformanceList(selectedPlayer,selectedTask1,"time",loadedData);
                                studentPerf.setPerformanceList(selectedPlayer,selectedTask1,"perf",loadedData);
                                studentProgressOverTrials.analyze(studentTime,studentPerf);
                                outputter.output(selectedPlayer,studentTime,studentPerf); //We probably need to say some more about the student performance, right?
                            } break;
                            case "GroupCompare" : {
                                StudentGroupCompare studentGroupCompare = new StudentGroupCompare();
                                studentTime = new PerformanceList();
                                studentPerf = new PerformanceList();
                                DistributionOneTime = new DistributionSet();
                                DistributionOnePerf = new DistributionSet();
                                outputter = new Outputter();
                                studentTime.setPerformanceList(selectedPlayer,selectedTask1,"time",loadedData);
                                studentPerf.setPerformanceList(selectedPlayer,selectedTask1,"perf",loadedData);
                                DistributionOneTime.setDistributionSet(selectedGroup1,selectedTask1,"time",loadedData);
                                DistributionOnePerf.setDistributionSet(selectedGroup1,selectedTask1,"perf",loadedData);
                                studentGroupCompare.analyze(studentTime,studentPerf,DistributionOneTime,DistributionOnePerf);
                                outputter.output(selectedPlayer,studentTime,studentPerf,DistributionOneTime,DistributionOnePerf);
                            } break;
                        }
                        break;
                    }
                    case "Group" : {
                        //Second, select by analysis type (ProgressOverTrials/GroupCompare)
                        //Compares the performance by 
                        switch (analysisType) {
                            case "ProgressOverTrials" : {
                                GroupProgressOverTrials groupProgressOverTrials = new GroupProgressOverTrials();
                                DistributionOneTime = new DistributionSet();
                                DistributionOnePerf = new DistributionSet();
                                outputter = new Outputter();
                                DistributionOneTime.setDistributionSet(selectedGroup1,selectedTask1,"time",loadedData);
                                DistributionOnePerf.setDistributionSet(selectedGroup1,selectedTask1,"perf",loadedData);
                                outputter.output("Group",selectedGroup1,DistributionOneTime,DistributionOnePerf);
                            } break;
                            case "GroupCompare" : {
                                //First perform the GroupCompare for time to complete and performance
                                GroupGroupCompare groupGroupCompare = new GroupGroupCompare();
                                DistributionOneTime = new DistributionSet();
                                DistributionTwoTime = new DistributionSet();
                                DistributionOnePerf = new DistributionSet();
                                DistributionTwoPerf = new DistributionSet();
                                resultTime = new ResultSet();
                                resultPerf = new ResultSet();
                                outputter = new Outputter();
                                DistributionOneTime.setDistributionSet(selectedGroup1,selectedTask1,"time",loadedData);
                                DistributionTwoTime.setDistributionSet(selectedGroup2,selectedTask1,"time",loadedData);
                                DistributionOnePerf.setDistributionSet(selectedGroup1,selectedTask1,"perf",loadedData);
                                DistributionTwoPerf.setDistributionSet(selectedGroup2,selectedTask1,"perf",loadedData);
                                groupGroupCompare.analyze(DistributionOneTime,DistributionTwoTime,DistributionOnePerf,DistributionTwoPerf,resultTime,resultPerf);
                                outputter.output("Group",selectedGroup1,selectedGroup2,resultTime,resultPerf);
                            } break;
                        }                    
                        break;
                    }
                    case "Task" : {
                        //Second, select by analysis type (Overview/TaskCompare)
                        switch (analysisType) {
                            case "Overview" : {
                                TaskOverview taskOverview = new TaskOverview();
                                DistributionOneTime = new DistributionSet();
                                DistributionOnePerf = new DistributionSet();
                                outputter = new Outputter();
                                DistributionOneTime.setDistributionSet(selectedTask1,"time",loadedData);
                                DistributionOnePerf.setDistributionSet(selectedTask1,"perf",loadedData);
                                outputter.output("Task",selectedTask1,DistributionOneTime,DistributionOnePerf);
                            } break;
                            case "TaskCompare" : {
                                TaskTaskCompare taskTaskCompare = new TaskTaskCompare();
                                DistributionOneTime = new DistributionSet();
                                DistributionTwoTime = new DistributionSet();
                                DistributionOnePerf = new DistributionSet();
                                DistributionTwoPerf = new DistributionSet();
                                resultTime = new ResultSet();
                                resultPerf = new ResultSet();
                                outputter = new Outputter();
                                DistributionOneTime.setDistributionSet(selectedTask1,"time",loadedData);
                                DistributionTwoTime.setDistributionSet(selectedTask2,"time",loadedData);
                                DistributionOnePerf.setDistributionSet(selectedTask1,"perf",loadedData);
                                DistributionTwoPerf.setDistributionSet(selectedTask2,"perf",loadedData);
                                taskTaskCompare.analyze(DistributionOneTime,DistributionTwoTime,DistributionOnePerf,DistributionTwoPerf,resultTime,resultPerf);
                                outputter.output("Task",selectedTask1,selectedTask2,resultTime,resultPerf);
                            } break;
                        }                    
                        break;
                    }
                }
            } else
                System.out.println("No data was loaded");
        } else
            System.out.println("No source or analysis was set");
    }
    
    //Method for loading local gameplay data (backup if server functionality is not available)
    public final RecordList loadDataFromCSV() {
        //Collect the input from the csv
        if (!"".equals(dataSource) && (dataSource != null)) {
            if (!"".equals(topic) && !"".equals(analysisType) && (topic != null) && (analysisType != null)) {
                try (FileReader file = new FileReader(dataSource);
                    BufferedReader buffFile = new BufferedReader(file)){
                    Integer count;

                    //Set variables for file reading and read a record from the CSV
                    String temp = buffFile.readLine();
                    loadedData = new RecordList();
                    //Record tempRec = new Record();

                    //write the csv into the record array if the csv is not empty
                    while (temp != null) {
                        Record tempRec = new Record();

                        //Dump the remp string into the temporary record
                        count = 0;
                        for (String dumpStr: temp.split(",")) {
                            switch (count) {
                                case 0: tempRec.setGameID(dumpStr); break;
                                case 1: tempRec.setTaskID(dumpStr); break;
                                case 2: tempRec.setPlayerID(dumpStr); break;
                                case 3: tempRec.setGroupID(dumpStr); break;
                                case 4: tempRec.setDateTime(dumpStr); break;
                                case 5: tempRec.setTimeToComplete(Double.parseDouble(dumpStr)); break;
                                case 6: tempRec.setPerformance(Double.parseDouble(dumpStr)); break;
                                case 7: tempRec.setTrialNumber(Short.parseShort(dumpStr)); break;
                            }
                            count += 1;
                        }

                        //Add new record to record array if data matches selected data
                        switch (topic) {
                            case "Student" : {
                                // Type specific analysis option selection
                                switch (analysisType) {
                                    case "ProgressOverTrials" : {
                                        if (tempRec.getGameID().equals(selectedGame) && tempRec.getTaskID().equals(selectedTask1) && tempRec.getPlayerID().equals(selectedPlayer)) {
                                            loadedData.records.add(tempRec);
                                        }
                                    } break;
                                    case "GroupCompare" : {                                
                                        if (tempRec.getGameID().equals(selectedGame) && tempRec.getTaskID().equals(selectedTask1) && tempRec.getGroupID().equals(selectedGroup1)) {
                                            loadedData.records.add(tempRec);
                                        }
                                    } break;
                                    default : System.out.println("No valid topic was selected");
                                }
                            } break;
                            case "Group" : {
                                // Type specific analysis option selection
                                switch (analysisType) {
                                    case "ProgressOverTrials" : {
                                        if (tempRec.getGameID().equals(selectedGame) && tempRec.getTaskID().equals(selectedTask1) && tempRec.getGroupID().equals(selectedGroup1)) {
                                            loadedData.records.add(tempRec);
                                        }
                                    } break;
                                    case "GroupCompare" : {
                                        if (tempRec.getGameID().equals(selectedGame) && tempRec.getTaskID().equals(selectedTask1)) {
                                            loadedData.records.add(tempRec);
                                        }
                                    } break;
                                    default : System.out.println("No valid topic was selected"); break;
                                }
                            } break;
                            case "Task" : {
                                // Type specific analysis option selection
                                switch (analysisType) {
                                    case "Overview" : {
                                        if (tempRec.getGameID().equals(selectedGame) && tempRec.getTaskID().equals(selectedTask1)) {
                                            loadedData.records.add(tempRec);
                                        }
                                    } break;
                                    case "TaskCompare" : {
                                        if (tempRec.getGameID().equals(selectedGame)) {
                                            loadedData.records.add(tempRec);
                                        }
                                    } break;
                                    default : System.out.println("No valid topic was selected");
                                }
                            } break;
                            default : System.out.println("No valid topic was selected"); break;
                        }

                        //Read a new record from the CSV
                        temp = buffFile.readLine();
                    }
                    //buffFile.close();
                } catch (IOException e) {
                    System.out.println("Error — " + e.toString());
                }
            } else {
                System.out.println("No valid topic or analysis was set");
            }
        } else {
                System.out.println("No data source was set");
        }
        
        return loadedData;
    }
}
